package io.github.blobanium.lt;

import net.fabricmc.api.DedicatedServerModInitializer;

public class LoadingTimerServer implements DedicatedServerModInitializer {

    @Override
    public void onInitializeServer() {
        System.out.println("Loading Timer doesn't work properly on the server just yet");
    }
}
