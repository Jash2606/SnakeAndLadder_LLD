
import java.util.Map;
/**
 * A factory class to create game boards based on a specified difficulty level.
 */
public class BoardFactory {
    public static Board createBoard(DifficultyLevel level) {
        return switch (level) {
            case EASY -> createEasyBoard();
            case MEDIUM -> createMediumBoard();
            case HARD -> createHardBoard();
        };
    }

    private static void addEffectsToBoard(Board board, Map<Integer, Integer> snakes, Map<Integer, Integer> ladders) {
        snakes.forEach((head, tail) -> board.getCell(head).setEffect(new SnakeEffect(tail)));
        ladders.forEach((bottom, top) -> board.getCell(bottom).setEffect(new LadderEffect(top)));
    }

    private static Board createEasyBoard() {
        Board board = new Board(30);
        addEffectsToBoard(board, Map.of(28, 10, 18, 3), Map.of(5, 15, 12, 25));
        return board;
    }

    private static Board createMediumBoard() {
        Board board = new Board(100);
        Map<Integer, Integer> snakes = Map.of(98, 13, 95, 75, 93, 89, 64, 41, 55, 34, 48, 26, 16, 7);
        Map<Integer, Integer> ladders = Map.of(4, 14, 9, 31, 20, 38, 28, 84, 40, 59, 51, 67, 63, 81, 71, 91);
        addEffectsToBoard(board, snakes, ladders);
        return board;
    }

    private static Board createHardBoard() {
        Board board = new Board(100);
        Map<Integer, Integer> snakes = Map.of(98, 13, 95, 75, 93, 89, 87, 24, 64, 41, 62, 19, 55, 34, 49, 11, 48, 26, 16, 7);
        Map<Integer, Integer> ladders = Map.of(4, 14, 9, 31, 20, 38, 40, 59, 63, 81);
        addEffectsToBoard(board, snakes, ladders);
        return board;
    }
}