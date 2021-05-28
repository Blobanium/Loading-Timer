package io.github.blobanium.lt;

import io.github.blobanium.lt.config.ConfigReader;
import io.github.blobanium.lt.toast.ToastExecutor;
import io.github.blobanium.lt.util.logging.TimeLogger;
import io.github.blobanium.lt.util.math.MathUtil;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoadingTimer implements ModInitializer {
	public static long startingTime = System.currentTimeMillis();
	public static long startingTimeNano = System.nanoTime();
	public static long STARTINGTIME2 = startingTime;
	public static byte hasGameStarted = 0;
	private static double loadMemory = 0;
	public static boolean isClientFullscreen = false;
	public static boolean isClientFullscreen2 = false;
	public static double finalResult = 0;
	public static boolean timerDone = false;
	public static final Logger LOGGER = LogManager.getLogger("Loading Timer");
	public static boolean resizeError = false;
    public static boolean advanceLoopControl = true;
	public static boolean hasResolutionChanged = false;
	public static boolean isLoopActive = false;
	private static double finalResultToast;
	public static boolean resourcesLoaded = false;

	@Override
	public void onInitialize() {
		LOGGER.info("Loading Timer initialized!");
		finalResult = MathUtil.calculateMain(STARTINGTIME2);
		TimeLogger.loggerMessage(1, finalResult, "");
		loadMemory = finalResult;
	}

    public static void load(){
		loopStart();
    }

    public static void lastMessage(){
		TimeLogger.loggerMessage(2, finalResult, "");
		double rawLoadingTime = MathUtil.roundValue(finalResult - loadMemory);
		if (rawLoadingTime < 2.3) {
			TimeLogger.loggerMessage(3, rawLoadingTime, ", your insane.");
		} else {
			TimeLogger.loggerMessage(3, rawLoadingTime, "");
		}
		if(ConfigReader.rawLoadingToast){
			finalResultToast = MathUtil.roundValue(rawLoadingTime);
		} else {
			finalResultToast = MathUtil.roundValue(finalResult);
		}
		ToastExecutor.executeToast("loading-timer.message_text", finalResultToast);
		timerDone = true;
	}

	private static void advanceloop(byte newHasGameStarted){
        hasGameStarted = newHasGameStarted;
        advanceLoopControl = false;
    }

    private static void loopEnd(){
        hasGameStarted = 3;
        lastMessage();
		isLoopActive = false;
        advanceLoopControl = false;
    }

	private static void loopStart(){
		finalResult = MathUtil.calculateMain(STARTINGTIME2);
        advanceLoopControl = true;
	}
}
