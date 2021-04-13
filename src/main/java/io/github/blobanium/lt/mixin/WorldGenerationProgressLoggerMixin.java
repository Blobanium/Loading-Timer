package io.github.blobanium.lt.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.WorldGenerationProgressLogger;

import io.github.blobanium.lt.LoadingTimer;
import io.github.blobanium.lt.util.math.MathUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mixin(WorldGenerationProgressLogger.class)
public class WorldGenerationProgressLoggerMixin {

    public static long worldStartingTime;
    public static final Logger LOGGER = LogManager.getLogger("Loading Timer");

    @Inject(at = @At("HEAD"), method = "start")
    private void start(CallbackInfo ci){
        if(LoadingTimer.worldLoadTime){
            if(LoadingTimer.insanePrecision){
                worldStartingTime = System.nanoTime();
            } else {
                worldStartingTime = System.currentTimeMillis();
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "stop")
    private void stop(CallbackInfo ci){
        double worldTime = MathUtil.calculateMain(worldStartingTime);
        LOGGER.info(worldTime);
    }
}
