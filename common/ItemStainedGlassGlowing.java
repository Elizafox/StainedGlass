package elizacat.mc.StainedGlass.common;

import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

public class ItemStainedGlassGlowing extends ItemBlock {
	public ItemStainedGlassGlowing(int id) {
		super(id);
		setHasSubtypes(true);
		setItemName(Common.buildLocalizationString("glassGlowing"));
	}
	
	@Override
	public int getMetadata(int meta) {
		return meta;
	}
	
	@Override
	public String getItemNameIS(ItemStack i) {
		return Common.buildLocalizationString("glassGlowing", i.getItemDamage());
	}
	
	public int getIconFromDamage(int meta) {
		return Common.stainedGlassGlowing.getBlockTextureFromSideAndMetadata(0, meta);
	}
}
