package com.example.client_chat;

import com.example.server.AuthServer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class HelloController
{

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

    @FXML
    HBox changePanel;
    @FXML
    Button changePage;
    @FXML
    Button changeButton;
    @FXML
    TextField newNameChangeField;
    @FXML
    TextField oldNameChangeField;
    @FXML
    PasswordField changePasswordField;

    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    final String IP_ADDRESS = "localhost";
    final int PORT = 8189;

    @FXML
    public void keyListener(KeyEvent keyEvent) {
        if (keyEvent.getCode().getCode() == 10) {
            sendMessage();
        }
    }

    public void setActive(boolean isAuthorized)
    {
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

    public void sendMessage()
    {
        try
        {
            out.writeUTF(textField.getText());
            textField.clear();
            textField.requestFocus();
        } catch (IOException e)
        {
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
                    System.out.println("Сервер не найден!");
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
                                    loadLog();
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
                                    SaveLog();
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

    public void changeName(ActionEvent actionEvent)
    {

        AuthServer.changeNickname(oldNameChangeField.getText(), newNameChangeField.getText(), changePasswordField.getText());
        changePanel.setVisible(false);
        changePanel.setManaged(false);
        upperPanel.setVisible(true);
        upperPanel.setManaged(true);
    }

    public void changePage(ActionEvent actionEvent)
    {
        upperPanel.setVisible(false);
        upperPanel.setManaged(false);
        changePanel.setManaged(true);
        changePanel.setVisible(true);
    }

//    Блок логирования:

    private void SaveLog()
    {
        try
        {
            File history = new File("log.txt");

            PrintWriter fileWriter = new PrintWriter(new FileWriter(history, false));

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(textArea.getText());
            bufferedWriter.close();

        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }

    private void loadLog() throws IOException
    {
        int endLog = 100;

        File history = new File("log.txt");
//        Проверим на существование файла:
        if (!history.exists())
        {
            history.createNewFile();
        }

        List<String> logList = new ArrayList<>();
        FileInputStream in = new FileInputStream(history);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

        String textStringLog;

        while ((textStringLog = bufferedReader.readLine()) != null)
        {
            logList.add(textStringLog);
        }

        if (logList.size() > endLog)
        {
            for (int i = logList.size() - endLog; i <= (logList.size() - 1); i++)
            {
                textArea.appendText(logList.get(i) + "\n");
            }
        }
        else
        {
            for (String s : logList)
            {
                textArea.appendText(s + "\n");

            }
        }
    }


}