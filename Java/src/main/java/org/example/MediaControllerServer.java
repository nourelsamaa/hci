package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class MediaControllerServer {

    private static final MediaController mediaController = new MediaController();

    public static void main(String[] args) throws IOException {
        // Create HTTP server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // Create a context for the "/command" path
        server.createContext("/command", new CommandHandler());

        // Start the server
        server.setExecutor(null); // Use the default executor
        server.start();

        // Log that the server has started
        System.out.println("MediaControllerServer is running. Listening for commands on port 8000");
    }

    static class CommandHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Get the JSON request from the body
            String jsonRequest = new String(exchange.getRequestBody().readAllBytes());

            // Log that a command has been received
            System.out.println("Received command: " + jsonRequest);

            // Process the JSON request
            processCommand(jsonRequest);

            // Send a response (you can customize this part)
            String response = "Command received successfully";
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }

        private void processCommand(String jsonRequest) {
            // Parse JSON (you might want to use a JSON library for better parsing)
            String command = jsonRequest.replaceAll("[^a-zA-Z]", ""); // Extract alphabetic characters

            // Log that the command is being processed
            System.out.println("Processing command: " + command);

            // Call the corresponding method in MediaController
            mediaController.pressKey(command.toLowerCase());
        }
    }
}
