package io.github.blobanium.lt.mixin;

import io.github.blobanium.lt.LoadingTimer;
import net.minecraft.client.gui.screen.TitleScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class LoadModMixin {
	private static final Logger LOGGER = LogManager.getLogger("Loading Timer");

	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
		LOGGER.debug("TitleScreen#init called, calling LoadingTimer.load");
		LoadingTimer.load();
	}
}
