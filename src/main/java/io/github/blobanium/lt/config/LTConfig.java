package io.github.blobanium.lt.config;

import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.autoconfig.util.fabric.UtilsImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LTConfig {

    @Config(name = "loadingtimer")
    public class ModConfig implements ConfigData {
        public boolean insanePrecision = false;
    }

    public static void init() {
        try {
            registerConfig();
        } catch (RuntimeException e) {
            System.out.println("Configuration file not found! Creating...");
            createFile();
            registerConfig();
            System.out.println("Configuration file generated!");
        }
    }

    public static void createFile() {
        File file = new File(UtilsImpl.getConfigFolder() + "/loadingtimer.json");
        try{
            FileWriter myWriter = new FileWriter(UtilsImpl.getConfigFolder() + "/loadingtimer.json");
            myWriter.write("{ }");
            myWriter.close();
        } catch (IOException e) {
            System.err.println("Something caused configuration creation to fail. Please report this to our GitHub issues page.");
            e.printStackTrace();
        }
    }

    public static void registerConfig() {
        ConfigHolder<ModConfig> holder = AutoConfig.register(ModConfig.class, (GsonConfigSerializer::new));
    }
}
