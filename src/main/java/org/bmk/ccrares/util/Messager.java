package org.bmk.ccrares.util;

import org.bmk.ccrares.Rares;
import org.bukkit.Bukkit;

public class Messager {

    public static void debug(String text) {
        if(Rares.debug) {
            Bukkit.getServer().getLogger().info("[CCRares Debug] " + text);
        }
    }

}
