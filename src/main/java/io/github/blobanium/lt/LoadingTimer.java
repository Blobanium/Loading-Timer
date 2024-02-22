package io.github.blobanium.lt;

import io.github.blobanium.lt.timestamp.TimestampManager;
import io.github.blobanium.lt.timestamp.Timestamps;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoadingTimer implements ModInitializer, PreLaunchEntrypoint {
	public static final Logger LOGGER = LogManager.getLogger("Loading Timer");

	@Override
	public void onInitialize() {
		LOGGER.info("Loading Timer initialized!");
	}

	@Override
	public void onPreLaunch() {
		TimestampManager.createTimeStamp(Timestamps.GAME_LOAD);
	}

	public static void finish(){
		System.out.println("time=" + TimestampManager.calculateTime(Timestamps.GAME_LOAD, false));
	}
}
