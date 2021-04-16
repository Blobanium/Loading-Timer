package io.github.blobanium.lt.mixin;

import net.minecraft.resource.ReloadableResourceManagerImpl;
import net.minecraft.resource.ResourceReloadMonitor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mixin(ReloadableResourceManagerImpl.class)
public class ReloadableResourceManagerImplMixin {
    private static final Logger LOGGER = LogManager.getLogger("Minecraft");

    @Inject(at = @At("HEAD"), method = "beginMonitoredReload")
    private void beginMonitoredReload(CallbackInfoReturnable<ResourceReloadMonitor> ci){
        LOGGER.info("test");
    }
}
