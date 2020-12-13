package uk.co.hopperelec.mc.joinprotection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;

public final class Main extends JavaPlugin implements Listener {
    String[] modsArr = {"HopperElecYT","PhantomRX","FabelWings","FuturisticIdiot","CME_9","MrNBull","ReluctantNebula","DoctorTortoise"};
    ArrayList<String> mods = new ArrayList<>(Arrays.asList(modsArr));
    int modsOnline = 0;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this,this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (mods.contains(event.getPlayer().getName())) {
            modsOnline += 1;
        } else if (modsOnline == 0) {
            event.getPlayer().kickPlayer("There is not a moderator online at the moment!");
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        if (mods.contains(event.getPlayer().getName())) {
            modsOnline -= 1;
        }
        if (modsOnline == 0) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.kickPlayer("The only available moderator has now left!");
            }
        }
    }
}
