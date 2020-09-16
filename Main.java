package com.TopThreePlayz.Manager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		this.getServer().getPluginManager().registerEvents(this, this);
		getCommand("commandspy").setTabCompleter(new ConstructorTabCompleter());
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("commandspy")) {
			if (!sender.hasPermission("commandspy.reload")) {
				sender.sendMessage(ChatColor.RED + "You cannot run this command");
				return true;
			}
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED + "Usage: /CommandSpy Reload");
				return true;
			}
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("reload")) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
						this.getConfig().getString("reload-message")));
						this.reloadConfig();
				}
			}
			
		}
		return false;
		
	}
	
	
	@EventHandler
	public void commandSpy(PlayerCommandPreprocessEvent e) {
		boolean toggled = getConfig().getBoolean("messages.commandspy-toggled");
		Player player = (Player) e.getPlayer();
		if (player.hasPermission("commandspy.see.commands")) {
			if (toggled == false) {
				return;
			}
			if (toggled == true) {
			String message = getConfig().getString("messages.commandspy-message");
			message.replace("%player%", player.getName());
			message.replace("%message%", e.getMessage());
			message = message.replace("%player%", player.getName()).replace("%command%", e.getMessage());
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
		}
	}
	}
	
	@EventHandler
	public void commandSpySign(SignChangeEvent e) {
		boolean toggled = getConfig().getBoolean("messages.signspy-toggled");
		Player player = (Player) e.getPlayer();
		if (player.hasPermission("commandspy.see.signs")) {
		if (toggled == false) {
			return;
		}
		if (toggled == true) {
			String message = getConfig().getString("messages.signspy-message");
			message.replace("%player%", player.getName());
			message.replace("%message%", e.getLine(0) + e.getLine(1) + e.getLine(2) + e.getLine(3));
			message = message.replace("%player%", player.getName()).replace("%message%", e.getLine(0) + " " + e.getLine(1)  + " " +  e.getLine(2) + " " + e.getLine(3));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
		}
		}
	}
	}


