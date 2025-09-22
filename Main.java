import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Starting Standard Snakes and Ladders Game ---");
        
        Game standardGame = new Game(
            DifficultyLevel.MEDIUM,
            Arrays.asList("Alice", "Bob"),
            new StandardRuleStrategy()
        );
        
        standardGame.start();
    }
}