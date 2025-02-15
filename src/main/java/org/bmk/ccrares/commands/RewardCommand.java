package org.bmk.ccrares.commands;

import org.bmk.ccrares.util.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RewardCommand implements CommandExecutor {
    /*
    This is the command that will be bound to the NPC at spawn to give players rewards
     */

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String cmd, @NotNull String[] args) {
        if(cmd.equalsIgnoreCase("reward")) {
            if(sender.hasPermission("ccrares.admin")) {

                // Make sure there are 2 command arguments (/reward <event> <player>)
                if(args.length == 2) {
                    // Reward for Valentine's event 2025
                    if(args[0].equalsIgnoreCase("vday25")) {
                        String pname = args[1]; // Get player name from second command argument
                        Player p = Bukkit.getPlayer(pname); // Get player from player name

                        // Add the item that is created in Items.getCelesteSunnies to player's inv
                        p.getInventory().addItem(Items.getCelesteSunnies(p));
                    }
                }
                else {
                    // Command did not have the correct amount of arguments
                    sender.sendMessage(ChatColor.RED + "Invalid usage! /reward <event> <player>");
                }
            }
        }
        return false;
    }
}
