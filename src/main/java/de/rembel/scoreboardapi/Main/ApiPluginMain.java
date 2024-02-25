package de.rembel.scoreboardapi.Main;

import de.rembel.scoreboardapi.ScoreboardAPI.CustomScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ApiPluginMain extends JavaPlugin {

    public static boolean debug = true;

    @Override
    public void onEnable() {
        for(Player player : Bukkit.getOnlinePlayers()){
            CustomScoreboard scoreboard = new CustomScoreboard("debug");
            scoreboard.addPlayer(player);
            Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                @Override
                public void run() {
                    scoreboard.setLine(0, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
                }
            }, 0, 20);
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
