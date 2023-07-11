package Project.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import Project.common.Constants;
import Project.common.Payload;
import Project.common.PayloadType;
import Project.common.RoomResultPayload;

/**
 * A server-side representation of a single client
 */
public class ServerThread extends Thread {
    private Socket client;
    private String sender;
    private boolean isRunning = false;
    private ObjectOutputStream out;// exposed here for send()
    // private Server server;// ref to our server so we can call methods on it
    // more easily
    private Room currentRoom;
    private static Logger logger = Logger.getLogger(ServerThread.class.getName());
    private long myClientId;

    public void setClientId(long id) {
        myClientId = id;
    }

    public long getClientId() {
        return myClientId;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public ServerThread(Socket myClient, Room room) {
        logger.info("ServerThread created");
        // get communication channels to single client
        this.client = myClient;
        this.currentRoom = room;

    }
    // aa2836
    // Paylod sender
    protected void setSender(String name) {
        if (name == null || name.isBlank()) {
            logger.warning("Invalid name being set");
            return;
        }
        sender = name;
    }

    public String getSender() {
        return sender;
    }

    protected synchronized Room getCurrentRoom() {
        return currentRoom;
    }

    protected synchronized void setCurrentRoom(Room room) {
        if (room != null) {
            currentRoom = room;
        } else {
            logger.info("Passed in room was null, this shouldn't happen");
        }
    }

    public void disconnect() {
        sendConnectionStatus(myClientId, getSender(), false);
        logger.info("Thread being disconnected by server");
        isRunning = false;
        cleanup();
    }

    // send methods

    public boolean sendReadyStatus(long clientId) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.READY);
        p.setClientId(clientId);
        return send(p);
    }

    public boolean sendRoomName(String name) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.JOIN_ROOM);
        p.setMessage(name);
        return send(p);
    }

    public boolean sendRoomsList(String[] rooms, String message) {
        RoomResultPayload payload = new RoomResultPayload();
        payload.setRooms(rooms);
        if (message != null) {
            payload.setMessage(message);
        }
        return send(payload);
    }

    public boolean sendExistingClient(long clientId, String sender) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.SYNC_CLIENT);
        p.setClientId(clientId);
        p.setSender(sender);
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
        Payload p = new Payload();
        p.setPayloadType(PayloadType.MESSAGE);
        p.setClientId(clientId);
        p.setMessage(message);
        return send(p);
    }

    public boolean sendConnectionStatus(long clientId, String who, boolean isConnected) {
        Payload p = new Payload();
        p.setPayloadType(isConnected ? PayloadType.CONNECT : PayloadType.DISCONNECT);
        p.setClientId(clientId);
        p.setSender(who);
        p.setMessage(String.format("%s the room %s", (isConnected ? "Joined" : "Left"), currentRoom.getName()));
        return send(p);
    }

    private boolean send(Payload payload) {
        try {
            logger.log(Level.FINE, "Outgoing payload: " + payload);
            out.writeObject(payload);
            logger.log(Level.INFO, "Sent payload: " + payload);
            return true;
        } catch (IOException e) {
            logger.info("Error sending message to client (most likely disconnected)");
            // uncomment this to inspect the stack trace
            // e.printStackTrace();
            cleanup();
            return false;
        } catch (NullPointerException ne) {
            logger.info("Message was attempted to be sent before outbound stream was opened: " + payload);
            // uncomment this to inspect the stack trace
            // e.printStackTrace();
            return true;// true since it's likely pending being opened
        }
    }
    
        // ...
    
        // processMesssgae 
        private String textStyles(String message) {
            // red
            message = message.replaceAll("==red (.*?)==", "[COLOR=red]$1[/COLOR]");
            // green
            message = message.replaceAll("==green (.*?)==", "[COLOR=green]$1[/COLOR]");
            // blue
            message = message.replaceAll("==blue (.*?)==", "[COLOR=blue]$1[/COLOR]");
    
            // bold
            message = message.replaceAll("\\*\\*(.*?)\\*\\*", "[B]$1[/B]");
            // italics
            message = message.replaceAll("-(.*?)-", "[I]$1[/I]");
            //underline
            message = message.replaceAll("__(.*?)__", "[U]$1[/U]");
    
            return message;
        }
    

    // end send methods
    @Override
    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());) {
            this.out = out;
            isRunning = true;
            Payload fromClient;
            while (isRunning && // flag to let us easily control the loop
                    (fromClient = (Payload) in.readObject()) != null // reads an object from inputStream (null would
                                                                     // likely mean a disconnect)
            ) {

                logger.info("Received from client: " + fromClient);
                processPayload(fromClient);

            } // close while loop
        } catch (Exception e) {
            // happens when client disconnects
            e.printStackTrace();
            logger.info("Client disconnected");
        } finally {
            isRunning = false;
            logger.info("Exited thread loop. Cleaning up connection");
            cleanup();
        }
    }

    private void processPayload(Payload p) {
        switch (p.getPayloadType()) {
            case CONNECT:
                setSender(p.getSender());
                break;
            case DISCONNECT:
                Room.disconnectClient(this, getCurrentRoom());
                break;
                case MESSAGE:
                if (currentRoom != null) {
                    //aa2836 7-10-2023
                    //gets the message from the object p
                    //removes any extra spaces at the beginning/end with trim()
                    //handles with the processMessage()  
                    //storing result in message variable.
                    String message = textStyles(p.getMessage().trim());
                    // check if its /rolls
                    if (message.startsWith("/roll ")) {
                        //calls the RollCommand
                        Roll(message.substring(6), this);
                        // if not /roll check for / flip
                    } else if (message.equalsIgnoreCase("/flip")) {
                        // calls flip command
                        Flip();
                    } else {
                        currentRoom.sendMessage(this, message);
                    }
                } else {
                    logger.log(Level.INFO, "Migrating to lobby on message with null room");
                    Room.joinRoom(Constants.LOBBY, this);
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
            case READY:
                // ((GameRoom) currentRoom).setReady(myClientId);
                break;
            default:
                break;

        }

    }

   

    /**
     * 
     */
    private void cleanup() {
        logger.info("Thread cleanup() start");
        try {
            client.close();
        } catch (IOException e) {
            logger.info("Client already closed");
        }
        logger.info("Thread cleanup() complete");
    }
    // handels flip command from client
    private void Flip() {
        // randomly genrates 0 or 1
        int result = Math.random() < 0.5 ? 0 : 1; 
        // if 0 then its heads if 1 then its tails
        String resultMessage = (result == 0) ? "Heads" : "Tails";
        // sends the result to all cients
        currentRoom.broadcastMessage(resultMessage);
    }
    // hadles roll command from client
    private void Roll(String command, ServerThread client) {
        // if command have "-" format 1 will be invoked
        if (command.contains("-")) {
            RollFormat1(command, this);
        // if not, but have "d" invokes Format 2
        } else if (command.contains("d")) {
            RollFormat2(command, this);
        }
    }
    
    
    private void RollFormat1(String rollCommand, ServerThread client) {
        
        // array will have two elements:
        
        String[] parts = rollCommand.split("-");
        if (parts.length == 2) {
            // takes the lowe and upper int vals from roll command
            int lowerint = Integer.parseInt(parts[0].trim());
            int upperint = Integer.parseInt(parts[1].trim());
            // makes a random number withing the lower and upper int range
            if (lowerint < upperint) {
                int result = lowerint + (int) (Math.random() * (upperint - lowerint + 1));
                // result 
                String resultMessage = "Dice roll result: " + result;
                // sends the result to all client in the room
                currentRoom.broadcastMessage(resultMessage);
            }
        }
    }
    
    private void RollFormat2(String rollCommand, ServerThread client) {
        // array will have two elements:
        
        String[] parts = rollCommand.split("d");
        if (parts.length == 2) {
            // takes the number of dice and side
            int numDice = Integer.parseInt(parts[0].trim());
            int numSides = Integer.parseInt(parts[1].trim());
            if (numDice > 0 && numSides > 0) {
                // result message 
                StringBuilder resultMessage = new StringBuilder("Dice roll results: ");
                for (int i = 0; i < numDice; i++) {
                    // genarates a random number
                    int result = 1 + (int) (Math.random() * numSides);
                    // adds result to the result messgae
                    resultMessage.append(result);
                    if (i < numDice - 1) {
                        // adds comma in the answer
                        resultMessage.append(", ");
                    }
                }
                // sends the result to all client in the room
                currentRoom.broadcastMessage(resultMessage.toString());
            }
        }
    }
    
    
    
}
    

    