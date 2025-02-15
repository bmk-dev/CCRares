package org.bmk.ccrares.listeners;

import org.bmk.ccrares.Rares;
import org.bmk.ccrares.util.Items;
import org.bmk.ccrares.util.Messager;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

public class AnvilEvent implements Listener {

    @EventHandler
    public void onAnvilCraft(PrepareAnvilEvent e) {
        AnvilInventory inv = e.getInventory();

        ItemStack tool = inv.getItem(0);
        ItemStack enchant = inv.getItem(1);
        if(tool != null) {
            if(enchant != null) {
                // Unbreakable enchant
                if (Items.isUnbreakableBook(enchant)) {
                    Messager.debug("Tool type: " + tool.getType().toString());
                    ItemStack output = tool.clone();

                    Messager.debug("Enchant base item: " + enchant.getType().toString());
                    Messager.debug("Detected both anvil slots occupied");
                    ItemMeta meta = tool.getItemMeta();
                    meta.setUnbreakable(true);
                    output.setItemMeta(meta);
                    e.setResult(output);
                    // Rares.plugin.getServer().getScheduler().runTask(Rares.plugin, () -> e.getView().setRepairCost(0));
                }
                /*else if(enchant.getType() == Material.ENCHANTED_BOOK) {
                    String toolType = tool.getType().toString();
                    if(toolType.contains("SWORD") || toolType.contains("PICKAXE") || toolType.contains("AXE") || toolType.contains("HOE") || toolType.contains("SHOVEL") || toolType.contains("BOW")) {
                        Map<Enchantment, Integer> enchants = enchant.getEnchantments();
                        Messager.debug("VANILLA ENCHANT DETECTED");
                        Messager.debug("Tool type: " + tool.getType().toString());
                        ItemStack output = tool.clone();

                        Messager.debug("Enchant base item: " + enchant.getType().toString());
                        Messager.debug("Detected both anvil slots occupied");
                        ItemMeta meta = tool.getItemMeta();
                        for (Enchantment en : enchants.keySet()) {
                            int level = enchants.get(en);
                            output.addUnsafeEnchantment(en, level);

                            Messager.debug("Applying enchant: " + en.getName() + ", level " + level);
                        }
                        output.setItemMeta(meta);
                        e.setResult(output);
                    }
                }*/

                else {
                    Messager.debug("Not unbreakable book");
                }
            }
        }
    }
}
