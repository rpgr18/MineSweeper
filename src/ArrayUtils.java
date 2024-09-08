import java.awt.*;
import java.util.ArrayList;

// utils class for ArrayLists
public class ArrayUtils {
  // checks if the given ArrayList<Point> contains the given Point
  boolean containsPoint(ArrayList<Point> list, Point that) {
    boolean temp = false;
    for (Point p : list) {
      if (p.x == that.x && p.y == that.y) {
        temp = true;
      }
    }
    return temp;
  }

  // checks how many of the empty cells in the given ArrayList<ACell> are revealed
  int cellsRevealed(ArrayList<ACell> list) {
    int temp = 0;
    for (ACell cell : list) {
      temp += cell.cellsRevealedHelper();
    }
    return temp;
  }

  // reveals all mines in the given ArrayList<ACell>
  void revealAllMines(ArrayList<ACell> list) {
    for (ACell cell : list) {
      if (cell.mineTotal(0) == 1) {
        if (cell.flagged) {
          cell.flag();
        }
        cell.reveal();
      }
    }
  }
}