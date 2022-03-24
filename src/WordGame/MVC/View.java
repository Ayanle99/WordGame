package WordGame.MVC;

import WordGame.Panels.ControlPanel;
import WordGame.Panels.GridPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class View extends JFrame{


    private JProgressBar progressBar;
    private JPanel mainPanel = new JPanel();

    private GridPanel gridPanel = new GridPanel();
    private ControlPanel controlPanel = new ControlPanel();
    private JTextField[][] grid;

    public JButton getHintBtn() {
        return controlPanel.getHintBtn();
    }
    public JButton getStats() {
        return controlPanel.getStats();
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public View(String title){

        setFrame(title);
        this.grid = gridPanel.getGrid();
        setMainPanel();

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void setFrame(String title){

        setTitle(title);
        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 420);

        setBackground(Color.yellow);
        setLocationRelativeTo(null);
        setResizable(false);

        progressBar = new JProgressBar(0, 6);
        progressBar.setStringPainted(true);
        progressBar.setForeground(Color.white);
        progressBar.setBorder(BorderFactory.createLoweredBevelBorder());
        progressBar.setFont(new Font("Arial", Font.BOLD, 20));


        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);

    }

    private void setMainPanel(){

        mainPanel.setBorder(new EmptyBorder(20,20,20,20));
        mainPanel.setLayout(new BorderLayout());

        mainPanel.setBackground(Color.decode("#58F748"));
        gridPanel.setBackground(Color.decode("#90C90A"));

        controlPanel.setBackground(Color.decode("#05AC7D"));
        mainPanel.add(gridPanel, BorderLayout.NORTH);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        progressBar.setBackground(Color.white);


        mainPanel.add(progressBar);
    }

    public JTextField[] getLastCol(){

        JTextField[] lastColumn = gridPanel.getGrid()[6];

        for (JTextField t : lastColumn){
            t.setEnabled(false);
        }

        return lastColumn;
    }

    public void reset(){

        JTextField[][] grid = getGrid();

        for (int i = 0;i < grid.length;i++){

            for (int j = 0;j<grid[i].length;j++){

                //grid[i][j].setBackground(Color.decode("#282A99"));
                grid[i][j].setForeground(Color.red);
                grid[i][j].setText("J");
                grid[i][j].setFont(new Font("Arial", Font.BOLD, 20));

            }
        }

    }
    public JTextField[][] getGrid() {
        return gridPanel.getGrid();
    }
    public void setGrid(JTextField[][] grid) {
        this.grid = grid;
    }
    public JButton getCheckBtn() {
        return controlPanel.getCheckBtn();
    }

    public JButton getRestartBtn() {
        getLastCol();
        return controlPanel.getRestartBtn();
    }

}
