package io.github.blobanium.lt;

import io.github.blobanium.lt.toast.ToastExecutor;
import io.github.blobanium.lt.util.math.MathUtil;
import io.github.blobanium.lt.util.logging.TimeLogger;
import io.github.blobanium.lt.config.SimpleConfig;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;

public class LoadingTimer implements ModInitializer {
	public static long timeToLoad;
	public static final long startingTime = System.currentTimeMillis();
	public static final long startingTimeNano = System.nanoTime();
	private static long STARTINGTIME2 = startingTime;
	public static byte hasGameStarted = 0;
	public static double loadMemory = 0;
	private static boolean isClientFullscreen = false;

	@Override
	public void onInitialize() {
		SimpleConfig CONFIG = SimpleConfig.of("LoadingTimer").provider(this::ltProvider).request();
		final boolean insanePrecision = CONFIG.getOrDefault("insane_precision", false);
		if (insanePrecision) {
			STARTINGTIME2 = startingTimeNano;
			MathUtil.mathUtilIPConfig = true;
		}
		System.out.println("Loading Timer initialized!");
	}

	private String ltProvider(String filename) {
		return "#Loading timer Config File."
				+ "\ninsane_precision=false #Makes the result of the loading time way more precise.";
	}

	public static void load() {
		// The "Load" Procedure Runs twice, one for initialization and the other for
		// loading completely
		// This is controlled by the variable called "hasGameStarted"
		double finalResult = MathUtil.calculateMain(STARTINGTIME2);
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
							TimeLogger.loggerMessage(2, finalResult, "");
							double rawLoadingTime = finalResult - loadMemory;
							if (rawLoadingTime < 0.05) {
								TimeLogger.loggerMessage(3, rawLoadingTime, ", quite insane isn't it?");
							} else {
								TimeLogger.loggerMessage(3, rawLoadingTime, "");
							}
							double finalResultToast = MathUtil.toastCalc(finalResult);
							// Send A System toast Once its done loading
							ToastExecutor.executeToast(finalResultToast);
						}
					}
				}
			} else {
				if (hasGameStarted == 1) {
					hasGameStarted = 2;
					TimeLogger.loggerMessage(2, finalResult, "");
					double rawLoadingTime = finalResult - loadMemory;
					if (rawLoadingTime < 0.05) {
						TimeLogger.loggerMessage(3, rawLoadingTime, ", quite insane isn't it?");
					} else {
						TimeLogger.loggerMessage(3, rawLoadingTime, "");
					}
					double finalResultToast = MathUtil.toastCalc(finalResult);
					// Send A System toast Once its done loading
					ToastExecutor.executeToast(finalResultToast);
				}
			}
		}
		// Throw An Exception if the Variable hasGameStarted is out of range
		if (!(hasGameStarted >= 1 && hasGameStarted <= 4)) {
			throw new IndexOutOfBoundsException(
					"Invalid value for byte hasGameStarted has been given: " + hasGameStarted + " ");
		}
	}
}
