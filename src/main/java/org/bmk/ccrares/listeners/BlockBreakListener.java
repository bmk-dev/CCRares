package org.bmk.ccrares.listeners;

import org.bmk.ccrares.util.Items;
import org.bmk.ccrares.util.Messager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BlockBreakListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockBreak(BlockBreakEvent e) {
        ItemStack i = e.getPlayer().getItemInHand();
        // Spawner pick
        if (i != null) {
            if(e.getBlock().getType() == Material.SPAWNER) {
                Block b = e.getBlock();
                if(Items.isSpawnerPick(i)) {
                    Messager.debug("Item is spawner pick");


                    if(Items.getSpawnerPickUses(i) > 0) {
                        Messager.debug("Spawner pick 0 uses left, removing");
                        //e.getPlayer().getInventory().remove(i);
                        ItemStack spawner = new ItemStack(Material.SPAWNER);
                        b.getWorld().dropItem(b.getLocation(), spawner);
                        Items.decreaseSpawnerPickUses(i);
                        ItemStack ns = i.clone();
                        ItemMeta meta = ns.getItemMeta();
                        meta.setLore(Items.decreaseSpawnerPickUses(i));
                        ns.setItemMeta(meta);
                        e.getPlayer().setItemInHand(ns);
                        //i.getItemMeta().setLore(Items.decreaseSpawnerPickUses(i));
                    }
                    else {
                        Messager.debug("No spawner pick uses left");
                    }
                }
                else {
                    Messager.debug("Not spawner pick");
                }
            }
            // AutoSmelt pick
            else if (Items.isAutosmeltPick(i)) {
                Material m = e.getBlock().getType();
                Location l = e.getBlock().getLocation();
                if (m == Material.IRON_ORE || m == Material.DEEPSLATE_IRON_ORE) {
                    Messager.debug("Dropping iron with autosmelt");
                    e.setDropItems(false);
                    ItemStack iron = new ItemStack(Material.IRON_INGOT);
                    l.getWorld().dropItem(l, iron);

                } else if (m == Material.GOLD_ORE || m == Material.DEEPSLATE_GOLD_ORE) {
                    e.setDropItems(false);
                    ItemStack g = new ItemStack(Material.GOLD_INGOT);
                    l.getWorld().dropItem(l, g);
                } else if (m == Material.ANCIENT_DEBRIS) {
                    e.setDropItems(false);
                    ItemStack n = new ItemStack(Material.NETHERITE_SCRAP);
                    l.getWorld().dropItem(l, n);
                } else if (m == Material.COPPER_ORE || m == Material.DEEPSLATE_COPPER_ORE) {
                    e.setDropItems(false);
                    ItemStack c = new ItemStack(Material.COPPER_INGOT);
                    l.getWorld().dropItem(l, c);
                }
            }
        }
    }
}
