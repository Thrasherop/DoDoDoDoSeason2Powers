package com.kopauniksoftware.dodododoseason2powers;

import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class RandomJuggernauts implements Listener {

    private int chance = 100;

    private Random rando = new Random();
    private int thisChance = rando.nextInt(chance);

    ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
    ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
    ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
    ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);

    Zombie thisZombie;
    Skeleton thisSkeleton;
    Slime thisSlime;
    PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 100000, 2);
    PotionEffect strength = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100000, 2);

    @EventHandler
    public void main(EntitySpawnEvent event){

        if (thisChance == 1){

            Entity entity = event.getEntity();

            if (entity.getType() == EntityType.ZOMBIE){
                thisZombie = (Zombie) entity;
                thisZombie.addPotionEffect(speed);
            } else if (entity.getType() == EntityType.SKELETON){
                thisSkeleton = (Skeleton) event.getEntity();
                thisSkeleton.addPotionEffect(speed);
            } else if (entity.getType() == EntityType.SLIME){
                thisSlime = (Slime) event.getEntity();
                thisSlime.addPotionEffect(strength);
            }
        }

        //Gets new chance
        thisChance = rando.nextInt(chance);

    }

}
