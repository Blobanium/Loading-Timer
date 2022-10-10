package io.github.blobanium.lt.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import io.github.blobanium.lt.LoadingTimerPreLaunch;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;


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

        @Override
        public Screen create(Screen parent) {

            final ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("loading-timer.config"));

            // Serialise the config into the config file. This will be called last after all variables are updated.
            builder.setSavingRunnable(ConfigReader::refreshConfig);
        
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        
            ConfigCategory general = builder.getOrCreateCategory(Text.translatable("category.examplemod.general"));
        
                general.addEntry(entryBuilder.startBooleanToggle(Text.translatable("loading-timer.config.insane_precision"), ConfigReader.insanePrecision)
                .setDefaultValue(false)
                .setTooltip(Text.translatable("loading-timer.config.insane_precision.description"))
                .setSaveConsumer(newValue -> ConfigReader.insanePrecision = newValue)
                .build());

                if(LoadingTimerPreLaunch.isConflictingKsysisLoaded){
                    general.addEntry(entryBuilder.startBooleanToggle(Text.translatable("loading-timer.config.world_load_time"), ConfigReader.worldLoadTime)
                            .setDefaultValue(false)
                            .setTooltip(Text.translatable("loading-timer.config.world_load_time.conflict.ksyxiswarning"))
                            .setSaveConsumer(newValue -> ConfigReader.worldLoadTime = newValue)
                            .build());
                } else {
                    general.addEntry(entryBuilder.startBooleanToggle(Text.translatable("loading-timer.config.world_load_time"), ConfigReader.worldLoadTime)
                            .setDefaultValue(false)
                            .setTooltip(Text.translatable("loading-timer.config.world_load_time.description"))
                            .setSaveConsumer(newValue -> ConfigReader.worldLoadTime = newValue)
                            .build());
                }

                general.addEntry(entryBuilder.startBooleanToggle(Text.translatable("loading-timer.config.resource_load_notif"), ConfigReader.resourceLoadNotif)
                .setDefaultValue(false)
                .setTooltip(Text.translatable("loading-timer.config.resource_load_notif.description"))
                .setSaveConsumer(newValue -> ConfigReader.resourceLoadNotif = newValue)
                .build());

                general.addEntry(entryBuilder.startBooleanToggle(Text.translatable("loading-timer.config.resource_load_notif_override"), ConfigReader.resourceLoadNotifStartupOverride)
                .setDefaultValue(false)
                .setTooltip(Text.translatable("loading-timer.config.resource_load_notif_override.description"))
                .setSaveConsumer(newValue -> ConfigReader.resourceLoadNotifStartupOverride = newValue)
                .build());

                general.addEntry(entryBuilder.startBooleanToggle(Text.translatable("loading-timer.config.resource_load_percent"), ConfigReader.resourceLoadPercent)
                .setDefaultValue(false)
                .setTooltip(Text.translatable("loading-timer.config.resource_load_percent.description"))
                .setSaveConsumer(newValue -> ConfigReader.resourceLoadPercent = newValue)
                .build());

                general.addEntry(entryBuilder.startBooleanToggle(Text.translatable("loading-timer.config.raw_loading_toast"), ConfigReader.rawLoadingToast)
                .setDefaultValue(false)
                .setTooltip(Text.translatable("loading-timer.config.raw_loading_toast.description"))
                .setSaveConsumer(newValue -> ConfigReader.rawLoadingToast = newValue)
                .build());

            return builder.build();
        }
    }
}
