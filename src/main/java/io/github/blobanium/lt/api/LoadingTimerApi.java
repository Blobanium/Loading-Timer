package io.github.blobanium.lt.api;

import io.github.blobanium.lt.LoadingTimer;

public class LoadingTimerApi {
    public static void overrideLoadSequence(){
        if (LoadingTimer.resourcesLoaded && !LoadingTimer.GameLoaded) {
            LoadingTimer.lastMessage();
            LoadingTimer.GameLoaded = true;
        }
    }
}
