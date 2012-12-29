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
	public static final String VERSION = "0.3dev";

	public static final String BLOCKTEX_PNG = "elizacat/mc/StainedGlass/block.png";

	public static Block stainedGlass;
	public static Block stainedGlassGlowing;
	public static Block stainedPane;
	public static Block smoothGlass;
	public static Block smoothGlassGlowing;
	public static Block smoothPane;

	public static final int defaultGlassID = 3130;
	public static final int defaultGlassGlowingID = 3131;
	public static final int defaultGlassPaneID = 3132;
	public static final int defaultSmoothGlassID = 3133;
	public static final int defaultSmoothGlassGlowingID = 3134;
	public static final int defaultSmoothGlassPaneID = 3135;

	public static int stainedGlassID;
	public static int stainedGlassGlowingID;
	public static int stainedPaneID;
	public static int smoothGlassID;
	public static int smoothGlassGlowingID;
	public static int smoothPaneID;

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

			smoothGlassID = getBlockID(config, "smoothGlass", "Normal smooth glass", defaultSmoothGlassID);
			smoothGlassGlowingID = getBlockID(config, "smoothGlowing", "Glowing smooth glass", defaultSmoothGlassGlowingID);
			smoothPaneID = getBlockID(config, "smoothPane", "Smooth pane", defaultSmoothGlassPaneID);
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
		stainedPane = (new BlockStainedPane(stainedPaneID, 0, 0, Material.glass));

		smoothGlass = (new BlockSmoothGlass(smoothGlassID, 16, Material.glass, false));
		smoothGlassGlowing = (new BlockSmoothGlassGlowing(smoothGlassGlowingID, 16, Material.glass, false));
		smoothPane = (new BlockSmoothPane(smoothPaneID, 16, 16, Material.glass));

		ModLoader.registerBlock(stainedGlass, ItemStainedGlass.class);
		ModLoader.registerBlock(stainedGlassGlowing, ItemStainedGlassGlowing.class);
		ModLoader.registerBlock(stainedPane, ItemStainedPane.class);
		
		ModLoader.registerBlock(smoothGlass, ItemSmoothGlass.class);
		ModLoader.registerBlock(smoothGlassGlowing, ItemSmoothGlassGlowing.class);
		ModLoader.registerBlock(smoothPane, ItemSmoothPane.class);

		for(int i = 0 ; i < 16 ; i++ ) {
			createStainedGlass(i);
			createStainedGlassGlowing(i);
			createStainedGlassPane(i);

			createSmoothGlass(i);
			createSmoothGlassGlowing(i);
			createSmoothGlassPane(i);
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

		recipe = new Object[]{ new ItemStack(stainedGlass, 1, i), Item.lightStoneDust };
		ModLoader.addShapelessRecipe(new ItemStack(stainedGlassGlowing, 1, i), recipe);

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

	public static void createSmoothGlass(int i) {
		Object[] recipe = new Object[]{ new ItemStack(stainedGlass, 1, i), Block.sand };
		String name = String.format("%s smooth glass", getColour(i));

		ModLoader.addShapelessRecipe(new ItemStack(smoothGlass, 1, i), recipe);
		ModLoader.addLocalization(buildLocalizationString("smoothGlass", i) + ".name", name);
	}

	public static void createSmoothGlassGlowing(int i) {
		Object[] recipe = new Object[]{ new ItemStack(stainedGlassGlowing, 1, i), Block.sand };
		String name = String.format("%s glowing smooth glass", getColour(i));

		ModLoader.addShapelessRecipe(new ItemStack(smoothGlassGlowing, 1, ~i & 15), recipe);
		ModLoader.addLocalization(buildLocalizationString("smoothGlassGlowing", i) + ".name", name);
	}

	public static void createSmoothGlassPane(int i) {
		Object[] recipe = new Object[]{ new ItemStack(stainedPane, 1, i), Block.sand };
		String name = String.format("%s smooth pane", getColour(i));

		ModLoader.addShapelessRecipe(new ItemStack(smoothPane, 1, ~i & 15), recipe);
		ModLoader.addLocalization(buildLocalizationString("smoothPane", i) + ".name", name);
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

