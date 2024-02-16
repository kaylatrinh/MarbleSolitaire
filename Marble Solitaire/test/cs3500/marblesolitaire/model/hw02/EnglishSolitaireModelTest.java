package cs3500.marblesolitaire.model.hw02;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Represents the testing cases for the {@code EnglishSolitaireModel}.
 */
public class EnglishSolitaireModelTest {

  private EnglishSolitaireModel model1;
  private EnglishSolitaireModel model2;
  private EnglishSolitaireModel model3;
  private EnglishSolitaireModel model4;


  @Before
  public void initData() {
    this.model1 = new EnglishSolitaireModel();
    this.model2 = new EnglishSolitaireModel(3, 4);
    this.model3 = new EnglishSolitaireModel(5);
    this.model4 = new EnglishSolitaireModel(7, 5, 6);
  }

  // Tests the constructor that takes in invalid integers to initialize the empty spot
  @Test
  public void testInvalidConstructor2() {
    try {
      new EnglishSolitaireModel(0, 0);
      fail("Expected exception was not thrown");
    } catch (Exception e) {
      assertNotNull(e);
    }

    try {
      new EnglishSolitaireModel(8, 9);
      fail("Expected exception was not thrown");
    } catch (Exception e) {
      assertNotNull(e);
    }

    try {
      new EnglishSolitaireModel(-2, 2);
      fail("Expected exception was not thrown");
    } catch (Exception e) {
      assertNotNull(e);
    }
  }

  // Tests the constructor that takes in an invalid arm thickness
  @Test
  public void testInvalidConstructor3() {
    try {
      new EnglishSolitaireModel(-1);
      fail("Expected exception was not thrown");
    } catch (Exception e) {
      assertNotNull(e);
    }

    try {
      new EnglishSolitaireModel(2);
      fail("Expected exception was not thrown");
    } catch (Exception e) {
      assertNotNull(e);
    }
  }

  // Tests a constructor that takes in either an invalid arm thickness, or empty position
  // coordinates
  @Test
  public void testInvalidConstructor4() {
    try {
      new EnglishSolitaireModel(-1, 3, 3);
      fail("Expected exception was not thrown");
    } catch (Exception e) {
      assertNotNull(e);
    }

    try {
      new EnglishSolitaireModel(2, 3, 3);
      fail("Expected exception was not thrown");
    } catch (Exception e) {
      assertNotNull(e);
    }

    try {
      new EnglishSolitaireModel(3, 0, 0);
      fail("Expected exception was not thrown");
    } catch (Exception e) {
      assertNotNull(e);
    }
  }

  @Test
  public void testConstructor1() {
    assertEquals(7, this.model1.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(3, 3));
    assertEquals(32, this.model1.getScore());
  }

  @Test
  public void testConstructor2() {
    assertEquals(7, this.model2.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(3, 4));
    assertEquals(32, this.model2.getScore());
  }

  @Test
  public void testConstructor3() {
    assertEquals(13, this.model3.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model3.getSlotAt(6, 6));
    assertEquals(104, this.model3.getScore());
  }

  @Test
  public void testConstructor4() {
    assertEquals(19, this.model4.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model4.getSlotAt(5, 6));
    assertEquals(216, this.model4.getScore());
  }

  @Test
  public void testMove() {
    //Testing a valid move to the right on model 1.
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(3, 2));
    this.model1.move(3, 1, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(3, 3));

