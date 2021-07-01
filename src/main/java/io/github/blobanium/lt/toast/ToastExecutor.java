package io.github.blobanium.lt.toast;

import io.github.blobanium.lt.LoadingTimer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.TranslatableText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ToastExecutor {
    private static String easterEggTranslatable;
    private static boolean lazydfu = FabricLoader.getInstance().isModLoaded("lazydfu");
    private static boolean smoothboot = FabricLoader.getInstance().isModLoaded("smoothboot");
    private static boolean dashloader = FabricLoader.getInstance().isModLoaded("dashloader");
    public static final Logger LOGGER = LogManager.getLogger("Loading Timer");

    public static void executeToast(String translatableDescription, double toastTimeValue){
        easterEggLaodingTimer();
        easterEggMods();
        SystemToast toast = SystemToast.create(MinecraftClient.getInstance(), SystemToast.Type.TUTORIAL_HINT, new TranslatableText(easterEggTranslatable), new TranslatableText(translatableDescription, toastTimeValue));
        MinecraftClient.getInstance().getToastManager().add(toast);
    }

    private static void easterEggLaodingTimer(){
        short min = 1;
        short max = 1000;
        long random_double = Math.round(Math.random() * (max - min + 1) + min);
        if(random_double == 100){
            easterEggTranslatable = "loading-timer.easteregg.title";
            LOGGER.info("Showing Easter egg!");
        } else {
            easterEggTranslatable = "loading-timer.title";
        }
    }

    private static void easterEggMods(){
        if(lazydfu || smoothboot || dashloader){
            if(lazydfu && smoothboot && dashloader){
                if(LoadingTimer.rawLoadingTime < 5) {
                    easterEggTranslatable = "loading-timer.easteregg.insane";
                } else {
                    LOGGER.info("Insanity Approaches..");
                }
            }
        }
    }
}
