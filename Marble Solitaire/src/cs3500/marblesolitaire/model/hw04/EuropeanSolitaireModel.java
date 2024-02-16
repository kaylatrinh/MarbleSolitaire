package cs3500.marblesolitaire.model.hw04;

/**
 * A class that represents a European Solitaire model, which is very similar to an English
 * Solitaire model but with different dimensions of a board. These now include corners of the
 * previous invalid positions.
 */

public class EuropeanSolitaireModel extends AbstractSolitaireModel {

  /**
   * A constructor that takes in no parameters and sets the default arm thickness to 3, with the
   * empty slot in the center.
   */
  public EuropeanSolitaireModel() {
    super(3, 3, 3);
    this.board = initBoard();
  }

  /**
   * A constructor that sets the given row and column as the empty slot.
   *
   * @param sRow the given row.
   * @param sCol the given column.
   * @throws IllegalArgumentException if the given position is an invalid bound of the board.
   */
  public EuropeanSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(3, sRow, sCol);
    if (invalidBounds(sRow, sCol)) {
      throw new IllegalArgumentException("Invalid empty cell position ("
              + sRow + ", " + sCol + ")");
    }
    this.board = initBoard();
  }

  /**
   * A constructor that sets the arm thickness.
   *
   * @param armThickness the arm thickness of the board.
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number.
   */
  public EuropeanSolitaireModel(int armThickness) throws IllegalArgumentException {
    super(armThickness, (armThickness * 3 - 3) / 2, (armThickness * 3 - 3) / 2);
    if (armThickness < 0 || armThickness % 2 == 0) {
      throw new IllegalArgumentException("Arm thickness must be a positive odd number.");
    }
    this.board = initBoard();
  }

  /**
   * A constructor that sets the arm thickness and the empty slot.
   *
   * @param armThickness the arm thickness of the board.
   * @param sRow         the row of the empty slot.
   * @param sCol         the column of the empty slot.
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number or the
   *                                  bounds of the given slot is invalid.
   */
  public EuropeanSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    super(armThickness, sRow, sCol);
    if (armThickness < 0 || armThickness % 2 == 0 || invalidBounds(sRow, sCol)) {
      throw new IllegalArgumentException("Arm thickness must be a positive odd number or empty "
              + "cell position invalid.");
    }
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
    return sRow < 0 || sCol < 0 // smaller than board
            || sRow >= this.getBoardSize() || sCol >= this.getBoardSize() // larger than board
            || (sRow < this.armThickness && sCol < minIndexTopLeft(sRow)) // top left
            || (sRow < this.armThickness && sCol > maxIndexTopRight(sRow)) // top right
            || (sRow > this.armThickness && sCol < minIndexBottomLeft(sRow)) // bottom left
            || (sRow > this.armThickness && sCol > maxIndexBottomRight(sRow)); // bottom right
  }

  /**
   * Determines a minimum boundary of the board if the row is smaller than the arm thickness.
   * @param row the given row.
   * @return an int that determines a valid column.
   */
  private int minIndexTopLeft(int row) {
    return this.armThickness - row - 1;
  }

  /**
   * Determines a minimum boundary of the board if the row is larger than the arm thickness.
   * @param row the given row.
   * @return an int that determines a valid column.
   */
  private int minIndexBottomLeft(int row) {
    return row - (this.getBoardSize() - this.armThickness);
  }

  /**
   * Determines a maximum boundary of the board if the row is less than the arm thickness.
   * @param row the given row.
   * @return an int that determines a valid column.
   */
  private int maxIndexTopRight(int row) {
    return this.getBoardSize() - this.armThickness + row;
  }

  /**
   * Determines a maximum boundary of the board if the row is greater than the arm thickness.
   * @param row the given row.
   * @return an int that determines a valid column.
   */
  private int maxIndexBottomRight(int row) {
    return this.getBoardSize() - row + (2 * this.armThickness) - 3;
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
    board[fromRow][fromCol] = SlotState.Empty;
    board[toRow][toCol] = SlotState.Marble;
  }

  @Override
  protected boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    return hasMarble(fromRow, fromCol)
            && (canMoveRight(fromRow, fromCol, toRow, toCol)
            || canMoveLeft(fromRow, fromCol, toRow, toCol)
            || canMoveDownAndLeft(fromRow, fromCol, toRow, toCol)
            || canMoveUpAndRight(fromRow, fromCol, toRow, toCol));
  }

  @Override
  public boolean isGameOver() {
    for (int row = 0; row < this.getBoardSize(); row++) {
      for (int col = 0; col < this.getBoardSize(); col++) {
        if (board[row][col] == SlotState.Marble) {
          if (isValidMove(row, col, row + 2, col)) {
            return false;
          }
          if (isValidMove(row, col, row - 2, col)) {
            return false;
          }
          if (isValidMove(row, col, row, col + 2)) {
            return false;
          }
          if (isValidMove(row, col, row, col - 2)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  @Override
  public int getBoardSize() {
    return (armThickness * 3) - 2;
  }
}
