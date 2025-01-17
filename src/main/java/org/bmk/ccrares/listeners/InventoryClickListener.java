package org.bmk.ccrares.listeners;

import org.bmk.ccrares.util.CEnchant;
import org.bmk.ccrares.util.Items;
import org.bmk.ccrares.util.Messager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryClickListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryClick(InventoryClickEvent e) {

        // Anvil inventory click
        if(e.getInventory().getType() == InventoryType.ANVIL) {



            int slot = e.getSlot();
            if(slot == 2) {
                Messager.debug("Player clicked in anvil result");
                AnvilInventory anvilInv = (AnvilInventory) e.getInventory();
                ItemStack tool = anvilInv.getItem(0);
                ItemStack enchant = anvilInv.getItem(1);
                ItemStack result = anvilInv.getResult();

                if (tool != null && tool.getType() != Material.AIR) {
                    if (enchant != null && enchant.getType() != Material.AIR) {
                        for(String s : enchant.getItemMeta().getLore()) {
                            Messager.debug("Lore: " + s);
                        }
                        if(result != null) {
                            Messager.debug("Result type: " + result.getType());
                        }
                        else {
                            Messager.debug("Result null");
                        }
                        if(Items.isUnbreakableBook(enchant)) {
                            Messager.debug("Enchanting unbreakable book");
                            Items.Enchant((Player)e.getWhoClicked(), anvilInv, CEnchant.UNBREAKABLE);

                        }
                    }
                }
            }


        }


    }

}
