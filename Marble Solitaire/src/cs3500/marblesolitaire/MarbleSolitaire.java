package cs3500.marblesolitaire;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * This is a class that makes it possible to run the Marble Solitaire game.
 */
public class MarbleSolitaire {

  /**
   * A method to help in allowing the user to run the game through the controller.
   * @param args Arguments of strings.
   */
  public static void main(String[] args) {
    MarbleSolitaireModel model;
    MarbleSolitaireView view;
    MarbleSolitaireController control;
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int size = 0;
    int row = 0;
    int col = 0;
    String modelType = "";

    for (int i = 0; i < args.length; i++) {
      switch (args[i].toLowerCase()) {
        case "english":
        case "european":
          modelType = args[i];
          size = 3;
          row = 3;
          col = 3;
          break;
        case "triangular":
          modelType = args[i];
          size = 5;
          row = 0;
          col = 0;
          break;
        case "-size":
          size = Integer.parseInt(args[i + 1]);
          i++;
          break;
        case "-hole":
          row = Integer.parseInt(args[i + 1]) - 1;
          i++;
          col = Integer.parseInt(args[i + 1]) - 1;
          i++;
          break;
        default:
          throw new IllegalArgumentException("Board could not be found.");
      }
    }

    switch (modelType) {
      case "english":
        model = new EnglishSolitaireModel(size, row, col);
        view = new MarbleSolitaireTextView(model, bw);
        break;
      case "european":
        model = new EuropeanSolitaireModel(size, row, col);
        view = new MarbleSolitaireTextView(model, bw);
        break;
      case "triangular":
        model = new TriangleSolitaireModel(size, row, col);
        view = new TriangleSolitaireTextView(model, bw);
        break;
      default:
        throw new IllegalArgumentException("No model provided.");
    }

    control = new MarbleSolitaireControllerImpl(model, view, br);
    control.playGame();
  }
}
