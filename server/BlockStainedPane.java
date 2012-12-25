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

// I really do hate notch.
public class BlockStainedPane extends Block {
	private int sideTextureIndex;

	public BlockStainedPane(int id, int tex, int sidetex, Material material) {
		super(id, tex, material);
		this.sideTextureIndex = sidetex;

		setHardness(0.3F);
		setStepSound(Block.soundGlassFootstep);
		setBlockName(Common.buildLocalizationString("glassPane"));
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override	
	public int getRenderType() {
		return 18; // same as a pane... server doesn't need this though.
	}
	
	@Override
	public void addCreativeItems(ArrayList itemList) {
		for( int i = 0 ; i < 16 ; i++ ){
			itemList.add( new ItemStack(this, 1, i));
		}
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void getCollidingBoundingBoxes(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, ArrayList par6ArrayList) {
		boolean var7 = this.canThisPaneConnectToThisBlockID(par1World.getBlockId(par2, par3, par4 - 1));
		boolean var8 = this.canThisPaneConnectToThisBlockID(par1World.getBlockId(par2, par3, par4 + 1));
		boolean var9 = this.canThisPaneConnectToThisBlockID(par1World.getBlockId(par2 - 1, par3, par4));
		boolean var10 = this.canThisPaneConnectToThisBlockID(par1World.getBlockId(par2 + 1, par3, par4));

		if ((!var9 || !var10) && (var9 || var10 || var7 || var8)) {
			if (var9 && !var10) {
				this.setBlockBounds(0.0F, 0.0F, 0.4375F, 0.5F, 1.0F, 0.5625F);
				super.getCollidingBoundingBoxes(par1World, par2, par3, par4, par5AxisAlignedBB, par6ArrayList);
			}
			else if (!var9 && var10) {
				this.setBlockBounds(0.5F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
				super.getCollidingBoundingBoxes(par1World, par2, par3, par4, par5AxisAlignedBB, par6ArrayList);
			}
		}
		else {
			this.setBlockBounds(0.0F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
			super.getCollidingBoundingBoxes(par1World, par2, par3, par4, par5AxisAlignedBB, par6ArrayList);
		}

		if ((!var7 || !var8) && (var9 || var10 || var7 || var8)) {
			if (var7 && !var8) {
				this.setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 0.5F);
				super.getCollidingBoundingBoxes(par1World, par2, par3, par4, par5AxisAlignedBB, par6ArrayList);
			}
			else if (!var7 && var8) {
				this.setBlockBounds(0.4375F, 0.0F, 0.5F, 0.5625F, 1.0F, 1.0F);
				super.getCollidingBoundingBoxes(par1World, par2, par3, par4, par5AxisAlignedBB, par6ArrayList);
			}
		}
		else {
			this.setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 1.0F);
			super.getCollidingBoundingBoxes(par1World, par2, par3, par4, par5AxisAlignedBB, par6ArrayList);
		}
	}


	@Override
	public void setBlockBoundsForItemRender() {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		float var5 = 0.4375F;
		float var6 = 0.5625F;
		float var7 = 0.4375F;
		float var8 = 0.5625F;
		boolean var9 = this.canThisPaneConnectToThisBlockID(par1IBlockAccess.getBlockId(par2, par3, par4 - 1));
		boolean var10 = this.canThisPaneConnectToThisBlockID(par1IBlockAccess.getBlockId(par2, par3, par4 + 1));
		boolean var11 = this.canThisPaneConnectToThisBlockID(par1IBlockAccess.getBlockId(par2 - 1, par3, par4));
		boolean var12 = this.canThisPaneConnectToThisBlockID(par1IBlockAccess.getBlockId(par2 + 1, par3, par4));

		if ((!var11 || !var12) && (var11 || var12 || var9 || var10)) {
			if (var11 && !var12) {
				var5 = 0.0F;
			}
			else if (!var11 && var12) {
				var6 = 1.0F;
			}
		}
		else {
			var5 = 0.0F;
			var6 = 1.0F;
		}

		if ((!var9 || !var10) && (var11 || var12 || var9 || var10)) {
			if (var9 && !var10) {
				var7 = 0.0F;
			}
			else if (!var9 && var10) {
				var8 = 1.0F;
			}
		}
		else {
			var7 = 0.0F;
			var8 = 1.0F;
		}

		this.setBlockBounds(var5, 0.0F, var7, var6, 1.0F, var8);
	}

	public int getSideTextureIndex() {
		return this.sideTextureIndex;
	}

	public boolean canThisPaneConnectToThisBlockID(int par1) {
		return this.blockID == Block.fenceIron.blockID ? Block.opaqueCubeLookup[par1] || par1 == this.blockID || par1 == Block.glass.blockID || par1 == Common.stainedGlass.blockID : Block.opaqueCubeLookup[par1] || par1 == Block.glass.blockID || par1 == Common.stainedPane.blockID || par1 == Common.stainedGlass.blockID || par1 == Block.thinGlass.blockID;
	}
}
