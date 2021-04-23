package io.github.blobanium.lt.util.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.blobanium.lt.config.ConfigReader;

public class TimeLogger {
    private static final Logger LOGGER = LogManager.getLogger("Loading Timer");

    public static void loggerMessage(int messageSelector, double variable, String extraText){
        if(messageSelector == 1) {LOGGER.info("Minecraft took " + variable + " seconds to initialize.");}
        if(messageSelector == 2) {LOGGER.info("Minecraft took " + variable + " seconds to fully load.");}
        if(messageSelector == 3) {LOGGER.info("That is " + variable + " seconds worth of raw loading time" + extraText);}
        if(!(messageSelector >= 1 && messageSelector <= 3)){
                LOGGER.fatal("An IndexOutOfBoundsException has occurred, int messageSelector: " + messageSelector + "  (Expected range: 1-3)");
                Thread.dumpStack();
        }
    }
}
