package ui;

import model.Refreshable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefreshWindow extends JFrame {

        private JButton button = new JButton("Play again");
        Refreshable refresh;

        public RefreshWindow(Refreshable refresh) {
            super("You're loser");
            this.refresh = refresh;
            this.setBounds(0, 0, 200, 100);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);

            Container container = this.getContentPane();
            container.setLayout(new GridLayout(1, 1, 2, 2));
            container.add(button);

            button.addActionListener(new ButtonEventListener());
        }

    class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            refresh.update();
            setVisible(false);
        }
    }
}
