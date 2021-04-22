package io.github.blobanium.lt.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;

import io.github.prospector.modmenu.api.ModMenuApi;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

public class ModMenuConfig implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            // Return the screen here with the one you created from Cloth Config Builder
            return (Screen) LTClothConfig.configBuilder();
        };
    }
}
