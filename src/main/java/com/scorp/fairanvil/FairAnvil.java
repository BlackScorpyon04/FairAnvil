package com.scorp.fairanvil;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;
import org.bukkit.plugin.java.JavaPlugin;

public final class FairAnvil extends JavaPlugin implements Listener {

    public boolean toggled = true;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("fairanvils").setExecutor(new ToggleCommand(this));
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void InventoryClick (InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player){
            if (toggled){
                Player player = (Player) e.getWhoClicked();

                if (e.getInventory().getType() == InventoryType.ANVIL){
                    AnvilInventory anvil = (AnvilInventory) e.getInventory();
                    InventoryType.SlotType slotType = e.getSlotType();
                    if (slotType == InventoryType.SlotType.RESULT){
                        player.setLevel(player.getLevel() + anvil.getRepairCost());
                        Experience.changeExp(player, -Experience.getExpFromLevel(anvil.getRepairCost()));
                    }
                }
            }
        }
    }


}