    //Testing a valid move to the left on model 2.
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model2.getSlotAt(3, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model2.getSlotAt(3, 6));
    this.model2.move(3, 6, 3, 4);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(3, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(3, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model2.getSlotAt(3, 4));

    //Testing a valid move down on model 3.
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model3.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model3.getSlotAt(6, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model3.getSlotAt(6, 5));
    this.model3.move(6, 4, 6, 6);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model3.getSlotAt(6, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model3.getSlotAt(6, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model3.getSlotAt(6, 6));

    //Testing a valid move up on model 4.
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model4.getSlotAt(5, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model4.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model4.getSlotAt(7, 6));
    this.model4.move(7, 6, 5, 6);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model4.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model4.getSlotAt(7, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model4.getSlotAt(5, 6));
  }

  @Test
  public void testInvalidMoves() {
    //Testing an invalid move from a position out of bounds.
    try {
      this.model1.move(-1, 1, 3, 1);
      fail("Expected exception was not thrown.");
    } catch (IllegalArgumentException e) {
      assertNotNull(e);
    }
    //Testing an invalid move to a position out of bounds.
    try {
      this.model2.move(3, 5, 3, 7);
      fail("Expected exception was not thrown.");
    } catch (IllegalArgumentException e) {
      assertNotNull(e);
    }
    //Testing an invalid move from an invalid position.
    try {
      this.model3.move(2, 2, 2, 4);
      fail("Expected exception was not thrown.");
    } catch (IllegalArgumentException e) {
      assertNotNull(e);
    }
    //Testing an invalid move to an invalid position.
    try {
      this.model4.move(2, 4, 2, 2);
      fail("Expected exception was not thrown.");
    } catch (IllegalArgumentException e) {
      assertNotNull(e);
    }
    //Testing an invalid move from an empty position.
    try {
      this.model1.move(3, 3, 3, 5);
      fail("Expected exception was not thrown.");
    } catch (IllegalArgumentException e) {
      assertNotNull(e);
    }
    //Testing an invalid move where the middle slot is empty.
    try {
      this.model2.move(3, 4, 3, 6);
      fail("Expected exception was not thrown.");
    } catch (IllegalArgumentException e) {
      assertNotNull(e);
    }
    //Testing an invalid move to a position with a marble in it.
    try {
      this.model3.move(-1, 5, 0, 3);
      fail("Expected exception was not thrown.");
    } catch (IllegalArgumentException e) {
      assertNotNull(e);
    }
    //Testing an invalid move from a position that is only one position away.
    try {
      this.model4.move(5, 7, 5, 6);
      fail("Expected exception was not thrown.");
    } catch (IllegalArgumentException e) {
      assertNotNull(e);
    }
    //Testing an invalid move from a position that is more than two positions away.
    try {
      this.model1.move(3, 3, 6, 3);
      fail("Expected exception was not thrown.");
    } catch (IllegalArgumentException e) {
      assertNotNull(e);
    }
    //Testing an invalid move to the same position.
    try {
      this.model2.move(2, 6, 2, 6);
      fail("Expected exception was not thrown.");
    } catch (IllegalArgumentException e) {
      assertNotNull(e);
    }
    //Testing an invalid move diagonally from the top left to the bottom right.
    try {
      this.model3.move(4, 4, 6, 6);
      fail("Expected exception was not thrown.");
    } catch (IllegalArgumentException e) {
      assertNotNull(e);
    }
    //Testing an invalid move diagonally from the bottom right to the top left.
    try {
      this.model4.move(8, 8, 6, 6);
      fail("Expected exception was not thrown.");
    } catch (IllegalArgumentException e) {
      assertNotNull(e);
    }
    //Testing an invalid move diagonally from the top right to the bottom left.
    try {
      this.model1.move(2, 4, 4, 2);
      fail("Expected exception was not thrown.");
    } catch (IllegalArgumentException e) {
      assertNotNull(e);
    }
    //Testing an invalid move diagonally from the bottom left to the top right.
    try {
      this.model2.move(4, 2, 2, 4);
      fail("Expected exception was not thrown.");
    } catch (IllegalArgumentException e) {
      assertNotNull(e);
    }
  }

  @Test
  public void testIsGameOver() {
    assertFalse(model1.isGameOver());
    assertFalse(model2.isGameOver());
    assertFalse(model3.isGameOver());
    assertFalse(model4.isGameOver());
    model1.move(3, 5, 3, 3);
    model1.move(3, 2, 3, 4);
    model1.move(3, 0, 3, 2);
    model1.move(1, 3, 3, 3);
    model1.move(4, 3, 2, 3);
    model1.move(6, 3, 4, 3);
    assertTrue(model1.isGameOver());
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(7, this.model1.getBoardSize());
    assertEquals(7, this.model2.getBoardSize());
    assertEquals(13, this.model3.getBoardSize());
    assertEquals(19, this.model4.getBoardSize());
  }

  @Test
  public void testGetSlotAt() {
    //Slot states for model 1.
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model1.getSlotAt(0, 0));

    //Slot states for model 2.
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model2.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model2.getSlotAt(0, 6));

    //Slot states for model 3.
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model3.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model3.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model3.getSlotAt(10, 10));

    //Slot states for model 4.
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model4.getSlotAt(5, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model4.getSlotAt(0, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model4.getSlotAt(3, 2));
  }

  @Test
  public void testGetScore() {
    assertEquals(32, this.model1.getScore());
    assertEquals(32, this.model2.getScore());
    assertEquals(104, this.model3.getScore());
    assertEquals(216, this.model4.getScore());
  }
}