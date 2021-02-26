package net.blobanium.example;

import net.blobanium.example.toast.ToastExecutor;
import net.blobanium.example.util.MathUtil;
import net.fabricmc.api.ModInitializer;

public class LoadingTimer implements ModInitializer {
	public static long timeToLoad;
	public static final long startingTime = System.currentTimeMillis();
	private static final long STARTINGTIME2 = startingTime;
	public static byte hasGameStarted = 0;
	public static double loadMemory = 0;
	
	@Override
	public void onInitialize() {
		System.out.println("Loading Timer Initialized");
	}

	public static void load() {
		// The "Load" Procedure Runs twice, one for initialization and the other for loading completely
		// This is controlled by the variable called "hasGameStarted"
		double finalResult = MathUtil.calculateMain(STARTINGTIME2);
		if(hasGameStarted == 0) {
			hasGameStarted = 1;
			System.out.println("Minecraft took " + finalResult + " seconds to initialize");
			loadMemory = finalResult;
		} else {
			if(hasGameStarted == 1) {
				hasGameStarted = 2;
				System.out.println("Minecraft took " + finalResult + " seconds to Fully Load");
				double rawLoadingTime = finalResult - loadMemory;
				if(rawLoadingTime < 0.05){
					System.out.println("That is " + rawLoadingTime + " seconds worth of Raw Loading time, Quite insane isn't it?");
				} else {
					System.out.println("That is " + rawLoadingTime + " seconds worth of Raw Loading time");
				}
				double finalResultToast = MathUtil.toastCalc(finalResult);
				// Send A System toast Once its done loading
				ToastExecutor.executeToast(finalResultToast);
			}
		}
		// Throw An Exception if the Variable hasGameStarted is out of range
		if(!(hasGameStarted == 1 | hasGameStarted == 2)){
			throw new IndexOutOfBoundsException("Variable hasGameStarted is out of range! Value is" + hasGameStarted + " ");
		}
	}
}

