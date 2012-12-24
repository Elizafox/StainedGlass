package elizacat.mc.StainedGlass.common;

import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

public class ItemStainedGlass extends ItemBlock {
	public ItemStainedGlass(int id) {
		super(id);
		setHasSubtypes(true);
		setItemName(Common.buildLocalizationString("glassBlock"));
	}
	
	@Override
	public int getMetadata(int meta) {
		return meta;
	}
	
	@Override
	public String getItemNameIS(ItemStack i) {
		return Common.buildLocalizationString("glassBlock", i.getItemDamage());
	}
	
	public int getIconFromDamage(int meta) {
		return Common.stainedGlass.getBlockTextureFromSideAndMetadata(0, meta);
	}
}
