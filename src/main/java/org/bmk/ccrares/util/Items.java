package org.bmk.ccrares.util;

import io.papermc.paper.datacomponent.item.WrittenBookContent;
import net.kyori.adventure.inventory.Book;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.EquippableComponent;
import org.bukkit.inventory.view.AnvilView;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Items {


    public static NamespacedKey releaseTokenKey = new NamespacedKey("ccrares", "rt-names");
    public static NamespacedKey cooldownKey = new NamespacedKey("ccrares", "cooldown");

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
    public static String starburstLore = "&7Starburst Candy"; // Speed 2 30s,
    public static String releaseTokenLore = "&7Release Token";
    public static String randomVpLore = "&7Random Votepoints";
    public static String celestialEnergyLore = "&7Celestial Energy";
    public static String vpAmountLore = "&7Votepoint Voucher";
    public static String claimblockVoucherLore = "&7Claimblock Voucher";
    public static String cherryParticleLore = "&7Cherry Companion Particles";
    public static String phantomRepellantLore = "&7Phantom Repellant";


    public static void setCooldown(ItemStack i, Player p, int cooldownSeconds) {
        long cooldownEndTime = (cooldownSeconds * 1000) + System.currentTimeMillis();
        ItemMeta meta = i.getItemMeta();
        meta.getPersistentDataContainer().set(cooldownKey, PersistentDataType.LONG, cooldownEndTime);
        i.setItemMeta(meta);
        p.setItemInHand(i);
    }

    public static boolean isOnCooldown(ItemStack i) {
        ItemMeta meta = i.getItemMeta();
        if(meta.getPersistentDataContainer().has(cooldownKey)) {
            Messager.debug("Found PDC");
            long cooldownTime = meta.getPersistentDataContainer().get(cooldownKey, PersistentDataType.LONG);
            Messager.debug("Cooldowntime: " + cooldownTime);
            if(System.currentTimeMillis() < cooldownTime) {
                Messager.debug("Item on cooldown");
                return true;

            }
            else {
                Messager.debug("Item not on cooldown any longer");
                return false;
            }
        }
        Messager.debug("Item not on cooldown");
        return false;
    }

    /*
    This method creates the actual reward item
     */
    public static ItemStack getCelesteSunnies(Player p) {
        // Create the item
        ItemStack sunnies = new ItemStack(Material.PAPER);
        ItemMeta sm = sunnies.getItemMeta(); // Get the item meta (contains lore, etc.)

        // Add the item name
        String name = Util.translateColors("&#D8CDF0❤ &#DFC0E9☀ &#E5B4E2C&#E8ADDEe&#ECA7DBl&#EFA1D7e&#F29AD3s&#F594D0t&#F98ECCe&#FC87C9'&#FF81C5s " +
                "&#F98ECCS&#F594D0u&#F29AD3n&#EFA1D7n&#ECA7DBi&#E8ADDEe&#E5B4E2s &#DFC0E9☀ &#D8CDF0❤");
        sm.setDisplayName(name);

        // Add the lore
        ArrayList<String> lore = new ArrayList<>();
        lore.add(Util.translateColors(cherryParticleLore));
        lore.add(Util.translateColors(phantomRepellantLore));
        lore.add(Util.translateColors(wearableHatLore));
        lore.add(" ");
        lore.add(Util.translateColors("&fThank you for finding my"));
        lore.add(Util.translateColors("&fsecret admirer, " + p.getName() + "!"));
        lore.add( Util.translateColors("&fThese &#D8CDF0V&#DACAEEa&#DCC6ECl&#DDC3EAe&#DFBFE8n&#E1BCE6t&#E3B8E4i&#E4B5E2n&#E6B1E0e&#E8AEDE’&#EAAADCs &#EDA4D9D&#EFA0D7a&#F19DD5y "));
        lore.add(Util.translateColors("&#F496D1S&#F692CFu&#F88FCDn&#FA8BCBn&#FB88C9i&#FD84C7e&#FF81C5s &fare perfect for"));
        lore.add( Util.translateColors("&fwhen your Valentine"));
        lore.add( Util.translateColors("&fis as bright as the stars!"));
        lore.add(Util.translateColors("                &#FFEF78- C&#D7EBA3e&#AFE8CEl&#87E4F9e&#AFC0E4s&#D79CCFt&#FF78BAe"));
        lore.add(" ");
        lore.add(ChatColor.DARK_GRAY + "Texture: Batberrii & Orcox");
        lore.add(" ");
        lore.add(Util.translateColors("&8==== &#E24E9DV&#E656A5a&#E95EADl&#ED66B4e&#F16EBCn&#F475C4t&#F87DCCi&#FB85D3n&#FF8DDBe&#FB84D2’&#F77BC9s &#EE69B82&#EA60AF0&#E657A62&#E24E9D5 &8===="));

        // Apply lore to item meta
        sm.setLore(lore);
        // Apply custom texture to item meta
        sm.setCustomModelData(10049);

        // Apply item meta to item
        sunnies.setItemMeta(sm);

        // Return the item
        return sunnies;
    }

    public static boolean isClaimblockVoucher(ItemStack i) {
        if(hasLore(i, claimblockVoucherLore)) {
            return true;
        }
        return false;
    }

    public static boolean isVpToken(ItemStack i) {
        if(hasLore(i, vpAmountLore)) {
            return true;
        }
        return false;
    }

    public static void openReleaseTokenBook(Player p, ItemStack i) {
        ArrayList<String> names = getReleaseTokenNames(i);
        String page = buildPage(names);

    }

    public static String buildPage(ArrayList<String> names) {
        StringBuilder sb = new StringBuilder();
        if(names != null && names.size() > 0) {
            sb.append(names.get(0));
            Messager.debug("Adding name to page: " + names.get(0));
            for (int i = 1; i < names.size(); i++) {
                sb.append(names.get(i) + ", ");
                Messager.debug("Adding name to page: " + names.get(i));
            }
        }
        return sb.toString();
    }

    public static ArrayList<String> getReleaseTokenNames(ItemStack i) {
        PersistentDataContainer container = i.getItemMeta().getPersistentDataContainer();
        if(container.has(releaseTokenKey, PersistentDataType.LIST.strings())) {
            Messager.debug("Found string list in PDC");
            List<String> list = container.get(releaseTokenKey, PersistentDataType.LIST.strings());
            if(list != null) {
                return new ArrayList<>(list);
            }
        }
        Messager.debug("No string list in PDC");
        return null;
    }

    public static void addReleaseTokenName(Player user, ItemStack i, Player newPlayer) {
        ArrayList<String> list = getReleaseTokenNames(i);
        if(list == null) {
            list = new ArrayList<>();
            Messager.debug("No list found in PDC, creating new...");
            list.add(newPlayer.getName());
        }
        else {
            Messager.debug("List found in PDC: ");
            for(String s : list) {
                Messager.debug(s);
            }
            if(!list.contains(newPlayer.getName())) {
                list.add(newPlayer.getName());
            }

        }

        BookMeta meta = (BookMeta) i.getItemMeta();
        meta.getPersistentDataContainer().set(releaseTokenKey, PersistentDataType.LIST.strings(), list);
        i.setItemMeta(meta);
        String s = buildPage(list);
        meta.addPage(s);
        user.setItemInHand(i);


    }

    public static boolean isRandomVotepoints(ItemStack i) {
        if(hasLore(i, randomVpLore)) {
            return true;
        }
        return false;
    }

    public static boolean isStarburstCandy(ItemStack i) {
        if(hasLore(i, starburstLore)) {
            return true;
        }
        return false;
    }

    public static boolean isReleaseToken(ItemStack i) {
        if(hasLore(i, releaseTokenLore)) {
            return true;
        }
        return false;
    }

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

    public static int getClaimblockAmount(ItemStack i) {
        if(i.getItemMeta() != null && i.getItemMeta().getLore() != null) {
            for(String s : i.getItemMeta().getLore()) {
                if(s.toLowerCase().contains("claimblocks: ")) {
                    String vps_ = s.substring(s.length() - 6);
                    int vps = Integer.parseInt(vps_.replaceAll("[\\D]", "")); // remove spaces/letters if any
                    Messager.debug("Claim block voucher amount: " + vps);

                    return vps;
                }
            }
        }

        return 0;
    }

    public static int getVotepointAmount(ItemStack i) {
        if(i.getItemMeta() != null && i.getItemMeta().getLore() != null) {
            for(String s : i.getItemMeta().getLore()) {
                if(s.toLowerCase().contains("votepoints: ")) {
                    String vps_ = s.substring(s.length() - 2);
                    int vps = Integer.parseInt(vps_.replaceAll("[\\D]", "")); // remove spaces/letters if any
                    Messager.debug("Vote point token amount: " + vps);

                    return vps;
                }
            }
        }

        return 0;
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
