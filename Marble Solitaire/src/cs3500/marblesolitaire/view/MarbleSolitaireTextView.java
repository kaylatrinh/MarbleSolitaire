package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents the text view of the {@code MarbleSolitaireModel}. This class outputs the board
 * that the user will be able to see. It does not alter anything in any other packages such as
 * the model or controller.
 */

public class MarbleSolitaireTextView implements MarbleSolitaireView {
  protected final MarbleSolitaireModelState modelState;
  protected final Appendable object;

  /**
   * A constructor that takes in the model state for the purpose of the viewer.
   *
   * @param modelState the model state of the {@code MarbleSolitaireModel}
   * @throws IllegalArgumentException if the model state is null.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState modelState)
          throws IllegalArgumentException {
    this(modelState, new StringBuilder());
  }

  /**
   * A constructor that takes in the modelState of the EnglishSolitare and an Appendable object.
   * @param modelState the modelState of the {@code MarbleSolitareModel}.
   * @param object an Appendable object.
   * @throws IllegalArgumentException if the modelState or object is null.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState modelState, Appendable object)
          throws IllegalArgumentException {
    if (modelState == null || object == null) {
      throw new IllegalArgumentException("Model or appendable object cannot be null.");
    }
    this.modelState = modelState;
    this.object = object;
  }

  @Override
  public String toString() throws IllegalArgumentException {
    String s = "";
    for (int sRow = 0; sRow < modelState.getBoardSize(); sRow++) {
      for (int sCol = 0; sCol < modelState.getBoardSize(); sCol++) {
        if (modelState.getSlotAt(sRow, sCol) == MarbleSolitaireModelState.SlotState.Empty) {
          s = s + "_";
          if (sCol + 1 < modelState.getBoardSize()
                  && modelState.getSlotAt(sRow, sCol + 1)
                  == MarbleSolitaireModelState.SlotState.Invalid) {
            break;
          }
        } else if (modelState.getSlotAt(sRow, sCol) == MarbleSolitaireModelState.SlotState.Marble) {
          s = s + "O";
          if (sCol + 1 < modelState.getBoardSize()
                  && modelState.getSlotAt(sRow, sCol + 1)
                  == MarbleSolitaireModelState.SlotState.Invalid) {
            break;
          }
        } else if (modelState.getSlotAt(sRow, sCol)
                == MarbleSolitaireModelState.SlotState.Invalid) {
          s = s + " ";
        }
        if (sCol != modelState.getBoardSize() - 1) {
          s = s + " ";
        }
      }
      if (sRow != modelState.getBoardSize() - 1) {
        s = s + "\n";
      }
    }
    return s;
  }


  /**
   * Transmits the state of the Marble Solitaire Board to the Appendable object.
   *
   * @throws IOException if the transmission to the Appendable object fails.
   */
  @Override
  public void renderBoard() throws IOException {
    try {
      object.append(toString());
      System.out.println(toString());
    } catch (IOException e) {
      throw new IOException("Board could not be rendered.");
    }
  }

  /**
   * Transmits a given message to the Appendable object.
   *
   * @param message the message to be transmitted.
   * @throws IOException if the transmission to the Appendable object fails.
   */
  @Override
  public void renderMessage(String message) throws IOException {
    try {
      object.append(message);
      System.out.println(message);
    } catch (IOException e) {
      throw new IOException("Message could not be rendered.");
    }
  }
}
