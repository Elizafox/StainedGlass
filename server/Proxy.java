package elizacat.mc.StainedGlass.proxy;

import java.io.File;

public class Proxy {
	public static File getConfig() {
		return new File(new File(".", "config"), "StainedGlass.cfg");
	}
}
