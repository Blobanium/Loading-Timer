package io.github.blobanium.lt.toast;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.TranslatableText;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.blobanium.lt.LoadingTimer;

public class ToastExecutor {
    public static final Logger LOGGER = LogManager.getLogger("Loading Timer");

    public static void executeToast(double toastTimeValue, int messageToastSelector){
        if(messageToastSelector == 1){
            LOGGER.debug("Showing Toast Notification");
            //Shhhhh!!
            short min = 1;
            short max = 1000;
            long random_double = Math.round(Math.random() * (max - min + 1) + min);
            if(random_double == 100){
                SystemToast toast = SystemToast.create(MinecraftClient.getInstance(), SystemToast.Type.TUTORIAL_HINT,
                new TranslatableText("loading-timer.title_ee"), new TranslatableText("loading-timer.message_text", toastTimeValue));
                MinecraftClient.getInstance().getToastManager().add(toast);
            } else {
                SystemToast toast = SystemToast.create(MinecraftClient.getInstance(), SystemToast.Type.TUTORIAL_HINT,
                new TranslatableText("loading-timer.title"), new TranslatableText("loading-timer.message_text", toastTimeValue));
                MinecraftClient.getInstance().getToastManager().add(toast);
            }
        }

        if(messageToastSelector == 2){
            SystemToast toast = SystemToast.create(MinecraftClient.getInstance(), SystemToast.Type.TUTORIAL_HINT,
            new TranslatableText("loading-timer.title"), new TranslatableText("loading-timer.world_message_text", toastTimeValue));
            MinecraftClient.getInstance().getToastManager().add(toast);
        }

        if(!(messageToastSelector >= 1 && messageToastSelector <= 2)){
            if(LoadingTimer.noException){
                LOGGER.fatal("An IndexOutOfBoundsException has occurred, int messageToastSelector: " + messageToastSelector + "  (Expected range: 1-2)");
                Thread.dumpStack();
            }
            throw new IndexOutOfBoundsException("Invalid value for int messageToastSelector has been given: " + messageToastSelector + " ");
        }
    }
}
