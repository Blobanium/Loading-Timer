package io.github.blobanium.lt.resource;

import io.github.blobanium.lt.LoadingTimer;
import io.github.blobanium.lt.config.ConfigReader;
import io.github.blobanium.lt.toast.ToastExecutor;
import io.github.blobanium.lt.util.math.MathUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerAddress;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResourceLoadingTimer {
    private static long resourceStartingTime;
    private static final Logger LOGGER = LogManager.getLogger("Loading Timer");
    private static double resourceResult;
    public static boolean resourcesLoaded = false;

    public static void startTimer(){
        resourcesLoaded = false;
        if(ConfigReader.insanePrecision){
            resourceStartingTime = System.nanoTime();
        } else {
            resourceStartingTime = System.currentTimeMillis();
        }
    }

    public static void stopTimer(){
        if(!resourcesLoaded){
            resourceResult = MathUtil.roundValue(MathUtil.calculateMain(resourceStartingTime));
            LOGGER.info("Resource Loading Time: " + resourceResult + " seconds");
            if(ConfigReader.resourceLoadNotif){
                if(LoadingTimer.timerDone || ConfigReader.resourceLoadNotifStartupOverride){
                    ToastExecutor.executeToast("loading-timer.resource_loading_text", resourceResult);
                }
            }
            resourcesLoaded = true;
        }
    }
}
