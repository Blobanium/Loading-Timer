package io.github.blobanium.lt.mixin;

import io.github.blobanium.lt.config.ConfigReader;
import io.github.blobanium.lt.toast.ToastExecutor;
import io.github.blobanium.lt.util.math.MathUtil;
import net.minecraft.server.WorldGenerationProgressLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldGenerationProgressLogger.class)
public class WorldGenerationProgressLoggerMixin {

    private static long worldStartingTime;
    private static final Logger LOGGER = LogManager.getLogger("Loading Timer");

    @Inject(at = @At("HEAD"), method = "start")
    private void start(CallbackInfo ci){
            if(ConfigReader.insanePrecision){
                worldStartingTime = System.nanoTime();
            } else {
                worldStartingTime = System.currentTimeMillis();
            }
    }

    /**
     * @reason Replace the "Time elapsed" log message in order to allow support for insane precision (And for other future purposes).
     * @author Blobanium
     */
    @Overwrite
    public void stop(){
        double worldTime = MathUtil.calculateMain(worldStartingTime);
        double worldTimeRounded = MathUtil.roundValue(worldTime);
        if(ConfigReader.insanePrecision){
            double worldTimeRoundMillis = MathUtil.roundValue(worldTime * 1000);
            LOGGER.info("Time elapsed: " + worldTimeRoundMillis + " ms");
        } else {
            long worldTimeRoundMillis = Double.valueOf(MathUtil.roundValue(worldTime * 1000)).longValue();
            LOGGER.info("Time elapsed: " + worldTimeRoundMillis + " ms");
        }
        if(ConfigReader.worldLoadTime){
            ToastExecutor.executeToast("loading-timer.world_message_text", worldTimeRounded);
        }
    }
}
