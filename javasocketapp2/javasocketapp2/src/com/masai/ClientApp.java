package com.masai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {
    public static void main(String[] args) {

        try {
            System.out.println("Sending request to the server");
            Socket socket = new Socket("localhost", 8888);
            System.out.println("Connection established");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            Scanner sc = new Scanner(System.in);

            // Reading thread
            Runnable readingThread = () -> {
                System.out.println("Client can get message from server now");
                try {
                    String msg;
                    while ((msg = reader.readLine()) != null) {
                        if (msg.equals("exit")) {
                            System.out.println("Server has terminated the chat");
                            break;
                        }
                        System.out.println("Server: " + msg);
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            };

            // Writing thread
            Runnable writingThread = () -> {
                System.out.println("Client can send message from the server now");
                while (true) {
                    String text = sc.nextLine();
                    out.println(text);
                    out.flush();
                    System.out.println("Client has sent :"+text);
                    
                    if (text.equals("exit")) {
                        System.out.println("Client has terminated the chat");
                        break;
                    }
                }
            };

            // Start the threads
            new Thread(readingThread).start();
            new Thread(writingThread).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}