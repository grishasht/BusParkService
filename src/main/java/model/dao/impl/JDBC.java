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

    private static final String LOG_MSG_PROPERTIES =
            "/home/hs/Desktop/GitHub/BusParkService/src/main/resources/log_msg.properties";

    {
        try {
            properties.load(new FileInputStream(LOG_MSG_PROPERTIES));
        } catch (IOException e) {
            log.error(properties.getProperty("FILE_NOT_FOUND") + "in JDBC");
        }
    }

    public JDBC(Connection connection) {
        this.connection = connection;
    }
}
