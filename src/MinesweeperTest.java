import java.util.ArrayList;
import tester.*;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;
import java.util.Random;
import java.util.Arrays;
import java.awt.Point;

class MinesweeperTest implements IUtils {
  // examples of cells
  ACell mine;
  ACell flaggedMine;
  ACell revealedMine;
  ACell empty;
  ACell flaggedEmpty;
  ACell revealedEmpty;
  ACell emptyWithNeighbors;
  EmptyCell noMN;
  EmptyCell oneMN;
  EmptyCell twoMN;
  EmptyCell threeMN;
  EmptyCell fourMN;
  EmptyCell fiveMN;
  EmptyCell sixMN;
  EmptyCell sevenMN;
  EmptyCell eightMN;
  ACell gridCell1;
  ACell gridCell2;
  ACell gridCell3;
  ACell gridCell4;
  ACell gridCell5;
  ACell gridCell6;
  ACell gridCell7;
  ACell gridCell8;

  // examples of minesweeper worlds
  MinesweeperWorld world1;
  MinesweeperWorld world2;
  MinesweeperWorld world3;
  MinesweeperWorld world4;
  MinesweeperWorld world5;
  MinesweeperWorld world6;

  // examples of WorldScenes
  WorldScene ws1;
  WorldScene ws2;
  WorldScene ws3;

  // examples of Points
  Point p1;
  Point p2;

  // examples of ArrayLists<Point>
  ArrayList<Point> noPoints;
  ArrayList<Point> pointList;

  // examples of ArrayLists<ACell>
  ArrayList<ACell> noCells;
  ArrayList<ACell> allMines;
  ArrayList<ACell> mix;
  ArrayList<ACell> allCells;

  // examples of ArrayLists<ArrayList<ACell>>
  ArrayList<ArrayList<ACell>> expectedGrid1;
  ArrayList<ArrayList<ACell>> expectedGrid2;

  // example of ArrayUtils
  ArrayUtils au;

  // example of Utils
  Utils u;

