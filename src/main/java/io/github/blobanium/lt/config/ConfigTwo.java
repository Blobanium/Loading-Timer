package io.github.blobanium.lt.config;

import java.io.File;
import java.util.List;

import com.oroarmor.config.*;

import net.fabricmc.loader.api.FabricLoader;

public class ConfigTwo extends Config {

    public ConfigTwo(List<ConfigItemGroup> configs, File configFile, String id) {
        super(configs, new File(FabricLoader.getInstance().getConfigDir().toFile(), "loading_timer_config.json"), "loading_timer_config");
        //TODO Auto-generated constructor stub
    }

    

}
