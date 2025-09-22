import java.util.*;
public interface IRuleStrategy {
    boolean canStart(PlayerToken token, int diceVal);
    boolean canKill(PlayerToken token, Cell targetCell, List<Player> players);
    boolean canEnd(PlayerToken token, int diceVal, int boardSize);
}