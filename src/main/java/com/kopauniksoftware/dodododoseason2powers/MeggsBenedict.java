package com.kopauniksoftware.dodododoseason2powers;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class MeggsBenedict implements Listener {

    String permittedPlayer = "MeggsBenedict";

    @EventHandler
    public void main(PlayerInteractEvent event) {

        //Performs check that the player is permitted player and that it is a left click using bedrock
        if (event.getPlayer().getName().equals(permittedPlayer)) {
            if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                if (event.getItem().getType() == Material.BEDROCK) {
                    //Gets player object
                    Player player = event.getPlayer();

                    //Performs gamemode switch
                    if (player.getGameMode() == GameMode.SURVIVAL) {
                        SurvToSpec(player);
                    } else if (player.getGameMode() == GameMode.SPECTATOR){
                        SpecToSurv(player);
                    }

                //This code is depreciated I think
                } else if (event.getPlayer().getGameMode() == GameMode.SPECTATOR) {
                    SpecToSurv(event.getPlayer());
                }
            }
        }
    }


    public void SpecToSurv (Player player){
        //Changes player gamemode
        player.setGameMode(GameMode.SURVIVAL);
    }

    public void SurvToSpec (Player player){
        //changes player gamemode
        player.setGameMode(GameMode.SPECTATOR);

        int newSat = player.getFoodLevel() - 8;
        player.setFoodLevel(newSat);
    }


}
