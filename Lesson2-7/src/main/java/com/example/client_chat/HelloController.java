package com.example.client_chat;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController
{
    private boolean isAuthorized;

    @FXML
    TextArea textArea;
    @FXML
    TextField textField;
    @FXML
    Button button;

    @FXML
    TextField loginField;
    @FXML
    PasswordField passwordField;
    @FXML
    Button enter;
    @FXML
    HBox upperPanel;
    @FXML
    HBox bottomPanel;

    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    String IP_ADDRESS = "localhost";
    int PORT = 8189;

    @FXML
    public void keyListener(KeyEvent keyEvent) {
        if (keyEvent.getCode().getCode() == 10) {
            sendMessage();
        }
    }

    public void setActive(boolean isAuthorized)
    {
        this.isAuthorized = isAuthorized;
        if (!isAuthorized)
        {
            upperPanel.setVisible(true);
            upperPanel.setManaged(true);
            bottomPanel.setVisible(false);
            bottomPanel.setManaged(false);
        }
        else
        {
            upperPanel.setVisible(false);
            upperPanel.setManaged(false);
            bottomPanel.setVisible(true);
            bottomPanel.setManaged(true);
        }
    }

    public void sendMessage() {
        //textArea.appendText(textField.getText() + "\n");
        try {
            out.writeUTF(textField.getText());
            textField.clear();
            textField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect()
    {
        Socket socket;
        try {

            while (true)
            {
                try
                {
                    socket = new Socket(IP_ADDRESS, PORT);


                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                break;
                }
                catch (IOException e)
                {
                    System.out.println("Сервер не найден!");;
                }
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }


            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true)
                        {
                            try
                            {
                                String str = in.readUTF();
                                if (str.startsWith("/authok"))
                                {
                                    setActive(true);
                                    break;
                                }
                                else
                                {
                                    textArea.appendText(str + "\n");
                                }
                            }
                            catch (SocketException e)
                            {
                                System.out.println("Сервер не отвечает или недоступен!");
                                break;
                            }

                        }

                        while (true)
                        {
                            try
                            {
                                String str = in.readUTF();
                                if(str.equals("/end"))
                                {
                                    break;
                                }
                                textArea.appendText(str + "\n");
                            }
                            catch (SocketException e)
                            {
                                System.out.println("Сервер не отвечает или недоступен!");
                                break;
                            }

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }).start();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void auth()
    {
        if (socket == null || socket.isClosed())
        {
            connect();
        }
        try
        {
            out.writeUTF("/auth " + loginField.getText() + " " + passwordField.getText());
            loginField.clear();
            passwordField.clear();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}