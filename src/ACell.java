import java.util.ArrayList;
import javalib.worldimages.*;

// to represent a cell
public abstract class ACell implements IUtils {
  ArrayList<ACell> neighbors; // all cells neighboring this cell
  boolean revealed;
  boolean flagged;

  // constructor
  ACell() {
    this.neighbors = new ArrayList<ACell>();
    this.revealed = false;
    this.flagged = false;
  }

  // constructor for testing
  ACell(boolean revealed, boolean flagged) {
    this.neighbors = new ArrayList<ACell>();
    this.revealed = revealed;
    this.flagged = flagged;
  }

  // adds the cells to each other's list of neighbors
  void makeNeighbors(ACell that) {
    this.neighbors.add(that);
    that.neighbors.add(this);
  }

  // returns the number of this cell's neighbors which are mines, or -1 if this cell is a mine
  public abstract int mineNeighbors();

  // reveals this cell
  public abstract void reveal();

  // flags this cell
  public void flag() {
    if (!this.revealed) {
      if (!this.flagged) {
        this.flagged = true;
      }
      else {
        this.flagged = false;
      }
    }
  }

  // adds this cell into the given number of total mines if it is a mine
  public abstract int mineTotal(int total);

  // draws this cell
  public abstract WorldImage drawCell();

  // helper method for the ArrayUtils method cellsRevealed
  public abstract int cellsRevealedHelper();
}