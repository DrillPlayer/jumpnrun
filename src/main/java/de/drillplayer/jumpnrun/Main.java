package de.drillplayer.jumpnrun;

import de.drillplayer.jumpnrun.listener.JumpListener;
import de.drillplayer.jumpnrun.listener.MoveListener;
import de.drillplayer.jumpnrun.listener.PlateListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main instance;

    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        PlateListener plateListener = new PlateListener();
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(plateListener, this);
        pluginManager.registerEvents(new MoveListener(plateListener), this);
        pluginManager.registerEvents(new JumpListener(plateListener), this);
        getLogger().info("Jump'n'Run Plugin gestartet!");
    }

    public void onDisable() {
        getLogger().info("Jump'n'Run Plugin runtergefahren!");
    }

    public static Main getInstance() {
        return instance;
    }
}
