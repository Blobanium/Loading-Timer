package io.github.blobanium.lt;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoadingTimerPreLaunch implements PreLaunchEntrypoint {
    public static final Logger LOGGER = LogManager.getLogger("Loading Timer");

    @Override
    public void onPreLaunch(){
        LoadingTimer.startingTime = System.currentTimeMillis();
        LoadingTimer.startingTimeNano = System.nanoTime();
        checkCompat();
        LoadingTimer.configRegister();
    }

    public static void checkCompat(){
        if(!FabricLoader.getInstance().isModLoaded("fabric")){
            LOGGER.warn("Fabric API was not detected");
            LOGGER.warn("Translations will not work at all.");
        }
    }
}
