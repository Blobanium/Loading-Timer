package io.github.blobanium.lt;

import io.github.blobanium.lt.toast.ToastExecutor;
import io.github.blobanium.lt.util.math.MathUtil;
import io.github.blobanium.lt.util.logging.DebugLogger;
import io.github.blobanium.lt.util.logging.TimeLogger;
import io.github.blobanium.lt.config.SimpleConfig;

import net.fabricmc.api.ModInitializer;

import net.minecraft.client.MinecraftClient;

public class LoadingTimer implements ModInitializer {
	public static long timeToLoad;
	public static long startingTime = System.currentTimeMillis();
	public static long startingTimeNano = System.nanoTime();
	private static long STARTINGTIME2 = startingTime;
	public static byte hasGameStarted = 0;
	public static double loadMemory = 0;
	private static boolean isClientFullscreen = false;
	public static double finalResult = 0;
	private static boolean manualFullscreenError = false;
	public static boolean timerDone = false;

	@Override
	public void onInitialize() {
		configRegister();
		System.out.println("Loading Timer initialized!");
	}

	public static void load() {
		// The "Load" Procedure Runs twice, one for initialization and the other for
		// loading completely
		// This is controlled by the variable called "hasGameStarted"
		finalResult = MathUtil.calculateMain(STARTINGTIME2);
		if (hasGameStarted == 0) {
			hasGameStarted = 1;
			if (MinecraftClient.getInstance().options.fullscreen) {
				isClientFullscreen = true;
			}
			TimeLogger.loggerMessage(1, finalResult, "");
			loadMemory = finalResult;
		} else {
			if (isClientFullscreen) {
				if(hasGameStarted == 1){
					hasGameStarted = 2;
				} else {
					if(hasGameStarted == 2){
						hasGameStarted = 3;
					} else {
						if(hasGameStarted == 3){
							hasGameStarted = 4;
							lastMessage();
						}
					}
				}
			} else {
				if (hasGameStarted == 1) {
					hasGameStarted = 4;
					lastMessage();
				}
			}
		}
		// Throw An Exception if the Variable hasGameStarted is out of range
		if (!(hasGameStarted >= 1 && hasGameStarted <= 4)) {
			throw new IndexOutOfBoundsException("Invalid value for byte hasGameStarted has been given: " + hasGameStarted + " ");
		}
	}

	public static void lastMessage(){
		TimeLogger.loggerMessage(2, finalResult, "");
		double rawLoadingTime = MathUtil.roundValue(finalResult - loadMemory);
		if (rawLoadingTime < 0.05) {
			TimeLogger.loggerMessage(3, rawLoadingTime, ", quite insane isn't it?");
		} else {
			TimeLogger.loggerMessage(3, rawLoadingTime, "");
		}
		double finalResultToast = MathUtil.roundValue(finalResult);
		// Send A System toast Once its done loading
		ToastExecutor.executeToast(finalResultToast, 1);
	}

	public void configRegister(){
		SimpleConfig CONFIG = SimpleConfig.of("LoadingTimer").provider(this::ltProvider).request();
		final boolean insanePrecision = CONFIG.getOrDefault("insane_precision", false);
		final boolean debugOption = CONFIG.getOrDefault("debug", false);
		if (insanePrecision) {
			STARTINGTIME2 = startingTimeNano;
			MathUtil.mathUtilIPConfig = true;
		}
		if (debugOption){
			DebugLogger.debug = true;
		}
	}

	private String ltProvider(String filename) {
		return "#Loading timer Config File."
				+ "\ninsane_precision=false #Makes the result of the loading time way more precise."
				+ "\ndebug=false #Enables Debug output for loading timer (WIP and not working yet)";
	}
}
