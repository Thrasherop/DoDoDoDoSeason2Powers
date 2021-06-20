package com.kopauniksoftware.dodododoseason2powers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class PlayerSpawn implements Listener {

    @EventHandler
    public void OnSpawn (PlayerRespawnEvent event){
        //Creates a String list with IGN's that use bedrock
        ArrayList<String> bedrockUsers = new ArrayList<String>(7);
        bedrockUsers.add("Thrasherop");
        bedrockUsers.add("MeggsBenedict");
        bedrockUsers.add("MazziMazz");

        //Gives player bedrock when they respawn if they are in the bedrockPLayersList
        if (bedrockUsers.contains(event.getPlayer().getName())) {
            event.getPlayer().getInventory().addItem(new ItemStack(Material.BEDROCK, 1));
        }
    }
}
