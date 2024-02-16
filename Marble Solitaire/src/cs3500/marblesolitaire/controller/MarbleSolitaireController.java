package cs3500.marblesolitaire.controller;

/**
 * The MarbleSolitaireController interface is to enact the actions that the user would like to
 * do, and it confers with the model package in order to know what to show to the user using the
 * view.
 */
public interface MarbleSolitaireController {

  /**
   * This method is part of the controller package in order for the user to be able to play the
   * Marble Solitaire game.
   */
  void playGame() throws IllegalStateException;
}
