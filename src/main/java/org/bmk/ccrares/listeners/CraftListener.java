package org.bmk.ccrares.listeners;

import it.unimi.dsi.fastutil.io.MeasurableInputStream;
import org.bmk.ccrares.util.Messager;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class CraftListener implements Listener {

    @EventHandler
    public void onCraftPrepare(CraftItemEvent e) {
        ItemStack[] i = e.getInventory().getContents();
        for(ItemStack is : i) {
            if(is.getItemMeta() != null && is.getItemMeta().hasLore()) {
                e.setCancelled(true);
                Messager.debug("Player tried to craft with lored item");
                e.getWhoClicked().sendMessage(ChatColor.RED + "You cannot craft using special items.");
            }
        }
    }

}
