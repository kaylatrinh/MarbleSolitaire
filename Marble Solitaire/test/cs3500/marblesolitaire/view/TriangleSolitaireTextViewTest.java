package cs3500.marblesolitaire.view;

import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * This class tests the {@code TriangleSolitaireTextView} in order to determine if the viewer is
 * seeing the correct Triangle Solitaire board.
 */
public class TriangleSolitaireTextViewTest {

  private MarbleSolitaireView view1;
  private MarbleSolitaireView view2;
  private MarbleSolitaireView view3;
  private MarbleSolitaireView view4;

  @Before
  public void initBoard() {
    MarbleSolitaireModel board1;
    MarbleSolitaireModel board2;
    MarbleSolitaireModel board3;
    MarbleSolitaireModel board4;
    board1 = new TriangleSolitaireModel();
    board2 = new TriangleSolitaireModel(6);
    board3 = new TriangleSolitaireModel(1, 1);
    board4 = new TriangleSolitaireModel(5, 4, 3);
    StringBuilder string1 = new StringBuilder();
    view1 = new TriangleSolitaireTextView(board1, string1);
    view2 = new TriangleSolitaireTextView(board2, string1);
    view3 = new TriangleSolitaireTextView(board3, string1);
    view4 = new TriangleSolitaireTextView(board4, string1);
  }

  @Test
  public void nullModelConstructor() {
    try {
      new TriangleSolitaireTextView(null, new StringBuilder());
      fail("Attempted invalid constructor.");
    } catch (IllegalArgumentException e) {
      assertNotNull(e);
    }
  }

  @Test
  public void nullAppendableConstructor() {
    try {
      new TriangleSolitaireTextView(new TriangleSolitaireModel(), null);
      fail("Attempted invalid constructor.");
    } catch (IllegalArgumentException e) {
      assertNotNull(e);
    }
  }

  @Test
  public void testToString() {
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", this.view1.toString());
    assertEquals("     _\n" +
            "    O O\n" +
            "   O O O\n" +
            "  O O O O\n" +
            " O O O O O\n" +
            "O O O O O O", this.view2.toString());
    assertEquals("    O\n" +
            "   O _\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", this.view3.toString());
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O _ O", this.view4.toString());
  }
}