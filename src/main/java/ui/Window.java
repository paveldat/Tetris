package ui;

import javax.swing.*;

import model.Refreshable;
import model.Coord;
import model.Mapable;
import model.Startable;
import service.FlyFigure;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JFrame implements Runnable, Mapable, Startable, Refreshable {

    private Box[][] boxes;
    private FlyFigure fly;
    private InfoWindow infoWindow = new InfoWindow();
    private RefreshWindow refreshWindow = new RefreshWindow(this);
    private StartingWindow startingWindow = new StartingWindow(this);
    private Timer timer;

    public Window() {
        setEnabled(false);
        boxes = new Box[Config.WIDTH][Config.HEIGHT];
        initForm();
        initBoxes();
        addKeyListener(new KeyAdapter());
        TimeAdapter timeAdapter = new TimeAdapter();
        timer = new Timer(150, timeAdapter);
        timer.start();
        infoWindow.setLocation(this.getLocation().x - infoWindow.getWidth(), this.getLocation().y);
        infoWindow.setVisible(true);
        refreshWindow.setLocationRelativeTo(this);
    }

    public void addFigure() {
        if (startingWindow.isVisible()) {
            timer.stop();
        }
        fly = new FlyFigure(this);
        if (fly.canPlaceFigure()) {
            showFigure(1);
        } else {
            this.setEnabled(false);
            refreshWindow.setVisible(true);
            return;
        }
    }

    public void start(String player) {
        timer.start();
        infoWindow.startNewGame(player);
        setEnabled(true);
    }

    public void update() {
        startingWindow.setVisible(true);
        clearBoxes();
        setEnabled(true);
        addFigure();
    }

    private void clearBoxes() {
        for (int x = 0; x < Config.WIDTH; ++x) {
            for (int y = 0; y < Config.HEIGHT; ++y) {
                boxes[x][y].setColor(0);
            }
        }
    }

    private void initForm() {
        setSize(Config.WIDTH * Config.SIZE + 15,
                Config.HEIGHT * Config.SIZE + 40);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tetris the Game");
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initBoxes() {
        for (int x = 0; x < Config.WIDTH; ++x) {
            for (int y = 0; y < Config.HEIGHT; ++y) {
                boxes[x][y] = new Box(x, y);
                add(boxes[x][y]);
            }
        }
    }

    public void run() {
        repaint();
    }

    private void showFigure() {
        showFigure(1);
    }

    private void hideFigure() {
        showFigure(0);
    }

    private void showFigure(int color) {
        for (Coord dot : fly.getFigure().dots) {
            setBoxColor(fly.getCoord().x + dot.x,fly.getCoord().y + dot.y, color);
        }
    }

    private void setBoxColor(int x, int y, int color) {
        if (x < 0 || x >= Config.WIDTH) return;
        if (y < 0 || y >= Config.HEIGHT) return;
        boxes[x][y].setColor(color);
    }

    public int getBoxColor(int x, int y) {
        if (x < 0 || x >= Config.WIDTH) return - 1;
        if (y < 0 || y >= Config.HEIGHT) return - 1;
        return boxes[x][y].getColor();
    }

    private void moveFly(int sx, int sy) {
        hideFigure();
        fly.moveFigure(sx, sy);
        showFigure();
    }

    private void turnFly(int direction) {
        hideFigure();
        fly.turnFigure(direction);
        showFigure();
    }

    class KeyAdapter implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT: moveFly(-1, 0); break;
                case KeyEvent.VK_RIGHT: moveFly(+1, 0); break;
                case KeyEvent.VK_UP: turnFly(1); break;
                case KeyEvent.VK_DOWN: turnFly(2); break;
                case KeyEvent.VK_U: moveFly(0, -1); break;
                case KeyEvent.VK_D: moveFly(0, +1); break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}
    }

    private void stripLines() {
        int counter = 0;
        for (int y = Config.HEIGHT - 1; y >= 0; --y) {
            while (isFullLine(y)) {
                counter++;
                dropLine(y);
            }
        }
        switch (counter) {
            case 1:
                infoWindow.updateLabel(100);
                break;
            case 2:
                infoWindow.updateLabel(200);
                break;
            case 3:
                infoWindow.updateLabel(700);
                break;
            case 4:
                infoWindow.updateLabel(1500);
                break;
            default:
                break;
        }
    }

    private void dropLine(int y) {
        for (int my = y - 1; my >= 0; --my) {
            for (int x = 0; x < Config.WIDTH; ++x) {
                setBoxColor(x, my + 1, getBoxColor(x, my));
            }
        }
        for (int x = 0; x < Config.WIDTH; ++x) {
            setBoxColor(x, 0, 0);
        }
    }

    private boolean isFullLine(int y) {
        for (int x = 0; x < Config.WIDTH; ++x) {
            if (getBoxColor(x, y) != 2) {
                return false;
            }
        }
        return true;
    }

    class TimeAdapter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            moveFly(0, 1);
            if (fly.isLanded()) {
                showFigure(2);
                stripLines();
                addFigure();
            }
        }
    }
}
