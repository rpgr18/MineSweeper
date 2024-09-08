import java.util.ArrayList;
import java.awt.Color;
import javalib.worldimages.*;

// to represent an empty cell
public class EmptyCell extends ACell implements IUtils {
  // constructor
  EmptyCell() {
    super();
  }

  // constructor for testing
  EmptyCell(boolean revealed, boolean flagged) {
    super(revealed, flagged);
  }

  //constructor for testing
  EmptyCell(ArrayList<ACell> neighbors, boolean revealed, boolean flagged) {
    super(revealed, flagged);
    this.neighbors = neighbors;
  }

  // returns the number of this cell's neighbors which are mines
  public int mineNeighbors() {
    int temp = 0;
    for (int i = 0; i < neighbors.size(); i++) {
      temp = this.neighbors.get(i).mineTotal(temp);
    }
    return temp;
  }

  // reveals this cell
  public void reveal() {
    if (!this.revealed && !this.flagged) {
      this.revealed = true;
      this.flagged = false;
      if (this.mineNeighbors() == 0) {
        for (int i = 0; i < this.neighbors.size(); i++) {
          this.neighbors.get(i).reveal();
        }
      }
    }
  }

  // adds this cell into the given number of total mines if it is a mine
  public int mineTotal(int total) {
    return total;
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
      return new OverlayImage(this.drawCount(),
              new FrameImage(new RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray)));
    }
  }

  WorldImage drawCount() {
    if (this.mineNeighbors() == 0) {
      return new EmptyImage();
    }
    else if (this.mineNeighbors() == 1) {
      return new TextImage("1", FONT_SIZE, Color.blue);
    }
    else if (this.mineNeighbors() == 2) {
      return new TextImage("2", FONT_SIZE, Color.green);
    }
    else if (this.mineNeighbors() == 3) {
      return new TextImage("3", FONT_SIZE, Color.magenta);
    }
    else if (this.mineNeighbors() == 4) {
      return new TextImage("4", FONT_SIZE, Color.orange);
    }
    else if (this.mineNeighbors() == 5) {
      return new TextImage("5", FONT_SIZE, Color.cyan);
    }
    else if (this.mineNeighbors() == 6) {
      return new TextImage("6", FONT_SIZE, Color.pink);
    }
    else if (this.mineNeighbors() == 7) {
      return new TextImage("7", FONT_SIZE, Color.red);
    }
    else {
      return new TextImage("8", FONT_SIZE, Color.yellow);
    }
  }

  // helper method for the ArrayUtils method cellsRevealed
  public int cellsRevealedHelper() {
    if (this.revealed) {
      return 1;
    }
    else {
      return 0;
    }
  }
}