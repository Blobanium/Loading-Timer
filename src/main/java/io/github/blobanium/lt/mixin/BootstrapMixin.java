package io.github.blobanium.lt.mixin;

import io.github.blobanium.lt.LoadingTimer;
import net.minecraft.Bootstrap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Bootstrap.class)
public class BootstrapMixin {
    @Inject(at = @At("HEAD"), method = "initialize()V")
    private static void initialize(CallbackInfo info){
    }
}
