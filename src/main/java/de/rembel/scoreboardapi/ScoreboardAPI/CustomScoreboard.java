package de.rembel.scoreboardapi.ScoreboardAPI;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.List;

public class CustomScoreboard {

    private String title;
    private List<Player> players = new ArrayList<Player>();
    private Scoreboard scoreboard;
    private Objective obj;

    public CustomScoreboard(String title){
        this.title=title;

        initScoreboard();
    }



    public void initScoreboard(){
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        obj = scoreboard.registerNewObjective("scoreboard","dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(title);
        for(EntryName entryName : EntryName.values()){
            Team team = scoreboard.registerNewTeam(entryName.toString());
            team.addEntry(entryName.getEntryName());
        }

    }

    public void addPlayer(Player player){
        players.add(player);
        player.setScoreboard(scoreboard);
    }

    public void removePlayer(Player player){
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
        players.remove(player);
    }

    public void setLine(int score, String content){
        Team team = getTeamByScore(score);
        if(team == null) return;

        team.setPrefix(content);

        EntryName entryName = getEntryByScore(score);
        obj.getScore(entryName.getEntryName()).setScore(entryName.getEntry());
    }

    public String getLine(int score){
        Team team = getTeamByScore(score);
        if(team == null) return null;

        return team.getPrefix();
    }

    public void removeLine(int score){
        EntryName entryName = getEntryByScore(score);
        obj.getScore(entryName.getEntryName()).resetScore();
    }

    public EntryName getEntryByScore(int score){
        for(EntryName entryName : EntryName.values()){
            if(entryName.getEntry() == score){
                return entryName;
            }
        }
        return null;
    }

    public Team getTeamByScore(int score){
        EntryName entryName = getEntryByScore(score);
        if(entryName == null) return null;
        return scoreboard.getTeam(entryName.toString());
    }

}

enum EntryName {

    Entry_0(0, ChatColor.GRAY.toString()),
    Entry_1(1, ChatColor.AQUA.toString()),
    Entry_2(2, ChatColor.BLUE.toString()),
    Entry_3(3, ChatColor.DARK_AQUA.toString()),
    Entry_4(4, ChatColor.DARK_GRAY.toString()),
    Entry_5(5, ChatColor.DARK_BLUE.toString()),
    Entry_6(6, ChatColor.DARK_GREEN.toString()),
    Entry_7(7, ChatColor.DARK_RED.toString()),
    Entry_8(8, ChatColor.DARK_PURPLE.toString()),
    Entry_9(9, ChatColor.GREEN.toString());

    private final int entry;
    private final String entryName;

    private EntryName(int entry, String entryName){
        this.entry=entry;
        this.entryName=entryName;
    }

    public int getEntry(){
        return entry;
    }

    public String getEntryName(){
        return entryName;
    }
}
