package io.github.blobanium.lt;

import io.github.blobanium.lt.toast.ToastExecutor;
import io.github.blobanium.lt.util.math.MathUtil;
import io.github.blobanium.lt.util.logging.TimeLogger;

import net.fabricmc.api.ModInitializer;

public class LoadingTimer implements ModInitializer {
	public static long timeToLoad;
	public static final long startingTime = System.currentTimeMillis();
	public static final long startingTimeNano = System.nanoTime();
	private static long STARTINGTIME2 = startingTime;
	public static byte hasGameStarted = 0;
	public static double loadMemory = 0;
	
	@Override
	public void onInitialize() {
		System.out.println("Loading Timer initialized!");
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
				TimeLogger.loggerMessage(3, rawLoadingTime, ", quite insane isn't it?");
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
			throw new IndexOutOfBoundsException("Invalid value for byte hasGameStarted has been given: " + hasGameStarted + " ");
		}
	}
}

