package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;

public class LoadingTimer implements ModInitializer {
	public static long timeToLoad;
	public static final long startingTime = System.currentTimeMillis();
	private static final long STARTINGTIME2 = startingTime;
	
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		System.out.println("Loading Timer Initialized");
	}

	public static void load() {
		final long timeToLoad = System.currentTimeMillis() - STARTINGTIME2;
		final long tTLDeductor = timeToLoad/1000;
		final double wTF = (timeToLoad - (tTLDeductor * 1000));
		final double finalResult = tTLDeductor + (wTF / 1000);
		System.out.println("Minecraft took " + finalResult + " seconds to load");
	}
}

