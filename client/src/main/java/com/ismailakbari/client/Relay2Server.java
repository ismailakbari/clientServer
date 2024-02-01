package com.ismailakbari.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;

public class Relay2Server {

    public static void relayToServer(Map<String, String> propertiesMap, String IP, int PORT) {
        try {
            Socket socket = new Socket(IP, PORT); // Connect to server on localhost:12345

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            //out.println("Hello, server!");
            for (Map.Entry<String,String> entry : propertiesMap.entrySet()) {
                out.println(entry.getKey() + "=" + entry.getValue());
                String serverResponse = in.readLine();
                System.out.println("Server response: " + serverResponse);
            }

            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
