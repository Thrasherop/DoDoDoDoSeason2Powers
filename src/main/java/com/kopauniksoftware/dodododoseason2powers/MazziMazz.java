package com.kopauniksoftware.dodododoseason2powers;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MazziMazz implements Listener {

    String permittedPlayer = "Foo";
    PotionEffectType[] potionBuffs = {PotionEffectType.SPEED, PotionEffectType.FAST_DIGGING, PotionEffectType.JUMP};

    @EventHandler
    public void main(PlayerMoveEvent event){

        if (event.getPlayer().getName().equals(permittedPlayer)){

            Player player = event.getPlayer();

            if (isUnderground(player)){

                //Removes debuffs
                player.removePotionEffect(PotionEffectType.SLOW);

                //Adds buffs
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000, 2));
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000, 2));
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 1000, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000, 1));


            } else if (!isUnderground(player)){

                //Removes buffs
                player.removePotionEffect(PotionEffectType.SPEED);
                player.removePotionEffect(PotionEffectType.FAST_DIGGING);
                player.removePotionEffect(PotionEffectType.JUMP);
                player.removePotionEffect(PotionEffectType.REGENERATION);
                player.removePotionEffect(PotionEffectType.NIGHT_VISION);


                //Adds debuffs
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000, 2));
            }
        }

    }

    private boolean isUnderground(Player player){

        //System.out.println("isUnderground called");

        World world = player.getWorld();
        Location playerLocation = player.getLocation();
        Block highestBlock = player.getWorld().getHighestBlockAt(playerLocation);

        int maxY = highestBlock.getY();
        int playerY = (int) player.getLocation().getY();

        if (playerY < maxY || playerY <= 50){
            //System.out.println("is underground returns true");
            return true;
        } else {
            //System.out.println("isUnderground returns false");
            return false;
        }
    }



}
