package fr.niavlys.dev.sm.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardManager {

    /**
     * The player associated with this scoreboard manager.
     */
    private final Player player;

    /**
     * The scoreboard object for managing the scoreboard.
     */
    private final Scoreboard scoreboard;

    /**
     * The objective representing the scoreboard's display.
     */
    private final Objective objective;

    /**
     * Constructor to initialize the ScoreboardManager with the specified player and title.
     * @param player The player for whom the scoreboard is managed.
     * @param title The title of the scoreboard.
     */
    public ScoreboardManager(Player player, String title) {
        this.player = player;
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.objective = scoreboard.registerNewObjective("board", "dummy", title);
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        player.setScoreboard(scoreboard);
    }

    /**
     * Set a line in the scoreboard with the specified text and line number.
     * @param line The line number to set.
     * @param text The text to display on the line.
     */
    public void setLine(int line, String text) {
        Score score = objective.getScore(text);
        score.setScore(line);
    }

    /**
     * Remove a line from the scoreboard with the specified text.
     * @param text The text of the line to remove.
     */
    public void removeLine(String text) {
        scoreboard.resetScores(text);
    }

    /**
     * Clear all entries from the scoreboard.
     */
    public void clearScoreboard() {
        for (String entry : scoreboard.getEntries()) {
            scoreboard.resetScores(entry);
        }
    }

    /**
     * Set the title of the scoreboard.
     * @param title The new title for the scoreboard.
     */
    public void setTitle(String title) {
        objective.setDisplayName(title);
    }

    /**
     * Get the line text by its score.
     * @param score The score of the line.
     */
    private String getLineByScore(int score) {
        for (String entry : scoreboard.getEntries()) {
            if (objective.getScore(entry).getScore() == score) {
                return entry;
            }
        }
        return null;
    }

    /**
     * Update the text of a specific line in the scoreboard.
     * @param line The line number to update.
     * @param newText The new text to set for the line.
     */
    public void updateLine(int line, String newText) {
        String oldText = getLineByScore(line);
        if (oldText != null) {
            removeLine(oldText);
            setLine(line, newText);
        }
    }

    /**
     * Move a line up in the scoreboard.
     * @param line The line number to move.
     */
    public void moveLineUp(int line) {
        if (line <= 0) return;
        String currentLine = getLineByScore(line);
        String upperLine = getLineByScore(line + 1);
        if (currentLine != null && upperLine != null) {
            swapLines(line, line + 1);
        }
    }

    /**
     * Move a line down in the scoreboard.
     * @param line The line number to move.
     */
    public void moveLineDown(int line) {
        if (line <= 0) return;
        String currentLine = getLineByScore(line);
        String lowerLine = getLineByScore(line - 1);
        if (currentLine != null && lowerLine != null) {
            swapLines(line, line - 1);
        }
    }

    /**
     * Swap two lines in the scoreboard.
     * @param line1 The first line number.
     * @param line2 The second line number.
     */
    public void swapLines(int line1, int line2) {
        String text1 = getLineByScore(line1);
        String text2 = getLineByScore(line2);
        if (text1 != null && text2 != null) {
            removeLine(text1);
            removeLine(text2);
            setLine(line2, text1);
            setLine(line1, text2);
        }
    }

    /**
     * Format a scoreboard line with a title and value.
     * @param titleColor The color of the title.
     * @param title The title text.
     * @param value The value text.
     */
    public String formatScoreLine(ChatColor titleColor, String title, String value) {
        return titleColor + title + ": " + ChatColor.WHITE + value;
    }

    /**
     * Set a formatted line in the scoreboard with title and value.
     * @param line The line number to set.
     * @param titleColor The color of the title.
     * @param title The title text.
     * @param value The value text.
     */
    public void setScoreLine(int line, ChatColor titleColor, String title, String value) {
        setLine(line, formatScoreLine(titleColor, title, value));
    }

    /**
     * Set the title of the scoreboard with a specific color.
     * @param color The color to apply to the title.
     * @param title The title text.
     */
    public void setColoredTitle(ChatColor color, String title) {
        objective.setDisplayName(color + title);
    }
}