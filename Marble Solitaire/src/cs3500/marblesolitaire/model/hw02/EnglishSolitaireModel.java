package cs3500.marblesolitaire.model.hw02;

/**
 * Represents a game of English Solitaire where a marble can only move horizontally or diagonally
 * by two spots, consisting of the spot to which is moving is empty and the spot between the from
 * position and to position is a marble.
 */
public class EnglishSolitaireModel implements MarbleSolitaireModel {
  private final int armThickness;
  private final int emptyRow;
  private final int emptyCol;
  private final SlotState[][] board;
  private static int DEFAULT_ARM = 3;


  /**
   * A constructor that takes in no parameters and sets the default arm thickness to 3, with the
   * empty slot in the center.
   */
  public EnglishSolitaireModel() {
    this.armThickness = DEFAULT_ARM;
    this.emptyRow = (armThickness * 3 - 3) / 2;
    this.emptyCol = (armThickness * 3 - 3) / 2;
    this.board = initBoard();
  }

  /**
   * A constructor that sets the given row and column as the empty slot.
   *
   * @param sRow the given row.
   * @param sCol the given column.
   * @throws IllegalArgumentException if the given position is an invalid bound of the board.
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this.armThickness = DEFAULT_ARM;
    if (invalidBounds(sRow, sCol)) {
      throw new IllegalArgumentException("Invalid empty cell position ("
              + sRow + ", " + sCol + ")");
    }
    this.emptyRow = sRow;
    this.emptyCol = sCol;
    this.board = initBoard();
  }

  /**
   * A constructor that sets the arm thickness.
   *
   * @param armThickness the arm thickness of the board.
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number.
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    this.armThickness = armThickness;
    if (armThickness < 0 || armThickness % 2 == 0) {
      throw new IllegalArgumentException("Arm thickness must be a positive odd number.");
    }
    this.emptyRow = (armThickness * 3 - 3) / 2;
    this.emptyCol = (armThickness * 3 - 3) / 2;
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
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    this.armThickness = armThickness;
    if (armThickness < 0 || armThickness % 2 == 0 || invalidBounds(sRow, sCol)) {
      throw new IllegalArgumentException("Arm thickness must be a positive odd number or empty "
              + "cell position invalid.");
    }
    this.emptyRow = sRow;
    this.emptyCol = sCol;
    this.board = initBoard();
  }

  /**
   * Initializes the board with the given spots as a marble, empty, or invalid.
   *
   * @return a board with the {@code SlotState}.
   */
  private SlotState[][] initBoard() {
    SlotState[][] board = new SlotState[this.getBoardSize()][this.getBoardSize()];
    for (int row = 0; row < this.getBoardSize(); row++) {
      for (int col = 0; col < this.getBoardSize(); col++) {
        if (row == this.emptyRow && col == this.emptyCol) {
          board[row][col] = SlotState.Empty;
        } else {
          if (invalidBounds(row, col)) {
            board[row][col] = SlotState.Invalid;
          } else {
            board[row][col] = SlotState.Marble;
          }
        }
      }
    }
    return board;
  }


  /**
   * Determines if the given position is out of the appropriate bounds.
   *
   * @param sRow the row of the given position.
   * @param sCol the column of the given position.
   * @return true if an invalid position, false if valid.
   */
  private boolean invalidBounds(int sRow, int sCol) {
    return sRow < 0 || sCol < 0 // smaller than board
            || sRow >= this.getBoardSize() || sCol >= this.getBoardSize() // larger than board
            || (sRow < minIndex() && sCol < minIndex()) // top left corner
            || (sRow > maxIndex() && sCol < minIndex()) // bottom left corner
            || (sRow < minIndex() && sCol > maxIndex()) // top right corner
            || (sRow > maxIndex() && sCol > maxIndex()); // bottom right corner
  }

  /**
   * Determines the minimum boundary of the board.
   *
   * @return an int with the minimum boundary.
   */
  private int minIndex() {
    return armThickness - 1;
  }

  /**
   * Determines the maximum boundary of the board.
   *
   * @return an int with the minimum boundary.
   */
  private int maxIndex() {
    return (armThickness * 2) - 2;
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

  private boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    return hasMarble(fromRow, fromCol)
            && (canMoveRight(fromRow, fromCol, toRow, toCol)
            || canMoveLeft(fromRow, fromCol, toRow, toCol)
            || canMoveDown(fromRow, fromCol, toRow, toCol)
            || canMoveUp(fromRow, fromCol, toRow, toCol));
  }

  private boolean hasMarble(int row, int col) {
    if (invalidBounds(row, col)) {
      return false;
    }
    return board[row][col] == SlotState.Marble;
  }


  private boolean canMoveRight(int fromRow, int fromCol, int toRow, int toCol) {
    return !invalidBounds(fromRow, fromCol)
            && !invalidBounds(toRow, toCol)
            && (!invalidBounds(fromRow, fromCol + 1)
            && board[fromRow][fromCol + 1] == SlotState.Marble)
            && (!invalidBounds(fromRow, fromCol + 2)
            && fromRow == toRow && fromCol + 2 == toCol)
            && board[toRow][toCol] == SlotState.Empty;
  }

  private boolean canMoveLeft(int fromRow, int fromCol, int toRow, int toCol) {
    return !invalidBounds(fromRow, fromCol)
            && !invalidBounds(toRow, toCol)
            && (!invalidBounds(fromRow, fromCol - 1)
            && board[fromRow][fromCol - 1] == SlotState.Marble)
            && (!invalidBounds(fromRow, fromCol - 2)
            && fromRow == toRow && fromCol - 2 == toCol)
            && board[toRow][toCol] == SlotState.Empty;
  }


  private boolean canMoveUp(int fromRow, int fromCol, int toRow, int toCol) {
    return !invalidBounds(fromRow, fromCol)
            && !invalidBounds(toRow, toCol)
            && (!invalidBounds(fromRow - 1, fromCol)
            && board[fromRow - 1][fromCol] == SlotState.Marble)
            && (!invalidBounds(fromRow - 2, fromCol)
            && fromRow - 2 == toRow && fromCol == toCol)
            && board[toRow][toCol] == SlotState.Empty;
  }


  private boolean canMoveDown(int fromRow, int fromCol, int toRow, int toCol) {
    return !invalidBounds(fromRow, fromCol)
            && !invalidBounds(toRow, toCol)
            && (!invalidBounds(fromRow + 1, fromCol)
            && board[fromRow + 1][fromCol] == SlotState.Marble)
            && (!invalidBounds(fromRow + 2, fromCol)
            && fromRow + 2 == toRow && fromCol == toCol)
            && board[toRow][toCol] == SlotState.Empty;
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

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || col < 0 || row > getBoardSize() || col > getBoardSize()) {
      throw new IllegalArgumentException("Row or column is beyond the dimensions of the board.");
    }
    return this.board[row][col];
  }

  @Override
  public int getScore() {
    int score = 0;
    for (int row = 0; row < this.getBoardSize(); row++) {
      for (int col = 0; col < this.getBoardSize(); col++) {
        if (this.board[row][col] == SlotState.Marble) {
          score = score + 1;
        }
      }
    }
    return score;
  }
}
