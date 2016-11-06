package com.chip.pip;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin implements Listener {

    File log;
    FileConfiguration logConfig;
    public static String prefix = ChatColor.BLUE + "pip> " + ChatColor.GRAY;

    @EventHandler
    public void onClick(InventoryClickEvent e){


        if(e.getWhoClicked() instanceof Player) {
            if(e.getClickedInventory().getName().equalsIgnoreCase(ChatColor.BLUE + "Pip> " + ChatColor.DARK_GRAY + "Admin Panel")) {
                e.setResult(Result.DENY);
                e.setCancelled(true);
                if(e.getCurrentItem().getType() == Material.TNT) {

                }else if(e.getCurrentItem().getType() == Material.REDSTONE_TORCH_ON) {

                }else if(e.getCurrentItem().getType() == Material.RED_ROSE) {

                }else if(e.getCurrentItem().getType() == Material.PISTON_BASE) {

                }else if(e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {

                }
            }

        }
    }

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        File logYML = new File(this.getDataFolder()+"logs.yml");
        FileConfiguration logConfig = YamlConfiguration.loadConfiguration(logYML);
        this.log = logYML;
        this.logConfig = logConfig;
        for(Player p:Bukkit.getOnlinePlayers()) {
            p.sendMessage(prefix + "I have been enabled! Use /pip for a panel.");
        }
    }

    public void saveFile(FileConfiguration config, File file) {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onDisable() {
        saveFile(this.logConfig, this.log);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("pip")) { // If the player typed /basic then do the following, note: If you only registered this executor for one command, you don't need this
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if(player.isOp() || player.hasPermission("pip.command")) {
                    openPipGui(player);
                }else{
                    player.sendMessage(prefix + "Hi! I'm Pip, a data-keeping bot. You don't have the permissions to run this command. Sorry!");
                }
            }else {
                sender.sendMessage(prefix + "I bring up an in-game GUI, try doing the command in-game!");
            }
            return true;
        }
        return false;
    }

    public void openPipGui(Player p){
        ItemStack im1 = new ItemStack(Material.TNT, 1);
        ItemMeta im1im = im1.getItemMeta();
        im1im.setDisplayName(ChatColor.GRAY + "Disable " + ChatColor.RED + "TNT");
        im1.setItemMeta(im1im);

        ItemStack im2 = new ItemStack(Material.REDSTONE_TORCH_ON, 1);
        ItemMeta im2im = im2.getItemMeta();
        im2im.setDisplayName(ChatColor.GRAY + "Player " + ChatColor.RED + "Logs");
        im2.setItemMeta(im2im);

        ItemStack im3 = new ItemStack(Material.RED_ROSE, 1);
        ItemMeta im3im = im3.getItemMeta();
        im3im.setDisplayName(ChatColor.GRAY + "Math " + ChatColor.RED + "Utilities");
        im3.setItemMeta(im3im);

        ItemStack im4 = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta im4im = im4.getItemMeta();        //ONLY LAUNCH IF ELYTRA IS ON!
        im4im.setDisplayName(ChatColor.GRAY + "Piston " + ChatColor.RED + "Launcher");
        im4.setItemMeta(im4im);

        ItemStack im5 = new ItemStack(Material.TIPPED_ARROW, 2);
        ItemMeta im5im = im5.getItemMeta();        //ONLY LAUNCH IF ELYTRA IS ON!
        im5im.setDisplayName(ChatColor.GRAY + "Change speed " + ChatColor.RED + "Launcher");
        im5.setItemMeta(im5im);

        ItemStack imp = new ItemStack(Material.PAPER, 1);
        ItemMeta impim = imp.getItemMeta();
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.DARK_RED + "The fun, helpful bot.");
        lore.add(ChatColor.DARK_AQUA + "(He's also an architect)");
        lore.add(ChatColor.GRAY + "Made by " + ChatColor.RED + "ChipDev");
        impim.setDisplayName(ChatColor.BLUE + "Pip> " + ChatColor.GRAY + "The dream machine.");
        impim.setLore(lore);
        imp.setItemMeta(impim);

        ItemStack ima = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)8);
        ItemMeta imaim = ima.getItemMeta();
        imaim.setDisplayName(ChatColor.GRAY + "For " + ChatColor.RED + "Devathon 2016");
        ima.setItemMeta(imaim);

        Inventory pipinv = Bukkit.createInventory(null, 18, ChatColor.BLUE + "Pip> " + ChatColor.DARK_GRAY + "Admin Panel");
        pipinv.setItem(2, im1);
        pipinv.setItem(3, im2);
        pipinv.setItem(4, im3); //I am 3! yes ^w^          PLS GIMME A TSHIRT I LOVE YOU ~CHIPDEV
        pipinv.setItem(5, im4);
        pipinv.setItem(9, ima);
        pipinv.setItem(13, imp);
        p.openInventory(pipinv);
    }


}
