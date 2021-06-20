package com.kopauniksoftware.dodododoseason2powers;

import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.plugin.java.JavaPlugin;

public final class DoDoDoDoSeason2Powers extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        System.out.println("Powers have been enabled");

        //Registers global changes
        getServer().getPluginManager().registerEvents(new PlayerSpawn(), this);
        getServer().getPluginManager().registerEvents(new AntiBedrock(), this);
        getServer().getPluginManager().registerEvents(new RandomJuggernauts(), this);

        //Registers individuals powers
        getServer().getPluginManager().registerEvents(new Thrasherop2(), this);
        getServer().getPluginManager().registerEvents(new MeggsBenedict(), this);
        getServer().getPluginManager().registerEvents(new AceOfShades(), this);
        getServer().getPluginManager().registerEvents(new EJLili(), this);
        getServer().getPluginManager().registerEvents(new Swordspoint(), this);
        getServer().getPluginManager().registerEvents(new Nimmmmmmmm(), this);
        getServer().getPluginManager().registerEvents(new MazziMazz2(), this);





    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
