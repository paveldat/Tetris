package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class InfoWindow extends JFrame {

    private ArrayList<JLabel> labels = new ArrayList<>();
    private ArrayList<String> players = new ArrayList<>();
    private ArrayList<Integer> scores = new ArrayList<>();
//    private LinkedHashMap<String, Integer> results = new LinkedHashMap<>();
    private String player;
    private Container container;
    private int score;

    public InfoWindow() {
        super("Game Info");
        this.setBounds(0, 0, 250, 200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        container = this.getContentPane();
        container.setLayout(new GridLayout(5, 2, 2, 2));
    }

    public void updateLabel(int score_) {
        score += score_;
        setLabelText();
    }

    public void startNewGame(String player) {
//        this.player = player;
//        if (!results.containsKey(player)) {
////            this.player = player;
//            results.put(player, 0);
//            addLabel();
//        }
        this.player = player;
        if (!playerExists(player)) {
            players.add(player);
            addLabel();
        }
        score = 0;
        setLabelText();
    }

    private boolean playerExists(String player) {
        for (String next : players) {
            if (next.equals(player)) {
                return true;
            }
        }
        return false;
    }

    private void addLabel() {
        JLabel newLabel = new JLabel();
        labels.add(newLabel);
        container.add(newLabel);
    }

    private void setLabelText() {
//        int index = players;
        JLabel label = labels.get(labels.size() - 1);
        label.setText(player + "'s score: " + score);
    }
}
