package com.kopauniksoftware.dodododoseason2powers;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;

public class Nimmmmmmmm implements Listener {

    /*
        This class handles the powers
        for "Nimmmmmmmm". Thic class
        gives her powers of no fall
        damage and infinite water breathing


        It has one field "Permitted Player",
        which is a string of the permitted
        players name.


        This class also has only two public
        methods: main() and waterBreathing()


     */

    public String permittedPlayer = "nimmmmmmmm";

    //Damage object for increased fire damage
    PotionEffect damage = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10, 2);


    @EventHandler
    public void main(EntityDamageEvent event){
        /*
            Takes in EntityDamageEvent
            and determines if it is
            fall damage for the permittedPlayer.
            If it is, it cancels the damage.
         */


        //Checks if its a player that is taking damage. If it is, it gets player object
        if (event.getEntityType() == EntityType.PLAYER){
            Player player = (Player) event.getEntity();

            //If it is permitted player and it is fall damage, cancel the event
            if (player.getName().equals(permittedPlayer)){
                if (event.getCause() == EntityDamageEvent.DamageCause.FALL){
                    event.setCancelled(true);
                }
            }
        }

        //Checks if player is taking fire damage

        if (event.getEntityType() == EntityType.PLAYER){
            if (event.getCause() == EntityDamageEvent.DamageCause.FIRE){

                System.out.println("now");
                Player player = (Player) event.getEntity();

                if (player.getName().equals(permittedPlayer)) {

                    System.out.println("here");
                    player.damage(5);
                }
            }
        }
    }



    @EventHandler
    public void waterBreathing (PlayerInteractEvent event) {
        /*
            Takes in PlayerInteractEvent
            and, if the player is the
            permitted player, and the
            player doesn't have water
            breathing, it gives water
            breathing effect.
         */

        //Checks if player is permittedPlayer
        if (event.getPlayer().getName().equals(permittedPlayer)) {

            //Creates PotionEffect Collection that has players potion effects
            Collection<PotionEffect> playersPots = event.getPlayer().getActivePotionEffects();

            //If player doesn't have water breathing, apply water breathing
            if (!playersPots.contains(PotionEffectType.WATER_BREATHING)) {
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 50000, 1));
            }
        }
    }
}
