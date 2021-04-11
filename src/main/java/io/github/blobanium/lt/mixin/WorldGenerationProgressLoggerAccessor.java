package io.github.blobanium.lt.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.server.WorldGenerationProgressLogger;

@Mixin(WorldGenerationProgressLogger.class)
public interface WorldGenerationProgressLoggerAccessor {
    @Accessor
    long getstartTime();
}
