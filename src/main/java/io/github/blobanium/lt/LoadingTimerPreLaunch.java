package io.github.blobanium.lt;

import io.github.blobanium.lt.config.ConfigReader;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
            if(FabricLoader.getInstance().isModLoaded("modmenu")){
                LOGGER.warn("Cloth Config Was Not Detected!!\nBecause you have modmenu, The config menu (in the mods section for this mod) for this mod will be inaccessible!!");
            } else {
                LOGGER.warn("Cloth Config Was Not Detected!!\nBecause you don't have modmenu, this shouldn't be a problem, but its highly recommended that you install it.");
            }
        }
    }
}
