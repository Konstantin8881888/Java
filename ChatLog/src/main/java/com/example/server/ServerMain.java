package com.example.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ServerMain {

    private Vector<ClientHandler> clientHandlers;
    protected static final ExecutorService executorService = Executors.newCachedThreadPool();
    static final Logger logger = LogManager.getLogger("ServerLogger");

    public void start() {
        ServerSocket server;
        Socket socket;

        clientHandlers = new Vector<>();

        try {
            AuthServer.connect();
            server = new ServerSocket(8189); //localhost:8189 -- 0 - 65000
//            System.out.println("Сервер запущен");
            logger.log(Level.INFO, "Сервер запущен");

            while (true){
//                System.out.println(AuthServer.getNickByloginPass("login1", "pass1"));
                socket = server.accept();
//                System.out.println("Клиент подключился");
                logger.log(Level.INFO, "Клиент подключился");
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

    public void sendOnlineUsers()
    {
        StringBuilder sb = new StringBuilder(" ");
        List<String> list = clientHandlers.stream().map(ClientHandler::getNickname).toList();
        for (String s:list)
        {
            sb.append(s);
            sb.append(" ");
        }
        sendToAll("/show " + sb.toString().trim());
    }

    public void subscribe(ClientHandler client)
    {
        clientHandlers.add(client);
    }

    public void unsubscribe(ClientHandler client)
    {
        sendToAll("Пользователь " + client.getNickname() + " вышел.");
        logger.log(Level.INFO, "Пользователь " + client.getNickname() + " вышел.");
        sendOnlineUsers();
        clientHandlers.remove(client);
    }

    public boolean isNickFree(String nick)
    {
        if (clientHandlers.isEmpty()) return true;
        for (ClientHandler client: clientHandlers)
        {
            if (client.getNickname().equals(nick))
            {
                return false;
            }
        }
        return true;
    }
}
