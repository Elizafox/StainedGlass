package elizacat.mc.StainedGlass.common;

import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

public class ItemSmoothGlassGlowing extends ItemBlock {
	public ItemSmoothGlassGlowing(int id) {
		super(id);
		setHasSubtypes(true);
		setItemName(Common.buildLocalizationString("smoothGlassGlowing"));
	}
	
	@Override
	public int getMetadata(int meta) {
		return meta;
	}
	
	@Override
	public String getItemNameIS(ItemStack i) {
		return Common.buildLocalizationString("smoothGlassGlowing", i.getItemDamage());
	}
	
	public int getIconFromDamage(int meta) {
		return Common.smoothGlassGlowing.getBlockTextureFromSideAndMetadata(0, meta);
	}
}
