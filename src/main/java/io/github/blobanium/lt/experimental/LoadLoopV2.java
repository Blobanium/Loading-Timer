package io.github.blobanium.lt.experimental;

import io.github.blobanium.lt.LoadingTimer;
import io.github.blobanium.lt.util.math.MathUtil;
import net.minecraft.client.MinecraftClient;

public class LoadLoopV2 {
    public static boolean advanceLoopControl = true;

    public static void load2(){
    	LoadingTimer.finalResult = MathUtil.calculateMain(LoadingTimer.STARTINGTIME2);
        advanceLoopControl = true;

    	if (LoadingTimer.hasGameStarted == 0 && advanceLoopControl) {
    		if (MinecraftClient.getInstance().options.fullscreen) {
    			LoadingTimer.isClientFullscreen = true;
    			LoadingTimer.isClientFullscreen2 = true;
    		}
    		LoadingTimer.getDimensions();
            advanceloop((byte) 1);
    	}
    
    	if(LoadingTimer.resV == MinecraftClient.getInstance().currentScreen.height && LoadingTimer.resH == MinecraftClient.getInstance().currentScreen.width){
    		if(LoadingTimer.hasGameStarted == 1 && advanceLoopControl){
    			if(LoadingTimer.isClientFullscreen){
                    advanceloop((byte) 2);
    			} else {
                    loopEnd();
    			}
    		}
    
    		if(LoadingTimer.hasGameStarted == 2 && advanceLoopControl){
                loopEnd();
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

    private static void loopEnd(){
        LoadingTimer.hasGameStarted = 3;
        LoadingTimer.lastMessage();
        advanceLoopControl = false;
    }

    private static void advanceloop(byte newHasGameStarted){
        LoadingTimer.hasGameStarted = newHasGameStarted;
        advanceLoopControl = false;
    }
}
