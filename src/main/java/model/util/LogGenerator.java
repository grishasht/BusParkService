package model.util;

import org.apache.log4j.Logger;

public class LogGenerator {
    private static Logger log;

    private LogGenerator() {
    }

    public static Logger getInstance(){
        if (log == null){
            log = Logger.getLogger(LogGenerator.class);
        }
        return log;
    }

}
