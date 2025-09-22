
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a single square or cell on the game board.
 */
public class Cell {
    private final int index;
    private ICellEffect effect;
    private final List<PlayerToken> tokens;

    public Cell(int index) {
        this.index = index;
        this.effect = new NoEffect(); // Default to no effect
        this.tokens = new ArrayList<>();
    }

    public void addToken(PlayerToken token) { this.tokens.add(token); }
    public void removeToken(PlayerToken token) { this.tokens.remove(token); }
    public List<PlayerToken> getTokens() { return Collections.unmodifiableList(tokens); }
    public int getIndex() { return index; }
    public ICellEffect getEffect() { return effect; }
    public void setEffect(ICellEffect effect) { this.effect = effect; }
    
    public void applyEffect(PlayerToken token, Board board) {
        this.effect.apply(token, board);
    }
}