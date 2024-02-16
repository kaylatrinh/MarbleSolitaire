package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * A class that represents {@code MarbleSolitaireView} in order for the player to view a
 * Triangular Marble Solitaire game. This is a marble solitaire game in the shape of a triangle
 * that allows for "diagonal" movements in the triangle.
 */
public class TriangleSolitaireTextView extends MarbleSolitaireTextView {

  /**
   * A constructor that takes in the model state for the purpose of the viewer.
   * @param modelState the model state of the {@code MarbleSolitaireModel}
   * @throws IllegalArgumentException if the model state is null.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState modelState)
          throws IllegalArgumentException {
    super(modelState, new StringBuilder());
  }


  /**
   * A constructor that takes in the modelState of the EnglishSolitare and an Appendable object.
   * @param modelState the modelState of the {@code MarbleSolitareModel}.
   * @param object an Appendable object.
   * @throws IllegalArgumentException if the modelState or object is null.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState modelState, Appendable object)
          throws IllegalArgumentException {
    super(modelState, object);
    if (modelState == null || object == null) {
      throw new IllegalArgumentException("Model or appendable object cannot be null.");
    }
  }

  @Override
  public String toString() throws IllegalArgumentException {
    String s = "";
    for (int sRow = 0; sRow < modelState.getBoardSize(); sRow++) {
      // needs to start from 1 so that there is no space before (0, 0)
      for (int sCol = 1; sCol < modelState.getBoardSize() - sRow; sCol++) {
        s = s + " ";
      }
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
}
