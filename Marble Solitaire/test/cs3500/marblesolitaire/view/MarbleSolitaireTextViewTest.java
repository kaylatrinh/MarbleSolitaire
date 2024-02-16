package cs3500.marblesolitaire.view;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Represents a testing class for the {@code MarbleSolitaireTextView}.
 */
public class MarbleSolitaireTextViewTest {

  private MarbleSolitaireView view1;
  private MarbleSolitaireView view2;
  private MarbleSolitaireView view3;
  private MarbleSolitaireView view4;
  private MarbleSolitaireView view5;
  private MarbleSolitaireView view6;
  private MarbleSolitaireView view7;
  private MarbleSolitaireView view8;
  private MarbleSolitaireView view9;
  private StringBuilder string1;

  @Before
  public void initBoard() {
    MarbleSolitaireModel board1;
    MarbleSolitaireModel board2;
    MarbleSolitaireModel board3;
    MarbleSolitaireModel board4;
    MarbleSolitaireModel board5;
    EuropeanSolitaireModel board6;
    EuropeanSolitaireModel board7;
    EuropeanSolitaireModel board8;
    EuropeanSolitaireModel board9;
    board1 = new EnglishSolitaireModel();
    board2 = new EnglishSolitaireModel(3, 4);
    board3 = new EnglishSolitaireModel(5);
    board4 = new EnglishSolitaireModel(7, 5, 6);
    board5 = new EnglishSolitaireModel();
    board6 = new EuropeanSolitaireModel();
    board7 = new EuropeanSolitaireModel(5);
    board8 = new EuropeanSolitaireModel(3, 4);
    board9 = new EuropeanSolitaireModel(5, 3, 4);

    string1 = new StringBuilder();
    view1 = new MarbleSolitaireTextView(board1, string1);
    view2 = new MarbleSolitaireTextView(board2, string1);
    view3 = new MarbleSolitaireTextView(board3, string1);
    view4 = new MarbleSolitaireTextView(board4, string1);
    view5 = new MarbleSolitaireTextView(board5, string1);
    view6 = new MarbleSolitaireTextView(board6, string1);
    view7 = new MarbleSolitaireTextView(board7, string1);
    view8 = new MarbleSolitaireTextView(board8, string1);
    view9 = new MarbleSolitaireTextView(board9, string1);
  }

  @Test
  public void nullModelConstructor() {
    try {
      new MarbleSolitaireTextView(null, new StringBuilder());
      fail("Attempted invalid constructor.");
    } catch (IllegalArgumentException e) {
      assertNotNull(e);
    }
  }

  @Test
  public void nullAppendableConstructor() {
    try {
      new MarbleSolitaireTextView(new EnglishSolitaireModel(), null);
      fail("Attempted invalid constructor.");
    } catch (IllegalArgumentException e) {
      assertNotNull(e);
    }
  }

  @Test
  public void testToString() {
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", this.view1.toString());
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O _ O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", this.view2.toString());
    assertEquals("        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O", this.view3.toString());
    assertEquals("            O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            _ O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            O O O O O O O", this.view4.toString());
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", this.view5.toString());

    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", this.view6.toString());

    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", this.view7.toString());
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", this.view8.toString());
    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O _ O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", this.view9.toString());
  }

  @Test
  public void testEnglishAfterOneMove() {
    EnglishSolitaireModel board = new EnglishSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(board);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", view.toString());
    board.move(3, 5, 3, 3);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", view.toString());
  }

  @Test
  public void testEuropeanAfterOneMove() {
    EuropeanSolitaireModel board = new EuropeanSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(board);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", view.toString());
    board.move(3, 1, 3, 3);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", view.toString());
  }

  @Test
  public void testRenderMessage() {
    try {
      view1.renderMessage("I like pizza.");
    } catch (IOException e) {
      throw new RuntimeException();
    }
    assertEquals("I like pizza.", string1.toString());
  }

  @Test
  public void testRenderBoard() {
    try {
      view1.renderBoard();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", string1.toString());
  }
}