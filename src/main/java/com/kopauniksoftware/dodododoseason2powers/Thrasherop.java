package com.kopauniksoftware.dodododoseason2powers;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Timer;
import java.util.TimerTask;

public class Thrasherop implements Listener {

    /*
        This class manages the powers for
        player "Thrasherop". His powers
        are being able to switch between
        the nether and overworld (normal
        world) at will, and place water
        in the nether.

        It has one public field:
        permittedPlayer. This is the string
        of the permitted players IGN.
     */

    //permittedPlayer is the player allowed to use these powers
    public String permittedPlayer = "foo";

    //Creates PotionEffect to protect player after a teleport
    private PotionEffect tempRes = new PotionEffect(PotionEffectType.SLOW_FALLING, 15, 1);
    private PotionEffect debuff1 = new PotionEffect(PotionEffectType.SLOW_DIGGING, 200,1);
    private PotionEffect debuff2 = new PotionEffect(PotionEffectType.HUNGER, 200, 1);
    private PotionEffect debuff3 = new PotionEffect(PotionEffectType.WEAKNESS, 200, 1);
    private PotionEffect debuff4 = new PotionEffect(PotionEffectType.SLOW, 200, 5);
    private PotionEffect debuff5 = new PotionEffect(PotionEffectType.SPEED, 50, 10); //Allows player to move in order to survive

    //Sets debuff delay
    long delay = 50;

