package WordGame.MVC;

import WordGame.MVC.Model;
import WordGame.MVC.View;
import WordGame.Panels.GridPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Controller {

    private Model model;
    private View view;
    private int col = 0;
    private String currentWord;
    private double attemptsCounter = 0;
    private String format = "%-20s %-20s %-20s\n";

    public Controller(Model model, View view) {

        this.model = model;
        this.view = view;
        this.currentWord = model.getTheWord();

        System.out.printf(format, "Attemp #","Your Guess", "Word");
        setGrid();
    }

    public void setGrid(){

        JTextField[][] grid = view.getGrid();

        for (int i = 0; i < grid.length;i++){
            for (int j = 0;j<grid[i].length;j++){
                view.getGrid()[i][j].setText(model.getUserGuess());
            }
        }

    }

    public void initilizeController(){

        JTextField[] first = view.getGrid()[0];

        view.getCheckBtn().addActionListener(e -> checkAnswer());
        view.getRestartBtn().addActionListener(e -> restartGame());
        view.getHintBtn().addActionListener(e->getHints());
        view.getStats().addActionListener(e->getStats());


    }

    private void getStats() {

        double per = (double) model.getAttempts()/6;
        per *= 100;

        JOptionPane.showMessageDialog(null,
                "The word: " + currentWord
        +"\nGuess count: " + model.getAttempts() + "/" + 6
        +"\nPercentage: " + (Math.round(per)) + "%",
                "Stats", JOptionPane.INFORMATION_MESSAGE);


    }
        private void getHints() {

        JOptionPane.showMessageDialog(null,
                "The word starts with: " + currentWord.charAt(0)
        +"\nAnd ends with: " + currentWord.charAt(currentWord.length()-1)
        ,"Hints", JOptionPane.INFORMATION_MESSAGE);
    }

    public void checkAnswer(){


        JTextField[] currentColumn = view.getGrid()[col];


        if (!(isFilled(currentColumn))){

            String errorStatement = "The column #" + col +" is empty." +
                    "\nPlease fill the column, before\nmoving on to the next\ncolumn.";

            JOptionPane.showMessageDialog(null,
                    errorStatement, "Column Empty!", JOptionPane.INFORMATION_MESSAGE);


            return;
        }


        ArrayList<Character> characters = new ArrayList<>();
        model.setAttempts(col+1);

        if (isFilled(currentColumn)){

            for (int i = 0; i <currentColumn.length;i++){
                String temp = currentColumn[i].getText();
                if (!(temp.isEmpty())) {
                    model.setUserGuess(temp);
                    char ch = temp.charAt(0);
                    characters.add(ch);
                }
            }

            if (col < 5){
                col++;
            }

            if (!(characters.isEmpty())){

                String str = characters.stream()
                        .map(e->e.toString())
                        .reduce((acc, e) -> acc  + e)
                        .get();

                model.setUserGuess(str);
                process(currentColumn);

                for (JTextField t : currentColumn){
                    t.setEnabled(false);

                }
            }


        }

        view.getProgressBar().setValue((model.getAttempts()));

        System.out.printf(format, model.getAttempts(), model.getUserGuess(), currentWord);

        if (model.getAttempts() >= 6){
            getCorrectWord();

            Color[] colors = {
              Color.yellow, Color.green,Color.orange,
              Color.pink
            };

            for (int i =0;i< colors.length;i++){
                view.getMainPanel().setBackground(colors[i]);
                view.getMainPanel().updateUI();
            }


        }
    }

    private boolean isFilled(JTextField[] currentColumn){

        for (JTextField t : currentColumn){
            String str = t.getText().trim();
            if (!(str.isEmpty())){
                return true;
            }
        }

        return false;

    }
    private void process(JTextField[] currentColumn){


        String guess = model.getUserGuess();

        if (guess.length() >=5)
        {

            for (int i = 0; i < 5;i++){

                char ch1 = currentWord.charAt(i);
                int index1 = currentWord.indexOf(ch1);

                char ch2 = guess.charAt(i);

                if (currentWord.contains(String.valueOf(ch2))){

                    int index2 = guess.indexOf(ch2);

                    if ((ch1==ch2) && (index1==index2)){
                        currentColumn[i].setBackground(Color.green);

                    }else {
                        currentColumn[i].setBackground(Color.orange);
                    }

                }else {
                    currentColumn[i].setText("");
                }

            }


        }



    }
    private boolean isCorrect(JTextField[] currentColumn) {

        int correctCounter = 0;

        for (JTextField t : currentColumn){
            if (t.getBackground()==Color.green){
                correctCounter++;
            }
        }

        return correctCounter == 5;
    }
    private void getCorrectWord() {

        JTextField[] lastCol = view.getLastCol();

        for (int i = 0; i < lastCol.length;i++){

            char ch = currentWord.charAt(i);

            lastCol[i].setText(String.valueOf(ch));
            lastCol[i].setBackground(Color.white);
            lastCol[i].setText(String.valueOf(ch));
            lastCol[i].setFont(new Font("Arial",
                    Font.BOLD, 20));
        }
    }
    public void restartGame(){

        view.reset();
    }

}
