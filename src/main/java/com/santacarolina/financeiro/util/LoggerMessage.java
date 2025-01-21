package com.santacarolina.financeiro.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerMessage {

    private static final Logger logger = LogManager.getLogger();

    public static void generateMessage(String message) {
        logger.info("\n" + message);
    }

}
