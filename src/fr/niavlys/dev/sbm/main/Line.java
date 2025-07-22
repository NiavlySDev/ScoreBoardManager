package fr.niavlys.dev.sbm.main;

import org.bukkit.ChatColor;

public class Line {

    private int line;
    private String text;
    private ChatColor color;

    public Line(int line, String text, ChatColor color) {
        this.text = text;
        this.line = line;
        this.color = color;
    }

    public String getText() {
        return text;
    }
    public int getLine() {
        return line;
    }
    public ChatColor getColor() {
        return color;
    }

    public void setLine(int line) {
        this.line = line;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setColor(ChatColor color) {
        this.color = color;
    }
}
