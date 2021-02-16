package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;

public class LoadingTimer implements ModInitializer {
	public static long timeToLoad;
	public static final long startingTime = System.currentTimeMillis();
	private static final long STARTINGTIME2 = startingTime;
	public static int hasGameStarted = 0;
	
	@Override
	public void onInitialize() {
		System.out.println("Loading Timer Initialized");
	}

	public static void load() {
		// The "Load" Procedure Runs twice, one for initialization and the other for loading completely
		// This is controlled by the variable called "hasGameStarted"
		if(hasGameStarted == 0) {
			hasGameStarted = 1;
			final long timeToLoad = System.currentTimeMillis() - STARTINGTIME2;
			final long tTLDeductor = timeToLoad/1000;
			final double beforeFinalResult = (timeToLoad - (tTLDeductor * 1000));
			final double finalResult = tTLDeductor + (beforeFinalResult / 1000);
			System.out.println("Minecraft took " + finalResult + " seconds to initialize");
		} else {
			if(hasGameStarted == 1) {
				hasGameStarted = 2;
				final long timeToLoad = System.currentTimeMillis() - STARTINGTIME2;
				final long tTLDeductor = timeToLoad/1000;
				final double beforeFinalResult = (timeToLoad - (tTLDeductor * 1000));
				final double finalResult = tTLDeductor + (beforeFinalResult / 1000);
				System.out.println("Minecraft took " + finalResult + " seconds to Fully Load");
			}
		}
		// Throw An Exception if the Variable hasGameStarted is out of range
		if(!(hasGameStarted == 1 | hasGameStarted == 2)){
			throw new IndexOutOfBoundsException("Variable hasGameStarted is out of range! Value of hasGameStarted is" + hasGameStarted + " ");
		}
	}
}

