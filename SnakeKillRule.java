import java.util.List;

public class SnakeKillRule extends StandardRuleStrategy {
    
    @Override
    public boolean canKill(PlayerToken movingToken, Cell targetCell, List<Player> allPlayers) {
        // Special Rule: If landing on a snake's head, it kills any token there, even your own.
        if (targetCell.getEffect() instanceof SnakeEffect && !targetCell.getTokens().isEmpty()) {
            return true;
        }
        
        // Otherwise, use the standard rule (don't kill your own pieces).
        return super.canKill(movingToken, targetCell, allPlayers);
    }
}