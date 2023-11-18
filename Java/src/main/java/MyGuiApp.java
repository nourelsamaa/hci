import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class MyGuiApp {

    public static void main(String[] args) {
        // Create and configure the JFrame
        JFrame frame = new JFrame("My GUI App");
        frame.setSize(400, 200); // Adjusted size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel with a FlowLayout
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Create JButton instances with HTML symbols
        JButton button1 = new JButton("\uD83D\uDD0A Volume Up");
        JButton button2 = new JButton("\uD83D\uDD09 Volume Down");
        JButton button3 = new JButton(" ⏸ Pause"); // Play symbol (▶)
        JButton button4 = new JButton("\uD83D\uDD07 Mute"); // Speaker symbol (🔈)
        JButton button5 = new JButton("⏪ Previous");
        JButton button6 = new JButton("⏩Next");

        // Add the JButtons to the JPanel
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);

        // Add the JPanel to the JFrame
        frame.getContentPane().add(panel);

        // Make the JFrame visible
        frame.setVisible(true);
    }
}
