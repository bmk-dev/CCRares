package org.bmk.ccrares.listeners;

import dev.esophose.playerparticles.particles.ParticleEffect;
import dev.esophose.playerparticles.styles.ParticleStyle;
import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import org.bmk.ccrares.Rares;
import org.bmk.ccrares.util.Items;
import org.bmk.ccrares.util.Messager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class SlotChangeListener implements Listener {
    /*
    Event called when any item in a player's inventory changes
     */
    @EventHandler
    public void onSlotChange(PlayerInventorySlotChangeEvent event) {

        // Slots 36-39 are armor slots
        if(event.getSlot() >= 36 && event.getSlot() <= 39) {

            Player p = event.getPlayer();

            ItemStack in = event.getNewItemStack(); // Item being equipped
            ItemStack io = event.getOldItemStack(); // Item being removed

            // Make sure item has lore
            if(in.getItemMeta() != null && in.getItemMeta().getLore() != null) {

                // Check whether NEW item has lore for cherry particles (&7Cherry Companion Particles).
                // If this is true then the player equipped the item
                if(Items.hasLore(in, Items.cherryParticleLore)) {

                    // Add the cherry particle
                    Rares.particleManager.setParticle(p, ParticleEffect.CHERRY_LEAVES, ParticleStyle.fromInternalName("companion"));
                }
            }

            else if(io.getItemMeta() != null && io.getItemMeta().getLore() != null) {

                // Check whether OLD item has lore for cherry particles
                // If this is true then the player removed the item
                if(Items.hasLore(io, Items.cherryParticleLore)) {

                    // Remove the cherry particles
                    Rares.particleManager.removeParticles(p);
                }
            }
        }
    }


}
