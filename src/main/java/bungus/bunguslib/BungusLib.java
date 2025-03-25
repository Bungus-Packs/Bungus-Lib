package bungus.bunguslib;

import bungus.bunguslib.config.BungusLibConfig;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BungusLib implements ModInitializer {
    public static final String MOD_ID="bunguslib";
    public static final Logger LOGGER= LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        LOGGER.info("Initializing "+MOD_ID);
        
        LOGGER.info("Loading configs for "+MOD_ID);
        BungusLibConfig.loadConfig();
    }
}
