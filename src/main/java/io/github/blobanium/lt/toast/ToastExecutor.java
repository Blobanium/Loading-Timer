package io.github.blobanium.lt.toast;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.LiteralText;

public class ToastExecutor {
    public static void executeToast(double toastTimeValue){
        //Shhhhh!!
        short min = 1;
        short max = 1000;
        long random_double = Math.round(Math.random() * (max - min + 1) + min);
        if(random_double == 100){
            SystemToast toast = SystemToast.create(MinecraftClient.getInstance(), SystemToast.Type.TUTORIAL_HINT,
            new LiteralText("Laoding Timer"), new LiteralText("Minecraft took " + toastTimeValue + " seconds to load"));
            MinecraftClient.getInstance().getToastManager().add(toast);
        } else {
            SystemToast toast = SystemToast.create(MinecraftClient.getInstance(), SystemToast.Type.TUTORIAL_HINT,
            new LiteralText("Loading Timer"), new LiteralText("Minecraft took " + toastTimeValue + " seconds to load"));
            MinecraftClient.getInstance().getToastManager().add(toast);
        }
    }
}
