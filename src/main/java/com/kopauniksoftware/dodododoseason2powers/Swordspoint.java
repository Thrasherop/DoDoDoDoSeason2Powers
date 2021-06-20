package com.kopauniksoftware.dodododoseason2powers;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Map;

public class Swordspoint implements Listener {

    String permittedPlayer = "Thrasherop";

    //Creates arraylist of banned blocks
    ArrayList<Material> bannedBlocks = new ArrayList<Material>();

    Swordspoint(){
        /*
            Constructor that fills the
            bannedBlocks ArrayList
         */

        bannedBlocks.add(Material.SPAWNER);
        bannedBlocks.add(Material.CAKE);
        bannedBlocks.add(Material.ACACIA_LEAVES);
        bannedBlocks.add(Material.BIRCH_LEAVES);
        bannedBlocks.add(Material.DARK_OAK_LEAVES);
        bannedBlocks.add(Material.JUNGLE_LEAVES);
        bannedBlocks.add(Material.SPRUCE_LEAVES);
        bannedBlocks.add(Material.OAK_LEAVES);
        bannedBlocks.add(Material.LARGE_FERN);
        bannedBlocks.add(Material.GRASS);
        bannedBlocks.add(Material.TALL_GRASS);
        bannedBlocks.add(Material.SEAGRASS);
        bannedBlocks.add(Material.TALL_SEAGRASS);


        //Theres a lot of different bed types
        bannedBlocks.add(Material.BLACK_BED);
        bannedBlocks.add(Material.RED_BED);
        bannedBlocks.add(Material.BLUE_BED);
        bannedBlocks.add(Material.BROWN_BED);
        bannedBlocks.add(Material.CYAN_BED);
        bannedBlocks.add(Material.GRAY_BED);
        bannedBlocks.add(Material.GREEN_BED);
        bannedBlocks.add(Material.LIGHT_BLUE_BED);
        bannedBlocks.add(Material.LIGHT_GRAY_BED);
        bannedBlocks.add(Material.LIME_BED);
        bannedBlocks.add(Material.MAGENTA_BED);
        bannedBlocks.add(Material.ORANGE_BED);
        bannedBlocks.add(Material.PINK_BED);
        bannedBlocks.add(Material.PURPLE_BED);
        bannedBlocks.add(Material.WHITE_BED);
        bannedBlocks.add(Material.YELLOW_BED);
    }

