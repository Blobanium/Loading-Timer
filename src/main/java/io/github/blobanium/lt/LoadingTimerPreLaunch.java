package io.github.blobanium.lt;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.blobanium.lt.config.ConfigReader;

public class LoadingTimerPreLaunch implements PreLaunchEntrypoint {
    private static final Logger LOGGER = LogManager.getLogger("Loading Timer");

    @Override
    public void onPreLaunch(){
        LoadingTimer.startingTime = System.currentTimeMillis();
        LoadingTimer.startingTimeNano = System.nanoTime();
        checkCompat();
        ConfigReader.configRegister();
    }

    private static void checkCompat(){
        if(!FabricLoader.getInstance().isModLoaded("cloth-config2")){
            
        }
    }
}
