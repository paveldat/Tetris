package ui;

import javax.swing.*;

public class Box extends JPanel {

    private int color;

    public int getColor() {
        return color;
    }

    public Box(int x, int y) {
        color = 0;
        setBounds(x * Config.SIZE, y * Config.SIZE, Config.SIZE, Config.SIZE);
        setBackground(Config.COLORS[0]);
        setVisible(true);
    }

    public void setColor(int color) {
        this.color = color;
        if (color >= 0 && color < Config.COLORS.length) {
            setBackground(Config.COLORS[color]);
        }
    }
}
