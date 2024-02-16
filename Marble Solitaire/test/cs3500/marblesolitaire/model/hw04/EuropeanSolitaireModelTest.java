package cs3500.marblesolitaire.model.hw04;

import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * This is a testing class for the {@code EuropeanSolitaireModel}. In the implementation of the
 * European Solitaire game, most of it is not different from the English Solitaire game. The only
 * thing that is changed are that there are more valid positions on the board (the triangles in each
 * corner).
 */

public class EuropeanSolitaireModelTest {

  private EuropeanSolitaireModel model1;
  private EuropeanSolitaireModel model2;
  private EuropeanSolitaireModel model3;
  private EuropeanSolitaireModel model4;
  private EuropeanSolitaireModel model5;
  private EuropeanSolitaireModel model6;
  private EuropeanSolitaireModel model7;



  @Before
  public void initData() {
    this.model1 = new EuropeanSolitaireModel();
    this.model2 = new EuropeanSolitaireModel(1, 5);
    this.model3 = new EuropeanSolitaireModel(5);
    this.model4 = new EuropeanSolitaireModel(7, 5, 6);
    this.model5 = new EuropeanSolitaireModel(5,5);
    this.model6 = new EuropeanSolitaireModel(5, 3);
    this.model7 = new EuropeanSolitaireModel(3, 1);

  }

  @Test
  public void testConstructor1() {
    assertEquals(7, this.model1.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(3, 3));
    assertEquals(36, this.model1.getScore());
  }

  @Test
  public void testConstructor2() {
    assertEquals(7, this.model2.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(3, 4));
    assertEquals(36, this.model2.getScore());
  }

  @Test
  public void testConstructor3() {
    assertEquals(13, this.model3.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model3.getSlotAt(6, 6));
    assertEquals(128, this.model3.getScore());
  }

  @Test
  public void testConstructor4() {
    assertEquals(19, this.model4.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model4.getSlotAt(5, 6));
    assertEquals(276, this.model4.getScore());
  }

  @Test
  public void testMove() {
    //Testing a valid move into the new top right valid positions
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(1, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model2.getSlotAt(2, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model2.getSlotAt(3, 5));
    this.model2.move(3, 5, 1, 5);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(2, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(3, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model2.getSlotAt(1, 5));

    //Testing a valid move into the new bottom right valid position
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model5.getSlotAt(5, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model5.getSlotAt(4, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model5.getSlotAt(3, 5));
    this.model5.move(3, 5, 5, 5);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model5.getSlotAt(4, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model5.getSlotAt(3, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model5.getSlotAt(5, 5));

    //Testing a valid move from the new top left valid position
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model6.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model6.getSlotAt(5, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model6.getSlotAt(5, 2));
    this.model6.move(5, 1, 5, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model6.getSlotAt(5, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model6.getSlotAt(5, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model6.getSlotAt(5, 3));

    //Testing a valid move from the new bottom left valid position
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model7.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model7.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model7.getSlotAt(2, 1));
    this.model7.move(1, 1, 3, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model7.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model7.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model7.getSlotAt(3, 1));
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
    model1.move(1, 1, 1, 3);
    model1.move(1, 4, 1, 2);
    model1.move(5, 5, 3, 5);
    model1.move(3, 5, 3, 3);
    model1.move(3, 3, 1, 3);
    model1.move(1, 3, 1, 1);
    model1.move(1, 1, 3, 1);
    model1.move(3, 1, 3, 3);
    model1.move(3, 3, 5, 3);
    model1.move(5, 3, 5, 5);
    model1.move(5, 1, 3, 1);
    model1.move(5, 2, 3, 2);
    model1.move(2, 2, 4, 2);
    model1.move(2, 5, 2, 3);
    assertTrue(model1.isGameOver());
  }

  @Test
  public void testGetScore() {
    assertEquals(36, this.model1.getScore());
    assertEquals(36, this.model2.getScore());
    assertEquals(128, this.model3.getScore());
    assertEquals(276, this.model4.getScore());
  }
}