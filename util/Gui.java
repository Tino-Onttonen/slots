package util;
import src.Slots;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;



/** Class for GUI */
public class Gui {
    private JFrame frame = new JFrame("Slots");
    private JPanel slotPanel = new JPanel(new GridLayout(3,3,1,1));
    private JPanel controlPanel = new JPanel();
    private JButton spinButton = new JButton("Spin!");
    private JButton balance = new JButton();
    private JButton[][] reels = new JButton[3][3];

    final int frameSize = 400;

    public Gui() {
        /* Default frame operations */
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameSize,frameSize);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.add(slotPanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);
        balance.setVisible(true);
        spinButton.addActionListener(e -> refresh());
        controlPanel.add(spinButton);
        controlPanel.add(balance);
        slotPanel.setVisible(true);
        balance.setText("Balance: " + Slots.balance());
    }

    public void refresh() {
        String[][] temp = Slots.gameLoop();
        slotPanel.removeAll();
        for (int i = 0; i < reels.length; i++) {
            for (int j = 0; j < reels.length; j++) {
                reels[i][j] = new JButton(temp[i][j]);
                Font font = new Font("Segoe UI Emoji", Font.PLAIN, 40);
                reels[i][j].setFont(font);
                slotPanel.add(reels[i][j]);
            }
        }
        balance.setText("Balance: " + Slots.balance());
    }

    public static void invoke() {
        SwingUtilities.invokeLater(() -> {
                Gui gui = new Gui();
        });
    }
}
