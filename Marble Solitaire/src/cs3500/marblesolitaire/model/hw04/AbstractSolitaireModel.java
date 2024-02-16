package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This is a class that abstracts the common methods for a Marble Solitaire game.
 * This includes initializing the board, invalid bounds and its helper methods, valid moves,
 * and its helper methods,
 */
public abstract class AbstractSolitaireModel implements MarbleSolitaireModel {
  protected int armThickness;
  protected int emptyRow;
  protected int emptyCol;
  protected SlotState[][] board;

  AbstractSolitaireModel(int armThickness, int emptyRow, int emptyCol) {
    this.armThickness = armThickness;
    this.emptyRow = emptyRow;
    this.emptyCol = emptyCol;
    this.board = initBoard();
  }

  /**
   * Initializes the board with the given spots as a marble, empty, or invalid.
   *
   * @return a board with the {@code SlotState}.
   */
  protected SlotState[][] initBoard() {
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
  protected abstract boolean invalidBounds(int sRow, int sCol);

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
  public abstract void move(int fromRow, int fromCol, int toRow, int toCol);

  protected abstract boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol);

  protected boolean hasMarble(int row, int col) {
    if (invalidBounds(row, col)) {
      return false;
    }
    return board[row][col] == SlotState.Marble;
  }


  protected boolean canMoveRight(int fromRow, int fromCol, int toRow, int toCol) {
    return !invalidBounds(fromRow, fromCol)
            && !invalidBounds(toRow, toCol)
            && (!invalidBounds(fromRow, fromCol + 1)
            && board[fromRow][fromCol + 1] == SlotState.Marble)
            && (!invalidBounds(fromRow, fromCol + 2)
            && fromRow == toRow && fromCol + 2 == toCol)
            && board[toRow][toCol] == SlotState.Empty;
  }

  protected boolean canMoveLeft(int fromRow, int fromCol, int toRow, int toCol) {
    return !invalidBounds(fromRow, fromCol)
            && !invalidBounds(toRow, toCol)
            && (!invalidBounds(fromRow, fromCol - 1)
            && board[fromRow][fromCol - 1] == SlotState.Marble)
            && (!invalidBounds(fromRow, fromCol - 2)
            && fromRow == toRow && fromCol - 2 == toCol)
            && board[toRow][toCol] == SlotState.Empty;
  }


  protected boolean canMoveUpAndRight(int fromRow, int fromCol, int toRow, int toCol) {
    return !invalidBounds(fromRow, fromCol)
            && !invalidBounds(toRow, toCol)
            && (!invalidBounds(fromRow - 1, fromCol)
            && board[fromRow - 1][fromCol] == SlotState.Marble)
            && (!invalidBounds(fromRow - 2, fromCol)
            && fromRow - 2 == toRow && fromCol == toCol)
            && board[toRow][toCol] == SlotState.Empty;
  }


  protected boolean canMoveDownAndLeft(int fromRow, int fromCol, int toRow, int toCol) {
    return !invalidBounds(fromRow, fromCol)
            && !invalidBounds(toRow, toCol)
            && (!invalidBounds(fromRow + 1, fromCol)
            && board[fromRow + 1][fromCol] == SlotState.Marble)
            && (!invalidBounds(fromRow + 2, fromCol)
            && fromRow + 2 == toRow && fromCol == toCol)
            && board[toRow][toCol] == SlotState.Empty;
  }

  @Override
  public abstract boolean isGameOver();

  public abstract int getBoardSize();

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

