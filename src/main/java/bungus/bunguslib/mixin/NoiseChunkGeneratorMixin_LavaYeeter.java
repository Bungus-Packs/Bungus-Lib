package bungus.bunguslib.mixin;

import bungus.bunguslib.config.BungusLibConfig;
import bungus.bunguslib.util.Helpers;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.AquiferSampler;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NoiseChunkGenerator.class)
public abstract class NoiseChunkGeneratorMixin_LavaYeeter {

    @Inject(method="createFluidLevelSampler",at=@At("HEAD"), cancellable = true)
    private static void createFluidLevelSampler(ChunkGeneratorSettings settings, CallbackInfoReturnable<AquiferSampler.FluidLevelSampler> cir) {
        for(BungusLibConfig.LavaAquiferData data:BungusLibConfig.lavaAquiferData) {
            if (Helpers.getServer().getOverworld().getRegistryManager().get(RegistryKeys.CHUNK_GENERATOR_SETTINGS).getKey(settings).get().getValue().getPath().equals(data.generationSettings())){
                int level= data.level();
                int seaLevel= settings.seaLevel();
                AquiferSampler.FluidLevel fluidLevelLava = new AquiferSampler.FluidLevel(level, Blocks.LAVA.getDefaultState());
                AquiferSampler.FluidLevel fluidLevelDefault = new AquiferSampler.FluidLevel(seaLevel, settings.defaultFluid());
                cir.setReturnValue((x,y,z)->y<Math.min(level,seaLevel)?fluidLevelLava:fluidLevelDefault);
            }
        }
    }
}
