package org.step.lection.fourth.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDb {

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/hibernate_test",
                    "user",
                    "userpassword"
            );

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from courses");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
                System.out.println(resultSet.getString(2));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
