package WordGame.Panels;

import WordGame.Misc.TextFieldLimit;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {

    private JTextField[][] grid;

    public GridPanel(){

        grid = new JTextField[7][5];
        setLayout(new GridLayout(7,5));
        setBorder(BorderFactory.createRaisedSoftBevelBorder());

        for (int i = 0;i < grid.length;i++){
            for (int j = 0;j<grid[i].length;j++) {

                grid[i][j] = new JTextField(String.valueOf(i));

                grid[i][j].setDocument(new TextFieldLimit(1));
                grid[i][j].setBackground(Color.white);
                grid[i][j].setFont(new Font("Arial", Font.BOLD, 20));

                add(grid[i][j]);


            }


        }

        setBackground(Color.decode("#7C7FF7"));

        setLastColumn();
    }


    private void setLastColumn(){

        JTextField[] last = getGrid()[6];

        for (JTextField t : last){
            t.setForeground(Color.white);
            t.setBackground(Color.decode("#F7F601"));
            t.setText("x");
        }
    }

    public JTextField[][] getGrid() {
        return grid;
    }
}
