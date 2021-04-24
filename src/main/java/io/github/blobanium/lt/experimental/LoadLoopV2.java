package io.github.blobanium.lt.experimental;

import io.github.blobanium.lt.LoadingTimer;
import io.github.blobanium.lt.util.math.MathUtil;
import net.minecraft.client.MinecraftClient;

public class LoadLoopV2 {
    public static boolean advanceLoop = true;

    public static void load2(){
    	LoadingTimer.finalResult = MathUtil.calculateMain(LoadingTimer.STARTINGTIME2);
        advanceLoop = true;

    	if (LoadingTimer.hasGameStarted == 0 && advanceLoop) {
    		LoadingTimer.hasGameStarted = 1;
    		if (MinecraftClient.getInstance().options.fullscreen) {
    			LoadingTimer.isClientFullscreen = true;
    			LoadingTimer.isClientFullscreen2 = true;
    		}
    		LoadingTimer.getDimensions();
            advanceLoop = false;
    	}
    
    	if(LoadingTimer.resV == MinecraftClient.getInstance().currentScreen.height && LoadingTimer.resH == MinecraftClient.getInstance().currentScreen.width){
    		if(LoadingTimer.hasGameStarted == 1 && advanceLoop){
    			if(LoadingTimer.isClientFullscreen){
    				LoadingTimer.hasGameStarted = 2;
                    advanceLoop = false;
    			} else {
    				LoadingTimer.hasGameStarted = 3;
    				LoadingTimer.lastMessage();
                    advanceLoop = false;
    			}
    		}
    
    		if(LoadingTimer.hasGameStarted == 2 && advanceLoop){
    			LoadingTimer.hasGameStarted = 3;
    			LoadingTimer.lastMessage();
                advanceLoop = false;
    		}
    
    	} else {
    		if(!LoadingTimer.resizeError){
    			if(LoadingTimer.isClientFullscreen2){
    				LoadingTimer.isClientFullscreen2 = false;
    			} else {
    			LoadingTimer.LOGGER.warn("Please refrain from changing resolutions during startup, as it may cause issues.");
    			LoadingTimer.resizeError = true;
    			}
    		}
    		LoadingTimer.getDimensions();
    	}
    }
}
