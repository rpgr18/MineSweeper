import java.util.ArrayList;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;
import java.util.Random;
import java.awt.Point;

public class MinesweeperWorld extends World implements IUtils {
  int numRows;
  int numColumns;
  int numMines;
  Random rand;
  ArrayList<ArrayList<ACell>> grid;
  boolean gameOver;

  // constructor
  MinesweeperWorld(int numRows, int numColumns, int numMines) {
    this.numRows = numRows;
    this.numColumns = numColumns;
    this.numMines = new Utils().validMines(numMines, numRows, numColumns);
    this.rand = new Random();
    this.grid = this.initializeGrid();
    this.gameOver = false;
  }

  // constructors for testing
  MinesweeperWorld(int numRows, int numColumns, int numMines, Random rand) {
    this.numRows = numRows;
    this.numColumns = numColumns;
    this.numMines = numMines;
    this.rand = rand;
    this.grid = this.initializeGrid();
    this.gameOver = false;
  }

  //constructors for testing
  MinesweeperWorld(int numRows, int numColumns, int numMines, ArrayList<ArrayList<ACell>> grid,
                   Random rand) {
    this.numRows = numRows;
    this.numColumns = numColumns;
    this.numMines = numMines;
    this.rand = rand;
    this.grid = grid;
    this.gameOver = false;
  }

  //constructors for testing
  MinesweeperWorld(int numRows, int numColumns, int numMines, Random rand, boolean gameOver) {
    this.numRows = numRows;
    this.numColumns = numColumns;
    this.numMines = numMines;
    this.rand = rand;
    this.grid = this.initializeGrid();
    this.gameOver = gameOver;
  }

  // initializes the grid with the correct number of rows and columns, and the correct number of
  // grids in random locations
  ArrayList<ArrayList<ACell>> initializeGrid() {
    ArrayList<Point> mineLocations = this.getMineLocations();
    ArrayList<ArrayList<ACell>> listFinal = new ArrayList<ArrayList<ACell>>();
    for (int i = 0; i < this.numRows; i++) {
      ArrayList<ACell> temp = new ArrayList<ACell>();
      for (int j = 0; j < this.numColumns; j++) {
        if (new ArrayUtils().containsPoint(mineLocations, new Point(j, i))) {
          temp.add(new Mine());
        }
        else {
          temp.add(new EmptyCell());
        }
        if (i > 0) {
          temp.get(j).makeNeighbors(listFinal.get(i - 1).get(j));
          if (j > 0) {
            temp.get(j).makeNeighbors(listFinal.get(i - 1).get(j - 1));
          }
          if (j < numColumns - 1) {
            temp.get(j).makeNeighbors(listFinal.get(i - 1).get(j + 1));
          }
        }
        if (j > 0) {
          temp.get(j).makeNeighbors(temp.get(j - 1));
        }
      }
      listFinal.add(temp);
    }
    return listFinal;
  }

  // creates a list of randomized coordinates at which to place mines without repeating
  ArrayList<Point> getMineLocations() {
    int temp = this.numMines;
    ArrayList<Point> list = new ArrayList<Point>();
    while (this.numMines > 0) {
      Point p = new Point(rand.nextInt(this.numColumns), rand.nextInt(this.numRows));
      if (!(new ArrayUtils().containsPoint(list, p))) {
        list.add(p);
        numMines--;
      }
    }
    this.numMines = temp;
    return list;
  }

  // draws the current state of the MinesweeperWorld
  public WorldScene makeScene() {
    WorldImage imageFinal = new EmptyImage();
    for (int i = 0; i < this.numRows; i++) {
      WorldImage temp = this.grid.get(i).get(0).drawCell();
      for (int j = 1; j < this.numColumns; j++) {
        temp = new BesideImage(temp, this.grid.get(i).get(j).drawCell());
      }
      imageFinal = new AboveImage(imageFinal, temp);
    }
    WorldScene scene = this.getEmptyScene();
    WorldImage scoreboard = this.makeScoreboard();

    scene.placeImageXY(new AboveImage(imageFinal, scoreboard), (CELL_SIDE_LENGTH
            * this.numColumns) / 2, CELL_SIDE_LENGTH * (this.numRows + 4) / 2);
    return scene;
  }

  WorldImage makeScoreboard() {
    int fontSize = Math.min(CELL_SIDE_LENGTH * this.numColumns / 12, CELL_SIDE_LENGTH * 2 / 3);
    WorldImage board = new RectangleImage(CELL_SIDE_LENGTH * this.numColumns, CELL_SIDE_LENGTH
            * 2, "solid", Color.lightGray);
    WorldImage temp = new EmptyImage();
    if (!this.gameOver) {
      temp = new OverlayImage(new TextImage("Cells left to reveal: "
              + Integer.toString(this.cellsToGo()), fontSize, Color.black), board);
    }
    else if (this.cellsToGo() == 0) {
      temp = new OverlayImage(new TextImage("You win!", fontSize, Color.black), board);
    }
    else {
      temp = new OverlayImage(new TextImage("You lose!", fontSize, Color.red), board);
    }
    WorldImage button = new OverlayImage(new OverlayImage(new TextImage("New game", fontSize,
            Color.black), new RectangleImage(CELL_SIDE_LENGTH * this.numColumns / 2,
            CELL_SIDE_LENGTH, "solid", Color.gray)), board);
    return new AboveImage(temp, button);
  }

  // changes this MinesweeperWorld based on a mouse click
  public void onMouseClicked(Posn pos, String buttonName) {
    if (!this.gameOver && pos.y <= CELL_SIDE_LENGTH * this.numRows && pos.x <= CELL_SIDE_LENGTH
            * this.numColumns) {
      int row = pos.y / CELL_SIDE_LENGTH;
      int col = pos.x / CELL_SIDE_LENGTH;

      if (buttonName.equals("LeftButton")) {
        ACell curr = this.grid.get(row).get(col);
        curr.reveal();
        if (curr.mineTotal(0) == 1 && !curr.flagged) {
          for (ArrayList<ACell> list : this.grid) {
            new ArrayUtils().revealAllMines(list);
          }
          this.gameOver = true;
        }
        else if (this.cellsToGo() == 0) {
          this.gameOver = true;
        }
      }
      if (buttonName.equals("RightButton")) {
        this.grid.get(row).get(col).flag();
      }
    }
    else {
      int leftBound = CELL_SIDE_LENGTH * this.numColumns / 4;
      int rightBound = CELL_SIDE_LENGTH * this.numColumns / 4 * 3;
      int upperBound = CELL_SIDE_LENGTH * this.numRows + (CELL_SIDE_LENGTH * 5 / 2);
      int lowerBound = CELL_SIDE_LENGTH * this.numRows + (CELL_SIDE_LENGTH * 7 / 2);
      if (buttonName.equals("LeftButton") && pos.x >= leftBound && pos.x <= rightBound && pos.y
              >= upperBound && pos.y <= lowerBound) {
        this.grid = this.initializeGrid();
        this.gameOver = false;
      }
    }
  }

  // calculates the number of cells left to reveal to win
  int cellsToGo() {
    int ecTotal = (this.numRows * this.numColumns) - this.numMines;
    int temp = 0;
    for (ArrayList<ACell> row : this.grid) {
      temp += new ArrayUtils().cellsRevealed(row);
    }
    return ecTotal - temp;
  }
}