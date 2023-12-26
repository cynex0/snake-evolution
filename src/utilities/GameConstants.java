package utilities;

import panels.BgPanel;
import panels.GamePanel;

import java.awt.Point;

public interface GameConstants {
    // game window constants
    static final Point WINDOW_SIZE = new Point(800, 800);
    static final int FPS = 60;
    static final int CELL_COUNT = 40;
    static final int CELL_SIZE = WINDOW_SIZE.x / CELL_COUNT;

    static final int EFFECT_DURATION = 8000; // timed effect duration in ms

    // background constants
    static final int BORDER_THC = 5; // border thickness
    static final int MARGIN_CELLS = 3; // distance from screen edge to margin in cells
    static final int MARGIN_INNER = CELL_SIZE * MARGIN_CELLS; // distance from screen edge to inner margin point, 3 cells away
    static final int MARGIN_OUTER = MARGIN_INNER - BORDER_THC; // distance from screen edge to outer margin point (closer to the screen)

    static final int MIN_CELL = MARGIN_CELLS; // first playable cell
    static final int MAX_CELL = CELL_COUNT - MARGIN_CELLS - 1; // last playable cell
}
