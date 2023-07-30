package com.masai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class ServerApp {
    public static void main(String[] args) {

        try {
            ServerSocket server = new ServerSocket(8888);
            System.out.println("Server is ready to accept connections");
            System.out.println("Waiting...");

            Socket socket = server.accept();
            System.out.println("Connected to the client");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            Scanner sc = new Scanner(System.in);

            // Writing thread
            Runnable writingThread = () -> {
                System.out.println("Server can send message to the client");
                while (true) {
                    String text = sc.nextLine();
                    out.println(text);
                    out.flush();
                    if (text.equals("exit")) {
                        System.out.println("Server has terminated the chat");
                        break;
                    }
                    System.out.println("Server has sent: " + text);
                }
            };

            // Reading thread
            Runnable readingThread = () -> {
                System.out.println("Server can read message from the client now");
                try {
                    String msg;
                    while ((msg = reader.readLine()) != null) {
                        if (msg.equals("exit")) {
                            System.out.println("Client has terminated the chat");
                            break;
                        }
                        System.out.println("Client: " + msg);
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            };

            // Start the threads
            new Thread(readingThread).start();
            new Thread(writingThread).start();
            
            

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