    //Gets plugin object
    private DoDoDoDoSeason2Powers plugin;
    public Thrasherop (DoDoDoDoSeason2Powers plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void main (PlayerInteractEvent event) {
        /*
            This main method is the
            entry point for the
            powers in this class.
         */

        //Verifies the right player and that the event is a right click with bedrock
        if (event.getPlayer().getName().equals(permittedPlayer)) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
                if (event.getItem().getType() == Material.BEDROCK) {

                    //Gets player object
                    Player player = event.getPlayer();

                    //Determines which world to tp player to
                    if (player.getWorld().getEnvironment() == World.Environment.NORMAL) {
                        normalToNether(player);
                    } else if (player.getWorld().getEnvironment() == World.Environment.NETHER){
                        netherToNormal(player);
                    }
                }
            }
        }
    }

    private void normalToNether (Player player){
        /*
            Teleports inputted
            Player object to
            the corresponding
            location in the nether
         */

        //Creates objects that represent players normal position
        Location oldLocation = player.getLocation();

        double normalX = oldLocation.getX();
        double normalY = oldLocation.getY();
        double normalZ = oldLocation.getZ();

        //Translates normal positions to nether positions
        double netherX = normalX / 8;
        double netherY = normalY;
        double netherZ = normalZ / 8;


        //Creates location object and teleports player there
        Location newLocation = new Location(player.getServer().getWorld("world_nether") , netherX, netherY, netherZ, oldLocation.getYaw(), oldLocation.getPitch());

        String command = "execute in minecraft:the_nether run tp " + player.getName() + " " + netherX + " " + netherY + " " + netherZ + " " + oldLocation.getYaw() + " " + oldLocation.getPitch();
        player.getServer().dispatchCommand(Bukkit.getConsoleSender(),command);

        //player.teleport(newLocation);
        player.sendMessage("Teleported back to nether");

        //Adds potion effect to keep the player safe for a moment
        player.addPotionEffect(tempRes, true);


        //Waits for a set amount of time before debuffing player
        //long delay = 500;

        player.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                //Adds debuff effects
                player.addPotionEffect(debuff1, true);
                player.addPotionEffect(debuff2, true);
                player.addPotionEffect(debuff3, true);
                //player.addPotionEffect(debuff4, true);
                //player.addPotionEffect(debuff5, true);
            }
        }, delay);



    }

    private void netherToNormal (Player player){

        /*
            Teleports Player object
            to corresponding normal
            coords
         */

        //Gets players current nether location
        Location oldLocation = player.getLocation();

        double netherX = oldLocation.getX();
        double netherY = oldLocation.getY();
        double netherZ = oldLocation.getZ();

        //Translates nether coords to overworld coords
        double normalX = netherX * 8;
        double normalY = netherY;
        double normalZ = netherZ * 8;

        //Creates new Location object and teleports player there
        Location newLocation = new Location(player.getServer().getWorld("world"),normalX, normalY, normalZ, oldLocation.getYaw(), oldLocation.getPitch());

        String command = "execute in minecraft:overworld run tp " + player.getName() + " " + normalX + " " + normalY + " " + normalZ + " " + oldLocation.getYaw() + " " + oldLocation.getPitch();
        player.getServer().dispatchCommand(Bukkit.getConsoleSender(),command);

        //player.teleport(newLocation);
        player.sendMessage("Teleported back to Overworld");


        //Adds potion effect to protect player
        player.addPotionEffect(tempRes, true);

        /*
        //Adds debuff effects
        player.addPotionEffect(debuff1, true);
        player.addPotionEffect(debuff2, true);
        player.addPotionEffect(debuff3, true);
        player.addPotionEffect(debuff4, true);
        player.addPotionEffect(debuff5, true);


         */


        //Waits for a set amount of time before debuffing player


        player.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                //Adds debuff effects
                player.addPotionEffect(debuff1, true);
                player.addPotionEffect(debuff2, true);
                player.addPotionEffect(debuff3, true);
                //player.addPotionEffect(debuff4, true);
                //player.addPotionEffect(debuff5, true);
            }
        }, delay);

    }

    @EventHandler
    public void waterInNether (PlayerInteractEvent event){

        /*
            Allows permitted player
            to place water in the
            nether
         */

        //Long list of if statements to make sure its valid to place the water
        if (event.getMaterial() == Material.WATER_BUCKET) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (event.getPlayer().getName().equals(permittedPlayer)) {
                    if (event.getPlayer().getWorld().getEnvironment() == World.Environment.NETHER) {

                        //Creates needed objects
                        BlockFace side = event.getBlockFace();
                        Location clickedBlock = event.getClickedBlock().getLocation();
                        Location newWaterLocation;


                        //Finds the correct side of the block to put the water
                        if (side == BlockFace.NORTH){
                            newWaterLocation = new Location(event.getPlayer().getWorld(), clickedBlock.getX(), clickedBlock.getY(), clickedBlock.getZ() - 1);
                        } else if (side == BlockFace.SOUTH) {
                            newWaterLocation = new Location(event.getPlayer().getWorld(), clickedBlock.getX() , clickedBlock.getY(), clickedBlock.getZ() + 1);
                        } else if (side == BlockFace.EAST) {
                            newWaterLocation = new Location(event.getPlayer().getWorld(), clickedBlock.getX() + 1, clickedBlock.getY(), clickedBlock.getZ());
                        } else if (side == BlockFace.WEST) {
                            newWaterLocation = new Location(event.getPlayer().getWorld(), clickedBlock.getX() - 1, clickedBlock.getY(), clickedBlock.getZ());
                        } else if (side == BlockFace.UP) {
                            newWaterLocation = new Location(event.getPlayer().getWorld(), clickedBlock.getX(), clickedBlock.getY() + 1, clickedBlock.getZ());
                        } else if (side == BlockFace.DOWN){
                            newWaterLocation = new Location(event.getPlayer().getWorld(), clickedBlock.getX(), clickedBlock.getY() - 1, clickedBlock.getZ());
                        } else {
                            newWaterLocation = new Location(event.getPlayer().getWorld(), clickedBlock.getX(), clickedBlock.getY() + 1, clickedBlock.getZ());
                        }

                        //Actually places the water block
                        if (newWaterLocation.getBlock().getType() == Material.AIR || newWaterLocation.getBlock().getType() == Material.LAVA || newWaterLocation.getBlock().getType() == Material.WATER){
                            newWaterLocation.getBlock().setType(Material.WATER);
                        } else {
                            event.setCancelled(true);
                        }

                    }
                }
            }
        }
    }
}
