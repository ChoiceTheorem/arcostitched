package dev.worldgen.lithostitched.platform;

import dev.worldgen.lithostitched.platform.services.IPlatformHelper;
import net.neoforged.fml.ModList;

public class NeoForgePlatformHelper implements IPlatformHelper {
    @Override
    public boolean isModLoaded(String id) {
        return ModList.get().isLoaded(id);
    }
}
// TO DO:port all forge feature to neoforge