package com.scorp.fairanvil;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
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
    public void EnchantTable(EnchantItemEvent e) {
        Player player = e.getEnchanter();
        int levelCost = 1 + e.whichButton();
        player.giveExpLevels(levelCost);
        Experience.changeExp(player, -Experience.getExpFromLevel(levelCost));
    }

    @EventHandler
    public void InventoryClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player){
            if (toggled){
                Player player = (Player) e.getWhoClicked();
                if (e.getInventory().getType() == InventoryType.ANVIL){
                    AnvilInventory anvil = (AnvilInventory) e.getInventory();
                    InventoryType.SlotType slotType = e.getSlotType();
                    if (slotType == InventoryType.SlotType.RESULT){
                        if (anvil.getRepairCost() <= player.getLevel() || player.getGameMode() == GameMode.CREATIVE){
                            player.setLevel(player.getLevel() + anvil.getRepairCost());
                            Experience.changeExp(player, -Experience.getExpFromLevel(anvil.getRepairCost()));
                        }
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PrepareAnvil(PrepareAnvilEvent e) {
        Player p = (Player) e.getViewers().get(0);
        AnvilInventory inv = e.getInventory();

        inv.setMaximumRepairCost(Integer.MAX_VALUE);
    }


}
