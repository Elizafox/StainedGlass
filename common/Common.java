package elizacat.mc.StainedGlass.common;

import java.util.logging.Level;

import cpw.mods.fml.common.FMLCommonHandler;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;

import net.minecraft.src.forge.Configuration;
import net.minecraft.src.forge.Property;

// Isn't this magical...
import elizacat.mc.StainedGlass.proxy.BlockStainedPane;

public class Common {
	public static final String NAME = "StainedGlass";
	public static final String VERSION = "0.2dev";

	public static final String BLOCKTEX_PNG = "elizacat/mc/StainedGlass/block.png";

	public static Block stainedGlass;
	public static Block stainedGlassGlowing;
	public static Block stainedPane;

	public static final int defaultGlassID = 3130;
	public static final int defaultGlassGlowingID = 3131;
	public static final int defaultGlassPaneID = 3132;

	public static int stainedGlassID;
	public static int stainedGlassGlowingID;
	public static int stainedPaneID;

	// Used for client, kept here for symbol accessibility reasons.
	public static int renderID;

	private static int getBlockID(Configuration config, String name, String comment, int defaultID) {
		Property prop;

		prop = config.getOrCreateBlockIdProperty(name, defaultID);
		prop.comment = comment;
		return prop.getInt();
	}

	public static void loadConfig(Configuration config) {
		try {
			Property prop;

			config.load();

			stainedGlassID = getBlockID(config, "stainedGlass", "Normal stained glass", defaultGlassID);
			stainedGlassGlowingID = getBlockID(config, "stainedGlowing", "Glowing stained glass", defaultGlassGlowingID);
			stainedPaneID = getBlockID(config, "stainedPane", "Stained pane", defaultGlassPaneID);
		}
		catch (Exception e) {
			FMLCommonHandler.instance().getFMLLogger().log(Level.SEVERE, "mod_StainedGlass encountered a problem loading it's configuration", e);
		}
		finally {
			config.save();
		}
	}

	public static void initBlocks() {
		stainedGlass = (new BlockStainedGlass(stainedGlassID, 0, Material.glass, false));
		stainedGlassGlowing = (new BlockStainedGlassGlowing(stainedGlassGlowingID, 0, Material.glass, false));
		stainedPane = (new BlockStainedPane(stainedPaneID, 0, 16, Material.glass));

		ModLoader.registerBlock(stainedGlass, ItemStainedGlass.class);
		ModLoader.registerBlock(stainedGlassGlowing, ItemStainedGlassGlowing.class);
		ModLoader.registerBlock(stainedPane, ItemStainedPane.class);
		
		for(int i = 0 ; i < 16 ; i++ ) {
			createStainedGlass(i);
			createStainedGlassGlowing(i);
			createStainedGlassPane(i);
		}
	}

	public static void createStainedGlass(int i) {
		Object[] recipe = new Object[]{ Block.glass, new ItemStack(Item.dyePowder, 1, i) };
		String name = String.format("%s glass", getColour(i));

		ModLoader.addShapelessRecipe(new ItemStack(stainedGlass, 1, ~i & 15), recipe);
		ModLoader.addLocalization(buildLocalizationString("glassBlock", i) + ".name", name);
	}

	public static void createStainedGlassGlowing(int i) {
		Object[] recipe;
		String name = String.format("%s glowing glass", getColour(i));

		recipe = new Object[]{ stainedGlass, Item.lightStoneDust };
		ModLoader.addShapelessRecipe(new ItemStack(stainedGlassGlowing, 1, ~i & 15), recipe);

		recipe = new Object[]{ Block.glass, new ItemStack(Item.dyePowder, 1, i), Item.lightStoneDust };
		ModLoader.addShapelessRecipe(new ItemStack(stainedGlassGlowing, 1, ~i & 15), recipe);

		ModLoader.addLocalization(buildLocalizationString("glassGlowing", i) + ".name", name);
	}

	public static void createStainedGlassPane(int i) {
		Object[] recipe = new Object[]{ Block.thinGlass, new ItemStack(Item.dyePowder, 1, i) };
		String name = String.format("%s glass pane", getColour(i));

		ModLoader.addShapelessRecipe(new ItemStack(stainedPane, 1, ~i & 15), recipe);
		ModLoader.addLocalization(buildLocalizationString("glassPane", i) + ".name", name);
	}  

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

