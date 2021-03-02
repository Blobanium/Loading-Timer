package net.blobanium.example;

import net.blobanium.example.toast.ToastExecutor;
import net.blobanium.example.util.math.MathUtil;
import net.blobanium.example.util.logging.TimeLogger;
import net.blobanium.example.config.LTConfig;

import me.shedaniel.autoconfig.AutoConfig;

import net.fabricmc.api.ModInitializer;

public class LoadingTimer implements ModInitializer {
	public static long timeToLoad;
	public static final long startingTime = System.currentTimeMillis();
	private static final long STARTINGTIME2 = startingTime;
	public static byte hasGameStarted = 0;
	public static double loadMemory = 0;
	
	@Override
	public void onInitialize() {
		LTConfig.init();
		initConfig();
		System.out.println("Loading Timer Initialized");
	}

	public static void load() {
		// The "Load" Procedure Runs twice, one for initialization and the other for loading completely
		// This is controlled by the variable called "hasGameStarted"
		double finalResult = MathUtil.calculateMain(STARTINGTIME2);
		if(hasGameStarted == 0) {
			hasGameStarted = 1;
			TimeLogger.loggerMessage(1, finalResult, "");
			loadMemory = finalResult;
		} else {
			if(hasGameStarted == 1) {
				hasGameStarted = 2;
				TimeLogger.loggerMessage(2, finalResult, "");
				double rawLoadingTime = finalResult - loadMemory;
				if(rawLoadingTime < 0.05){
				TimeLogger.loggerMessage(3, rawLoadingTime, ", Quite insane isn't it?");
				} else {
				TimeLogger.loggerMessage(3, rawLoadingTime, "");
				}
				double finalResultToast = MathUtil.toastCalc(finalResult);
				// Send A System toast Once its done loading
				ToastExecutor.executeToast(finalResultToast);
			}
		}
		// Throw An Exception if the Variable hasGameStarted is out of range
		if(!(hasGameStarted == 1 | hasGameStarted == 2)){
			throw new IndexOutOfBoundsException("Invalid Value for byte hasGameStarted has been given: " + hasGameStarted + " ");
		}
	}

	public void initConfig(){
		LTConfig.ModConfig config = AutoConfig.getConfigHolder(LTConfig.ModConfig.class).getConfig();
		if(config.toggleA){
			System.out.println("toggleA Is On");
		} else {
			System.out.println("toggleA Is Off");
		}
	}
}

