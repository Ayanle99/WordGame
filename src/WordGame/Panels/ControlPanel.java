package WordGame.Panels;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

    private JButton checkBtn = new JButton("Check");
    private JButton restartBtn = new JButton("Restart");
    private JButton hintBtn = new JButton("Hint");
    private JButton stats = new JButton("Stats");

    public ControlPanel(){


        setLayout(new GridLayout(2,2));
        setBorder(BorderFactory.createLoweredBevelBorder());

        add(checkBtn);
        add(hintBtn);
        add(stats);
        add(restartBtn);
        setBackground(Color.decode("#282A99"));

    }

    public JButton getCheckBtn() {
        return checkBtn;
    }

    public JButton getRestartBtn() {
        return restartBtn;
    }

    public JButton getHintBtn() {
        return hintBtn;
    }

    public JButton getStats() {
        return stats;
    }
}
