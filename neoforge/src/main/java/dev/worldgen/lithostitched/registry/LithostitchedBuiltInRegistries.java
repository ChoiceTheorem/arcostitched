package dev.worldgen.lithostitched.registry;

import com.mojang.serialization.Codec;
import dev.worldgen.lithostitched.LithostitchedCommon;
import dev.worldgen.lithostitched.worldgen.modifier.*;
import dev.worldgen.lithostitched.worldgen.modifier.predicate.ModifierPredicate;
import dev.worldgen.lithostitched.worldgen.surface.LithostitchedSurfaceRules;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.*;
import net.minecraft.core.Registry;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * Built-in registries for Lithostitched on NeoForge.
 */
public final class LithostitchedBuiltInRegistries {
	public static final ResourceKey<Registry<Codec<? extends BiomeModifier>>> BIOME_MODIFIER_SERIALIZERS = ResourceKey.createRegistryKey(new ResourceLocation("neoforge","biome_modifier_serializers"));
	// private static final DeferredRegister<Codec<? extends Modifier>> DEFERRED_MODIFIER_TYPES = DeferredRegister.create(LithostitchedRegistries.MODIFIER_TYPE, LithostitchedCommon.MOD_ID);
	public static final Registry<Codec<? extends Modifier>> MODIFIER_TYPES_REGISTRY = new RegistryBuilder<>(LithostitchedRegistries.MODIFIER_TYPE).sync(true).create();
    // configure the builder if you want, for example with .sync(true) then build the registry
	// public static final Registry<Codec<? extends Modifier>> MODIFIER_TYPE = DEFERRED_MODIFIER_TYPES.makeRegistry((MODIFIER_TYPE_BUILDER) -> new RegistryBuilder<Codec<? extends Modifier>>().hasTags().disableSync().disableSaving());

	// private static final DeferredRegister<Codec<? extends ModifierPredicate>> DEFERRED_MODIFIER_PREDICATES_TYPES = DeferredRegister.create(LithostitchedRegistries.MODIFIER_PREDICATE_TYPE, LithostitchedCommon.MOD_ID);
	// public static final Registry<Codec<? extends ModifierPredicate>> MODIFIER_PREDICATE_TYPE = DEFERRED_MODIFIER_PREDICATES_TYPES.makeRegistry(() -> new RegistryBuilder<Codec<? extends ModifierPredicate>>().hasTags().disableSync().disableSaving());
	public static final Registry<Codec<? extends ModifierPredicate>> MODIFIER_PREDICATE_TYPE_REGISTRY = new RegistryBuilder<>(LithostitchedRegistries.MODIFIER_PREDICATE_TYPE).create();
	// public static final Registry<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS_REGISTRY = new RegistryBuilder<>(NeoForgeRegistries.BIOME_MODIFIER_SERIALIZERS).create();
	public static void init(IEventBus bus) {

		bus.addListener((RegisterEvent event) -> {
			event.register(Registries.MATERIAL_RULE, (helper) -> {
				helper.register("transient_merged", LithostitchedSurfaceRules.TransientMergedRuleSource.CODEC.codec());
			});
		});

		bus.addListener((DataPackRegistryEvent.NewRegistry event) -> {
			event.dataPackRegistry(LithostitchedRegistries.WORLDGEN_MODIFIER, Modifier.CODEC);
		});
		bus.addListener((RegisterEvent event) -> {
			event.register(MODIFIER_TYPES_REGISTRY = new RegistryBuilder<>(LithostitchedRegistries.MODIFIER_TYPE).sync(true).create());
		});
		bus.addListener((RegisterEvent event) -> event.register(MODIFIER_PREDICATE_TYPE_REGISTRY));
		// registerNeoforgeBiomeModifiers((name, codec) -> BIOME_MODIFIER_SERIALIZERS.register(name, () -> codec));
		// BIOME_MODIFIER_SERIALIZERS.register(bus);

		// LithostitchedCommon.registerCommonModifiers((name, codec) -> MODIFIER_TYPES_REGISTRY.register(name, () -> codec));
		// registerNeoforgeModifiers((name, codec) -> MODIFIER_TYPES_REGISTRY.register(name, () -> codec));
		// MODIFIER_TYPES_REGISTRY.register(bus);

		// LithostitchedCommon.registerCommonModifierPredicates((name, codec) -> DEFERRED_MODIFIER_PREDICATES_TYPES.register(name, () -> codec));
		// DEFERRED_MODIFIER_PREDICATES_TYPES.register(bus);

	}

	public static void registerNeoforgeModifiers(BiConsumer<String, Codec<? extends Modifier>> consumer) {
		consumer.accept("add_biome_spawns", AddBiomeSpawnsModifier.CODEC);
		consumer.accept("add_features", AddFeaturesModifier.CODEC);
		consumer.accept("remove_biome_spawns", RemoveBiomeSpawnsModifier.CODEC);
		consumer.accept("remove_features", RemoveFeaturesModifier.CODEC);
		consumer.accept("replace_climate", ReplaceClimateModifier.CODEC);
		consumer.accept("replace_effects", ReplaceEffectsModifier.CODEC);
	}

	public static void registerNeoforgeBiomeModifiers(BiConsumer<String, Codec<? extends BiomeModifier>> consumer) {
		consumer.accept("replace_climate", LithostitchedNeoforgeBiomeModifiers.ReplaceClimateBiomeModifier.CODEC);
		consumer.accept("replace_effects", LithostitchedNeoforgeBiomeModifiers.ReplaceEffectsBiomeModifier.CODEC);
	}
}
