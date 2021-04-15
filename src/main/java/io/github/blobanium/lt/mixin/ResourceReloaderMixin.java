package io.github.blobanium.lt.mixin;

import net.minecraft.resource.ResourceReloader;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.blobanium.lt.LoadingTimer;
import io.github.blobanium.lt.util.math.MathUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mixin(ResourceReloader.class)
public class ResourceReloaderMixin {
    private static final Logger LOGGER = LogManager.getLogger("Loading Timer");
    private float lastReading = 0;

    @Inject(at = @At("TAIL"), method = "getProgress")
    private float getProgress(CallbackInfoReturnable<Float> ci){
        float loadPrecent = (ci.getReturnValueF() * 100);
        if((!(lastReading == ci.getReturnValueF())) && LoadingTimer.resourceLoadPercent){
            LOGGER.info("Resource loading progress: " + MathUtil.roundValue(loadPrecent) + " %");
            lastReading = ci.getReturnValueF();
        }
        return ci.getReturnValueF();
    }
}
