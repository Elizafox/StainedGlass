package elizacat.mc.StainedGlass.common;

import java.io.File;
import java.util.logging.Level;

import cpw.mods.fml.common.FMLCommonHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityRenderer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.Tessellator;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

import net.minecraft.src.forge.*;

import elizacat.mc.StainedGlass.proxy.Proxy;

public class mod_StainedGlass extends NetworkMod {
	
	public static final int defaultGlassID = 200;
	public static final int defaultGlassGlowingID = 201;
	public static final int defaultGlassPaneID = 202;

	public static int renderID;
	
	public Configuration config = new Configuration(Proxy.getConfig());
	
	private void loadConfig() {
		try {
			config.load();
			Common.stainedGlassID = Integer.parseInt(config.getOrCreateBlockIdProperty("stainedGlass", defaultGlassID).value);
			Common.stainedGlassGlowingID = Integer.parseInt(config.getOrCreateBlockIdProperty("stainedGlowing", defaultGlassGlowingID).value);
			Common.stainedPaneID = Integer.parseInt(config.getOrCreateBlockIdProperty("stainedPane", defaultGlassPaneID).value);
			config.save();
		}
		catch (Exception e) {
			FMLCommonHandler.instance().getFMLLogger().log(Level.SEVERE, "mod_StainedGlass encountered a problem loading it's configuration", e);
		}
		finally {
			config.save();
		}
	}
	
	public void load() {
		MinecraftForgeClient.preloadTexture(Common.BLOCKTEX_PNG);
	
		renderID = ModLoader.getUniqueBlockModelID(this, false);

		// Load config
		loadConfig();

		Common.stainedGlass = (new BlockStainedGlass(Common.stainedGlassID, 0, Material.glass, false));
		Common.stainedGlassGlowing = (new BlockStainedGlassGlowing(Common.stainedGlassGlowingID, 0, Material.glass, false));
		Common.stainedPane = (new BlockStainedPane(Common.stainedPaneID, 0, 16, Material.glass));

		ModLoader.registerBlock(Common.stainedGlass, ItemStainedGlass.class);
		ModLoader.registerBlock(Common.stainedGlassGlowing, ItemStainedGlassGlowing.class);
		ModLoader.registerBlock(Common.stainedPane, ItemStainedPane.class);

		for(int i = 0 ; i < 16 ; i++ ) {
			createBlocks(i);
		}
	}

	public void createBlocks(int i) {
		createStainedGlass(i);
		createStainedGlassGlowing(i);
		createStainedGlassPane(i);
	}

	public void createStainedGlass(int i) {
		Object[] recipe = new Object[]{ Block.glass, new ItemStack(Item.dyePowder, 1, i) };
		String name = String.format("%s glass", Common.getColour(i));

		ModLoader.addShapelessRecipe(new ItemStack(Common.stainedGlass, 1, ~i & 15), recipe);
		ModLoader.addLocalization(Common.buildLocalizationString("glassBlock", i) + ".name", name);
	}

	public void createStainedGlassGlowing(int i) {
		Object[] recipe;
		String name = String.format("%s glowing glass", Common.getColour(i));

		recipe = new Object[]{ Common.stainedGlass, Item.lightStoneDust };
		ModLoader.addShapelessRecipe(new ItemStack(Common.stainedGlassGlowing, 1, ~i & 15), recipe);

		recipe = new Object[]{ Block.glass, new ItemStack(Item.dyePowder, 1, i), Item.lightStoneDust };
		ModLoader.addShapelessRecipe(new ItemStack(Common.stainedGlassGlowing, 1, ~i & 15), recipe);

		ModLoader.addLocalization(Common.buildLocalizationString("glassGlowing", i) + ".name", name);
	}

	public void createStainedGlassPane(int i) {
		Object[] recipe = new Object[]{ Block.thinGlass, new ItemStack(Item.dyePowder, 1, i) };
		String name = String.format("%s glass pane", Common.getColour(i));

		ModLoader.addShapelessRecipe(new ItemStack(Common.stainedPane, 1, ~i & 15), recipe);
		ModLoader.addLocalization(Common.buildLocalizationString("glassPane", i) + ".name", name);
	}

	public boolean renderWorldBlock(RenderBlocks render, IBlockAccess access, int x, int y, int z, Block genBlock, int renderType) {
		return Proxy.renderWorldBlock(render, access, x, y, z, genBlock, renderType);
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
