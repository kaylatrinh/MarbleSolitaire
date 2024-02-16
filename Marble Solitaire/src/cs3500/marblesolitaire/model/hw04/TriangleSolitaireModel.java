package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * A class for Triangle Solitaire that is similar to other solitaire games, but in the shape of a
 * triangle and with the ability to move "diagonally."
 */
public class TriangleSolitaireModel extends AbstractSolitaireModel {


  /**
   * A constructor that takes in no parameters and sets the default arm thickness to 3, with the
   * empty slot in the center.
   */
  public TriangleSolitaireModel() {
    super(5, 0, 0);
  }

  /**
   * A constructor that sets the arm thickness.
   *
   * @param dimensions the arm thickness of the board.
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number.
   */
  public TriangleSolitaireModel(int dimensions) throws IllegalArgumentException {
    super(dimensions, 0, 0);
    if (dimensions < 0) {
      throw new IllegalArgumentException("Dimension must be a positive number.");
    }
    this.board = initBoard();
  }

  /**
   * A constructor that sets the given row and column as the empty slot.
   *
   * @param sRow the given row.
   * @param sCol the given column.
   * @throws IllegalArgumentException if the given position is an invalid bound of the board.
   */
  public TriangleSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(5, sRow, sCol);
    if (invalidBounds(sRow, sCol)) {
      throw new IllegalArgumentException("Invalid empty cell position ("
              + sRow + ", " + sCol + ")");
    }
    this.emptyRow = sRow;
    this.emptyCol = sCol;
    this.board = initBoard();
  }


  /**
   * A constructor that sets the arm thickness and the empty slot.
   *
   * @param dimensions the arm thickness of the board.
   * @param sRow       the row of the empty slot.
   * @param sCol       the column of the empty slot.
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number or the
   *                                  bounds of the given slot is invalid.
   */
  public TriangleSolitaireModel(int dimensions, int sRow, int sCol)
          throws IllegalArgumentException {
    super(dimensions, sRow, sCol);
    if (dimensions < 0 || invalidBounds(sRow, sCol)) {
      throw new IllegalArgumentException("Dimension must be a positive number or empty "
              + "cell position invalid.");
    }
    this.emptyRow = sRow;
    this.emptyCol = sCol;
    this.board = initBoard();
  }

  /**
   * Determines if the given position is out of the appropriate bounds.
   *
   * @param sRow the row of the given position.
   * @param sCol the column of the given position.
   * @return true if an invalid position, false if valid.
   */
  @Override
  protected boolean invalidBounds(int sRow, int sCol) {
    return sRow < 0 || sCol < 0
            || sRow >= armThickness || sCol >= armThickness
            || sCol > sRow;
  }


  /**
   * Moving the marble from one position to another.
   *
   * @param fromRow the row number of the position to be moved from.
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from.
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to.
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to.
   *                (starts at 0)
   * @throws IllegalArgumentException if there is an attempt to make an invalid move such as
   *                                  moving from an empty position, the middle slot is empty,
   *                                  there is already a marble in the to
   *                                  position, the position is only one spot away,
   *                                  the position is more than two spots away, it
   *                                  is the same position, or moving diagonally.
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) {
    if (!isValidMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("This is not a valid move.");
    }
    if (fromRow - toRow == -2 && fromCol == toCol) {
      board[fromRow + 1][fromCol] = SlotState.Empty;
    }
    if (fromRow - toRow == 2 && fromCol == toCol) {
      board[fromRow - 1][fromCol] = SlotState.Empty;
    }
    if (fromRow == toRow && fromCol - toCol == -2) {
      board[fromRow][fromCol + 1] = SlotState.Empty;
    }
    if (fromRow == toRow && fromCol - toCol == 2) {
      board[fromRow][fromCol - 1] = SlotState.Empty;
    }
    if (fromRow - toRow == 2 && fromCol - toCol == 2) {
      board[fromRow - 1][fromCol - 1] = SlotState.Empty;
    }
    if (fromRow - toRow == -2 && fromCol - toCol == -2) {
      board[fromRow + 1][fromCol + 1] = SlotState.Empty;
    }
    board[fromRow][fromCol] = MarbleSolitaireModelState.SlotState.Empty;
    board[toRow][toCol] = MarbleSolitaireModelState.SlotState.Marble;
  }

  @Override
  protected boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    return hasMarble(fromRow, fromCol)
            && (canMoveRight(fromRow, fromCol, toRow, toCol)
            || canMoveLeft(fromRow, fromCol, toRow, toCol)
            || canMoveDownAndLeft(fromRow, fromCol, toRow, toCol)
            || canMoveUpLeft(fromRow, fromCol, toRow, toCol)
            || canMoveDownRight(fromRow, fromCol, toRow, toCol)
            || canMoveUpAndRight(fromRow, fromCol, toRow, toCol));
  }

  private boolean canMoveDownRight(int fromRow, int fromCol, int toRow, int toCol) {
    return !invalidBounds(fromRow, fromCol)
            && !invalidBounds(toRow, toCol)
            && (!invalidBounds(fromRow + 1, fromCol + 1)
            && board[fromRow + 1][fromCol + 1] == MarbleSolitaireModelState.SlotState.Marble)
            && (!invalidBounds(fromRow + 2, fromCol + 2)
            && fromRow + 2 == toRow && fromCol + 2 == toCol)
            && board[toRow][toCol] == MarbleSolitaireModelState.SlotState.Empty;

  }

  private boolean canMoveUpLeft(int fromRow, int fromCol, int toRow, int toCol) {
    return !invalidBounds(fromRow, fromCol)
            && !invalidBounds(toRow, toCol)
            && (!invalidBounds(fromRow - 1, fromCol - 1)
            && board[fromRow - 1][fromCol - 1] == MarbleSolitaireModelState.SlotState.Marble)
            && (!invalidBounds(fromRow - 2, fromCol - 2)
            && fromRow - 2 == toRow && fromCol - 2 == toCol)
            && board[toRow][toCol] == MarbleSolitaireModelState.SlotState.Empty;
  }


  @Override
  public boolean isGameOver() {
    for (int row = 0; row < this.getBoardSize(); row++) {
      for (int col = 0; col < this.getBoardSize(); col++) {
        if (board[row][col] == MarbleSolitaireModelState.SlotState.Marble) {
          if (isValidMove(row, col, row + 2, col)) {
            return false;
          }
          if (isValidMove(row, col, row, col + 2)) {
            return false;
          }
          if (isValidMove(row, col, row, col - 2)) {
            return false;
          }
          if (isValidMove(row, col, row - 2, col)) {
            return false;
          }
          if (isValidMove(row, col, row + 2, col + 2)) {
            return false;
          }
          if (isValidMove(row, col, row - 2, col - 2)) {
            return false;
          }
        }
      }
    }
    return true;
  }


  @Override
  public int getBoardSize() {
    return armThickness;
  }
}
