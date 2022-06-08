package com.example.lesson24;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class HelloController
{
    @FXML
    TextArea textArea;

    @FXML
    Button button;

    @FXML
    TextField textField;

    @FXML
    public void sendMessage()
    {
        textArea.appendText(textField.getText() + "\n");
        textField.clear();
        textField.requestFocus();
    }

    @FXML
    private void enterPressed(KeyEvent e)
    {
        if (e.getCode().equals(KeyCode.ENTER))
        {
            sendMessage();
        }

    }
}