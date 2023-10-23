import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class ClientHandler extends Thread {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private List<ClientHandler> clients;

    public ClientHandler(Socket socket, List<ClientHandler> clients) {
        this.clientSocket = socket;
        this.clients = clients;
    }

    @Override
    public void run() {
        try {
            // Create input and output streams for the client socket
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Add client handler to the list of clients
            clients.add(this);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                // Broadcast the received message to all clients
                broadcastMessage(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Error in client handler: " + e.getMessage());
        } finally {
            try {
                // Remove the client handler from the list of clients and close the socket
                clients.remove(this);
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Error closing client socket: " + e.getMessage());
            }
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    private void broadcastMessage(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }
}

public class ChatServer {
    private static final int PORT = 9000;
    private List<ClientHandler> clients;

    public ChatServer() {
        clients = new ArrayList<>();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Chat Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                // Create a new client handler for each connected client
                ClientHandler clientHandler = new ClientHandler(clientSocket, clients);
                clientHandler.start();
            }
        } catch (IOException e) {
            System.out.println("Error in Chat Server: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        server.start();
    }
}


