import java.util.List;

public class StandardRuleStrategy implements IRuleStrategy {

    @Override
    public boolean canStart(PlayerToken token, int diceVal) {
        return token.getPosition() == 0 && diceVal == 6;
    }

    @Override
    public boolean canKill(PlayerToken movingToken, Cell targetCell, List<Player> allPlayers) {
        if (targetCell.getTokens().isEmpty()) return false;
        
        PlayerToken tokenOnCell = targetCell.getTokens().get(0);
        Player ownerOfMovingToken = findOwner(movingToken, allPlayers);
        Player ownerOfTokenOnCell = findOwner(tokenOnCell, allPlayers);

        // A token cannot kill another token belonging to the same player.
        return ownerOfMovingToken != ownerOfTokenOnCell;
    }

    @Override
    public boolean canEnd(PlayerToken token, int diceVal, int boardSize) {
        // Player must roll the exact number to land on the final cell.
        return token.getPosition() + diceVal == boardSize;
    }

    protected Player findOwner(PlayerToken token, List<Player> players) {
        for (Player player : players) {
            if (player.getTokens().contains(token)) return player;
        }
        return null;
    }
}