package net.blobanium.example.config;

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
        public boolean toggleA = true;
        public boolean toggleB = false;
    }

    public static void init() {
        try {
            registerConfig();
        } catch (RuntimeException e) {
            System.out.println("Config Not Found! Creating The Config..");
            createFile();
            registerConfig();
            System.out.println("A Config File should be generated, however at this state it is not working yet");
        }
    }

    public static void createFile() {
        File file = new File(UtilsImpl.getConfigFolder() + "/loadingtimer.json");
        System.out.println(file);
        System.out.println(UtilsImpl.getConfigFolder());
        try{
            FileWriter myWriter = new FileWriter(UtilsImpl.getConfigFolder() + "/loadingtimer.json");
            myWriter.write("{ }");
            myWriter.close();
        } catch (IOException e) {
            System.err.println("Something caused Config Creation to fail");
            e.printStackTrace();
        }
    }

    public static void registerConfig() {
        ConfigHolder<ModConfig> holder = AutoConfig.register(ModConfig.class, (GsonConfigSerializer::new));
        System.out.println("holder: " + holder);
    }

    public static void loadConfig() {
    }
}