package elizacat.mc.StainedGlass.common;

import java.util.ArrayList;

import net.minecraft.src.Block;
import net.minecraft.src.BlockGlass;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;

// Voodoo time...
import elizacat.mc.StainedGlass.proxy.BlockStainedPane;

public class BlockSmoothPane extends BlockStainedPane {
	public BlockSmoothPane(int id, int tex, int sidetex, Material material) {
		super(id, tex, sidetex, material);
		setBlockName(Common.buildLocalizationString("glassSmooth"));
	}

	public int getBlockTextureFromSideAndMetadata(int side, int meta) {
		// Next row
		return 16 + meta;
	}
}
