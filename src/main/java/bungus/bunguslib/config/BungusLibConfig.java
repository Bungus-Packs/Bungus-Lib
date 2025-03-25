package bungus.bunguslib.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.World;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import netscape.javascript.JSObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BungusLibConfig {
    private static final File CONFIG = new File("config/bunguslib_config.json");

    //generatorSettings is the name of the json containing router
    public record LavaAquiferData(String generationSettings, int level) {}
    
    public static final List<LavaAquiferData> lavaAquiferData=new ArrayList<>();


    public static void loadConfig() {
        if (!CONFIG.exists()) {
            CONFIG.getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(CONFIG)) {
                JsonObject data=new JsonObject();
                
                JsonArray lavaAquiferData=new JsonArray();
                JsonObject sunlessSeaLavaYeeter=new JsonObject();
                sunlessSeaLavaYeeter.addProperty("generatorSettings","sunlesssea_noise");
                sunlessSeaLavaYeeter.addProperty("level",-1000);
                lavaAquiferData.add(sunlessSeaLavaYeeter);
                
                data.add("lavaAquiferYeeterData",lavaAquiferData);
                
                new GsonBuilder().setPrettyPrinting().create().toJson(data,writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        try(FileReader reader=new FileReader(CONFIG)){
            JsonObject config=new Gson().fromJson(reader,JsonObject.class);
            
            lavaAquiferData.clear();
            JsonArray lavaAquiferYeeterData=config.get("lavaAquiferYeeterData").getAsJsonArray();
            for(int i=0;i<lavaAquiferYeeterData.size();i++){
                JsonObject lavaAquiferYeeter=lavaAquiferYeeterData.get(i).getAsJsonObject();
                String generatorSettings=lavaAquiferYeeter.get("generatorSettings").getAsString();
                int level=lavaAquiferYeeter.get("level").getAsInt();
                lavaAquiferData.add(new LavaAquiferData(generatorSettings,level));
            }
            
            
            
            
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void a() {
        ChunkGenerator a;
        //World a;
        //a.getRegistryManager().get(RegistryKeys.CHUNK_GENERATOR_SETTINGS).getKey();
    }
}
