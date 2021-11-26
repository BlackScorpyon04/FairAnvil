package com.scorp.fairanvil;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ToggleCommand implements CommandExecutor {

    FairAnvil plugin;

    public ToggleCommand(FairAnvil fairAnvil) {
        plugin = fairAnvil;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("fairanvils.toggle")){
            plugin.toggled = !plugin.toggled;
        }
        return true;
    }
}
