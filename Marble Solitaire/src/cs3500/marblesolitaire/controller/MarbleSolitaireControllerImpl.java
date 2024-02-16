package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * This class is the implementation of the {@code MarbleSolitaireController} that process user
 * inputs and works with the model class in order for the text to know what to show the user.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private Readable input;

  /**
   * The purpose of this constructor is to instantiate how this constructor can be colled.
   * @param model a {@code MarbleSolitaireModel}.
   * @param view a {@code MarbleSolitaireView}.
   * @param input An input from the user that can be read.
   * @throws IllegalArgumentException if any of the inputs (model, view, or input) are null.
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable input) throws IllegalArgumentException {
    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("Fields cannot be null.");
    }
    this.model = model;
    this.view = view;
    this.input = input;
  }


  /**
   * Plays the game.
   */
  @Override
  public void playGame() throws IllegalStateException {

    Scanner scanner = new Scanner(this.input);

    List<Integer> list = new ArrayList<>();

    while (scanner.hasNext()) {
      String in;
      try {
        in = scanner.next();
      } catch (NoSuchElementException e) {
        throw new IllegalStateException(e);
      }
      if (in.equalsIgnoreCase("q")) {
        quitGame();
        return;
      }
      try {
        int num = Integer.parseInt(in);
        if (num >= 0) {
          list.add(Integer.parseInt(in));
        }
      } catch (NumberFormatException n) {
        continue;
      }
      if (list.size() >= 4) {
        try {
          model.move(list.get(0) - 1, list.get(1) - 1, list.get(2) - 1, list.get(3) - 1);
          gameBoard();
        } catch (IllegalArgumentException e) {
          try {
            this.view.renderMessage("\nInvalid move. Waiting for new input.\n");
            System.out.println(this.view.toString());
          } catch (IOException ex) {
            throw new IllegalStateException();
          }
        } finally {
          list.clear();
        }
      }
      if (model.isGameOver()) {
        endGame();
        return;
      }
    }
    throw new IllegalStateException();
  }

  /**
   * A helper method that shows a board that is still in play and its score.
   */
  private void gameBoard() {
    try {
      this.view.renderBoard();
      this.view.renderMessage("\nScore: " + model.getScore() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  /**
   * A helper method that shows a board that has been quit by pressing the letter "Q".
   */
  private void quitGame() {
    try {
      this.view.renderMessage("Game quit!");
      this.view.renderMessage("\nState of game when quit:\n");
      this.view.renderBoard();
      this.view.renderMessage("\nScore: " + model.getScore() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  /**
   * A helper method that shows the end of the game when moves can no longer be made.
   */
  private void endGame() {
    try {
      this.view.renderMessage("Game over!\n");
      this.view.renderBoard();
      this.view.renderMessage("\nScore: " + model.getScore() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }
}
