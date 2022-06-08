import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Window extends JFrame {
    public Window() {
        setTitle("Наш чат");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setBounds(0, 0, 500, 700);
        setLayout(new GridLayout(5, 1));

        Font font = new Font("Arial", Font.BOLD, 20);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(20, 20, 120, 32);
        textArea.setFont(font);
        JScrollPane scroll = new JScrollPane(textArea);
        add(scroll);

        JTextArea textArea2 = new JTextArea();
        textArea2.setBounds(20, 20, 120, 32);
        textArea2.setFont(font);
        textArea2.setForeground(Color.RED);
        JScrollPane scroll2 = new JScrollPane(textArea2);
        add(scroll2);


        JButton buttonPush = new JButton("Отправить");
        buttonPush.addActionListener(e -> {
            System.out.println("Сообщение отправлено.");
            textArea2.append("- " + textArea.getText() + "\n");
            textArea.setText("");
        });
        add(buttonPush);

        JButton buttonSave = new JButton("Сохранить");
        buttonSave.addActionListener(e -> {
                    System.out.println("Сохранено");
                    try
                    {
                        FileWriter writer = new FileWriter("messageLog.txt", false);
                        writer.write(textArea2.getText());
                        writer.close();
                    }
                    catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                });
            add(buttonSave);

        JButton buttonLoad = new JButton("Загрузить сообщения");
        buttonLoad.addActionListener(e -> {
            System.out.println("Сообщения загружены.");
            textArea2.setText("");

            try
            {
                FileReader reader = new FileReader("messageLog.txt");
                Scanner loadFile = new Scanner(reader);

                while (loadFile.hasNextLine())
                {
                    textArea2.append(loadFile.nextLine() + "\n");
                }

                reader.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }

        });
        add(buttonLoad);

        setVisible(true);
    }
}
