package elizacat.mc.StainedGlass.common;

import java.util.ArrayList;

import net.minecraft.src.Block;
import net.minecraft.src.BlockGlass;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.forge.ITextureProvider;

public class BlockStainedGlassGlowing extends BlockStainedGlass {
	public BlockStainedGlassGlowing( int id, int tex, Material material, boolean local) {
		super(id, tex, material, local);
		setLightValue(1.0F);
		setBlockName(Common.buildLocalizationString("glassGlowing"));
	}
}
