package de.drillplayer.jumpnrun.listener;

import de.drillplayer.jumpnrun.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;
import java.util.Map;

public class MoveListener implements Listener {

    private final Map<String, Boolean> jump;
    private final List<Location> blocks;

    public MoveListener(PlateListener plateListener) {
        this.jump = plateListener.getJump();
        this.blocks = plateListener.getBlocks();
    }


    @EventHandler
    public void moveListener(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (jump.get(player.getUniqueId().toString()) != null) {
            if (player.getLocation().getY() == Main.getInstance().getConfig().getInt("jump.groundlevel")) {
                jump.replace(player.getUniqueId().toString(), false);
                for (Location location : blocks) {
                    location.getBlock().setType(Material.AIR);
                }
            }
        }
    }
}
