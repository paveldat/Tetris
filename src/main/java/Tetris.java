import ui.InfoWindow;
import ui.StartingWindow;
import ui.Window;

public class Tetris {
    public static void main(String[] args) {
        Window window = new Window();
//        StartingWindow startingWindow = new StartingWindow();
//        javax.swing.SwingUtilities.invokeLater(startingWindow);
        javax.swing.SwingUtilities.invokeLater(window);
        window.addFigure();
    }
}