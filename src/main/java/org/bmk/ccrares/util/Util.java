package org.bmk.ccrares.util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Util {


    public static int getOpenSlots(Player p) {
        int empty = 0;
        for(int i = 0; i <= 35; i++ ) {
            if(p.getInventory().getItem(i) == null ){
                empty++;
            }
        }
        //Messager.debug(p.getName() + " has " + empty + " empty inventory slots");
        return empty;
    }

}
