package net.blobanium.example.toast;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.LiteralText;

public class ToastExecutor {
    public static void executeToast(double toastTimeValue){
        SystemToast toast = SystemToast.create(MinecraftClient.getInstance(), SystemToast.Type.TUTORIAL_HINT,
        new LiteralText("Minecraft startup"), new LiteralText("Minecraft took " + toastTimeValue + " seconds to load"));
        MinecraftClient.getInstance().getToastManager().add(toast);
    }
}
