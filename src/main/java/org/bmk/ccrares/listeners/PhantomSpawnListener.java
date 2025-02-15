package org.bmk.ccrares.listeners;

import com.destroystokyo.paper.event.entity.PhantomPreSpawnEvent;
import org.bmk.ccrares.util.Items;
import org.bmk.ccrares.util.Messager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class PhantomSpawnListener implements Listener {

    @EventHandler
    public void onPhantomSpawn(PhantomPreSpawnEvent e) {
        Messager.debug("Received PhantomPreSpawnEvent");
        if(e.getSpawningEntity() instanceof Player) {
            Messager.debug("SpawningEntity instance of Player");
            Player p = (Player) e.getSpawningEntity();
            ItemStack is = p.getInventory().getItem(EquipmentSlot.HEAD);
            if(Items.hasLore(is, Items.phantomRepellantLore)) {
                Messager.debug("Cancelling phantom spawn event");
                e.setCancelled(true);
            }
        }
    }
}
