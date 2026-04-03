package util;
import src.Slots;
import util.Slot;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;



/** Graphical User-Interface. */
public class Gui {

    /** Main frame. */
    private JFrame frame = new JFrame("Slots");

    /** Panel, which will set the 3x3 layout for the reels/JButtons. */
    private final JPanel slotPanel = new JPanel(new GridLayout(3, 3, 10, 10));

    /** Panel, which will set the layout for all the Control-components.  */
    private final JPanel controlPanel = new JPanel(new GridLayout(1, 3, 5, 1));

    /** Array / Grid sizing. */
    private final int row = 3;

    /** Symbol size on the GUI */
    private final int fontSize = 50;

    /** Array of three elements that stores the control buttons.
     * Controls consists of the Spin, Balance & Bet buttons.
     */
    private final JButton[] controls = new JButton[row];

    /** 3x3 array of JButtons that form the reels. */
    private final JButton[][] reels = new JButton[row][row];

    /** Frame size, used as the height and width. */
    private final int frameSize = 400;

    private final int balance = 1;


    private final int spin = 2;
    private static int bet = 0;


    public Gui() {

        /* Default frame operations */

        // Bet button
        controls[bet] = new JButton("Bet: " + Slots.bet());
        controls[bet].addActionListener(e -> changeBet());

        // Balance button
        controls[balance] = new JButton("Balance: " + Slots.balance());

        // Spin button
        controls[spin] = new JButton("Spin!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameSize, frameSize);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.add(slotPanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);
        controls[spin].addActionListener(e -> refresh());
        for (int i = 0; i < controls.length; i++) {
            controlPanel.add(controls[i]);
        }
        frame.setVisible(true);
        JOptionPane.showMessageDialog(frame, "Press the spin button, and start playing!");
    }

    /** Refresh the slot symbols. */
    public void refresh() {

        // Let the user spin if they have enough balance
        if (Slots.balance() >= Slots.bet()) {

            String[][] temp = Slots.gameLoop();
            slotPanel.removeAll();
            for (int i = 0; i < reels.length; i++) {
                for (int j = 0; j < reels.length; j++) {
                    reels[i][j] = new JButton(temp[i][j]);
                    Font font = new Font("Segoe UI Emoji", Font.PLAIN, fontSize);
                    reels[i][j].setFont(font);
                    slotPanel.add(reels[i][j]);
                    slotPanel.setVisible(true);
                    controls[balance].setVisible(true);
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Game over, out of balance!");
        }
        controls[balance].setText("Balance: " + Slots.balance());
    }
    // Thread-safe invoking of swing.
    public static void invoke() {
        SwingUtilities.invokeLater(() -> {
            Gui gui = new Gui();
        });
    }
    public void changeBet() {
        bet++;
        if (bet == 3) {
            bet = 0;
        }
        Slots.changeBetAmount(bet);
        controls[0].setText("Bet: " + Slots.bet());
    }
}
