package io.github.blobanium.lt.mixin;

import io.github.blobanium.lt.resource.ResourceLoadingTimer;
import net.minecraft.resource.ReloadableResourceManagerImpl;
import net.minecraft.resource.ResourceReloadMonitor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ReloadableResourceManagerImpl.class)
public class ReloadableResourceManagerImplMixin {
    @Inject(at = @At("HEAD"), method = "beginMonitoredReload")
    private void beginMonitoredReload(CallbackInfoReturnable<ResourceReloadMonitor> ci){
        ResourceLoadingTimer.startTimer();
    }
}
