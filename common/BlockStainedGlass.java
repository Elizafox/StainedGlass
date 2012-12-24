package elizacat.mc.StainedGlass.common;

import java.util.ArrayList;

import net.minecraft.src.Block;
import net.minecraft.src.BlockGlass;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.forge.ITextureProvider;

public class BlockStainedGlass extends BlockGlass {
	public BlockStainedGlass(int id, int tex, Material material, boolean local) {
		super(id, tex, material, local);
		setHardness(0.3F);
		setStepSound(Block.soundGlassFootstep);
		setBlockName(Common.buildLocalizationString("glassBlock"));
	}
	
	public int getRenderBlockPass() {
		return 1;
	}
	
	public int getBlockTextureFromSideAndMetadata(int side, int meta){
		return meta;
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	public String getTextureFile() {
		return Common.BLOCKTEX_PNG;
	}
	
	@Override
	public void addCreativeItems(ArrayList itemList) {
		for( int i = 0 ; i < 16 ; i++ ) {
			itemList.add(new ItemStack(this, 1, i));
		}
	}
}
