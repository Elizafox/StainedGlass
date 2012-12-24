package elizacat.mc.StainedGlass.common;

import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

public class ItemStainedPane extends ItemBlock {

	public ItemStainedPane(int id) {
		super(id);
		setHasSubtypes(true);
		setItemName(Common.buildLocalizationString("glassPane"));
	}
	
	@Override
	public int getMetadata( int meta ) {
		return meta;
	}
	
	@Override
	public String getItemNameIS(ItemStack i) {
		return Common.buildLocalizationString("glassPane", i.getItemDamage());
	}
	
	public int getIconFromDamage(int meta) {
		return Common.stainedPane.getBlockTextureFromSideAndMetadata(0, meta);
	}

}