  // reinitializes all examples
  void reset() {
    this.mine = new Mine();
    this.flaggedMine = new Mine(false, true);
    this.revealedMine = new Mine(true, false);
    this.empty = new EmptyCell();
    this.flaggedEmpty = new EmptyCell(false, true);
    this.revealedEmpty = new EmptyCell(true, false);
    this.emptyWithNeighbors = new EmptyCell(new ArrayList<ACell>(Arrays.asList(new EmptyCell(),
            new EmptyCell(), new EmptyCell(), new EmptyCell())), false, false);
    this.noMN = new EmptyCell(true, false);
    this.oneMN = new EmptyCell(new ArrayList<ACell>(Arrays.asList(this.mine)), true, false);
    this.twoMN = new EmptyCell(new ArrayList<ACell>(Arrays.asList(this.mine, this.mine,
            this.empty)), true, false);
    this.threeMN = new EmptyCell(new ArrayList<ACell>(Arrays.asList(this.empty, this.flaggedMine,
            this.mine, this.mine)), true, false);
    this.fourMN = new EmptyCell(new ArrayList<ACell>(Arrays.asList(this.empty, this.flaggedMine,
            this.mine, this.mine, this.revealedMine)), true, false);
    this.fiveMN = new EmptyCell(new ArrayList<ACell>(Arrays.asList(this.empty, this.flaggedMine,
            this.mine, this.mine, this.revealedMine, this.mine)), true, false);
    this.sixMN = new EmptyCell(new ArrayList<ACell>(Arrays.asList(this.empty, this.flaggedMine,
            this.mine, this.mine, this.revealedMine, this.mine, this.empty, this.mine)),
            true, false);
    this.sevenMN = new EmptyCell(new ArrayList<ACell>(Arrays.asList(this.empty, this.flaggedMine,
            this.mine, this.mine, this.revealedMine, this.mine, this.empty, this.mine, this.mine)),
            true, false);
    this.eightMN = new EmptyCell(new ArrayList<ACell>(Arrays.asList(this.empty, this.flaggedMine,
            this.mine, this.mine, this.revealedMine, this.mine, this.empty, this.mine, this.mine,
            this.mine)), true, false);
    this.world1 = new MinesweeperWorld(4, 4, 3, new Random(3));
    this.world2 = new MinesweeperWorld(4, 4, 3, new Random(7));
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        this.world2.grid.get(j).get(i).reveal();
      }
    }
    this.world3 = new MinesweeperWorld(4, 4, 3, new Random(9), true);
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        if (this.world3.grid.get(j).get(i).mineNeighbors() == -1) {
          this.world3.grid.get(j).get(i).flag();
        }
        else {
          this.world3.grid.get(j).get(i).reveal();
        }
      }
    }
    this.world4 = new MinesweeperWorld(2, 2, 1, new ArrayList<ArrayList<ACell>>(), new Random(3));
    this.world5 = new MinesweeperWorld(2, 2, 1, new Random(3));
    this.world6 = new MinesweeperWorld(4, 4, 3, new Random(9), true);

    this.gridCell1 = new EmptyCell();
    this.gridCell2 = new EmptyCell();
    this.gridCell3 = new EmptyCell();
    this.gridCell4 = new Mine();

    this.gridCell1.makeNeighbors(this.gridCell2);
    this.gridCell1.makeNeighbors(this.gridCell3);
    this.gridCell2.makeNeighbors(this.gridCell3);
    this.gridCell1.makeNeighbors(this.gridCell4);
    this.gridCell2.makeNeighbors(this.gridCell4);
    this.gridCell3.makeNeighbors(this.gridCell4);

    this.expectedGrid1 = new ArrayList<ArrayList<ACell>>(Arrays.asList(new
                    ArrayList<ACell>(Arrays.asList(this.gridCell1, this.gridCell2)),
            new ArrayList<ACell>(Arrays.asList(this.gridCell3, this.gridCell4))));

    this.gridCell5 = new EmptyCell();
    this.gridCell6 = new EmptyCell();
    this.gridCell7 = new Mine();
    this.gridCell8 = new EmptyCell();

    this.gridCell5.makeNeighbors(this.gridCell6);
    this.gridCell5.makeNeighbors(this.gridCell7);
    this.gridCell6.makeNeighbors(this.gridCell7);
    this.gridCell5.makeNeighbors(this.gridCell8);
    this.gridCell6.makeNeighbors(this.gridCell8);
    this.gridCell7.makeNeighbors(this.gridCell8);

    this.expectedGrid2 = new ArrayList<ArrayList<ACell>>(Arrays.asList(new
                    ArrayList<ACell>(Arrays.asList(this.gridCell5, this.gridCell6)),
            new ArrayList<ACell>(Arrays.asList(this.gridCell7, this.gridCell8))));

    // stores expected scene for world1 as ws1
    this.ws1 = this.world1.getEmptyScene();
    WorldImage w1Square = new FrameImage(new RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",
            Color.cyan));
    WorldImage w1Row = new BesideImage(new BesideImage(new BesideImage(w1Square, w1Square),
            w1Square), w1Square);
    ws1.placeImageXY(new AboveImage(new AboveImage(new AboveImage(new AboveImage(w1Row, w1Row),
            w1Row), w1Row), this.world1.makeScoreboard()), CELL_SIDE_LENGTH * 2, CELL_SIDE_LENGTH
            * 4);

    // stores expected scene for world2 as ws2
    this.ws2 = this.world2.getEmptyScene();
    WorldImage w2Row1 = new BesideImage(new BesideImage(new BesideImage(new OverlayImage(new
            TextImage("1", FONT_SIZE, Color.blue), new FrameImage(new
            RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray))), new
            OverlayImage(new TextImage("2", FONT_SIZE, Color.green), new FrameImage(new
            RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray)))),
            new OverlayImage(new StarImage(CELL_SIDE_LENGTH / 3, 8, 3, OutlineMode.SOLID, Color.red),
                    new FrameImage(new RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray)))),
            new OverlayImage(new TextImage("1", FONT_SIZE, Color.blue), new FrameImage(new
                    RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray))));
    WorldImage w2Row2 = new BesideImage(new BesideImage(new BesideImage(new OverlayImage(new
            TextImage("1", FONT_SIZE, Color.blue), new FrameImage(new
            RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray))), new OverlayImage(new
            StarImage(CELL_SIDE_LENGTH / 3, 8, 3, OutlineMode.SOLID, Color.red),
            new FrameImage(new RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray)))),
            new OverlayImage(new TextImage("3", FONT_SIZE, Color.magenta), new FrameImage(new
                    RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray)))),
            new OverlayImage(new TextImage("2", FONT_SIZE, Color.green), new FrameImage(new
                    RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray))));
    WorldImage w2Row4 = new BesideImage(new BesideImage(new BesideImage(new FrameImage(new
            RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray))), new
            OverlayImage(new TextImage("1", FONT_SIZE, Color.blue), new FrameImage(new
            RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray)))),
            new OverlayImage(new TextImage("1", FONT_SIZE, Color.blue),
                    new FrameImage(new RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray))),
            new OverlayImage(new TextImage("1", FONT_SIZE, Color.blue), new FrameImage(new
                    RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray))));
    ws2.placeImageXY(new AboveImage(new AboveImage(new AboveImage(new AboveImage(w2Row1, w2Row2),
            w2Row1), w2Row4), this.world2.makeScoreboard()), CELL_SIDE_LENGTH * 2, CELL_SIDE_LENGTH
            * 4);

    // stores expected scene for world3 as ws3
    this.ws3 = this.world3.getEmptyScene();
    WorldImage w3Row1 = new BesideImage(new BesideImage(new BesideImage(new FrameImage(new
            RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray)), new
            OverlayImage(new TextImage("1", FONT_SIZE, Color.blue), new FrameImage(new
            RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray)))),
            new OverlayImage(new TextImage("1", FONT_SIZE, Color.blue),
                    new FrameImage(new RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray)))),
            new OverlayImage(new TextImage("1", FONT_SIZE, Color.blue), new FrameImage(new
                    RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray))));
    WorldImage w3Row2 = new BesideImage(new BesideImage(new BesideImage(new FrameImage(new
            RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray)), new
            OverlayImage(new TextImage("1", FONT_SIZE, Color.blue), new FrameImage(new
            RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray)))),
            new OverlayImage(new EquilateralTriangleImage(CELL_SIDE_LENGTH / 2, "solid",
                    Color.orange), new FrameImage(new RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",
                    Color.cyan)))),
            new OverlayImage(new TextImage("1", FONT_SIZE, Color.blue), new FrameImage(new
                    RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray))));
    WorldImage w3Row3 = new BesideImage(new BesideImage(new BesideImage(new FrameImage(new
            RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray)), new
            OverlayImage(new TextImage("2", FONT_SIZE, Color.green), new FrameImage(new
            RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray)))),
            new OverlayImage(new TextImage("3", FONT_SIZE, Color.magenta),
                    new FrameImage(new RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray)))),
            new OverlayImage(new TextImage("3", FONT_SIZE, Color.magenta), new FrameImage(new
                    RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray))));
    WorldImage w3Row4 = new BesideImage(new BesideImage(new BesideImage(new FrameImage(new
            RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray)), new
            OverlayImage(new TextImage("1", FONT_SIZE, Color.blue), new FrameImage(new
            RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray)))),
            new OverlayImage(new EquilateralTriangleImage(CELL_SIDE_LENGTH / 2, "solid",
                    Color.orange), new FrameImage(new RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",
                    Color.cyan)))),
            new OverlayImage(new EquilateralTriangleImage(CELL_SIDE_LENGTH / 2, "solid",
                    Color.orange), new FrameImage(new RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",
                    Color.cyan))));
    ws3.placeImageXY(new AboveImage(new AboveImage(new AboveImage(new AboveImage(w3Row1, w3Row2),
            w3Row3), w3Row4), this.world3.makeScoreboard()), CELL_SIDE_LENGTH * 2, CELL_SIDE_LENGTH
            * 4);

    this.p1 = new Point(2, 1);
    this.p2 = new Point(3, 3);
    this.noPoints = new ArrayList<Point>();
    this.pointList = new ArrayList<Point>(Arrays.asList(new Point(0, 0), new
            Point(2, 1), new Point(4, 3)));
    this.noCells = new ArrayList<ACell>();
    this.allMines = new ArrayList<ACell>(Arrays.asList(this.mine, this.flaggedMine,
            this.revealedMine, this.mine));
    this.mix = new ArrayList<ACell>(Arrays.asList(this.mine, this.empty, this.flaggedMine,
            this.revealedEmpty, this.revealedEmpty));
    this.allCells = new ArrayList<ACell>(Arrays.asList(this.empty, this.revealedEmpty,
            this.flaggedEmpty));
    this.au = new ArrayUtils();
    this.u = new Utils();
  }

  // tests the validMines method of the Utils class
  boolean testValidMines(Tester t) {
    reset();

    return t.checkExpect(this.u.validMines(9, 4, 4), 9)
            && t.checkException(new IllegalArgumentException("Number of mines cannot exceed "
            + "total number of cells"), this.u, "validMines", 9, 2, 2);
  }

  // tests constructor exception of the MinesweeperWorld class
  boolean testConstuctorException(Tester t) {
    reset();

    return t.checkConstructorException(new IllegalArgumentException("Number of mines cannot "
            + "exceed total number of cells"), "MinesweeperWorld", 4, 6, 27);
  }

  // tests the containsPoint method of the ArrayUtils class
  boolean testContainsPoint(Tester t) {
    reset();

    return t.checkExpect(this.au.containsPoint(this.noPoints, this.p1), false)
            && t.checkExpect(this.au.containsPoint(this.pointList, this.p1), true)
            && t.checkExpect(this.au.containsPoint(this.pointList, this.p2), false);
  }

  // tests the cellsRevealed method of the ArrayUtils class
  boolean testCellsRevealed(Tester t) {
    reset();

    return t.checkExpect(this.au.cellsRevealed(this.noCells), 0)
            && t.checkExpect(this.au.cellsRevealed(this.allMines), 0)
            && t.checkExpect(this.au.cellsRevealed(this.mix), 2);
  }

  // tests the revealAllMines method of the ArrayUtils class
  void testRevealAllMines(Tester t) {
    reset();
    // testing initial conditions
    t.checkExpect(this.noCells, new ArrayList<ACell>());
    t.checkExpect(this.mix, new ArrayList<ACell>(Arrays.asList(this.mine, this.empty,
            this.flaggedMine, this.revealedEmpty, this.revealedEmpty)));
    t.checkExpect(this.allCells, new ArrayList<ACell>(Arrays.asList(this.empty, this.revealedEmpty,
            this.flaggedEmpty)));

    // call method
    this.au.revealAllMines(this.noCells);
    this.au.revealAllMines(this.mix);
    this.au.revealAllMines(this.allCells);

    // testing final conditions
    t.checkExpect(this.noCells, new ArrayList<ACell>());
    t.checkExpect(this.mix, new ArrayList<ACell>(Arrays.asList(this.revealedMine, this.empty,
            this.revealedMine, this.revealedEmpty, this.revealedEmpty)));
    t.checkExpect(this.allCells, new ArrayList<ACell>(Arrays.asList(this.empty, this.revealedEmpty,
            this.flaggedEmpty)));
  }

  // tests the drawCell method of the ACell abstract class
  boolean testDrawCell(Tester t) {
    reset();

    return t.checkExpect(this.revealedEmpty.drawCell(), new OverlayImage(new EmptyImage(),
            new FrameImage(new RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray))))
            && t.checkExpect(this.flaggedMine.drawCell(), new OverlayImage(new
            EquilateralTriangleImage(CELL_SIDE_LENGTH / 2, "solid", Color.orange),
            new FrameImage(new RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid", Color.cyan))))
            && t.checkExpect(this.empty.drawCell(), new FrameImage(new
            RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.cyan)))
            && t.checkExpect(this.mine.drawCell(), new FrameImage(new
            RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.cyan)))
            && t.checkExpect(this.flaggedEmpty.drawCell(), new OverlayImage(new
            EquilateralTriangleImage(CELL_SIDE_LENGTH / 2, "solid", Color.orange),
            new FrameImage(new RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid", Color.cyan))))
            && t.checkExpect(this.revealedMine.drawCell(), new OverlayImage(new StarImage(
            CELL_SIDE_LENGTH / 3, 8, 3, OutlineMode.SOLID, Color.red), new FrameImage(new
            RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",  Color.gray))))
            && t.checkExpect(this.fourMN.drawCell(), new OverlayImage(new TextImage("4", FONT_SIZE,
            Color.orange), new FrameImage(new RegularPolyImage(CELL_SIDE_LENGTH, 4, "solid",
            Color.gray))));
  }

  // tests the reveal method of the ACell abstract class
  void testReveal(Tester t) {
    reset();

    // testing initial conditions
    t.checkExpect(this.empty.revealed, false);
    t.checkExpect(this.revealedEmpty.revealed, true);
    t.checkExpect(this.flaggedEmpty.revealed, false);
    t.checkExpect(this.mine.revealed, false);
    t.checkExpect(this.revealedMine.revealed, true);
    t.checkExpect(this.flaggedMine.revealed, false);
    t.checkExpect(this.flaggedEmpty.flagged, true);
    t.checkExpect(this.flaggedMine.flagged, true);
    t.checkExpect(this.emptyWithNeighbors.revealed, false);
    t.checkExpect(this.emptyWithNeighbors.neighbors, new ArrayList<ACell>(Arrays.asList(new
            EmptyCell(), new EmptyCell(), new EmptyCell(), new EmptyCell())));

    // calling the method
    this.empty.reveal();
    this.revealedEmpty.reveal();
    this.flaggedEmpty.reveal();
    this.mine.reveal();
    this.revealedMine.reveal();
    this.flaggedMine.reveal();
    this.emptyWithNeighbors.reveal(); // tests flood fill

    // testing final conditions
    t.checkExpect(this.empty.revealed, true);
    t.checkExpect(this.revealedEmpty.revealed, true);
    t.checkExpect(this.flaggedEmpty.revealed, false);
    t.checkExpect(this.mine.revealed, true);
    t.checkExpect(this.revealedMine.revealed, true);
    t.checkExpect(this.flaggedMine.revealed, false);
    t.checkExpect(this.flaggedEmpty.flagged, true);
    t.checkExpect(this.flaggedMine.flagged, true);
    t.checkExpect(this.emptyWithNeighbors.revealed, true);
    t.checkExpect(this.emptyWithNeighbors.neighbors, new ArrayList<ACell>(Arrays.asList(
            new EmptyCell(true, false), new EmptyCell(true, false), new EmptyCell(true, false),
            new EmptyCell(true, false))));
  }

  // tests the mineNeighbors method of the ACell abstract class
  boolean testMineNeighbors(Tester t) {
    reset();

    return t.checkExpect(this.oneMN.mineNeighbors(), 1)
            && t.checkExpect(this.empty.mineNeighbors(), 0)
            && t.checkExpect(this.mine.mineNeighbors(), -1)
            && t.checkExpect(this.twoMN.mineNeighbors(), 2)
            && t.checkExpect(this.eightMN.mineNeighbors(), 8)
            && t.checkExpect(this.fourMN.mineNeighbors(), 4);
  }

  // tests the mineTotal method of the ACell abstract class
  boolean testMineTotal(Tester t) {
    reset();

    return t.checkExpect(this.empty.mineTotal(1), 1)
            && t.checkExpect(this.revealedEmpty.mineTotal(2), 2)
            && t.checkExpect(this.flaggedEmpty.mineTotal(3), 3)
            && t.checkExpect(this.mine.mineTotal(1), 2)
            && t.checkExpect(this.revealedMine.mineTotal(2), 3)
            && t.checkExpect(this.flaggedMine.mineTotal(3), 4);
  }

  // tests the makeNeighbors method of the ACell abstract class
  void testMakeNeighbors(Tester t) {
    reset();

    // test initial conditions
    t.checkExpect(this.empty.neighbors, new ArrayList<ACell>());
    t.checkExpect(this.mine.neighbors, new ArrayList<ACell>());
    t.checkExpect(this.revealedMine.neighbors, new ArrayList<ACell>());
    t.checkExpect(this.flaggedMine.neighbors, new ArrayList<ACell>());

    // call method
    this.empty.makeNeighbors(this.mine);
    this.mine.makeNeighbors(this.flaggedMine);
    this.revealedMine.makeNeighbors(this.empty);
    this.revealedMine.makeNeighbors(this.mine);

    // test end conditions
    t.checkExpect(this.empty.neighbors.get(0), this.mine);
    t.checkExpect(this.empty.neighbors.get(1), this.revealedMine);
    t.checkExpect(this.mine.neighbors.get(0), this.empty);
    t.checkExpect(this.mine.neighbors.get(1), this.flaggedMine);
    t.checkExpect(this.mine.neighbors.get(2), this.revealedMine);
    t.checkExpect(this.revealedMine.neighbors.get(0), this.empty);
    t.checkExpect(this.revealedMine.neighbors.get(1), this.mine);
    t.checkExpect(this.flaggedMine.neighbors.get(0), this.mine);
  }

  // tests the flag method of the ACell abstract class
  void testFlag(Tester t) {
    reset();

    // test initial conditions
    t.checkExpect(this.empty.flagged, false);
    t.checkExpect(this.mine.flagged, false);
    t.checkExpect(this.revealedEmpty.flagged, false);
    t.checkExpect(this.revealedMine.flagged, false);
    t.checkExpect(this.flaggedEmpty.flagged, true);
    t.checkExpect(this.flaggedMine.flagged, true);

    // call method
    this.empty.flag();
    this.mine.flag();
    this.revealedEmpty.flag();
    this.revealedMine.flag();
    this.flaggedEmpty.flag();
    this.flaggedMine.flag();

    // test end conditions
    t.checkExpect(this.empty.flagged, true);
    t.checkExpect(this.mine.flagged, true);
    t.checkExpect(this.revealedEmpty.flagged, false);
    t.checkExpect(this.revealedMine.flagged, false);
    t.checkExpect(this.flaggedEmpty.flagged, false);
    t.checkExpect(this.flaggedMine.flagged, false);
  }

  // tests the cellsRevealed helper of the ACell abstract class
  boolean testsCellsRevealedHelper(Tester t) {
    reset();

    return t.checkExpect(this.empty.cellsRevealedHelper(), 0)
            && t.checkExpect(this.revealedEmpty.cellsRevealedHelper(), 1)
            && t.checkExpect(this.flaggedEmpty.cellsRevealedHelper(), 0)
            && t.checkExpect(this.mine.cellsRevealedHelper(), 0)
            && t.checkExpect(this.revealedMine.cellsRevealedHelper(), 0)
            && t.checkExpect(this.flaggedMine.cellsRevealedHelper(), 0);
  }

  // tests the drawCount method of the EmptyCell class
  boolean testDrawCount(Tester t) {
    reset();

    return t.checkExpect(this.noMN.drawCount(), new EmptyImage())
            && t.checkExpect(this.oneMN.drawCount(), new TextImage("1", FONT_SIZE, Color.blue))
            && t.checkExpect(this.twoMN.drawCount(), new TextImage("2", FONT_SIZE, Color.green))
            && t.checkExpect(this.threeMN.drawCount(), new TextImage("3", FONT_SIZE, Color.magenta))
            && t.checkExpect(this.fourMN.drawCount(), new TextImage("4", FONT_SIZE, Color.orange))
            && t.checkExpect(this.fiveMN.drawCount(), new TextImage("5", FONT_SIZE, Color.cyan))
            && t.checkExpect(this.sixMN.drawCount(), new TextImage("6", FONT_SIZE, Color.pink))
            && t.checkExpect(this.sevenMN.drawCount(), new TextImage("7", FONT_SIZE, Color.red))
            && t.checkExpect(this.eightMN.drawCount(), new TextImage("8", FONT_SIZE, Color.yellow));
  }

  // tests the initializeGrid method of the MinesweeperWorld class
  void testIntializeGrid(Tester t) {
    reset();

    // test final conditions
    t.checkExpect(this.world4.initializeGrid(), this.expectedGrid1);
  }

  // tests the makeScene method of the MinesweeperWorld class
  boolean testMakeScene(Tester t) {
    reset();

    return t.checkExpect(this.world1.makeScene(), this.ws1)
            && t.checkExpect(this.world2.makeScene(), this.ws2)
            && t.checkExpect(this.world3.makeScene(), this.ws3);
  }

  // tests the getMineLocations method of the MinesweeperWorld class
  boolean testGetMineLocations(Tester t) {
    reset();

    return t.checkExpect(this.world1.getMineLocations(), new ArrayList<Point>(Arrays.asList(new
            Point(3, 2), new Point(0, 3), new Point(2, 1))))
            && t.checkExpect(this.world2.getMineLocations(), new ArrayList<Point>(Arrays.asList(new
            Point(3, 2), new Point(2, 2), new Point(1, 3))))
            && t.checkExpect(this.world3.getMineLocations(), new ArrayList<Point>(Arrays.asList(new
            Point(3, 0), new Point(1, 1), new Point(2, 2))));
  }

  // tests the cellsToGo method of the MinesweeperWorld class
  boolean cellsToGo(Tester t) {
    reset();

    return t.checkExpect(this.world1.cellsToGo(), 13)
            && t.checkExpect(this.world2.cellsToGo(), 0)
            && t.checkExpect(this.world3.cellsToGo(), 0);
  }

  // tests the onMouseClicked method of the MinesweeperWorld class
  void testOnMouseClicked(Tester t) {
    reset();

    // test initial conditions
    t.checkExpect(this.world1.grid.get(1).get(1).revealed, false);
    t.checkExpect(this.world1.grid.get(1).get(2).flagged, false);
    t.checkExpect(this.world1.grid.get(1).get(0).flagged, false);
    t.checkExpect(this.world1.grid.get(3).get(0).revealed, false);
    t.checkExpect(this.world5.grid, this.expectedGrid1); // test reset button

    // call method
    this.world1.onMouseClicked(new Posn(60,  60), "LeftButton");
    this.world1.onMouseClicked(new Posn(100,  60), "RightButton");
    this.world1.onMouseClicked(new Posn(30,  60), "RightButton");
    this.world1.onMouseClicked(new Posn(30,  60), "RightButton");
    this.world1.onMouseClicked(new Posn(20,  140), "LeftButton");
    this.world5.onMouseClicked(new Posn(40, 200), "LeftButton"); // test reset button

    // test final conditions
    t.checkExpect(this.world1.grid.get(1).get(1).revealed, true);
    t.checkExpect(this.world1.grid.get(1).get(2).flagged, true);
    t.checkExpect(this.world1.grid.get(1).get(0).flagged, false);
    t.checkExpect(this.world1.grid.get(2).get(0).revealed, true);
    t.checkExpect(this.world1.grid.get(3).get(0).revealed, true);
    t.checkExpect(this.world1.grid.get(2).get(2).revealed, true);
    t.checkExpect(this.world5.grid, this.expectedGrid2); // test reset button
  }

  // tests the makeScoreboard method of the MinesweeperWorld class
  boolean testMakeScoreboard(Tester t) {
    return t.checkExpect(this.world1.makeScoreboard(), new AboveImage(new
            OverlayImage(new TextImage("Cells left to reveal: " + Integer.toString(13),
            CELL_SIDE_LENGTH / 3, Color.black), new RectangleImage(CELL_SIDE_LENGTH * 4,
            CELL_SIDE_LENGTH * 2, "solid", Color.lightGray)), new OverlayImage(new
            OverlayImage(new TextImage("New game", CELL_SIDE_LENGTH / 3, Color.black),
            new RectangleImage(CELL_SIDE_LENGTH * 2, CELL_SIDE_LENGTH, "solid",
                    Color.gray)), new RectangleImage(CELL_SIDE_LENGTH * 4,
            CELL_SIDE_LENGTH * 2, "solid", Color.lightGray))))
            && t.checkExpect(this.world3.makeScoreboard(), new AboveImage(new
            OverlayImage(new TextImage("You win!",
            CELL_SIDE_LENGTH / 3, Color.black), new RectangleImage(CELL_SIDE_LENGTH * 4,
            CELL_SIDE_LENGTH * 2, "solid", Color.lightGray)), new OverlayImage(new
            OverlayImage(new TextImage("New game", CELL_SIDE_LENGTH / 3, Color.black),
            new RectangleImage(CELL_SIDE_LENGTH * 2, CELL_SIDE_LENGTH, "solid",
                    Color.gray)), new RectangleImage(CELL_SIDE_LENGTH * 4,
            CELL_SIDE_LENGTH * 2, "solid", Color.lightGray))))
            && t.checkExpect(this.world6.makeScoreboard(), new AboveImage(new
            OverlayImage(new TextImage("You lose!",
            CELL_SIDE_LENGTH / 3, Color.red), new RectangleImage(CELL_SIDE_LENGTH * 4,
            CELL_SIDE_LENGTH * 2, "solid", Color.lightGray)), new OverlayImage(new
            OverlayImage(new TextImage("New game", CELL_SIDE_LENGTH / 3, Color.black),
            new RectangleImage(CELL_SIDE_LENGTH * 2, CELL_SIDE_LENGTH, "solid",
                    Color.gray)), new RectangleImage(CELL_SIDE_LENGTH * 4,
            CELL_SIDE_LENGTH * 2, "solid", Color.lightGray))));
  }

  void testBigBang(Tester t) {
    reset();

    MinesweeperWorld ms = new MinesweeperWorld(16, 30, 99);
    World world = ms;
    world.bigBang(ms.numColumns * CELL_SIDE_LENGTH, (ms.numRows + 4) * CELL_SIDE_LENGTH);
  }
}