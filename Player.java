
import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<PlayerToken> tokens;

    public Player(String name, int numberOfTokens) {
        this.name = name;
        this.tokens = new ArrayList<>();
        for (int i = 0; i < numberOfTokens; i++) {
            this.tokens.add(new PlayerToken());
        }
    }
    
    // In a real game, this might take more parameters to decide which token to move.
    public void takeTurn(Dice dice) {
        int roll = dice.roll();
        System.out.println(name + " rolled a " + roll);
    }

    public String getName() {
        return name;
    }

    public List<PlayerToken> getTokens() {
        return tokens;
    }
}