package com.dragovorn.argonaut.core.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public final class ArgonautCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // TODO: fun stuff

        sender.sendMessage(ChatColor.RED + "Please specify a sub-command to execute!");
        return false;
    }
}
