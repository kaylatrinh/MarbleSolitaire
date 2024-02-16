package cs3500.marblesolitaire.controller;

import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * This is a testing class for the controller, ensuring that the build of the class is a valid
 * construction in addition to it not modifying any part of the model, and being transmitted to
 * the view.
 */
public class MarbleSolitaireControllerImplTest {
  MarbleSolitaireModel model;
  StringBuffer out;
  Reader in;
  MarbleSolitaireTextView view;
  MarbleSolitaireController controller;

  @Before
  public void initData() throws Exception {
    model = new EnglishSolitaireModel();
    out = new StringBuffer();
    in = new StringReader("1 2 q");
    view = new MarbleSolitaireTextView(model, out);
    controller = new MarbleSolitaireControllerImpl(model, view, in);
  }

  // Tests a constructor if the model is null
  @Test
  public void testNullModelConstructor() {
    try {
      MarbleSolitaireController control = new MarbleSolitaireControllerImpl(null, view,
              in);
      fail("Invalid constructor attempted.");
    } catch (IllegalArgumentException e) {
      assertNotNull(e);
    }
  }

  // Tests a constructor if the view is null
  @Test
  public void testNullViewConstructor() {
    try {
      MarbleSolitaireController control = new MarbleSolitaireControllerImpl(model, null,
              in);
      fail("Invalid constructor attempted.");
    } catch (IllegalArgumentException e) {
      assertNotNull(e);
    }
  }

  // Tests a constructor if the reader is null
  @Test
  public void testNullReaderConstructor() {
    try {
      MarbleSolitaireController control = new MarbleSolitaireControllerImpl(model, view, null);
      fail("Invalid constructor attempted.");
    } catch (IllegalArgumentException e) {
      assertNotNull(e);
    }
  }

  // Tests when there is a bad input
  @Test
  public void testUnfinishedInput() {
    try {
      MarbleSolitaireModel move1 = new EnglishSolitaireModel();
      in = new StringReader("2 4 4");
      MarbleSolitaireView view = new MarbleSolitaireTextView(move1, out);
      MarbleSolitaireController control = new MarbleSolitaireControllerImpl(model, view, in);
      control.playGame();
      fail("Unable to construct due to bad input.");
    } catch (IllegalStateException e) {
      assertNotNull(e);
    }
  }

  // Tests when an invalid input is attempted to pass
  @Test
  public void testInvalidInput() {
    try {
      StringBuffer out = new StringBuffer();
      MarbleSolitaireModel move1 = new EnglishSolitaireModel();
      in = new StringReader("3 foo 1 2");
      MarbleSolitaireView view = new MarbleSolitaireTextView(move1, out);
      MarbleSolitaireController control = new MarbleSolitaireControllerImpl(model, view, in);
      control.playGame();
      fail("Bad input provided.");
    } catch (IllegalStateException e) {
      assertNotNull(e);
    }
  }

  // Tests if the Readable runs out of inputs
  @Test
  public void testRunOut() {
    try {
      StringBuffer out = new StringBuffer();
      MarbleSolitaireModel move1 = new EnglishSolitaireModel();
      in = new StringReader("2 4 4 4");
      MarbleSolitaireView view = new MarbleSolitaireTextView(move1, out);
      MarbleSolitaireController control = new MarbleSolitaireControllerImpl(model, view, in);
      control.playGame();
      fail("Out of inputs.");
    } catch (IllegalStateException e) {
      assertNotNull(e);
    }
  }

  // Tests for a lowercase q case
  @Test
  public void testNoCapQ() throws Exception {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("q");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, out);
    MarbleSolitaireController control = new MarbleSolitaireControllerImpl(model, view, in);
    control.playGame();
    assertEquals("Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n", out.toString());
  }

  //Tests for an uppercase Q case
  @Test
  public void testCapQ() throws Exception {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("Q");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, out);
    MarbleSolitaireController control = new MarbleSolitaireControllerImpl(model, view, in);
    control.playGame();
    assertEquals("Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n", out.toString());
  }

  // Tests a game that ends when there are no more valid moves to be made
  @Test
  public void testCompleteGame() throws Exception {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("4 6 4 4 4 3 4 5 4 1 4 3 2 4 4 4 5 4 3 4 7 4 5 4");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, out);
    MarbleSolitaireController control = new MarbleSolitaireControllerImpl(model, view, in);
    control.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O _ _ O _ O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "_ _ O _ O _ O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "_ _ O O O _ O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 28\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "_ _ O _ O _ O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 27\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "_ _ O _ O _ O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "Score: 26\n" +
            "Game over!\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "_ _ O _ O _ O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "Score: 26\n", out.toString());
  }

  // Tests when there is a "q" given in place of an input for the fromRow
  @Test
  public void testFromRowQ() throws Exception {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("4 6 4 4 q");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, out);
    MarbleSolitaireController control = new MarbleSolitaireControllerImpl(model, view, in);
    control.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n", out.toString());
  }

  // Tests when there is a "q" given in place of an input for the fromColumn
  @Test
  public void testFromColQ() {
    StringBuffer out = new StringBuffer();
    MarbleSolitaireModel move1 = new EnglishSolitaireModel();
    in = new StringReader("4 6 4 4 4 q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(move1, out);
    MarbleSolitaireController control = new MarbleSolitaireControllerImpl(model, view, in);
    control.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n", out.toString());
  }

  // Tests when there is a "q" given in place of an input for the toRow
  @Test
  public void testToRow() {
    StringBuffer out = new StringBuffer();
    MarbleSolitaireModel move1 = new EnglishSolitaireModel();
    in = new StringReader("4 6 4 4 4 3 q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(move1, out);
    MarbleSolitaireController control = new MarbleSolitaireControllerImpl(model, view, in);
    control.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n", out.toString());
  }

  // Tests when there is a "q" given in place of an input for the toRow
  @Test
  public void testToColQ() {
    StringBuffer out = new StringBuffer();
    MarbleSolitaireModel move1 = new EnglishSolitaireModel();
    in = new StringReader("4 6 4 4 4 3 4 q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(move1, out);
    MarbleSolitaireController control = new MarbleSolitaireControllerImpl(model, view, in);
    control.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n", out.toString());
  }
}