    @EventHandler
    public void main(BlockBreakEvent event){

        //If the player isn't in Survival, then it exits
        if (event.getPlayer().getGameMode() != GameMode.SURVIVAL){
            return;
        }

        if (event.getPlayer().getName().equals(permittedPlayer)) {

            //Gets tool related objects
            ItemStack tool = event.getPlayer().getItemInHand();
            Map<Enchantment, Integer> toolEnchants = tool.getEnchantments();

            //If the Tool has silk touch, it exits
            if (tool.containsEnchantment(Enchantment.SILK_TOUCH)) {
                return;
            }

            //Finds out how many items should be dropped
            int numDrops;

            //Creates arraylist with all pickaxes
            ArrayList<Material> pickaxes = new ArrayList();
            pickaxes.add(Material.DIAMOND_PICKAXE);
            pickaxes.add(Material.WOODEN_PICKAXE);
            pickaxes.add(Material.STONE_PICKAXE);
            pickaxes.add(Material.IRON_PICKAXE);
            pickaxes.add(Material.GOLDEN_PICKAXE);


            if (tool.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
                numDrops = tool.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
            } else if (tool.getType() == Material.AIR) {
                numDrops = 1;
            } else if (pickaxes.contains(tool.getType())) {
                numDrops = 2;
            } else if (tool.getType().toString().equals("NETHERITE_PICKAXE")) {
                numDrops = 2;
            } else {
                numDrops = 1;
            }

            Block block = event.getBlock();

            /*
               FIXME:
                Change the drops once the update comes
             */


            ItemStack drop;

            if (block.getType() == Material.IRON_ORE) {
                drop = new ItemStack(Material.IRON_INGOT, numDrops);
            } else if (block.getType() == Material.DIAMOND_ORE) {
                drop = new ItemStack(Material.DIAMOND, numDrops);
            } else if (block.getType() == Material.COAL_ORE) {
                drop = new ItemStack(Material.COAL, numDrops * 2);
            } else if (block.getType() == Material.LAPIS_ORE) {
                drop = new ItemStack(Material.LAPIS_LAZULI, numDrops * 2);
            } else if (block.getType() == Material.GOLD_ORE) {
                drop = new ItemStack(Material.GOLD_INGOT, numDrops * 2);
            } else if (block.getType() == Material.REDSTONE_ORE) {
                drop = new ItemStack(Material.REDSTONE);
            } else if (block.getType() == Material.EMERALD) {
                drop = new ItemStack(Material.EMERALD);
            } else if (block.getType() == Material.NETHER_QUARTZ_ORE) {
                drop = new ItemStack(Material.QUARTZ, numDrops * 3);
            } else if (block.getType().toString() == "NETHER_GOLD_ORE") {
                drop = new ItemStack(Material.GOLD_NUGGET, numDrops * 5);
            } else if (block.getType() == Material.STONE) {
                drop = new ItemStack(Material.COBBLESTONE, 1);
            } else if (block.getType() == Material.GRASS_BLOCK){
                drop = new ItemStack(Material.DIRT, 1);
            } else if (block.getType() == Material.FARMLAND) {
                drop = new ItemStack(Material.DIRT, 1);
            } else if (block.getType() == Material.GRASS_PATH){
                drop = new ItemStack(Material.DIRT, 1);
            } else {
                drop = checkDrops(block);
            }

            //Deals durability
            short durability = (short) (tool.getDurability() + 1);
            tool.setDurability(durability);
            short minDurability = (short) 0;

            System.out.println(tool.getDurability());

            if (tool.getDurability() <= (short) 0){
                System.out.println("Breaking players pick");
                tool.setType(Material.STICK);
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BAMBOO_SAPLING_BREAK, 0.7F, 0.7F);
            }

            //Checks to make sure the player is in survival before
            //if (event.getPlayer().getGameMode() == GameMode.SURVIVAL){
            event.setCancelled(true);
            block.setType(Material.AIR);
            Location dropLocation = block.getLocation();

            block.getWorld().dropItem(dropLocation, drop);
            //}

        }


    }

    private ItemStack checkDrops(Block block){
        ItemStack drops;
        Boolean doDrop = true;


        /*
        //Creates arraylist of banned blocks
        ArrayList<Material> bannedBlocks = new ArrayList<Material>();

        bannedBlocks.add(Material.SPAWNER);
        bannedBlocks.add(Material.CAKE);
        bannedBlocks.add(Material.ACACIA_LEAVES);
        bannedBlocks.add(Material.BIRCH_LEAVES);
        bannedBlocks.add(Material.DARK_OAK_LEAVES);
        bannedBlocks.add(Material.JUNGLE_LEAVES);
        bannedBlocks.add(Material.SPRUCE_LEAVES);
        bannedBlocks.add(Material.OAK_LEAVES);
        bannedBlocks.add(Material.LARGE_FERN);
        bannedBlocks.add(Material.GRASS);
        bannedBlocks.add(Material.TALL_GRASS);
        bannedBlocks.add(Material.SEAGRASS);
        bannedBlocks.add(Material.TALL_SEAGRASS);


         */

        /*
            Cycles through banned blocks
            If the inputted block is in
            the banned blocks, then it sets
            doDrop to false and breaks the
            loop.
         */

        for (Material banned : bannedBlocks){

            if (block.getType() == banned){
                doDrop = false;
                break;
            } else {
                continue;
            }

        }



        //If drops is false, then it returns an empty dirt stack
        //If it is true, then it drops the item
        if (doDrop){
            drops = new ItemStack(block.getType(), 1);
        } else {
            drops = new ItemStack(Material.DIRT, 0);
        }

        return drops;
    }


}
