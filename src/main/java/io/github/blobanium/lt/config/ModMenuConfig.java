package io.github.blobanium.lt.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.gui.ModsScreen;

import io.github.prospector.modmenu.api.ModMenuApi;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;

import net.fabricmc.loader.api.FabricLoader;

import net.minecraft.text.TranslatableText;
import net.minecraft.client.gui.screen.Screen;


public class ModMenuConfig implements ModMenuApi {

    private static ConfigScreenFactory<?> CONFIG = FabricLoader.getInstance().isModLoaded("cloth-config2")
    ? new LTClothConfig()
    : parent -> null;
    
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
            // Return the screen here with the one you created from Cloth Config Builder;
            return CONFIG;
    }

    private static class LTClothConfig implements ConfigScreenFactory<Screen> {
    

        private static String currentValue = "test";

        @Override
        public Screen create(Screen parent) {

            final ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(new TranslatableText("loading-timer.config"));
    
            builder.setSavingRunnable(() -> {
                // Serialise the config into the config file. This will be called last after all variables are updated.
            });
        
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        
            ConfigCategory general = builder.getOrCreateCategory(new TranslatableText("category.examplemod.general"));
        
                general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("loading-timer.config.insane_precision"), ConfigReader.insanePrecision)
                .setDefaultValue(false) // Recommended: Used when user click "Reset"
                .setTooltip(new TranslatableText("This option is awesome!")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> ConfigReader.insanePrecision = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config
        
                general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("loading-timer.config.world_load_time"), ConfigReader.worldLoadTime)
                .setDefaultValue(false) // Recommended: Used when user click "Reset"
                .setTooltip(new TranslatableText("This option is awesome!")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> ConfigReader.worldLoadTime = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config

                general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("loading-timer.config.resource_load_notif"), ConfigReader.resourceLoadNotif)
                .setDefaultValue(false) // Recommended: Used when user click "Reset"
                .setTooltip(new TranslatableText("This option is awesome!")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> ConfigReader.worldLoadTime = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config

                general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("loading-timer.config.resource_load_percent"), ConfigReader.resourceLoadPercent)
                .setDefaultValue(false) // Recommended: Used when user click "Reset"
                .setTooltip(new TranslatableText("This option is awesome!")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> ConfigReader.worldLoadTime = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config

                general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("loading-timer.config.raw_loading_toast"), ConfigReader.rawLoadingToast)
                .setDefaultValue(false) // Recommended: Used when user click "Reset"
                .setTooltip(new TranslatableText("This option is awesome!")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> ConfigReader.worldLoadTime = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config

            return builder.build();
        }
    }
}
