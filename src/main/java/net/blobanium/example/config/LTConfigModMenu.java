package net.blobanium.example.config;

import me.shedaniel.autoconfig.AutoConfig;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

import com.terraformersmc.modmenu.api.ModMenuApi;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;

public class LTConfigModMenu {
    @Environment(EnvType.CLIENT)
    public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AutoConfig.getConfigScreen(LTConfig.ModConfig.class, parent).get();
    }
}
}
