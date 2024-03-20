package ru.aston.ramisabd.homework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {

    public static Connection getConnection() {
        String urlDB;
        String username;
        String password;

        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/application.properties")) {

            properties.load(fileInputStream);

            urlDB = properties.getProperty("dp.host");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(urlDB, username, password);

            String initDB = Files.readString(Path.of("src/main/resources/init.sql"));
            connection.prepareStatement(initDB).execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}
