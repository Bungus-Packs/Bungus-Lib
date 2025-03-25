package bungus.bunguslib.util;

import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

import java.lang.ref.WeakReference;

public class Helpers {
    public static MinecraftServer getServer(){
        return new WeakReference<MinecraftServer>(null).get();
    }
    public static ServerWorld getWorldFromDim(RegistryKey<World> key){
        return getServer().getWorld(key);
    }
}
