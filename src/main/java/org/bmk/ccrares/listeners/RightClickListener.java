package org.bmk.ccrares.listeners;

import org.bmk.ccrares.util.Items;
import org.bmk.ccrares.util.Messager;
import org.bmk.ccrares.util.RandomFireWorks;
import org.bmk.ccrares.util.Util;
import org.bukkit.*;
import org.bukkit.block.BlastFurnace;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.block.Smoker;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.net.http.WebSocket;
import java.util.Random;

public class RightClickListener implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getPlayer().getItemInHand() != null) {
                ItemStack i = e.getPlayer().getItemInHand();
                Player player = e.getPlayer();

                if (e.getHand() == EquipmentSlot.HAND) {

                    if(Items.isVpToken(i)) {
                        int vp = Items.getVotepointAmount(i);
                        //player.sendMessage("votepoints: " + vp);
                        player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"eco give " + player.getName() + " " + vp);
                    }
                    else if(Items.isClaimblockVoucher(i)) {
                        int vp = Items.getClaimblockAmount(i);
                        //player.sendMessage("votepoints: " + vp);
                        player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"acb " + player.getName() + " " + vp);
                        player.sendMessage(ChatColor.GREEN + "You received " + vp + " claim blocks.");

                    }
                    else if(Items.isRandomVotepoints(i)) {
                        Random rand = new Random();
                        int amt = rand.nextInt(1,11);
                        player.sendMessage(ChatColor.GREEN + "You won " + amt + " vote points!");
                        player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"eco give " + player.getName() + " " + amt);
                    }
                    else if(Items.isReleaseToken(i)) {
                        if(i.getType() == Material.WRITTEN_BOOK) {
                            BookMeta meta = (BookMeta)i.getItemMeta();
                           // meta.
                        }

                    }
                    else if(Items.isStarburstCandy(i)) {
                        if(!Items.isOnCooldown(i)) {
                            if(i.getAmount() == 1) {
                                player.setFoodLevel(20);
                                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 1));
                                Items.setCooldown(i, player, 1800);
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "Please unstack this item before using.");
                            }
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "This item is on cooldown.");
                        }
                    } else if(Items.isWearableHat(i)) {
                        if(e.getPlayer().getInventory().getItem(EquipmentSlot.HEAD).getType() == Material.AIR) {
                            ItemStack is = i.clone();
                            is.setAmount(1);
                            e.getPlayer().getInventory().setItem(EquipmentSlot.HEAD, is);
                            e.getPlayer().getItemInHand().setAmount(e.getPlayer().getItemInHand().getAmount() - 1);
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are already wearing a hat!");
                        }
                    }

                    // Player weather changer
                    else if (Items.isPlayerWeatherChanger(i)) {
                        player.setPlayerWeather(WeatherType.CLEAR);
                        player.sendMessage(ChatColor.GOLD + "Set your weather to clear.");
                    }


                    // Random firework wand
                    else if (Items.isFireworkWand(i)) {

                        if(!Items.isOnCooldown(i)) {
                            //Messager.debug("Launching random firework");
                            Location loc = player.getLocation();
                            RandomFireWorks.getManager().launchRandomFirework(loc);
                            Items.setCooldown(i, player, 1);
                        }
                    }


                    // Celestial Toolbox
                    else if (Items.isCelestialToolbox(i)) {
                        if (Util.getOpenSlots(player) > 2) {
                            player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "serveritem give celestial_pickaxe 1 " + player.getName());
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "serveritem give celestial_axe 1 " + player.getName());
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "serveritem give celestial_shovel 1 " + player.getName());
                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have enough inventory space to use this item.");
                        }
                        e.setCancelled(true);
                    }
                    else if (Items.isPlantBox(i)) {
                        if (Util.getOpenSlots(player) > 4) {
                            player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "serveritem give fiddle_leaf_plant 1 " + player.getName());
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "serveritem give hanging_daisy 1 " + player.getName());
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "serveritem give aralia_plant 1 " + player.getName());
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "serveritem give hanging_pothos 1 " + player.getName());
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "serveritem give snake_plant 1 " + player.getName());


                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have enough inventory space to use this item.");
                        }
                        e.setCancelled(true);
                    }


                /*
                        RIGHT CLICK BLOCK ONLY
                 */
                    if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        if(Items.isFurnaceFuel15(i)) {
                            if (e.getClickedBlock().getType() == Material.FURNACE) {

                                Furnace f = (Furnace) e.getClickedBlock().getState();
                                //Messager.debug("Setting burn time to 100");
                                f.setBurnTime((short) 18000); // 15 mins * 60 seconds * 20 ticks (burn time is in ticks)
                                f.update();
                                e.setCancelled(true);


                            } else if (e.getClickedBlock().getType() == Material.BLAST_FURNACE) {

                                BlastFurnace f = (BlastFurnace) e.getClickedBlock().getState();
                                //Messager.debug("Setting burn time to 100");
                                f.setBurnTime((short) 18000); // 15 mins * 60 seconds * 20 ticks (burn time is in ticks)
                                f.update();
                                e.setCancelled(true);

                            } else if (e.getClickedBlock().getType() == Material.SMOKER) {
                                Smoker smoker = (Smoker) e.getClickedBlock().getState();
                                smoker.setBurnTime((short) 18000); // 15 mins * 60 seconds * 20 ticks (burn time is in ticks)
                                smoker.update();
                                e.setCancelled(true);
                            }
                        }
                    }
                }
            }


        }

    }
}
