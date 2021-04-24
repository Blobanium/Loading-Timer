package io.github.blobanium.lt;

import io.github.blobanium.lt.config.ConfigReader;
import io.github.blobanium.lt.toast.ToastExecutor;
import io.github.blobanium.lt.util.math.MathUtil;
import io.github.blobanium.lt.util.logging.TimeLogger;
import net.fabricmc.api.ModInitializer;

import net.minecraft.client.MinecraftClient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoadingTimer implements ModInitializer {
	public static long startingTime = System.currentTimeMillis();
	public static long startingTimeNano = System.nanoTime();
	public static long STARTINGTIME2 = startingTime;
	private static byte hasGameStarted = 0;
	private static double loadMemory = 0;
	private static boolean isClientFullscreen = false;
	private static boolean isClientFullscreen2 = false;
	private static double finalResult = 0;
	public static boolean timerDone = false;
	private static final Logger LOGGER = LogManager.getLogger("Loading Timer");
	static int resV = 0;
	static int resH = 0;
	static boolean resizeError = false;

	@Override
	public void onInitialize() {
		LOGGER.info("Loading Timer initialized!");
		finalResult = MathUtil.calculateMain(STARTINGTIME2);
		TimeLogger.loggerMessage(1, finalResult, "");
		loadMemory = finalResult;
	}

	public static void load() {
		finalResult = MathUtil.calculateMain(STARTINGTIME2);
		if (hasGameStarted == 0) {
			hasGameStarted = 1;
			if (MinecraftClient.getInstance().options.fullscreen) {
				isClientFullscreen = true;
				isClientFullscreen2 = true;
			}
			LOGGER.debug("hasGameStarted=1");
			getDimensions();
		} else {
			if(resV == MinecraftClient.getInstance().currentScreen.height && resH == MinecraftClient.getInstance().currentScreen.width){
				if (isClientFullscreen) {
					if(hasGameStarted == 1){
						hasGameStarted = 2;
						LOGGER.debug("hasGameStarted=2");
					} else {
						if(hasGameStarted == 2){
							hasGameStarted = 3;
							LOGGER.debug("hasGameStarted=3");
							lastMessage();
						}
					}
				} else {
					if (hasGameStarted == 1) {
						hasGameStarted = 3;
						lastMessage();
					}
				}
			} else {
				if(!resizeError){
					if(isClientFullscreen2){
						isClientFullscreen2 = false;
					} else {
					LOGGER.warn("Please refrain from changing resolutions during startup, as it may cause issues.");
					resizeError = true;
					}
				}
				getDimensions();
			}
		}
		if (!(hasGameStarted >= 1 && hasGameStarted <= 3)) {
			LOGGER.fatal("An IndexOutOfBoundsException has occurred, byte hasGameStarted: " + hasGameStarted + "  (Expected range: 1-3)");
			Thread.dumpStack();
		}
	}

	private static void lastMessage(){
		TimeLogger.loggerMessage(2, finalResult, "");
		double rawLoadingTime = MathUtil.roundValue(finalResult - loadMemory);
		if (rawLoadingTime < 2.3) {
			TimeLogger.loggerMessage(3, rawLoadingTime, ", your insane.");
		} else {
			TimeLogger.loggerMessage(3, rawLoadingTime, "");
		}
		if(ConfigReader.rawLoadingToast){
			double finalResultToast = MathUtil.roundValue(rawLoadingTime);
			// Send A System toast Once its done loading
			ToastExecutor.executeToast(finalResultToast, 1);
		} else {
			double finalResultToast = MathUtil.roundValue(finalResult);
			ToastExecutor.executeToast(finalResultToast, 1);
		}
		timerDone = true;
	}

	private static void getDimensions(){
		resV = MinecraftClient.getInstance().currentScreen.height;
		resH = MinecraftClient.getInstance().currentScreen.width;
	}
}
