package net.blobanium.example.config;

import net.blobanium.example.LoadingTimer;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

import net.blobanium.example.config.LTConfig;


public class LTConfigInit {
    public static void init() {
    AutoConfig.register(LTConfig.ModConfig.class, GsonConfigSerializer::new);
    AutoConfig.getConfigHolder(LTConfig.ModConfig.class).getConfig();
    }
}
