package org.richfaces.democars.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBasePopulator {
    private URL scriptUrl;

    public DataBasePopulator() {
        ClassLoader cs = getClass().getClassLoader();
        scriptUrl = cs.getResource("import.sql");
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:derby:demoCars");
    }

    public void populate() throws Exception {
        FileReader input = new FileReader(scriptUrl.getFile());
        BufferedReader bufRead = new BufferedReader(input);
        Connection connection = getConnection();
        String line;

        line = bufRead.readLine();

        Statement statement = connection.createStatement();
        connection.setAutoCommit(false);
        while (line != null) {
            if (line.equals("--commit")) {
                connection.commit();
                statement.close();
                statement = connection.createStatement();
            } else {
                statement.addBatch(line);
            }
            line = bufRead.readLine();
        }
        statement.close();
        connection.setAutoCommit(true);
        connection.close();
        bufRead.close();
    }
}