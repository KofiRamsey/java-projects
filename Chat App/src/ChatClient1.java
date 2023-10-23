import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient1 {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 9000;

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private BufferedReader userInputReader;

    public void connect() {
        try {
            // Connect to the chat server
            clientSocket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Connected to the Chat Server.");

            // Create input and output streams for the client socket
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Create a separate thread for reading server messages
            Thread messageReaderThread = new Thread(this::readMessages);
            messageReaderThread.start();

            // Start reading user input and sending messages to the server
            userInputReader = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            while ((userInput = userInputReader.readLine()) != null) {
                sendMessage(userInput);
            }
        } catch (IOException e) {
            System.out.println("Error in Chat Client: " + e.getMessage());
        } finally {
            disconnect();
        }
    }

    private void sendMessage(String message) {
        out.println(message);
    }

    private void readMessages() {
        try {
            String serverMessage;
            while ((serverMessage = in.readLine()) != null) {
                System.out.println(serverMessage);
            }
        } catch (IOException e) {
            System.out.println("Error reading server message: " + e.getMessage());
        }
    }

    private void disconnect() {
        try {
            if (clientSocket != null) {
                clientSocket.close();
            }
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            if (userInputReader != null) {
                userInputReader.close();
            }
            System.out.println("Disconnected from the Chat Server.");
        } catch (IOException e) {
            System.out.println("Error disconnecting from the Chat Server: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ChatClient1 chatClient = new ChatClient1();
        chatClient.connect();
    }
}
