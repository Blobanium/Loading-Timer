package io.github.blobanium.lt.util.resource;

import io.github.blobanium.lt.LoadingTimer;
import io.github.blobanium.lt.util.math.MathUtil;

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
        LOGGER.info("Resource Loading Time: " + MathUtil.calculateMain(resourceStartingTime) + " seconds");
    }
}
