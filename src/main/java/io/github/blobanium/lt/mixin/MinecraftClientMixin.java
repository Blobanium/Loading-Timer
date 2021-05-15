package io.github.blobanium.lt.mixin;

import net.minecraft.client.MinecraftClient;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.blobanium.lt.LoadingTimer;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(at = @At("HEAD"), method = "onResolutionChanged")
    private void onResolutionChanged(CallbackInfo info){
        LoadingTimer.hasResolutionChanged = true;
    }
}
