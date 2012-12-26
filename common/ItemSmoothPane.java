package elizacat.mc.StainedGlass.common;

import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

public class ItemSmoothPane extends ItemBlock {
	public ItemSmoothPane(int id) {
		super(id);
		setHasSubtypes(true);
		setItemName(Common.buildLocalizationString("smoothPane"));
	}
	
	@Override
	public int getMetadata( int meta ) {
		return meta;
	}
	
	@Override
	public String getItemNameIS(ItemStack i) {
		return Common.buildLocalizationString("smoothPane", i.getItemDamage());
	}
	
	public int getIconFromDamage(int meta) {
		// Index for the items for this. This looks nicer.
		return 32 + meta;
	}

}
