package model.dao.impl;

import model.util.LogGenerator;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class JDBC {
    protected Logger log = LogGenerator.getInstance();
    protected Properties properties = new Properties();
    protected Connection connection;

    {
        try {
            properties.load(new FileInputStream("src/main/resources/log_msg.properties"));
        } catch (IOException e) {
            log.error(properties.getProperty("FILE_NOT_FOUND") + "in JDBC");
        }
    }

    public JDBC(Connection connection) {
        this.connection = connection;
    }
}
