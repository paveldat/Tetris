package ui;

import model.Startable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartingWindow extends JFrame {

    private JTextField input = new JTextField("Enter your name");
    private JButton button = new JButton("Start the game!");
    private Startable start;

    public StartingWindow(Startable start) {
        super("Glad to see you again!");
        this.start = start;
        this.setBounds(0, 0, 250, 100);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(2, 1, 2, 2));
        container.add(input);
        container.add(button);

        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setVisible(true);

        button.addActionListener(new ButtonEventListener());
    }

    class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String player = input.getText();
            start.start(player);
            setVisible(false);
        }
    }
}
