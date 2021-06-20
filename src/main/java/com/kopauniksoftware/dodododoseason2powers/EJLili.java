package com.kopauniksoftware.dodododoseason2powers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;

public class EJLili implements Listener {

    String permittedPlayer = "EJLili";

    @EventHandler
    public void fireProof (PlayerInteractEvent event) {
        /*
            Takes in PlayerInteractEvent
            and, if the player is the
            permitted player, and the
            player doesn't have fire
            res, then grants it
         */

        //Checks if player is permittedPlayer
        if (event.getPlayer().getName().equals(permittedPlayer)) {

            //Creates PotionEffect Collection that has players potion effects
            Collection<PotionEffect> playersPots = event.getPlayer().getActivePotionEffects();

            //If player doesn't have water breathing, apply water breathing
            if (!playersPots.contains(PotionEffectType.FIRE_RESISTANCE)) {
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 50000, 1));
            }
        }
    }
}
