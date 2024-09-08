public class Utils {
  // checks if the given number of mines is less than or equal to the number of cells
  int validMines(int numMines, int numRows, int numColumns) {
    if (numMines <= numRows * numColumns) {
      return numMines;
    }
    else {
      throw new IllegalArgumentException("Number of mines cannot exceed total number of cells");
    }
  }
}