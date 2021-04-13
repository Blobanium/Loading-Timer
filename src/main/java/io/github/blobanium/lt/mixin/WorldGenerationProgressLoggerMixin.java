package io.github.blobanium.lt.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.WorldGenerationProgressLogger;

import io.github.blobanium.lt.world.WorldLoadingTime;

@Mixin(WorldGenerationProgressLogger.class)
public class WorldGenerationProgressLoggerMixin {
    @Inject(at = @At("HEAD"), method = "start")
    private void start(CallbackInfo ci){
        WorldLoadingTime.start();
    }

    @Inject(at = @At("HEAD"), method = "stop")
    private void stop(CallbackInfo ci){
        System.out.println("Stop");
    }
}
