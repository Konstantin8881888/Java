package com.example.client_chat;

import com.example.server.AuthServer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

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
    ListView<String> clientList;


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


    @FXML
    HBox regPanel;
    @FXML
    Button regPage;
    @FXML
    TextField nickField;
    @FXML
    TextField regLoginField;
    @FXML
    PasswordField regPasswordField;
    @FXML
    Button register;

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
            clientList.setVisible(false);
            clientList.setManaged(false);
        }
        else
        {
            upperPanel.setVisible(false);
            upperPanel.setManaged(false);
            bottomPanel.setVisible(true);
            bottomPanel.setManaged(true);
            clientList.setVisible(true);
            clientList.setManaged(true);
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
                                    textArea.appendText(str + "\n");
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
                                if (str.startsWith("/"))
                                {
                                    if (str.startsWith("/show"))
                                    {
                                        String[] nicknames = str.split(" ");
                                        Platform.runLater(new Runnable()
                                        {
                                            @Override
                                            public void run()
                                            {
                                                clientList.getItems().clear();
                                                for (int i = 1; i < nicknames.length; i++)
                                                {
                                                    clientList.getItems().add(nicknames[i]);
                                                }
                                            }
                                        });
                                    }
                                    if(str.equals("/end"))
                                    {
                                        break;
                                    }
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
            if (loginField.getText().isBlank()||passwordField.getText().isBlank())
            {
                textArea.appendText("Неправильный логин или пароль! \n");
                return;
            }
            out.writeUTF("/auth " + loginField.getText() + " " + passwordField.getText());
            loginField.clear();
            passwordField.clear();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void reg(ActionEvent actionEvent)
    {
        AuthServer.registerLogin(nickField.getText(), regLoginField.getText(), regPasswordField.getText());
        regPanel.setVisible(false);
        regPanel.setManaged(false);
        upperPanel.setVisible(true);
        upperPanel.setManaged(true);
    }

    public void regPage(ActionEvent actionEvent)
    {
        upperPanel.setVisible(false);
        upperPanel.setManaged(false);
        regPanel.setManaged(true);
        regPanel.setVisible(true);
    }
}