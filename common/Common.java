package elizacat.mc.StainedGlass.common;

import net.minecraft.src.Block;
import net.minecraft.src.Item;

public class Common {
	public static final String NAME = "StainedGlass";
	public static final String VERSION = "0.1dev";

	public static final String BLOCKTEX_PNG = "elizacat/mc/StainedGlass/block.png";

	public static Block stainedGlass;
	public static Block stainedGlassGlowing;
	public static Block stainedPane;

	public static int stainedGlassID;
	public static int stainedGlassGlowingID;
	public static int stainedPaneID;

        private static final String[] colours =  
		{"White", "Orange", "Magenta", "Light blue", "Yellow", "Lime", 
		 "Pink", "Gray", "Light gray", "Cyan", "Purple", "Blue", 
		 "Brown", "Green", "Period red", "Black"};

	public static final String buildLocalizationString(String type, int colour) {
		StringBuilder sb = new StringBuilder();

		sb.append("elizacat.stainedglass.");
		if (colour != -1)
			sb.append(getColour(colour).replaceAll("\\s",""));
		sb.append(type);
		return sb.toString();
	}

	public static final String buildLocalizationString(String type) {
		return buildLocalizationString(type, -1);
	}

	public static final String getColour(int colour) {
		return colours[colour];
	}
}

