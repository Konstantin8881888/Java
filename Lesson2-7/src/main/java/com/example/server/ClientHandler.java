package com.example.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler {
    Socket socket;
    ServerMain server;
    DataOutputStream out;
    DataInputStream in;

    public ClientHandler(Socket socket, ServerMain serverMain) {
        this.socket = socket;
        this.server = serverMain;

        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true)
                        {
                            String str = in.readUTF();

                            if (str.startsWith("/auth"))
                            {
                                String[] creds = str.split(" ");
                                String nick = AuthServer.getNickByloginPass(creds[1], creds[2]);
                                if (nick != null)
                                {
                                    sendMsg("/authok");
                                    server.subscribe(ClientHandler.this);
                                    break;
                                }
                                else
                                {
                                    sendMsg("Неправильный логин или пароль!");
                                }
                            }
                        }

                        while (true) {
                            String str;
                            str = in.readUTF();
                            if (str.equals("/end"))
                            {
                                out.writeUTF("/end");
                                break;
                            }
                            serverMain.sendToAll(str);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finally
                    {
                        try
                        {
                            out.writeUTF("/end");
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        try
                        {
                            in.close();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        try
                        {

                            out.close();
                        } catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        try
                        {
                            socket.close();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    server.unsubscribe(ClientHandler.this);
                }
            }).start();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg)
    {
        System.out.println("Client send message: " + msg);
        try
        {
            out.writeUTF(msg + "\n");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
