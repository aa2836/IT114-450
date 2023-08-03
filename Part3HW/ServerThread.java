package Part3HW;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

/**
 * A server-side representation of a single client
 */
public class ServerThread extends Thread {
    private Socket client;
    private boolean isRunning = false;
    private ObjectOutputStream out;//exposed here for send()
    private Server server;// ref to our server so we can call methods on it
    // more easily
    private String username;


    private void info(String message) {
        System.out.println(String.format("Thread[%s]: %s", getId(), message));
    }

    public ServerThread(Socket myClient, Server server) {
        info("Thread created");
        // get communication channels to single client
        this.client = myClient;
        this.server = server;

    }

    public void disconnect() {
        info("Thread being disconnected by server");
        isRunning = false;
        cleanup();
    }

    public boolean send(String message) {
        // added a boolean so we can see if the send was successful
        try {
            out.writeObject(message);
            return true;
        } catch (IOException e) {
            info("Error sending message to client (most likely disconnected)");
            // comment this out to inspect the stack trace
            // e.printStackTrace();
            cleanup();
            return false;
        }
    }

    @Override
    public void run() {
        info("Thread starting");
        try (ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());) {
            this.out = out;
            isRunning = true;
            String fromClient;
            while (isRunning && // flag to let us easily control the loop
                    (fromClient = (String) in.readObject()) != null // reads an object from inputStream (null would
                                                                    // likely mean a disconnect)
            ) {

                info("Received from client: " + fromClient);
                if (fromClient.startsWith("/")) {
                    if (fromClient.startsWith("/roll")) {
                        String[] parts = fromClient.split(" ");
                        if (parts.length == 2) {
                            String[] rollParts = parts[1].split("d");
                            if (rollParts.length == 2) {
                                // Format: /roll #d#
                                int rolls = Integer.parseInt(rollParts[0]);
                                int sides = Integer.parseInt(rollParts[1]);
                                Random random = new Random();
                                String results = "Roll results: ";
                                for (int i = 0; i < rolls; i++) {
                                    results += (random.nextInt(sides) + 1) + " ";
                                }
                                server.broadcast(results.trim(), this.getId());
                            } else {
                                // Format: /roll 0 - X or 1 - X
                                int max = Integer.parseInt(parts[1]);
                                Random random = new Random();
                                server.broadcast("Roll result: " + (random.nextInt(max) + 1), this.getId());
                            }
                        }
                    } else if (fromClient.startsWith("/flip")) {
                        // Flip a coin
                        Random random = new Random();
                        server.broadcast("Flip result: " + (random.nextBoolean() ? "heads" : "tails"), this.getId());
                } else if (fromClient.startsWith("@")) {
                        // Send a private message
                        String[] parts = fromClient.split(" ", 2);
                        if (parts.length == 2) {
                            String username = parts[0].substring(1); // remove the '@'
                            String message = parts[1];
                            server.privateMessage(username, message, this.getId());
                    }
                } else if (fromClient.startsWith("/mute")) {
                    String username = fromClient.split(" ")[1];
                    server.muteUser(username, this.getId());
                } else if (fromClient.startsWith("/unmute")) {
                    String username = fromClient.split(" ")[1];
                    server.unmuteUser(username, username, this.getId());
                }
            } else {
                // Regular message
                server.broadcast(fromClient, this.getId());
            }
        } 
            out.writeObject("Enter your username: ");
            this.username = (String) in.readObject();       
        // close while loop
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

    private void cleanup() {
        info("Thread cleanup() start");
        try {
            client.close();
        } catch (IOException e) {
            info("Client already closed");
        }
        info("Thread cleanup() complete");
    }

    public Object getUsername() {
        return this.username;
    }
}