package io.github.blobanium.lt.mixin;

import io.github.blobanium.lt.LoadingTimer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.blobanium.lt.config.ConfigReader;
import io.github.blobanium.lt.resource.ResourceLoadingTimer;
import io.github.blobanium.lt.util.math.MathUtil;
import net.minecraft.resource.SimpleResourceReload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mixin(SimpleResourceReload.class)
public class SimpleResourceReloadMixin {
    private static final Logger LOGGER = LogManager.getLogger("Loading Timer");
    private float lastReading = 0;

    @Inject(at = @At("TAIL"), method = "getProgress")
    private float getProgress(CallbackInfoReturnable<Float> ci){
        float loadPercent = (ci.getReturnValueF() * 100);
        if(!(lastReading == ci.getReturnValueF())){
            if(ConfigReader.resourceLoadPercent){
            LOGGER.info("Resource loading progress: " + MathUtil.roundValue(loadPercent) + " %");
            }
            lastReading = ci.getReturnValueF();
            if(loadPercent == 100.0F){
                ResourceLoadingTimer.stopTimer();
                LoadingTimer.resourcesLoaded = true;
            }
        }
        return ci.getReturnValueF();
    }
}
