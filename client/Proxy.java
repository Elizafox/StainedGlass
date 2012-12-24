package elizacat.mc.StainedGlass.proxy;

import java.io.File;
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

import elizacat.mc.StainedGlass.common.BlockStainedPane;
import elizacat.mc.StainedGlass.common.mod_StainedGlass;

public class Proxy {
	public static File getConfig() {
		return new File(new File(Minecraft.getMinecraftDir(), "config"), "StainedGlass.cfg");
	}
	
	// This is gotyaoi's custom render function
	// "copied mostly from renderBlocks. I'll try to get it cleaned up some in the next release. For now it's pretty safe to ignore it."
	public static boolean renderWorldBlock(RenderBlocks render, IBlockAccess access, int x, int y, int z, Block genBlock, int renderType) {
		if(renderType == mod_StainedGlass.renderID){
			BlockStainedPane block = (BlockStainedPane)genBlock;
			int var5 = access.getHeight();
			Tessellator var6 = Tessellator.instance;
			var6.setBrightness(block.getMixedBrightnessForBlock(access, x, y, z));
			float var7 = 1.0F;
			int var8 = block.colorMultiplier(access, x, y, z);
			float var9 = (float)(var8 >> 16 & 255) / 255.0F;
			float var10 = (float)(var8 >> 8 & 255) / 255.0F;
			float var11 = (float)(var8 & 255) / 255.0F;
			if (EntityRenderer.anaglyphEnable)
			{
				float var12 = (var9 * 30.0F + var10 * 59.0F + var11 * 11.0F) / 100.0F;
				float var13 = (var9 * 30.0F + var10 * 70.0F) / 100.0F;
				float var14 = (var9 * 30.0F + var11 * 70.0F) / 100.0F;
				var9 = var12;
				var10 = var13;
				var11 = var14;
			}
			var6.setColorOpaque_F(var7 * var9, var7 * var10, var7 * var11);
			boolean var66 = false;
			boolean var67 = false;
			int var15;
			int var16;
			int var68;
			if (render.overrideBlockTexture >= 0)
			{
				var15 = render.overrideBlockTexture;
				var16 = render.overrideBlockTexture;
			}
			else
			{
				var68 = access.getBlockMetadata(x, y, z);
				var15 = block.getBlockTextureFromSideAndMetadata(0, var68);
				var16 = block.getSideTextureIndex();
			}
			var68 = (var15 & 15) << 4;
			int var17 = var15 & 240;
			double var18 = (double)((float)var68 / 256.0F);
			double var20 = (double)(((float)var68 + 7.99F) / 256.0F);
			double var22 = (double)(((float)var68 + 15.99F) / 256.0F);
			double var24 = (double)((float)var17 / 256.0F);
			double var26 = (double)(((float)var17 + 15.99F) / 256.0F);
			int var28 = (var16 & 15) << 4;
			int var29 = var16 & 240;
			double var30 = (double)((float)(var28 + 7) / 256.0F);
			double var32 = (double)(((float)var28 + 8.99F) / 256.0F);
			double var34 = (double)((float)var29 / 256.0F);
			double var36 = (double)((float)(var29 + 8) / 256.0F);
			double var38 = (double)(((float)var29 + 15.99F) / 256.0F);
			double var40 = (double)x;
			double var42 = (double)x + 0.5D;
			double var44 = (double)(x + 1);
			double var46 = (double)z;
			double var48 = (double)z + 0.5D;
			double var50 = (double)(z + 1);
			double var52 = (double)x + 0.5D - 0.0625D;
			double var54 = (double)x + 0.5D + 0.0625D;
			double var56 = (double)z + 0.5D - 0.0625D;
			double var58 = (double)z + 0.5D + 0.0625D;
			boolean var60 = block.canThisPaneConnectToThisBlockID(access.getBlockId(x, y, z - 1));
			boolean var61 = block.canThisPaneConnectToThisBlockID(access.getBlockId(x, y, z + 1));
			boolean var62 = block.canThisPaneConnectToThisBlockID(access.getBlockId(x - 1, y, z));
			boolean var63 = block.canThisPaneConnectToThisBlockID(access.getBlockId(x + 1, y, z));
			boolean var64 = block.shouldSideBeRendered(access, x, y + 1, z, 1);
			boolean var65 = block.shouldSideBeRendered(access, x, y - 1, z, 0);
			if ((!var62 || !var63) && (var62 || var63 || var60 || var61))
			{
				if (var62 && !var63)
				{
					var6.addVertexWithUV(var40, (double)(y + 1), var48, var18, var24);
					var6.addVertexWithUV(var40, (double)(y + 0), var48, var18, var26);
					var6.addVertexWithUV(var42, (double)(y + 0), var48, var20, var26);
					var6.addVertexWithUV(var42, (double)(y + 1), var48, var20, var24);
					if (!var61 && !var60)
					{
						var6.addVertexWithUV(var42, (double)(y + 1), var58, var30, var34);
						var6.addVertexWithUV(var42, (double)(y + 0), var58, var30, var38);
						var6.addVertexWithUV(var42, (double)(y + 0), var56, var32, var38);
						var6.addVertexWithUV(var42, (double)(y + 1), var56, var32, var34);
					}
					if (var64 || y < var5 - 1 && access.isAirBlock(x - 1, y + 1, z))
					{
						var6.addVertexWithUV(var40, (double)(y + 1) + 0.01D, var58, var32, var36);
						var6.addVertexWithUV(var42, (double)(y + 1) + 0.01D, var58, var32, var38);
						var6.addVertexWithUV(var42, (double)(y + 1) + 0.01D, var56, var30, var38);
						var6.addVertexWithUV(var40, (double)(y + 1) + 0.01D, var56, var30, var36);
					}
					if (var65 || y > 1 && access.isAirBlock(x - 1, y - 1, z))
					{
						var6.addVertexWithUV(var40, (double)y - 0.01D, var58, var32, var36);
						var6.addVertexWithUV(var42, (double)y - 0.01D, var58, var32, var38);
						var6.addVertexWithUV(var42, (double)y - 0.01D, var56, var30, var38);
						var6.addVertexWithUV(var40, (double)y - 0.01D, var56, var30, var36);
					}
				}
				else if (!var62 && var63)
				{
					var6.addVertexWithUV(var42, (double)(y + 1), var48, var20, var24);
					var6.addVertexWithUV(var42, (double)(y + 0), var48, var20, var26);
					var6.addVertexWithUV(var44, (double)(y + 0), var48, var22, var26);
					var6.addVertexWithUV(var44, (double)(y + 1), var48, var22, var24);
					if (!var61 && !var60)
					{
						var6.addVertexWithUV(var42, (double)(y + 1), var56, var30, var34);
						var6.addVertexWithUV(var42, (double)(y + 0), var56, var30, var38);
						var6.addVertexWithUV(var42, (double)(y + 0), var58, var32, var38);
						var6.addVertexWithUV(var42, (double)(y + 1), var58, var32, var34);
					}
					if (var64 || y < var5 - 1 && access.isAirBlock(x + 1, y + 1, z))
					{
						var6.addVertexWithUV(var42, (double)(y + 1) + 0.01D, var58, var32, var34);
						var6.addVertexWithUV(var44, (double)(y + 1) + 0.01D, var58, var32, var36);
						var6.addVertexWithUV(var44, (double)(y + 1) + 0.01D, var56, var30, var36);
						var6.addVertexWithUV(var42, (double)(y + 1) + 0.01D, var56, var30, var34);
					}
					if (var65 || y > 1 && access.isAirBlock(x + 1, y - 1, z))
					{
						var6.addVertexWithUV(var42, (double)y - 0.01D, var58, var32, var34);
						var6.addVertexWithUV(var44, (double)y - 0.01D, var58, var32, var36);
						var6.addVertexWithUV(var44, (double)y - 0.01D, var56, var30, var36);
						var6.addVertexWithUV(var42, (double)y - 0.01D, var56, var30, var34);
					}
				}
			}
			else
			{
				var6.addVertexWithUV(var40, (double)(y + 1), var48, var18, var24);
				var6.addVertexWithUV(var40, (double)(y + 0), var48, var18, var26);
				var6.addVertexWithUV(var44, (double)(y + 0), var48, var22, var26);
				var6.addVertexWithUV(var44, (double)(y + 1), var48, var22, var24);
				if (var64)
				{
					var6.addVertexWithUV(var40, (double)(y + 1) + 0.01D, var58, var32, var38);
					var6.addVertexWithUV(var44, (double)(y + 1) + 0.01D, var58, var32, var34);
					var6.addVertexWithUV(var44, (double)(y + 1) + 0.01D, var56, var30, var34);
					var6.addVertexWithUV(var40, (double)(y + 1) + 0.01D, var56, var30, var38);
				}
				else
				{
					if (y < var5 - 1 && access.isAirBlock(x - 1, y + 1, z))
					{
						var6.addVertexWithUV(var40, (double)(y + 1) + 0.01D, var58, var32, var36);
						var6.addVertexWithUV(var42, (double)(y + 1) + 0.01D, var58, var32, var38);
						var6.addVertexWithUV(var42, (double)(y + 1) + 0.01D, var56, var30, var38);
						var6.addVertexWithUV(var40, (double)(y + 1) + 0.01D, var56, var30, var36);
					}
					if (y < var5 - 1 && access.isAirBlock(x + 1, y + 1, z))
					{
						var6.addVertexWithUV(var42, (double)(y + 1) + 0.01D, var58, var32, var34);
						var6.addVertexWithUV(var44, (double)(y + 1) + 0.01D, var58, var32, var36);
						var6.addVertexWithUV(var44, (double)(y + 1) + 0.01D, var56, var30, var36);
						var6.addVertexWithUV(var42, (double)(y + 1) + 0.01D, var56, var30, var34);
					}
				}
				if (var65)
				{
					var6.addVertexWithUV(var40, (double)y - 0.01D, var58, var32, var38);
					var6.addVertexWithUV(var44, (double)y - 0.01D, var58, var32, var34);
					var6.addVertexWithUV(var44, (double)y - 0.01D, var56, var30, var34);
					var6.addVertexWithUV(var40, (double)y - 0.01D, var56, var30, var38);
				}
				else
				{
					if (y > 1 && access.isAirBlock(x - 1, y - 1, z))
					{
						var6.addVertexWithUV(var40, (double)y - 0.01D, var58, var32, var36);
						var6.addVertexWithUV(var42, (double)y - 0.01D, var58, var32, var38);
						var6.addVertexWithUV(var42, (double)y - 0.01D, var56, var30, var38);
						var6.addVertexWithUV(var40, (double)y - 0.01D, var56, var30, var36);
					}
					if (y > 1 && access.isAirBlock(x + 1, y - 1, z))
					{
						var6.addVertexWithUV(var42, (double)y - 0.01D, var58, var32, var34);
						var6.addVertexWithUV(var44, (double)y - 0.01D, var58, var32, var36);
						var6.addVertexWithUV(var44, (double)y - 0.01D, var56, var30, var36);
						var6.addVertexWithUV(var42, (double)y - 0.01D, var56, var30, var34);
					}
				}
			}
			if ((!var60 || !var61) && (var62 || var63 || var60 || var61))
			{
				if (var60 && !var61)
				{
					var6.addVertexWithUV(var42, (double)(y + 1), var46, var18, var24);
					var6.addVertexWithUV(var42, (double)(y + 0), var46, var18, var26);
					var6.addVertexWithUV(var42, (double)(y + 0), var48, var20, var26);
					var6.addVertexWithUV(var42, (double)(y + 1), var48, var20, var24);
					if (!var63 && !var62)
					{
						var6.addVertexWithUV(var52, (double)(y + 1), var48, var30, var34);
						var6.addVertexWithUV(var52, (double)(y + 0), var48, var30, var38);
						var6.addVertexWithUV(var54, (double)(y + 0), var48, var32, var38);
						var6.addVertexWithUV(var54, (double)(y + 1), var48, var32, var34);
					}
					if (var64 || y < var5 - 1 && access.isAirBlock(x, y + 1, z - 1))
					{
						var6.addVertexWithUV(var52, (double)(y + 1), var46, var32, var34);
						var6.addVertexWithUV(var52, (double)(y + 1), var48, var32, var36);
						var6.addVertexWithUV(var54, (double)(y + 1), var48, var30, var36);
						var6.addVertexWithUV(var54, (double)(y + 1), var46, var30, var34);
					}
					if (var65 || y > 1 && access.isAirBlock(x, y - 1, z - 1))
					{
						var6.addVertexWithUV(var52, (double)y, var46, var32, var34);
						var6.addVertexWithUV(var52, (double)y, var48, var32, var36);
						var6.addVertexWithUV(var54, (double)y, var48, var30, var36);
						var6.addVertexWithUV(var54, (double)y, var46, var30, var34);
					}
				}
				else if (!var60 && var61)
				{
					var6.addVertexWithUV(var42, (double)(y + 1), var48, var20, var24);
					var6.addVertexWithUV(var42, (double)(y + 0), var48, var20, var26);
					var6.addVertexWithUV(var42, (double)(y + 0), var50, var22, var26);
					var6.addVertexWithUV(var42, (double)(y + 1), var50, var22, var24);
					if (!var63 && !var62)
					{
						var6.addVertexWithUV(var54, (double)(y + 1), var48, var30, var34);
						var6.addVertexWithUV(var54, (double)(y + 0), var48, var30, var38);
						var6.addVertexWithUV(var52, (double)(y + 0), var48, var32, var38);
						var6.addVertexWithUV(var52, (double)(y + 1), var48, var32, var34);
					}
					if (var64 || y < var5 - 1 && access.isAirBlock(x, y + 1, z + 1))
					{
						var6.addVertexWithUV(var52, (double)(y + 1), var48, var30, var36);
						var6.addVertexWithUV(var52, (double)(y + 1), var50, var30, var38);
						var6.addVertexWithUV(var54, (double)(y + 1), var50, var32, var38);
						var6.addVertexWithUV(var54, (double)(y + 1), var48, var32, var36);
					}
					if (var65 || y > 1 && access.isAirBlock(x, y - 1, z + 1))
					{
						var6.addVertexWithUV(var52, (double)y, var48, var30, var36);
						var6.addVertexWithUV(var52, (double)y, var50, var30, var38);
						var6.addVertexWithUV(var54, (double)y, var50, var32, var38);
						var6.addVertexWithUV(var54, (double)y, var48, var32, var36);
					}
				}
			}
			else
			{
				var6.addVertexWithUV(var42, (double)(y + 1), var50, var18, var24);
				var6.addVertexWithUV(var42, (double)(y + 0), var50, var18, var26);
				var6.addVertexWithUV(var42, (double)(y + 0), var46, var22, var26);
				var6.addVertexWithUV(var42, (double)(y + 1), var46, var22, var24);
				if (var64)
				{
					var6.addVertexWithUV(var54, (double)(y + 1), var50, var32, var38);
					var6.addVertexWithUV(var54, (double)(y + 1), var46, var32, var34);
					var6.addVertexWithUV(var52, (double)(y + 1), var46, var30, var34);
					var6.addVertexWithUV(var52, (double)(y + 1), var50, var30, var38);
				}
				else
				{
					if (y < var5 - 1 && access.isAirBlock(x, y + 1, z - 1))
					{
						var6.addVertexWithUV(var52, (double)(y + 1), var46, var32, var34);
						var6.addVertexWithUV(var52, (double)(y + 1), var48, var32, var36);
						var6.addVertexWithUV(var54, (double)(y + 1), var48, var30, var36);
						var6.addVertexWithUV(var54, (double)(y + 1), var46, var30, var34);
					}
					if (y < var5 - 1 && access.isAirBlock(x, y + 1, z + 1))
					{
						var6.addVertexWithUV(var52, (double)(y + 1), var48, var30, var36);
						var6.addVertexWithUV(var52, (double)(y + 1), var50, var30, var38);
						var6.addVertexWithUV(var54, (double)(y + 1), var50, var32, var38);
						var6.addVertexWithUV(var54, (double)(y + 1), var48, var32, var36);
					}
				}
				if (var65)
				{
					var6.addVertexWithUV(var54, (double)y, var50, var32, var38);
					var6.addVertexWithUV(var54, (double)y, var46, var32, var34);
					var6.addVertexWithUV(var52, (double)y, var46, var30, var34);
					var6.addVertexWithUV(var52, (double)y, var50, var30, var38);
				}
				else
				{
					if (y > 1 && access.isAirBlock(x, y - 1, z - 1))
					{
						var6.addVertexWithUV(var52, (double)y, var46, var32, var34);
						var6.addVertexWithUV(var52, (double)y, var48, var32, var36);
						var6.addVertexWithUV(var54, (double)y, var48, var30, var36);
						var6.addVertexWithUV(var54, (double)y, var46, var30, var34);
					}
					if (y > 1 && access.isAirBlock(x, y - 1, z + 1))
					{
						var6.addVertexWithUV(var52, (double)y, var48, var30, var36);
						var6.addVertexWithUV(var52, (double)y, var50, var30, var38);
						var6.addVertexWithUV(var54, (double)y, var50, var32, var38);
						var6.addVertexWithUV(var54, (double)y, var48, var32, var36);
					}
				}
			}
			return true;
		}
		return false;
	}
}
