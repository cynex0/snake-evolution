package utilities;

import java.awt.Point;


/**
 * Defines constants used in the game.
 */
public interface GameConstants {
    // game window constants
    /**
     * Point representing the window dimensions.
     */
    static final Point WINDOW_SIZE = new Point(800, 800);

    /**
     * The base FPS of the game.
     */
    static final int FPS = 60;

    /**
     * The amount of cells in one row/column.
     */
    static final int CELL_COUNT = 40;

    /**
     * Size of one cell. Determined by the window size and the cell count.
     */
    static final int CELL_SIZE = WINDOW_SIZE.x / CELL_COUNT;

    /**
     * Duration of timed bonus food effects (in ms).
     */
    static final int EFFECT_DURATION = 8000;

    // background constants
    /**
     * Thickness of the borders in pixels
     */
    static final int BORDER_THC = 5;

    /**
     * Size of the margin (area between borders and screen edge) in cells. Unplayable area/cells.
     */
    static final int MARGIN_CELLS = 3;

    /**
     * Distance from screen edge to inner margin point.
     * Determined by the size of the cells and size of the margin in cells.
     */
    static final int MARGIN_INNER = CELL_SIZE * MARGIN_CELLS;

    /**
     * Distance from screen edge to outer margin point (excluding the thickness)-
     */
    static final int MARGIN_OUTER = MARGIN_INNER - BORDER_THC;

    /**
     * Index of the first playable cell.
     */
    static final int MIN_CELL = MARGIN_CELLS;

    /**
     * Index of the last playable cell.
     */
    static final int MAX_CELL = CELL_COUNT - MARGIN_CELLS - 1;
}
