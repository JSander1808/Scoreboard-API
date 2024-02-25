package de.rembel.scoreboardapi.ScoreboardAPI;

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

    public void setLine(int score, String content){
        Team team = getTeamByScore(score);
        if(team == null) return;

        team.setPrefix(content);

        EntryName entryName = getEntryByScore(score);
        obj.getScore(entryName.getEntryName()).setScore(entryName.getEntry());
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
