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
			}
			resV = MinecraftClient.getInstance().currentScreen.height;
			resH = MinecraftClient.getInstance().currentScreen.width;
		} else {
			if(resV == MinecraftClient.getInstance().currentScreen.height && resH == MinecraftClient.getInstance().currentScreen.width){
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
			} else {
				
				if(!resizeError){
					LOGGER.warn("Please refrain from changing resolutions during startup, as it may cause issues");
					resizeError = true;
				}
				resV = MinecraftClient.getInstance().currentScreen.height;
				resH = MinecraftClient.getInstance().currentScreen.width;
			}
		}
		// Throw An Exception if the Variable hasGameStarted is out of range
		if (!(hasGameStarted >= 1 && hasGameStarted <= 4)) {
			if(noException){
				LOGGER.fatal("An IndexOutOfBoundsException has occurred, byte hasGameStarted: " + hasGameStarted + "  (Expected range: 1-4)");
				Thread.dumpStack();
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
		final boolean worldLoadTime = CONFIG.getOrDefault("world_loading_timer", false);
		if (insanePrecision) {
			LOGGER.debug("Insane Precision is on");
			STARTINGTIME2 = startingTimeNano;
			MathUtil.mathUtilIPConfig = true;
		}
		if(noExceptionConfig){
			noException = true;
		}
		if(worldLoadTime){
			//This Config is still in WIP, currently, it does nothing.
		}
	}

	private static String ltProvider(String filename) {
		return "#Loading timer Config File. For more details on what these options do, go to https://github.com/Blobanium/Loading-Timer/wiki/Config-Guide."
		+ "\ninsane_precision=false"
		+ "\nno_exception=false"
		+ "\nworld_loading_timer=false";
	}
}
