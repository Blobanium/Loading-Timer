package io.github.blobanium.lt.experimental;

import io.github.blobanium.lt.LoadingTimer;
import io.github.blobanium.lt.util.math.MathUtil;
import net.minecraft.client.MinecraftClient;

public class LoadLoopV2 {
    public static void load2(){
    	LoadingTimer.finalResult = MathUtil.calculateMain(LoadingTimer.STARTINGTIME2);
    
    	if (LoadingTimer.hasGameStarted == 0) {
    		LoadingTimer.hasGameStarted = 1;
    		if (MinecraftClient.getInstance().options.fullscreen) {
    			LoadingTimer.isClientFullscreen = true;
    			LoadingTimer.isClientFullscreen2 = true;
    		}
    		LoadingTimer.getDimensions();
    	}
    
    	if(LoadingTimer.resV == MinecraftClient.getInstance().currentScreen.height && LoadingTimer.resH == MinecraftClient.getInstance().currentScreen.width){
    		if(LoadingTimer.hasGameStarted == 1){
    			if(LoadingTimer.isClientFullscreen){
    				LoadingTimer.hasGameStarted = 2;
    			} else {
    				LoadingTimer.hasGameStarted = 3;
    				LoadingTimer.lastMessage();
    			}
    		}
    
    		if(LoadingTimer.hasGameStarted == 2){
    			LoadingTimer.hasGameStarted = 3;
    			LoadingTimer.lastMessage();
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
