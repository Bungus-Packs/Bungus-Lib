package bungus.bunguslib.mixin;

import bungus.bunguslib.util.Helpers;
import com.mojang.datafixers.DataFixer;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.SaveLoader;
import net.minecraft.server.WorldGenerationProgressListenerFactory;
import net.minecraft.util.ApiServices;
import net.minecraft.world.level.storage.LevelStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.ref.WeakReference;
import java.net.Proxy;
@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin_RefGetter {
    @Inject(method = "<init>",at = @At("RETURN"))
    private void onConstruct(Thread thread, LevelStorage.Session levelStorageAccess, ResourcePackManager packRepository, SaveLoader worldStem, Proxy proxy, DataFixer dataFixer, ApiServices services, WorldGenerationProgressListenerFactory chunkProgressListenerFactory, CallbackInfo ci) {
        Helpers.server= new WeakReference<>((MinecraftServer)((Object)this)).get();
    }
}
