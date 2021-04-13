package io.github.blobanium.lt.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.server.WorldGenerationProgressLogger;

//how to convert a accessor into a mixin
@Mixin(WorldGenerationProgressLogger.class)
public class WorldGenerationProgressLoggerMixin {
    @Inject(at = @At("HEAD"), method = "start")
    private void start(){
        System.out.println("Start");
    }

    @Inject(at = @At("HEAD"), method = "stop")
    private void stop(){
        System.out.println("Stop");
    }
}
