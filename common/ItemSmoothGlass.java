package elizacat.mc.StainedGlass.common;

import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

public class ItemSmoothGlass extends ItemBlock {
	public ItemSmoothGlass(int id) {
		super(id);
		setHasSubtypes(true);
		setItemName(Common.buildLocalizationString("smoothGlass"));
	}
	
	@Override
	public int getMetadata(int meta) {
		return meta;
	}
	
	@Override
	public String getItemNameIS(ItemStack i) {
		return Common.buildLocalizationString("smoothGlass", i.getItemDamage());
	}
	
	public int getIconFromDamage(int meta) {
		return Common.stainedGlass.getBlockTextureFromSideAndMetadata(0, meta);
	}
}
