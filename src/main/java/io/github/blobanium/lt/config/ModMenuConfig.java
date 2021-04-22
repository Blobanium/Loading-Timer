package io.github.blobanium.lt.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;

import io.github.prospector.modmenu.api.ModMenuApi;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;
import net.minecraft.client.gui.screen.Screen;

public class ModMenuConfig implements ModMenuApi {
    
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            // Return the screen here with the one you created from Cloth Config Builder
            LTClothConfig.configBuilder();
            return (Screen) LTClothConfig.configBuilder();
        };
    }

    private static class LTClothConfig {
    
        private static String currentValue = "test";
    
        public static Screen configBuilder(){
        ConfigBuilder builder = ConfigBuilder.create().setTitle(new TranslatableText("loading-timer.config"));
    
        builder.setSavingRunnable(() -> {
            // Serialise the config into the config file. This will be called last after all variables are updated.
        });
    
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
    
        ConfigCategory general = builder.getOrCreateCategory(new TranslatableText("category.examplemod.general"));
    
        general.addEntry(entryBuilder.startStrField(new TranslatableText("option.examplemod.optionA"), currentValue)
            .setDefaultValue("This is the default value") // Recommended: Used when user click "Reset"
            .setTooltip(new TranslatableText("This option is awesome!")) // Optional: Shown when the user hover over this option
            .setSaveConsumer(newValue -> currentValue = newValue) // Recommended: Called when user save the config
            .build()); // Builds the option entry for cloth config
    
            Screen screen = builder.build();
            MinecraftClient.getInstance().openScreen(screen);
        return builder.build();
        }
    }
}
