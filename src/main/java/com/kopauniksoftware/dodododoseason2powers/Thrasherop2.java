package com.kopauniksoftware.dodododoseason2powers;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.Date;

public class Thrasherop2 implements Listener {

    String permittedPlayer = "Thrasherop";

    //Value for max teleport
    private int maxTPDistance = 100;

    //Misc objects
    private Player thisPlayer;
    private long lastTP = 0;

    //global variables for waterDamage optimization
    private Block thisBlock;



    @EventHandler
    public void main(PlayerInteractEvent event){
        /*
            Manages the actual pearl throwing
            ability
         */

        if (event.getPlayer().getName().toString().equals(permittedPlayer)) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
                if (event.getItem().getType() == Material.BEDROCK){

                    //Throws pearl
                    Player player = event.getPlayer();
                    player.launchProjectile(EnderPearl.class);

                }

            } else if (event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR){
                if (event.getItem().getType() == Material.BEDROCK) {
                    //Teleports to block
                    Player player = event.getPlayer();

                    //Gets the block and location
                    Block block = player.getTargetBlockExact(maxTPDistance);
                    Location thisLoc = block.getLocation();

                    //Gets objects needed
                    Location changeLoc = thisLoc;
                    Location destination;

                    char thisYaw = getYaw(player);
                    World world = player.getWorld();

                    boolean doTP = true;

                    //Chain of logic to determine what block is safe if any

                    if (world.getBlockAt(changeLoc.add(0, 1, 0)).getType() == Material.AIR) {
                        destination = thisLoc;

                    } else if (player.getLocation().getPitch() == 90) {

                        destination = player.getTargetBlockExact(maxTPDistance).getLocation();

                    }else {

                        //Resets changeLoc
                        changeLoc = block.getLocation();

                        //Declares otherSafeBlock object
                        Block otherSafeBlock;

                        //Finds which one would be the other safeBlock
                        if (thisYaw == 'N') {
                            otherSafeBlock = world.getBlockAt(changeLoc.add(0,0,1));
                        } else if (thisYaw == 'S') {
                            otherSafeBlock = world.getBlockAt(changeLoc.add(0,0,-1));
                        } else if (thisYaw == 'E') {
                            otherSafeBlock = world.getBlockAt(changeLoc.add(-1,0,0));
                        } else if (thisYaw == 'W') {
                            otherSafeBlock = world.getBlockAt(changeLoc.add(1,0,0));
                        } else {
                            //If something goes wrong, then it won't teleport
                            destination = block.getLocation();//Initializes destination in order to satisfy compiler
                            player.sendMessage("Can't lock onto a safe teleport");
                            doTP = false;
                            return;
                        }

                        //Takes the safe block and determines if its actually safe
                        if (otherSafeBlock.getType() == Material.AIR){
                            destination = otherSafeBlock.getLocation();
                        } else {
                            //If its not safe, then this runs

                            destination = block.getLocation();//Initializes destination in order to satisfy compiler
                            player.sendMessage("Can't lock onto a safe teleport");
                            doTP = false;
                            return;
                        }


                    }


                    if (doTP) {
                        destination.setPitch(player.getLocation().getPitch());
                        destination.setYaw(player.getLocation().getYaw());
                        player.teleport(destination);
                    }
                }
            }
        }
    }

    @EventHandler
    public void noPearlDamage(PlayerTeleportEvent event){
        /*
            Stops pearls from dealing damage to
            the permittedPlayer
         */

        if (event.getPlayer().getName().toString().equals(permittedPlayer)){
            if (event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL){

                Player player = event.getPlayer();

                //Replaces pearl event with normal tp
                event.setCancelled(true);
                player.teleport(event.getTo());

                //Updates last tp
                lastTP = System.currentTimeMillis();

            }
        }

    }

    @EventHandler
    public void noFallDamage(EntityDamageEvent event){

        /*
            This class helps protect the player
            from fall damage for .5 seconds after
            the last pearl.

            This is needed because pearls usually
            break fall damage, but noPearlDamage
            doesn't do this by itself.
         */

        if (event.getEntityType() == EntityType.PLAYER){
            if (event.getEntity().getName().toString().equals(permittedPlayer)){
                if (event.getCause() == EntityDamageEvent.DamageCause.FALL){

                    //Gets current time
                    long curTime = System.currentTimeMillis();

                    //Gets elapsed time since last tp
                    long elapsed = curTime - lastTP;

                    //Checks if the difference is less than 1 second
                    if (elapsed <= 500){
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void waterDamage(PlayerMoveEvent event){

        if (event.getPlayer().getName().toString().equals(permittedPlayer)){

            //Stops damage event if player is in boat
            if (event.getPlayer().isInsideVehicle()){
                //System.out.println("Player is inside vehicle");
                return;
            }

            Material m = event.getPlayer().getLocation().getBlock().getType();

            if ( m == Material.WATER) {
                // player is in water
                event.getPlayer().damage(1);
            }

        }

    }

    private static char getYaw(Player p) {
        float yaw = p.getLocation().getYaw();
        yaw = (yaw % 360 + 360) % 360; // true modulo, as javas modulo is weird for negative values
        if (yaw > 135 || yaw < -135) {
            return 'N';
        } else if (yaw < -45) {
            return 'E';
        } else if (yaw > 45) {
            return 'W';
        } else {
            return 'S';
        }
    }
}
