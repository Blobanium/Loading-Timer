package io.github.blobanium.lt.world;

import io.github.blobanium.lt.LoadingTimer;
import io.github.blobanium.lt.util.math.MathUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorldLoadingTime {

    public static long worldStartingTime;

    public static final Logger LOGGER = LogManager.getLogger("Loading Timer");

    public static void start(){
        if(LoadingTimer.worldLoadTime){
            if(LoadingTimer.insanePrecision){
                worldStartingTime = System.nanoTime();
            } else {
                worldStartingTime = System.currentTimeMillis();
            }
        }
    }


    public static void stop(){
        double worldTime = MathUtil.calculateMain(worldStartingTime);
        LOGGER.info(worldTime);
    }
}
