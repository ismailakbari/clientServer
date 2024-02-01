package com.ismailakbari.server;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        String outputPath ;// "/Users/ismail/IdeaProjects/Mine/clientServer/outputDir";
        int PORT ; //12345 ;

        Map<String, String> config = new HashMap<>();
        try {
            config = ConfigReader.readProps(args[0]);
            outputPath = config.get("outputPath");
            PORT = Integer.parseInt(config.get("PORT"));

            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server is listening on port " + PORT + " ...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                // Handle the client in a separate thread
                Thread clientThread = new Thread(() -> handleClient(clientSocket, outputPath));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket, String filePath) {
        try {
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;
            StringBuilder props = new StringBuilder();
            String clientFilename ="";
            boolean readFilename = false;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                String receivedMessage = new String(buffer, 0, bytesRead);
                if(!readFilename && receivedMessage.contains("filename="))
                {
                    readFilename = true;
                    String[] fn = receivedMessage.split("filename=");
                    clientFilename = fn[1].trim();
                    System.out.println("filename split:" + fn[0] + "|" + fn[1]);
                }
                else
                    props.append(receivedMessage);
                //props.append(System.getProperty("line.separator"));
                //PropWriter.writeProps(filePath, receivedMessage);
                //System.out.println("Received from " + clientSocket + ": " + receivedMessage);
                // Send a response back to the client
                String responseMessage = "Server received your message: " + receivedMessage;
                outputStream.write(responseMessage.getBytes());
            }
            //System.out.println("sb=" + props);
            PropWriter.writeProps2(filePath + "/" + clientFilename, "" + props);

            System.out.println("Client disconnected: " + clientSocket);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
