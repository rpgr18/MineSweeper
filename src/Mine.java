import java.awt.Color;
import javalib.worldimages.*;

// to represent a mine
public class Mine extends ACell implements IUtils {
  // constructor
  Mine() {
    super();
  }

  // constructor for testing
  Mine(boolean revealed, boolean flagged) {
    super(revealed, flagged);
  }

  // returns the number of this cell's neighbors which are mines, or -1 if this cell is a mine
  public int mineNeighbors() {
    return -1;
  }

  // reveals this cell
  public void reveal() {
    if (!this.revealed && !this.flagged) {
      this.revealed = true;
      this.flagged = false;
    }
  }

  // adds this cell into the given number of total mines if it is a mine
  public int mineTotal(int total) {
    return total + 1;
  }

  // draws this cell
  public WorldImage drawCell() {
    if (!this.revealed) {
      WorldImage temp = new FrameImage(new RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",
              Color.cyan));
      if (this.flagged) {
        temp = new OverlayImage(new EquilateralTriangleImage(CELL_SIDE_LENGTH / 2, "solid",
                Color.orange), temp);
      }
      return temp;
    }
    else {
      return new OverlayImage(new StarImage(CELL_SIDE_LENGTH / 3, 8, 3, OutlineMode.SOLID,
              Color.red), new FrameImage(new RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",
              Color.gray)));
    }
  }

  // helper method for the ArrayUtils method cellsRevealed
  public int cellsRevealedHelper() {
    return 0;
  }
}