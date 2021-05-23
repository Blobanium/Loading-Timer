package io.github.blobanium.lt;

import net.fabricmc.api.DedicatedServerModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoadingTimerServer implements DedicatedServerModInitializer {

    private static final Logger LOGGER = LogManager.getLogger("Loading Timer");

    @Override
    public void onInitializeServer() {
        LOGGER.warn("Loading Timer doesn't work properly on the server.");
    }
}
