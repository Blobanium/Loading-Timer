package io.github.blobanium.lt.util.resource;

import io.github.blobanium.lt.LoadingTimer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResourceLoadingTimer {
    public static long resourceStartingTime;
    private static final Logger LOGGER = LogManager.getLogger("Loading Timer");

    public static void startTimer(){
        if(LoadingTimer.insanePrecision){
            resourceStartingTime = System.nanoTime();
        } else {
            resourceStartingTime = System.currentTimeMillis();
        }
    }

    public static void stopTimer(){
        if(LoadingTimer.insanePrecision){
            LOGGER.info(System.nanoTime() - resourceStartingTime);
        } else {
            LOGGER.info(System.currentTimeMillis() - resourceStartingTime);
        }
    }
}
