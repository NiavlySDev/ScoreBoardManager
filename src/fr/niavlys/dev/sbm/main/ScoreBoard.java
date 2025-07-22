package fr.niavlys.dev.sbm.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {

    private Player p;
    private List<Line> lines;
    private Scoreboard board;


    public ScoreBoard(Player p) {
        this.p = p;
        this.lines = new ArrayList<>();
    }

    public List<Line> getLines() {
        return lines;
    }
    public Line getLine(int i) {
        for(Line l : lines) {
            if(l.getLine() == i) {
                return l;
            }
        }
        return null;
    }

    public void afficher() {
        maj();
        p.setScoreboard(board);
    }
    public void cacher(){
        reset();
    }
    public void maj(){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        if (manager != null) {
            board = manager.getNewScoreboard();
            Objective objective = board.registerNewObjective("sb", "dummy", ChatColor.GOLD+"SunRealms");
            objective.setDisplaySlot(org.bukkit.scoreboard.DisplaySlot.SIDEBAR);

            for (Line line : lines) {
                objective.getScore(line.getColor()+line.getText()).setScore(line.getLine());
            }
        }
    }

    private void addLine(Line line){
        lines.add(line);
        maj();
    }
    public void addLine(int i, String text, ChatColor color){
        i=100-i;
        addLine(new Line(i, text, color));
    }
    public void removeLine(int i){
        lines.remove(i);
        maj();
    }
    private void updateLine(int i, Line line){
        for(Line l : lines){
            if(l.getLine() == i){
                l.setText(line.getText());
                l.setColor(line.getColor());
                l.setLine(line.getLine());
            }
        }
    }
    public void updateLine(int i, String text, ChatColor color){
        i = 100-i;
        updateLine(i, new Line(i, text, color));
    }

    public void reset(){
        lines.clear();
        maj();
    }
}