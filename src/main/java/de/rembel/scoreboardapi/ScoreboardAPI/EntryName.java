package de.rembel.scoreboardapi.ScoreboardAPI;

import net.md_5.bungee.api.ChatColor;

public enum EntryName {

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
