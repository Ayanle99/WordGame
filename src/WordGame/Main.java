package WordGame;

import WordGame.MVC.Controller;
import WordGame.MVC.Model;
import WordGame.MVC.View;

import javax.swing.*;

public class Main {


    public static void main(String[] args) {

        Model model = new Model("");
        View view = new View("Word Game");
        Controller controller = new Controller(model, view);
        controller.initilizeController();


    }

}
