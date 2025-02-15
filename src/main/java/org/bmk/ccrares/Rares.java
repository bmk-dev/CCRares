package org.bmk.ccrares;

import org.bmk.ccrares.commands.RewardCommand;
import org.bmk.ccrares.listeners.*;
import org.bmk.ccrares.util.Messager;
import org.bmk.ccrares.util.ParticleManager;
import org.bmk.ccrares.util.RandomFireWorks;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Rares extends JavaPlugin {

    public static boolean debug = true;
    public static Plugin plugin;
    public static FileConfiguration releaseTokens;
    public static ParticleManager particleManager;
    public static ArrayList<String> vdayWinnersList;

    public static File vdayWinnerFile;
    public static FileConfiguration vdayWinners;

    @Override
    public void onEnable() {

        // Plugin startup logic
        plugin = this;
        getServer().getPluginManager().registerEvents(new AnvilEvent(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(),this);
        getServer().getPluginManager().registerEvents(new RightClickListener(), this);
        getServer().getPluginManager().registerEvents(new ClickEntityListener(), this);
        getServer().getPluginManager().registerEvents(new CraftListener(), this);
        getServer().getPluginManager().registerEvents(new SmithingListener(), this);
        getServer().getPluginManager().registerEvents(new SlotChangeListener(), this);
        getServer().getPluginManager().registerEvents(new PhantomSpawnListener(), this);

        RandomFireWorks.getManager().addColors();
        RandomFireWorks.getManager().addTypes();

        // Initialize a new instance of ParticleManager
        particleManager = new ParticleManager();

        vdayWinnerFile = new File(getDataFolder() + File.separator + "winners.yml");
        if(!vdayWinnerFile.exists()) {
            try {
                vdayWinnerFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        vdayWinners = YamlConfiguration.loadConfiguration(vdayWinnerFile);
        vdayWinnersList = (ArrayList<String>) vdayWinners.getList("winners");
        Messager.debug("Loaded Valentine's winners list. Size: " + vdayWinnersList.size());

        getCommand("reward").setExecutor(new RewardCommand());

        /*File file = new File(getDataFolder()+ File.separator+"releaseTokens.yml");
        releaseTokens = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()) {
            try {
                file.createNewFile(); //This needs a try catch
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }*/

    }

    @Override
    public void onDisable() {
        vdayWinners.set("winners", vdayWinnersList);
        try {
            vdayWinners.save(vdayWinnerFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Plugin shutdown logic
    }
}
