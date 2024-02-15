package io.github.blobanium.lt;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoadingTimer implements ModInitializer {
	public static long startingTime = System.currentTimeMillis();
	public static final Logger LOGGER = LogManager.getLogger("Loading Timer");

	@Override
	public void onInitialize() {
		LOGGER.info("Loading Timer initialized!");
	}
}
