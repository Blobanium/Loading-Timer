package io.github.blobanium.lt.toast;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.TranslatableText;

public class ToastExecutor {
    private static String easterEggTranslatable = "loading-timer.title";

    public static void executeToast(String translatableDescription, double toastTimeValue){
        calculateEasterEgg();
        SystemToast toast = SystemToast.create(MinecraftClient.getInstance(), SystemToast.Type.TUTORIAL_HINT, new TranslatableText(easterEggTranslatable), new TranslatableText(translatableDescription, toastTimeValue));
        MinecraftClient.getInstance().getToastManager().add(toast);
    }

    private static void calculateEasterEgg(){
        short min = 1;
        short max = 1000;
        long random_double = Math.round(Math.random() * (max - min + 1) + min);
        if(random_double == 100){
            easterEggTranslatable = "loading-timer.title_ee";
        } else {
            easterEggTranslatable = "loading-timer.title";
        }
    }
}
