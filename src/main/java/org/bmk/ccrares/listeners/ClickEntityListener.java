package org.bmk.ccrares.listeners;

import org.bmk.ccrares.util.Items;
import org.bmk.ccrares.util.Messager;
import org.bmk.ccrares.util.Util;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class ClickEntityListener implements Listener {

    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent e) {
        if(!e.isCancelled() && e.getHand() == EquipmentSlot.HAND) {
            Player p = e.getPlayer();
            ItemStack i = p.getItemInHand();




            /*
            ITEM FRAME WAND
             */
            if(e.getRightClicked() instanceof Player) {
                Player clicked = (Player) e.getRightClicked();
                Messager.debug("Player right clicked player");
                if (Items.isReleaseToken(i)) {
                    Messager.debug("Player clicked player with release token");
                    Items.addReleaseTokenName(p, i, clicked);
                    e.setCancelled(true);

                }

            }
            if(Items.isItemFrameInvisWand(i)) {
                Messager.debug(p.getName() + " used item frame wand");
                if (e.getRightClicked().getType() == EntityType.ITEM_FRAME) {
                    ItemFrame ie = (ItemFrame) e.getRightClicked();
                    if (ie.isVisible()) {
                        ie.setVisible(false);
                    } else {
                        ie.setVisible(true);
                    }
                    e.setCancelled(true);
                }
                else if(e.getRightClicked().getType() == EntityType.GLOW_ITEM_FRAME) {
                    GlowItemFrame gif = (GlowItemFrame) e.getRightClicked();
                    if (gif.isVisible()) {
                        gif.setVisible(false);
                    } else {
                        gif.setVisible(true);
                    }
                    e.setCancelled(true);
                }
            }




        }
    }

}
