import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    private final int sides;

    public Dice(int sides) {
        this.sides = sides;
    }

    // Default to a standard 6-sided die.
    public Dice() {
        this(6); 
    }

    public int roll() {
        return ThreadLocalRandom.current().nextInt(1, sides + 1);
    }
    
}
