package de.drillplayer.jumpnrun.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.*;

public class PlateListener implements Listener {

    private final Map<String, Boolean> jump = new HashMap<>();
    private final List<Location> blocks = new ArrayList<>();

    @EventHandler
    public void plateListener(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        List<Material> terracotta = new ArrayList<>();
        terracotta.addAll(Arrays.stream(Material.values()).filter(material -> {
            if (material.name().endsWith("TERRACOTTA")) {
                if (!material.name().startsWith("LEGACY")) {
                    return true;
                }
            }
            return false;
        }).toList());

        Random rn = new Random();
        int x = rn.nextInt((int) Math.round(Math.sqrt(2.0)) * 2);           ///  Formel zum Generieren von Zufallszahlen in bestimmtem Spektrum:  random.nextInt(max - min + 1) + min
        int z = rn.nextInt((int) Math.round(Math.sqrt(2.0)) * 2);           ///



        if (event.getAction() == Action.PHYSICAL) {
            if (event.getClickedBlock().getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE) {
                Block plate = event.getClickedBlock();
                Location plateloc = plate.getLocation();
                Location blockloc = plateloc.add(x, 0, z);
                jump.put(player.getUniqueId().toString(), true);
                blockloc.getBlock().setType(terracotta.get(rn.nextInt(33)));
                blocks.add(blockloc);
            }
        }
    }

    public Map<String, Boolean> getJump() {
        return jump;
    }

    public List<Location> getBlocks() {
        return blocks;
    }
}
