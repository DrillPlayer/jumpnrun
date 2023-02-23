package de.drillplayer.jumpnrun.listener;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.*;

public class JumpListener implements Listener {
    private final Map<String, Boolean> jump;
    private final List<Location> blocks;

    public JumpListener(PlateListener plateListener) {
        this.jump = plateListener.getJump();
        this.blocks = plateListener.getBlocks();
    }

    @EventHandler
    public void jumpListener(PlayerJumpEvent event) {
        Player player = event.getPlayer();
        if (jump.get(player.getUniqueId().toString()) != null) {
            if (jump.get(player.getUniqueId().toString())) {
                Location playerloc = player.getLocation();
                List<Material> terracotta = new ArrayList<>();
                terracotta.addAll(Arrays.stream(Material.values()).filter(material -> {
                    if (material.name().endsWith("TERRACOTTA")) {
                        if (!material.name().startsWith("LEGACY")) {
                            return true;
                        }
                    }
                    return false;
                }).toList());

                int x;
                int y;
                int z;
                Random rn = new Random();
                Location newblockloc;


                x = rn.nextInt((int) Math.round(Math.sqrt(2.0)) * 2);                   ///
                y = rn.nextInt(2);                                                      ///  Formel zum Generieren von Zufallszahlen in bestimmtem Spektrum:  random.nextInt(max - min + 1) + min
                z = rn.nextInt((int) Math.round(Math.sqrt(2.0)) * 2);                   ///
                newblockloc = playerloc.add(x, y, z);


                if (blocks.size() == 2) {
                    blocks.get(0).getBlock().setType(Material.AIR);
                    blocks.remove(0);
                }

                Block newblock = newblockloc.getBlock();
                newblock.setType(terracotta.get(rn.nextInt(33)));
                blocks.add(newblockloc);
            }
        }
    }
}
