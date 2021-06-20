package com.kopauniksoftware.dodododoseason2powers;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class AntiBedrock implements Listener {

    @EventHandler
    public void main(BlockPlaceEvent event){

        //If bedrock is placed and the player isn't in creative it undoes the action
        if (event.getBlock().getType() == Material.BEDROCK){

            System.out.println("Bedrock has attempted to be placed");

            if (event.getPlayer().getGameMode() != GameMode.CREATIVE){
                event.setCancelled(true);
            }
        }
    }

}
