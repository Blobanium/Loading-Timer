package net.blobanium.example;

import net.fabricmc.api.ModInitializer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.LiteralText;

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
		if(hasGameStarted == 0) {
			hasGameStarted = 1;
			long timeToLoad = System.currentTimeMillis() - STARTINGTIME2;
			long tTLDeductor = timeToLoad/1000;
			double beforeFinalResult = (timeToLoad - (tTLDeductor * 1000));
			double finalResult = tTLDeductor + (beforeFinalResult / 1000);
			System.out.println("Minecraft took " + finalResult + " seconds to initialize");
			loadMemory = finalResult;
		} else {
			if(hasGameStarted == 1) {
				hasGameStarted = 2;
				long timeToLoad = System.currentTimeMillis() - STARTINGTIME2;
				long tTLDeductor = timeToLoad/1000;
				double beforeFinalResult = (timeToLoad - (tTLDeductor * 1000));
				double finalResult = tTLDeductor + (beforeFinalResult / 1000);
				System.out.println("Minecraft took " + finalResult + " seconds to Fully Load");
				double rawLoadingTime = finalResult - loadMemory;
				if(rawLoadingTime < 0.05){
					System.out.println("That is " + rawLoadingTime + " seconds worth of Raw Loading time, Quite insane isn't it?");
				} else {
					System.out.println("That is " + rawLoadingTime + " seconds worth of Raw Loading time");
				}
			    double roundedVariableOutput1 = finalResult*1000;
				double roundedVariableOutput2 = Math.round(roundedVariableOutput1);
				double roundedVariableOutput3 = roundedVariableOutput2/1000;
				// Send A System toast Once its done loading
				SystemToast toast = SystemToast.create(MinecraftClient.getInstance(), SystemToast.Type.TUTORIAL_HINT,
                new LiteralText("Minecraft startup"), new LiteralText("Minecraft took " + roundedVariableOutput3 + " seconds to load"));
        		MinecraftClient.getInstance().getToastManager().add(toast);
			}
		}
		// Throw An Exception if the Variable hasGameStarted is out of range
		if(!(hasGameStarted == 1 | hasGameStarted == 2)){
			throw new IndexOutOfBoundsException("Variable hasGameStarted is out of range! Value is" + hasGameStarted + " ");
		}
	}
}

