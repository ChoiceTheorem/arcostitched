package dev.worldgen.lithostitched;

import dev.worldgen.lithostitched.registry.LithostitchedBuiltInRegistries;
//import net.neoforged.fml.config.ModConfig;
import net.neoforged.bus.api.IEventBus;
//import net.neoforged.fml.ModLoadingContext;
//import net.minecraft.core.registries.BuiltInRegistries;
//import net.minecraft.core.registries.Registries;
import net.neoforged.fml.common.Mod;


/**
 * Mod class for Lithostitched on Forge.
 */
@Mod(LithostitchedCommon.MOD_ID)
public final class LithostitchedNeoforge {

	public LithostitchedNeoforge(IEventBus modEventBus) {
		LithostitchedCommon.init();
		LithostitchedBuiltInRegistries.init(modEventBus);
	}

}
