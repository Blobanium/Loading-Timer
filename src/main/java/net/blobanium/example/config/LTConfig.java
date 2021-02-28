package net.blobanium.example.config;

import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.ConfigData;

public class LTConfig {
    @Config(name = "modid")
class ModConfig implements ConfigData {
    boolean toggleA = true;
    boolean toggleB = false;
    
    @ConfigEntry.Gui.CollapsibleObject
    InnerStuff stuff = new InnerStuff();
    
    @ConfigEntry.Gui.Excluded
    InnerStuff invisibleStuff = new InnerStuff();
    
    class InnerStuff {
        int a = 0;
        int b = 1;
    }
}
}
