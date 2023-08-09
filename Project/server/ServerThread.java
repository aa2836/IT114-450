package Project.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import Project.common.Payload;
import Project.common.PayloadType;
import Project.common.RoomResultPayload;

/**
 * A server-side representation of a single client
 */
public class ServerThread extends Thread {
    private Socket client;
    private String clientName;
    private boolean isRunning = false;
    private ObjectOutputStream out;// exposed here for send()
    // private Server server;// ref to our server so we can call methods on it
    // more easily
    private Room currentRoom;
    private static Logger logger = Logger.getLogger(ServerThread.class.getName());
    private long myId;
    private boolean isMuted = false;
    private ConcurrentHashMap<String, Boolean> muteList = new ConcurrentHashMap<>();


    public void setClientId(long id) {
        myId = id;
    }

    public long getClientId() {
        return myId;
    }

    public boolean isRunning() {
        return isRunning;
    }

    private void info(String message) {
        System.out.println(String.format("Thread[%s]: %s", getId(), message));
    }

    public ServerThread(Socket myClient, Room room) {
        info("Thread created");
        // get communication channels to single client
        this.client = myClient;
        this.currentRoom = room;

    }

    protected void setClientName(String name) {
        if (name == null || name.isBlank()) {
            System.err.println("Invalid client name being set");
            return;
        }
        clientName = name;
    }

    public String getClientName() {
        return clientName;
    }

    protected synchronized Room getCurrentRoom() {
        return currentRoom;
    }

    protected synchronized void setCurrentRoom(Room room) {
        if (room != null) {
            currentRoom = room;
        } else {
            info("Passed in room was null, this shouldn't happen");
        }
    }

    public void disconnect() {
        sendConnectionStatus(myId, getClientName(), false);
        info("Thread being disconnected by server");
        isRunning = false;
        cleanup();
    }

