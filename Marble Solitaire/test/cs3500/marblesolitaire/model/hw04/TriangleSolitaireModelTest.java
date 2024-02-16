package cs3500.marblesolitaire.model.hw04;

import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This is a testing class for the {@code TriangleSolitaireModel} to ensure that the board is
 * created in the shape of a triangle, and that there are only six valid directions that a marble
 * can be moved: left, right, "diagonal" up-left, down-left, up-right, down-right.
 */
public class TriangleSolitaireModelTest {

  private TriangleSolitaireModel model1;
  private TriangleSolitaireModel model2;
  private TriangleSolitaireModel model3;
  private TriangleSolitaireModel model4;
  private TriangleSolitaireModel model5;
  private TriangleSolitaireModel model6;
  private TriangleSolitaireModel model7;


  @Before
  public void initData() {
    this.model1 = new TriangleSolitaireModel();
    this.model2 = new TriangleSolitaireModel(3, 2);
    this.model3 = new TriangleSolitaireModel(5);
    this.model4 = new TriangleSolitaireModel(7, 4, 3);
    this.model5 = new TriangleSolitaireModel(4, 3, 0);
    this.model6 = new TriangleSolitaireModel(5, 4, 2);
    this.model7 = new TriangleSolitaireModel(7, 6, 4);
  }

  // Tests the constructor that takes in invalid integers to initialize the empty spot
  @Test
  public void testInvalidConstructorTwoInputs() {
    try {
      new EuropeanSolitaireModel(0, 0);
      fail("Expected exception was not thrown");
    } catch (Exception e) {
      assertNotNull(e);
    }

    try {
      new EuropeanSolitaireModel(6, 1);
      fail("Expected exception was not thrown");
    } catch (Exception e) {
      assertNotNull(e);
    }
  }

  // Tests the constructor that takes in an invalid arm thickness
  @Test
  public void testInvalidConstructorOneInput() {
    try {
      new EuropeanSolitaireModel(-1);
      fail("Expected exception was not thrown");
    } catch (Exception e) {
      assertNotNull(e);
    }
  }

  // Tests a constructor that takes in either an invalid arm thickness, or empty position
  // coordinates
  @Test
  public void testInvalidConstructorThreeInputs() {
    try {
      new EuropeanSolitaireModel(-1, 3, 3);
      fail("Expected exception was not thrown");
    } catch (Exception e) {
      assertNotNull(e);
    }

    try {
      new EuropeanSolitaireModel(2, 3, 4);
      fail("Expected exception was not thrown");
    } catch (Exception e) {
      assertNotNull(e);
    }
  }

  @Test
  public void testConstructorNoInputs() {
    assertEquals(5, this.model1.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(0, 0));
    assertEquals(14, this.model1.getScore());
  }

  @Test
  public void testConstructorTwoInputs() {
    assertEquals(5, this.model2.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(3, 2));
    assertEquals(14, this.model2.getScore());
  }

  @Test
  public void testConstructorOneInput() {
    assertEquals(5, this.model3.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model3.getSlotAt(0, 0));
    assertEquals(14, this.model3.getScore());
  }

  @Test
  public void testConstructorThreeInputs() {
    assertEquals(7, this.model4.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model4.getSlotAt(4, 3));
    assertEquals(27, this.model4.getScore());
  }

  @Test
  public void testMove() {
    //Testing a valid move two columns right and same row
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model2.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model2.getSlotAt(3, 1));
    this.model2.move(3, 0, 3, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model2.getSlotAt(3, 2));

    //Testing a valid move two columns left and same row
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model5.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model5.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model5.getSlotAt(3, 2));
    this.model5.move(3, 2, 3, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model5.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model5.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model5.getSlotAt(3, 0));

    //Testing a valid move two rows up and same column
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(1, 0));
    this.model1.move(2, 0, 0, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(0, 0));

    //Testing a valid move two rows down and same column
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model6.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model6.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model6.getSlotAt(2, 2));
    this.model6.move(2, 2, 4, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model6.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model6.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model6.getSlotAt(4, 2));

    //Testing a valid move two rows up and two columns right
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model4.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model4.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model4.getSlotAt(2, 1));
    this.model4.move(2, 1, 4, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model4.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model4.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model4.getSlotAt(4, 3));

    //Testing a valid move two rows down and two columns left
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model7.getSlotAt(6, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model7.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model7.getSlotAt(4, 2));
    this.model7.move(4, 2, 6, 4);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model7.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model7.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model7.getSlotAt(6, 4));
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
      this.model2.move(3, 2, 3, 5);
      fail("Expected exception was not thrown.");
    } catch (IllegalArgumentException e) {
      assertNotNull(e);
    }
    //Testing an invalid move from an empty position
    try {
      this.model3.move(0,0,0,2);
      fail("Expected exception was not thrown.");
    } catch (IllegalArgumentException e) {
      assertNotNull(e);
    }
    //Testing an invalid move over an empty slot
    try {
      this.model4.move(5, 4, 3, 2);
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
    model1.move(2, 0, 0, 0);
    model1.move(4, 0, 2, 0);
    model1.move(3, 2, 1, 0);
    model1.move(4, 1, 2, 1);
    model1.move(4, 3, 4, 1);
    model1.move(1, 0, 3, 0);
    model1.move(1, 1, 3, 1);
    model1.move(3, 0, 3, 2);
    model1.move(3, 3, 3, 1);
    model1.move(4, 1, 2, 1);
    model1.move(2, 2, 2, 0);
    assertTrue(model1.isGameOver());
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(5, this.model1.getBoardSize());
    assertEquals(5, this.model2.getBoardSize());
    assertEquals(5, this.model3.getBoardSize());
    assertEquals(7, this.model4.getBoardSize());
    assertEquals(4, this.model5.getBoardSize());
    assertEquals(5, this.model6.getBoardSize());
    assertEquals(7, this.model7.getBoardSize());

  }

  @Test
  public void testGetSlotAt() {
    //Slot states for model 1.
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(1, 0));

    //Slot states for model 2.
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model2.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model2.getSlotAt(3, 1));

    //Slot states for model 3.
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model3.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model3.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model3.getSlotAt(1, 2));

    //Slot states for model 4.
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model4.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model4.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model4.getSlotAt(2, 1));

    //Slot states for model 5.
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model5.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model5.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model5.getSlotAt(3, 2));

    //Slot states for model 6.
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model6.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model6.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model6.getSlotAt(2, 2));

    //Slot states for model 7.
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model7.getSlotAt(6, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model7.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model7.getSlotAt(4, 2));
  }

  @Test
  public void testGetScore() {
    assertEquals(14, this.model1.getScore());
    assertEquals(14, this.model2.getScore());
    assertEquals(14, this.model3.getScore());
    assertEquals(27, this.model4.getScore());
    assertEquals(9, this.model5.getScore());
    assertEquals(14, this.model6.getScore());
    assertEquals(27, this.model7.getScore());
  }
}