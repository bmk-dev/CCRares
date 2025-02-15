package org.bmk.ccrares.listeners;

import org.bmk.ccrares.util.Messager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.ItemStack;

public class SmithingListener implements Listener {

    @EventHandler
    public void onSmithPrepare(PrepareSmithingEvent e) {
        ItemStack[] i = e.getInventory().getContents();
        for(ItemStack is : i) {
            if(is != null) {
                if (is.getItemMeta() != null && is.getItemMeta().hasLore()) {
                    e.setResult(new ItemStack(Material.AIR));
                }
            }
        }
    }

}
