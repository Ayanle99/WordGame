package WordGame.MVC;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Model {

    private String FILE_PATH = "src/WordGame/words.txt";
    private Random random = new Random();
    private ArrayList<String> words = new ArrayList<>();

    private String userGuess;
    private int attempts;

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public int getAttempts() {
        return attempts;
    }

    public Model(String userGuess) {
        this.userGuess = userGuess;

    }

    public String  getTheWord(){

        ArrayList<String> words = new ArrayList<>();
        String randomWord = "";

        try {

            File myObj = new File(FILE_PATH);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {

                String line = myReader.nextLine();

                words.add(line.toLowerCase());

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        int index = random.nextInt(words.size());
        randomWord = words.get(index);


        return randomWord;
    }


    public String getUserGuess() {
        return userGuess;
    }

    public void setUserGuess(String userGuess) {
        this.userGuess = userGuess;
    }
}
