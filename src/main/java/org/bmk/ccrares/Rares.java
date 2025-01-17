package org.bmk.ccrares;

import org.bmk.ccrares.listeners.*;
import org.bmk.ccrares.util.RandomFireWorks;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Rares extends JavaPlugin {

    public static boolean debug = true;
    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(new AnvilEvent(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(),this);
        getServer().getPluginManager().registerEvents(new RightClickListener(), this);
        getServer().getPluginManager().registerEvents(new ClickEntityListener(), this);
        // Plugin startup logic
        RandomFireWorks.getManager().addColors();
        RandomFireWorks.getManager().addTypes();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
