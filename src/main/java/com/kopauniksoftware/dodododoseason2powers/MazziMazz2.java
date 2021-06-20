package com.kopauniksoftware.dodododoseason2powers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MazziMazz2 implements Listener {

    String permittedPlayer = "MazziMazz";
    private boolean powersActive = false;

    //Buff objects
    private PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 999999999,4);
    private PotionEffect jump = new PotionEffect(PotionEffectType.JUMP, 999999999, 3);

    @EventHandler
    public void main(PlayerInteractEvent event){


        if (event.getPlayer().getName().toString().equals(permittedPlayer)){
            if (event.getItem().getType() == Material.BEDROCK){
                if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

                    if (powersActive) {
                        deactivate(event.getPlayer());
                    } else {
                        activate(event.getPlayer());
                    }
                }
            }
        }

    }

    private void deactivate(Player player){

        player.removePotionEffect(PotionEffectType.SPEED);
        player.removePotionEffect(PotionEffectType.JUMP);

        powersActive = false;

    }

    private void activate(Player player){
        player.addPotionEffect(speed);
        player.addPotionEffect(jump);

        powersActive = true;
    }

}
