package org.step.lection.fourth.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ConnectDb {

    private ResourceBundle dbProperties = ResourceBundle.getBundle("database");

    public static void main(String[] args) {
        ConnectDb connectDb = new ConnectDb();
        ResourceBundle dbProperties = connectDb.getDbProperties();

        try {
            Class.forName(dbProperties.getString("db.driver"));

            Connection connection = DriverManager.getConnection(
                    dbProperties.getString("db.url"),
                    dbProperties.getString("db.username"),
                    dbProperties.getString("db.password")
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

    public ResourceBundle getDbProperties() {
        return dbProperties;
    }
}
