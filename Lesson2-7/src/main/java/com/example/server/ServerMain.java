package com.example.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ServerMain {

    private Vector<ClientHandler> clientHandlers;

    public void start() {
        ServerSocket server;
        Socket socket;

        clientHandlers = new Vector<>();

        try {
            AuthServer.connect();
            server = new ServerSocket(8189); //localhost:8189 -- 0 - 65000
            System.out.println("Сервер запущен");

            while (true){
//                System.out.println(AuthServer.getNickByloginPass("login1", "pass1"));
                socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(socket, this);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally
        {
            sendToAll("/end");
        }
        AuthServer.disconnect();
    }
    public void sendToAll(String msg)
    {
        for (ClientHandler client: clientHandlers)
        {
            client.sendMsg(msg);
        }
    }

    public void subscribe(ClientHandler client)
    {
        clientHandlers.add(client);
    }

    public void unsubscribe(ClientHandler client)
    {
        clientHandlers.remove(client);
    }
}
