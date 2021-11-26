package com.scorp.fairanvil;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Locale;

public class ToggleCommand implements CommandExecutor {

    FairAnvil plugin;

    public ToggleCommand(FairAnvil fairAnvil) {
        plugin = fairAnvil;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("fairanvils.toggle")){
            if (args[0].toLowerCase().equals("toggle")){
                plugin.toggled = !plugin.toggled;
                if (plugin.toggled){
                    sender.sendMessage("FairAnvils enabled");
                }else{
                    sender.sendMessage("FairAnvils disabled");
                }
            }
        }
        return true;
    }
}
