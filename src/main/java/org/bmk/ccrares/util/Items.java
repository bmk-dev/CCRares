package org.bmk.ccrares.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.view.AnvilView;

import java.util.List;

public class Items {


    public static String wearableHatLore = "&7Wearable Hat";
    public static String unbreakingEnchantLore = "&7Unbreakable Enchantment";
    public static String autosmeltEnchantLore = "&7AutoSmelt Enchantment";
    public static String spawnerPickEnchantLore = "&7Spawner Silk Touch";
    public static String playerWeatherLore = "&7Player Weather Changer";
    public static String fireworkSpawnerLore = "&7Random Firework Wand";
    public static String itemFrameInvisLore = "&7Item Frame Wand";
    public static String furnaceFuelLore = "&7Furnace Fuel";
    public static String plantBoxLore = "&7Plant Decor Box";
    public static String celestialToolBoxLore = "&7Celestial Toolbox";
    public static String starburstLore = "&7Starburst Candy";

    public static boolean isUnbreakableBook(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        if(i.getType() == Material.BOOK) {
            if (hasLore(i, unbreakingEnchantLore)) {
                Messager.debug("Unbreakable book has proper lore");

                return true;
            }
        }

        return false;
    }

    public static boolean isPlantBox(ItemStack i) {
        if(hasLore(i, plantBoxLore)) {
            return true;
        }
        return false;
    }

    public static boolean isWearableHat(ItemStack i) {
        if(hasLore(i, wearableHatLore)) {
            return true;
        }
        return false;
    }

    public static boolean isCelestialToolbox(ItemStack i) {
        if(hasLore(i, celestialToolBoxLore)) {
            return true;
        }
        return false;
    }

    public static boolean isSpawnerPick(ItemStack i) {
        if(hasLore(i, spawnerPickEnchantLore)) {
            return true;
        }
        return false;
    }

    public static boolean isFireworkWand(ItemStack i) {
        if(hasLore(i, fireworkSpawnerLore)) {
            return true;
        }
        return false;
    }

    public static boolean isItemFrameInvisWand(ItemStack i) {
        if(hasLore(i, itemFrameInvisLore)) {
            return true;
        }
        return false;
    }

    public static boolean isFurnaceFuel15(ItemStack i) {
        if(hasLore(i, furnaceFuelLore)) {
            return true;
        }
        return false;
    }

    public static int getSpawnerPickUses(ItemStack i) {
        if(i.getItemMeta() != null && i.getItemMeta().getLore() != null) {
            for(String s : i.getItemMeta().getLore()) {
                if(s.toLowerCase().contains("uses left: ")) {
                    String uses_ = s.substring(s.length() - 2);
                    int uses = Integer.parseInt(uses_.replaceAll("[\\D]", "")); // remove spaces/letters if any
                    Messager.debug("Spawner pick uses left: " + uses);

                    return uses;
                }
            }
        }

        return 0;
    }

    public static List<String> decreaseSpawnerPickUses(ItemStack i) {
        List<String> lore = i.getItemMeta().getLore();

        for(int t = 0; t < lore.size(); t++) {
            String s = lore.get(t);
            if(s.toLowerCase().contains("uses left: ")) {
                Messager.debug("Uses lore on line " + t);
                String uses_ = s.substring(s.length() - 2);
                int uses = Integer.parseInt(uses_.replaceAll("[\\D]", "")); // remove spaces/letters if any
                Messager.debug("Spawner pick uses left: " + uses);
                int newUses = uses - 1;
                String newLore = ChatColor.GRAY + "Uses left: " + newUses;
                Messager.debug("New lore: " + newLore);
                lore.set(t, newLore);

                return lore;
            }
        }
        Messager.debug("Error, returning original lore");
        return lore;
    }

    public static boolean isPlayerWeatherChanger(ItemStack i) {

        if(hasLore(i, playerWeatherLore)) {
            return true;
        }
        return false;
    }

    public static boolean isAutosmeltPick(ItemStack i) {
        //ItemMeta m = i.getItemMeta();
        if(hasLore(i, autosmeltEnchantLore)) {
            return true;
        }
        return false;
    }

    public static void Enchant(Player p, AnvilInventory inv, CEnchant enchant) {
        ItemStack tool = inv.getItem(0);
        ItemStack e = inv.getItem(1);
        ItemStack result = inv.getResult();
        if(enchant == CEnchant.UNBREAKABLE) {
            Messager.debug("Enchanted unbreakable tool");
            inv.remove(tool);
            inv.remove(e);
            p.getInventory().addItem(result);
            inv.remove(result);
        }



    }

    public static boolean hasLore(ItemStack i, String lore) {
        if(i.getItemMeta() != null && i.getItemMeta().getLore() != null) {
            if (i.getItemMeta().getLore().contains(ChatColor.translateAlternateColorCodes('&', lore))) {
                return true;
            }
        }
        return false;
    }

}
