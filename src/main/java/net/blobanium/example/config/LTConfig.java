package net.blobanium.example.config;

import net.blobanium.example.LoadingTimer;

import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.ConfigHolder;

/* ATTENTION!
// This Is The Example from the autoconfig wiki by shedaniel
// I Will change this once I Get the config file working
// Once i get the config working i will change this class
*/

public class LTConfig {
    @Config(name = "loading-timer")
    public class ModConfig implements ConfigData {
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