    // send methods
    public boolean sendRoomName(String name) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.JOIN_ROOM);
        p.setMessage(name);
        return send(p);
    }

    public boolean sendRoomsList(String[] rooms, String message) {
        RoomResultPayload payload = new RoomResultPayload();
        payload.setRooms(rooms);
        //Fixed in Module7.Part9
        if(message != null){
            payload.setMessage(message);
        }
        return send(payload);
    }

    public boolean sendExistingClient(long clientId, String clientName) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.SYNC_CLIENT);
        p.setClientId(clientId);
        p.setClientName(clientName);
        return send(p);
    }

    public boolean sendResetUserList() {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.RESET_USER_LIST);
        return send(p);
    }

    public boolean sendClientId(long id) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.CLIENT_ID);
        p.setClientId(id);
        return send(p);
    }

    public boolean sendMessage(long clientId, String message) {
        if (!isMuted && !isRecipientMuted(clientId)) {
            // Process text triggers
            message = processTextTriggers(message);
        
            Payload p = new Payload();
            p.setPayloadType(PayloadType.MESSAGE);
            p.setClientId(clientId);
            p.setMessage(message);
            return send(p);
        }
        return false;
    }
    
    


    public boolean sendConnectionStatus(long clientId, String who, boolean isConnected) {
        Payload p = new Payload();
        p.setPayloadType(isConnected ? PayloadType.CONNECT : PayloadType.DISCONNECT);
        p.setClientId(clientId);
        p.setClientName(who);
        p.setMessage(isConnected ? "connected" : "disconnected");
        return send(p);
    }

    private boolean send(Payload payload) {
        // added a boolean so we can see if the send was successful
        try {
            // TODO add logger
            logger.log(Level.FINE, "Outgoing payload: " + payload);
            out.writeObject(payload);
            logger.log(Level.INFO, "Sent payload: " + payload);
            return true;
        } catch (IOException e) {
            info("Error sending message to client (most likely disconnected)");
            // comment this out to inspect the stack trace
            // e.printStackTrace();
            cleanup();
            return false;
        } catch (NullPointerException ne) {
            info("Message was attempted to be sent before outbound stream was opened: " + payload);
            return true;// true since it's likely pending being opened
        }
    }

    // end send methods
    @Override
    public void run() {
        info("Thread starting");
        try (ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());) {
            this.out = out;
            isRunning = true;
            Payload fromClient;
            while (isRunning && // flag to let us easily control the loop
                    (fromClient = (Payload) in.readObject()) != null // reads an object from inputStream (null would
                                                                     // likely mean a disconnect)
            ) {

                info("Received from client: " + fromClient);
                processPayload(fromClient);

            } // close while loop
        } catch (Exception e) {
            // happens when client disconnects
            e.printStackTrace();
            info("Client disconnected");
        } finally {
            isRunning = false;
            info("Exited thread loop. Cleaning up connection");
            cleanup();
        }
    }

    void processPayload(Payload p) {
        switch (p.getPayloadType()) {
            case CONNECT:
                setClientName(p.getClientName());
                break;
            case DISCONNECT:
                Room.disconnectClient(this, getCurrentRoom());
                break;
            case MESSAGE:
                String message = p.getMessage();
                //aa2836-07/24/2023
                // checks if starts with /roll
                // if it does then execute processRollsCommand
                if (message.startsWith("/roll")) {
                    processRollCommand(message);
                // checks if starts with /flip
                // if it does then execute processflipCommand
                } else if (message.startsWith("/flip")) {
                    processFlipCommand();
                //checks if messege starts wuth @
                // if it doesn then it excecutes processPrivateMessage
                } else if (message.startsWith("@")) {
                    processPrivateMessage(message);
                // checks if messeege starts with mute 
                // if it does then excecutes processMuteCommand
                } else if (message.startsWith("mute ")){
                    processMuteCommand(message);

                // checks if messege starts with unmute 
                // if it does then excecutes processUnmuteCommand
                } else if (message.startsWith("unmute ")){
                    processUnmuteCommand(message);

                } else {
                
                    if (currentRoom != null) {
                        currentRoom.sendMessage(this, p.getMessage());
                    } else {
                        // TODO migrate to lobby
                        logger.log(Level.INFO, "Migrating to lobby on message with null room");
                        Room.joinRoom("lobby", this);
                    }
                }
                break;
            case GET_ROOMS:
                Room.getRooms(p.getMessage().trim(), this);
                break;
            case CREATE_ROOM:
                Room.createRoom(p.getMessage().trim(), this);
                break;
            case JOIN_ROOM:
                Room.joinRoom(p.getMessage().trim(), this);
                break;
            default:
                break;

        }


    }
    private String processTextTriggers(String message) {
        // Bold trigger: *text*
        message = message.replaceAll("\\*(.*?)\\*", "<b>$1</b>");

        // Italics trigger: _text_
        message = message.replaceAll("_(.*?)_", "<i>$1</i>");

        // Underline trigger: +text+
        message = message.replaceAll("\\+(.*?)\\+", "<u>$1</u>");

        // Color trigger: [color:red]text[/color]
        message = message.replaceAll("\\[color:red\\](.*?)\\[/color\\]", "<font color=\"red\">$1</font>");
        message = message.replaceAll("\\[color:blue\\](.*?)\\[/color\\]", "<font color=\"blue\">$1</font>");
        message = message.replaceAll("\\[color:green\\](.*?)\\[/color\\]", "<font color=\"green\">$1</font>");

        return message;
    }


    private void processRollCommand(String message) {
    //aa2836 7-24-2023
        try {
        //checks to if the message have 'd'
        if (message.contains("d")) {
            // Format 2: /roll #d#
            // Splits to get the number of dice and side
            String[] rollParts = message.substring(6).split("d");
            if (rollParts.length == 2) {
                // gets the number of dice 
                int numDice = Integer.parseInt(rollParts[0]);
                // gets the number side
                int numSides = Integer.parseInt(rollParts[1]);
                if (numDice > 0 && numSides > 0) {
                    int result = 0;
                    StringBuilder rollResult = new StringBuilder("<b>Roll result:</b> ");
                    // rolls the dice
                    for (int i = 0; i < numDice; i++) {
                        //genarates random roll between the parameters.
                        int roll = (int) (Math.random() * numSides) + 1;
                        result += roll;
                        // adds the role value to the string
                        rollResult.append(roll);
                        if (i < numDice - 1) {
                            rollResult.append("<b>, </b>");
                        }
                    }
                    // adds the total result
                    rollResult.append("<b>. Total: ").append(result).append("</b>");
                    // Broadcast the roll result to all clients in the room
                    if (currentRoom != null) {
                        currentRoom.sendMessage(this, rollResult.toString());
                    }
                }
            }
        } else {
            // Format 1: /roll 0 - X or 1 - X
            // takes the maximum value 
            int max = Integer.parseInt(message.substring(6).trim());
            // checks for positive value
            if (max > 0) {
                //generates a random result
                int result = (int) (Math.random() * max) + 1;
                String rollResult = "<b>Roll result: " + result+" </b>";
                // Broadcast the roll result to all clients in the room
                if (currentRoom != null) {
                    currentRoom.sendMessage(this, rollResult);
                }
            }
        }
    } catch (NumberFormatException e) {
        // Handle invalid roll command
        logger.log(Level.WARNING, "Invalid roll command: " + message);
    }
}

    private void processFlipCommand() {
        //aa2836 7-24-2023
        // Getting a random number between 0 and 1
        // if lesss then 0.5 result heads or else result tails
        String result = (Math.random() < 0.5) ? "Heads" : "Tails";
        String flipResult = "<b>Coin flip result: " + result+" </b>";
        // Broadcast the flip result to all clients in the room
        if (currentRoom != null) {
            currentRoom.sendMessage(this, flipResult);
        }
    }

    // aa2836 7-24-2023
    private void processPrivateMessage(String message) {
        // Extract receiver's username from the message
        int spaceIndex = message.indexOf(" ");
        // checks for space 
        if (spaceIndex != -1) {
            // takes the recivers username from the messege 
            String receiverName = message.substring(1, spaceIndex);
            // takes the private messsge
            String privateMessage = message.substring(spaceIndex + 1);
            
            // Find the receiver in the current room
            if (currentRoom != null) {
                ServerThread receiver = currentRoom.findClientByName(receiverName);
                // checks if the the reciver is in the current room
                if (receiver != null) {
                    // Send the private message to the sender and receiver only
                    sendMessage(getClientId(), "You whispered to "+ receiverName + ": " + privateMessage);
                    // sends private message to reciver
                    receiver.sendMessage(getClientId(),getClientName()+" whispered to you : " + privateMessage);
                } else {
                    // if the reciver is not found
                    sendMessage(getClientId(), "User " + receiverName + " not found in the room.");
                }
            }
        }
    }   

    private void processMuteCommand(String message) {
        //aa2836 7-24-2023
        // takes the target username
        String targetUsername = message.substring(5).trim();
        if (currentRoom != null) {
        // Find the target client by their username in the current room
        ServerThread targetClient = currentRoom.findClientByName(targetUsername);
        if (targetClient != null) {
            // Check if the target user is already unmuted
            if (targetClient.isMuted()) {
                // If the target user is already muted, do not send a repeat message
                sendMessage(getClientId(), targetUsername + " is already muted");
            } else {
                // Set target user's ServerThread to true (muted)
                targetClient.setMuted(true);
                muteList.put(targetUsername, true);

                // Notify the sender that the target user has been muted
                sendMessage(getClientId(), "You muted " + targetUsername);

                // Notify the muted user
                targetClient.sendMessage(getClientId(), getClientName() + " muted you");
            }
        } else {
            // If the user is not found in the same room
            sendMessage(getClientId(), "User " + targetUsername + " not found in the room.");
        }
        } else {
            // If the user is not in any room
            sendMessage(getClientId(), "You are not currently in a room.");
        }
            
         
    }


    private void processUnmuteCommand(String message) {
        // takes the target username
        String targetUsername = message.substring(7).trim();
        
        if (currentRoom != null) {
            // checks if the cllient is in the room
            ServerThread targetClient = currentRoom.findClientByName(targetUsername);
            if (targetClient != null) {
                if (targetClient.isMuted()) {
                //sets taregt user's serverThread to false
                // is unmuted
                targetClient.setMuted(false);
                muteList.remove(targetUsername);
                // lets the sender know the user in unmuted
                sendMessage(getClientId(), "You Unmuted " + targetUsername + ". ");
                 targetClient.sendMessage(getClientId(), getClientName() + " unmuted you");
            } else {
                // If the target user is already unmuted, do not send a repeat message
                sendMessage(getClientId(), targetUsername + " is already unmuted");
            }
        } else {
                // if the user not in the room
                sendMessage(getClientId(), "User " + targetUsername + " not found in the room.");
        }
        }
    }   
    //returns isMuted of the ServerThread 
    // lets user know muted or not.
    public boolean isMuted() {
        return isMuted;
    }
    //sets isMuted of the ServerThread to the specified value muting or unmuting user.
    public void setMuted(boolean isMuted) {
        this.isMuted = isMuted;
    }

    private boolean isRecipientMuted(long clientId) {
    // Find the target client by clientId in the current room
    ServerThread targetClient = currentRoom.findClientById(clientId);
    if (targetClient != null) {
        // Get the target client's username
        String targetUsername = targetClient.getClientName();
        // Check if the target client is in the mute list of the sender
        return muteList.containsKey(targetUsername) && muteList.get(targetUsername);
    }
    return false;
}



    private void cleanup() {
        info("Thread cleanup() start");
        try {
            client.close();
        } catch (IOException e) {
            info("Client already closed");
        }
        info("Thread cleanup() complete");
    }
}