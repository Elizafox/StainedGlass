package elizacat.mc.StainedGlass.proxy;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.BlockPane;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.World;
import net.minecraft.src.forge.ITextureProvider;

import elizacat.mc.StainedGlass.common.Common;
import elizacat.mc.StainedGlass.common.BlockStainedPaneCommon;

// I hate notch.
public class BlockStainedPane extends BlockStainedPaneCommon implements ITextureProvider {
	public BlockStainedPane(int id, int tex, int sidetex, Material material) {
		super(id, tex, sidetex, material);
	}

	// Client side only
	@Override
	public int getRenderBlockPass() {
		return 1;
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta) {
		return meta;
	}
	

	@Override	
	public String getTextureFile() {
		return Common.BLOCKTEX_PNG;
	}
	
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int x, int y, int z, int side) {
		int otherblock = par1IBlockAccess.getBlockId(x, y, z);
		return otherblock != Block.thinGlass.blockID && side != this.blockID ? super.shouldSideBeRendered(par1IBlockAccess, x, y, z, side) : false;
	}

	public int getSideTextureIndex() {
		return sideTextureIndex;
	}
}
