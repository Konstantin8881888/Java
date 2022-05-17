package com.example.server;

import javax.xml.transform.Result;
import java.sql.*;

public class AuthServer
{
    private static Connection connection;
    private static Statement statement;

    public static void connect()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:users.db");
            statement = connection.createStatement();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static String getNickByloginPass(String login, String password)
    {
        String sql = String.format("SELECT nickname FROM users WHERE login = '%s' AND password = '%s'", login, password);
        try
        {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next())
            {
                return resultSet.getString("nickname");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public static void registerLogin(String nick, String login, String password)
    {
        ResultSet rs;
        int count = 0;
        try {
            connect();
            rs = statement.executeQuery("SELECT count(*) FROM users");
            rs.next();
            count = (rs.getInt(1) + 1);

        } catch (SQLException e) {
            e.printStackTrace();
        }



        String sql = String.format("INSERT INTO users (id, login, password, nickname) VALUES ('%s', '%s', '%s', '%s')", count, nick, login, password);

        try
        {
            int st = statement.executeUpdate(sql);
            System.out.println("Добавлен " + st + " пользователь");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    public static void changeNickname (String oldNick, String newNick, String passChange)
    {
        connect();
        try {
            statement.execute("UPDATE users set nickname = " + newNick + " WHERE nickname = " + oldNick + " AND password = " + passChange);
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }

    }


    public static void disconnect()
    {
        try
        {
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
