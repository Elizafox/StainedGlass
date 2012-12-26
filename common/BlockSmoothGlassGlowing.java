package elizacat.mc.StainedGlass.common;

import java.util.ArrayList;

import net.minecraft.src.Block;
import net.minecraft.src.BlockGlass;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.forge.ITextureProvider;

public class BlockSmoothGlassGlowing extends BlockStainedGlassGlowing {
	public BlockSmoothGlassGlowing( int id, int tex, Material material, boolean local) {
		super(id, tex, material, local);
		setBlockName(Common.buildLocalizationString("glassGlowing"));
	}

	public int getBlockTextureFromSideAndMetadata(int side, int meta) {
		// Next row
		return 16 + meta;
	}
}
