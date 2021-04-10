package io.github.blobanium.lt;

import io.github.blobanium.lt.toast.ToastExecutor;
import io.github.blobanium.lt.util.math.MathUtil;
import io.github.blobanium.lt.util.logging.TimeLogger;
import io.github.blobanium.lt.config.SimpleConfig;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import net.minecraft.client.MinecraftClient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoadingTimer implements ModInitializer {
	public static long timeToLoad;
	public static long startingTime = System.currentTimeMillis();
	public static long startingTimeNano = System.nanoTime();
	private static long STARTINGTIME2 = startingTime;
	public static byte hasGameStarted = 0;
	public static double loadMemory = 0;
	private static boolean isClientFullscreen = false;
	public static double finalResult = 0;
	public static boolean timerDone = false;
	public static final Logger LOGGER = LogManager.getLogger("Loading Timer");
	public static boolean noException = false;

	@Override
	public void onInitialize() {
		LOGGER.info("Loading Timer initialized!");
		finalResult = MathUtil.calculateMain(STARTINGTIME2);
		TimeLogger.loggerMessage(1, finalResult, "");
		loadMemory = finalResult;
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
		} else {
			if (isClientFullscreen && !FabricLoader.getInstance().isModLoaded("architectury")) {
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
			if(noException){
				LOGGER.fatal("An IndexOutOfBoundsException has occurred, byte hasGameStarted: " + hasGameStarted + "  (Expected range: 1-4)");
			} else {
			throw new IndexOutOfBoundsException("Invalid value for byte hasGameStarted has been given: " + hasGameStarted + " ");
			}
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

	public static void configRegister(){
		LOGGER.debug("Registering config..");
		SimpleConfig CONFIG = SimpleConfig.of("LoadingTimer").provider(namespace -> ltProvider(namespace)).request();
		final boolean insanePrecision = CONFIG.getOrDefault("insane_precision", false); 
		final boolean noExceptionConfig = CONFIG.getOrDefault("no_exception", false); 
		if (insanePrecision) {
			LOGGER.debug("Insane Precision is on");
			STARTINGTIME2 = startingTimeNano;
			MathUtil.mathUtilIPConfig = true;
		}
		if(noExceptionConfig){
			noException = true;
		}
	}

	private static String ltProvider(String filename) {
		return "#Loading timer Config File."
		+ "\ninsane_precision=false"
		+ "\nno_exception=false";
	}
}
