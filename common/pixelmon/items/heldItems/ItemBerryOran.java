package pixelmon.items.heldItems;

import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.enums.EnumHeldItems;
import pixelmon.items.ItemHeld;

public class ItemBerryOran extends ItemHeld {

	public ItemBerryOran(int id) {
		super(id, EnumHeldItems.oran);
		SetUsableInBattle(true);
	}

	public boolean effectEntity(PixelmonEntityHelper helper) {
		if (helper.getHealth() < (int) ((float) helper.getMaxHealth() / .3f)) {
			helper.setHealth(helper.getHealth() + 10);
			ChatHandler.sendChat(helper.getOwner(), helper.getName() + " just consumed an Oran Berry and gained 10 health!");
			return true;
		}
		return false;
	}

}