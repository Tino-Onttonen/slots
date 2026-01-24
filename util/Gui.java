package util;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;



/** Class for GUI */
public class Gui {
    private FlowLayout flowLayout = new FlowLayout();
    private JFrame frame = new JFrame("Slots");
    private JButton betButton = new JButton("Bet");
    private JButton spinButton = new JButton("Spin");


    public Gui() {
    }

    /** Set the GUI to visible, thus displaying it. */
    public void initialize() {
        frame.setLayout(flowLayout);
        final int frameSizeX = 400;
        final int frameSizeY = 400;
        frame.setSize(frameSizeX,frameSizeY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.add(spinButton);
        frame.add(betButton);
        spinButton.setLocation(30,30);
        spinButton.setSize(30,30);
        betButton.setLocation(60,60);
        betButton.setSize(20,20);
        frame.setVisible(true);
        spinButton.setVisible(true);
        betButton.setVisible(true);

    }
    public static void start() {
    SwingUtilities.invokeLater(() -> {
        Gui gui = new Gui();
        gui.initialize();
        });
    }
}
