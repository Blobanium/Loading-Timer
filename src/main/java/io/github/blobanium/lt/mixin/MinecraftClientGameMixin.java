package io.github.blobanium.lt.mixin;

import net.minecraft.client.MinecraftClientGame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClientGame.class)
public class MinecraftClientGameMixin {
    @Inject(at = @At("HEAD"), method = "onStartGameSession ()V")
    private void onStartGameSession(CallbackInfo info) {
        //im gonna put something here soon
    }
}
