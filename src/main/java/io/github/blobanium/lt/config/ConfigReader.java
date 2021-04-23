package io.github.blobanium.lt.config;

import io.github.blobanium.lt.LoadingTimer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigReader {
	public static boolean insanePrecision = false;
    public static boolean rawLoadingToast = false;
    public static boolean resourceLoadNotifStartupOverride = false;
    public static boolean resourceLoadNotif = false;
    public static boolean resourceLoadPercent = false;
    public static boolean worldLoadTime = false;

    public static final Logger LOGGER = LogManager.getLogger("Loading Timer");

    public static void configRegister(){
    	LOGGER.debug("Registering config..");
    	SimpleConfig CONFIG = SimpleConfig.of("LoadingTimer").provider(namespace -> ConfigReader.ltProvider(namespace)).request();
    	final boolean insanePrecisionConfig = CONFIG.getOrDefault("insane_precision", insanePrecision); 
    	final boolean worldLoadTimeConfig = CONFIG.getOrDefault("world_loading_timer", worldLoadTime);
    	final boolean resourceLoadPercentConfig = CONFIG.getOrDefault("show_resource_load_percent", resourceLoadPercent);
    	final boolean resourceLoadNotifConfig = CONFIG.getOrDefault("resource_loading_notification", resourceLoadNotif);
    	final boolean resourceLoadNotifStartupOverrideConfig = CONFIG.getOrDefault("resource_loading_notif_override", resourceLoadNotifStartupOverride);
    	final boolean rawLoadingToastConfig = CONFIG.getOrDefault("raw_loading_toast", rawLoadingToast);
    	if (insanePrecisionConfig) {
    		LOGGER.debug("Insane Precision is on");
    		LoadingTimer.STARTINGTIME2 = LoadingTimer.startingTimeNano;
    		insanePrecision = true;
    	}
    	if(worldLoadTimeConfig){
    		worldLoadTime = true;
    	}
    	if(resourceLoadPercentConfig){
    		resourceLoadPercent = true;
    	}
    	if(resourceLoadNotifConfig){
    		resourceLoadNotif = true;
    	}
    	if(resourceLoadNotifStartupOverrideConfig){
    		resourceLoadNotifStartupOverride = true;
    		if(!resourceLoadNotif){
    			LOGGER.warn("the resource_loading_notif_override config won't work unless resource_loading_notification is set to true!!");
    		}
    	}
    	if(rawLoadingToastConfig){
    		rawLoadingToast = true;
    	}
    }

    private static String ltProvider(String filename) {
    	return "#Loading timer Config File. For more details on what these options do, go to https://github.com/Blobanium/Loading-Timer/wiki/Config-Guide."
    	+ "\ninsane_precision=" + insanePrecision
    	+ "\nworld_loading_timer=" + worldLoadTime
    	+ "\nshow_resource_load_percent=" + resourceLoadPercent
    	+ "\nresource_loading_notification=" + resourceLoadNotif
    	+ "\nresource_loading_notif_override=" + resourceLoadNotifStartupOverride
    	+ "\nraw_loading_toast=" + rawLoadingToast;
    }
}
