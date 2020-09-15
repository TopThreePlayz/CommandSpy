package com.TopThreePlayz.Manager;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class ConstructorTabCompleter implements TabCompleter {

	
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args){
        if(command.getName().equalsIgnoreCase("commandspy") && args.length == 1) { //if we are on the first arg
            if(sender instanceof Player) { //and we are a player
                //return these commands to auto complete with
                return Arrays.asList("reload");
            }
        }
        return null;
       
    }
}
