

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the game board, composed of a list of cells.
 */
public class Board {
    private final List<Cell> cells;

    public Board(int size) {
        this.cells = new ArrayList<>(size + 1);
        // Create cells from 0 (base) to size (end cell)
        for (int i = 0; i <= size; i++) { 
            this.cells.add(new Cell(i));
        }
    }

    public Cell getCell(int idx) {
        if (idx >= 0 && idx < cells.size()) {
            return cells.get(idx);
        }
        return null;
    }

    // The playable board size is the number of cells minus the starting base.
    public int size() {
        return cells.size() - 1;
    }
}