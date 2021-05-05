package io.github.blobanium.lt.mixin;

import net.minecraft.resource.ReloadableResourceManagerImpl;
import net.minecraft.resource.ResourceReload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.blobanium.lt.resource.ResourceLoadingTimer;

@Mixin(ReloadableResourceManagerImpl.class)
public class ReloadableResourceManagerImplMixin {
    @Inject(at = @At("HEAD"), method = "beginMonitoredReload")
    private void beginMonitoredReload(CallbackInfoReturnable<ResourceReload> ci){
        ResourceLoadingTimer.startTimer();
    }
}
