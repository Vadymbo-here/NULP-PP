package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
    public static Logger logger = Logger.getLogger(Log.class.getName());

    public static void init() {
        try {
            // In mylogging.properties, necessary
            // logging messages settings are given.
            // First it has to be read
            LogManager.getLogManager().readConfiguration(
                    new FileInputStream(
                            "src\\utils\\mylogging.properties"));
        } catch (SecurityException | IOException e1) {
            System.out.println(
                    "SecurityException message ,, "
                            + e1.getMessage());
        }
        logger.setLevel(Level.FINE);
        logger.addHandler(new ConsoleHandler());
        // adding custom handler
        // logger.addHandler(new MyHandler());
        try {
            // FileHandler file name with max size and number of log files limit
            Handler fileHandler = new FileHandler(
                    "D:\\Code\\Proggrams\\PP_labki1\\NULP_PP\\COMPLEX\\deposits\\src\\logs\\mylogs",
                    10000, 3);

            fileHandler.setFormatter(new SimpleFormatter());
            logger.setLevel(Level.ALL);

            // setting custom filter for FileHandler
            // fileHandler.setFilter(new MyFilter());
            logger.addHandler(fileHandler);

            // for (int i = 0; i < 1000; i++) {
            // // logging messages
            // logger.log(Level.INFO, "Msg" + i);
            // }
            logger.log(Level.CONFIG, "Config data");
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }
}
