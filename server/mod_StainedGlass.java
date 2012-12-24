package elizacat.mc.StainedGlass.proxy;

import java.io.File;

import net.minecraft.src.forge.Configuration;
import net.minecraft.src.forge.NetworkMod;

import elizacat.mc.StainedGlass.common.Common;

public class mod_StainedGlass extends NetworkMod {
	public Configuration config = new Configuration(new File(new File(".", "config"), "StainedGlass.cfg"));
	
	public void load() {
		// Load config
		Common.loadConfig(config);
		Common.initBlocks();
	}

	public String getVersion() {
		return Common.VERSION;
	}
	
	public boolean clientSideRequired() {
		return true;
	}
	
	public boolean serverSideRequired() {
		return false;
	}
}
