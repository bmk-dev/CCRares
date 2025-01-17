package org.bmk.ccrares.listeners;

import org.bmk.ccrares.Rares;
import org.bmk.ccrares.util.Items;
import org.bmk.ccrares.util.Messager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AnvilEvent implements Listener {

    @EventHandler
    public void onAnvilCraft(PrepareAnvilEvent e) {
        AnvilInventory inv = e.getInventory();

        ItemStack tool = inv.getItem(0);
        ItemStack enchant = inv.getItem(1);
        if(tool != null) {
            if(enchant != null) {
                // Unbreakable enchant
                if (Items.isUnbreakableBook(enchant)) {
                    Messager.debug("Tool type: " + tool.getType().toString());
                    ItemStack output = tool.clone();

                    Messager.debug("Enchant base item: " + enchant.getType().toString());
                    Messager.debug("Detected both anvil slots occupied");
                    ItemMeta meta = tool.getItemMeta();
                    meta.setUnbreakable(true);
                    output.setItemMeta(meta);
                    e.setResult(output);
                    // Rares.plugin.getServer().getScheduler().runTask(Rares.plugin, () -> e.getView().setRepairCost(0));
                }
                else {
                    Messager.debug("Not unbreakable book");
                }
            }
        }
    }
}
