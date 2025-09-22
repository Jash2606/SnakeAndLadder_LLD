import java.util.*;

public class Game {
    private Board board;
    private List<Player> players;
    private Dice dice;
    private IRuleStrategy rules;
    private Player winner;
    private int currentPlayerIndex;

    public Game(DifficultyLevel level, List<String> playerNames, IRuleStrategy rules) {
        this.board = BoardFactory.createBoard(level);
        this.players = new ArrayList<>();
        for (String name : playerNames) {
            // Assuming 1 token per player for simplicity
            this.players.add(new Player(name, 1)); 
        }
        this.dice = new Dice();
        this.rules = rules;
        this.winner = null;
        this.currentPlayerIndex = 0;
    }

    public void start() {
        System.out.println("Game started on a board of size " + board.size());
        Scanner scanner = new Scanner(System.in);

        while (winner == null) {
            playTurn();
            System.out.println("------------------------------------");
            if (winner != null) {
                System.out.println("🎉 Congratulations " + winner.getName() + "! You have won the game! 🎉");
                break;
            }
            System.out.println("Press Enter to continue to the next turn...");
            scanner.nextLine();
        }
        scanner.close();
        System.out.println("Game Over.");
    }

    public void playTurn() {
        if (winner != null) return;

        Player currentPlayer = players.get(currentPlayerIndex);
        System.out.println("It's " + currentPlayer.getName() + "'s turn.");
        
        // Simplified for one token per player
        PlayerToken token = currentPlayer.getTokens().get(0);
        int currentPosition = token.getPosition();
        
        int diceVal = dice.roll();
        System.out.println(currentPlayer.getName() + " rolled a " + diceVal);

        if (currentPosition == 0) { // Token is at the base
            if (rules.canStart(token, diceVal)) {
                System.out.println("Rolled a 6! Token is now on the board at position 1.");
                moveToken(token, 0, 1);
            } else {
                System.out.println("Needs a 6 to start. Turn skipped.");
            }
        } else { // Token is on the board
            int potentialNewPosition = currentPosition + diceVal;
            
            if (rules.canEnd(token, diceVal, board.size())) {
                moveToken(token, currentPosition, board.size());
                winner = currentPlayer;
                return;
            }
            
            if (potentialNewPosition > board.size()) {
                System.out.println("Move overshoots the board. Turn skipped.");
            } else {
                Cell targetCell = board.getCell(potentialNewPosition);
                
                if (rules.canKill(token, targetCell, players)) {
                    System.out.println("Killing opponent token(s) at position " + potentialNewPosition);
                    List<PlayerToken> tokensToKill = new ArrayList<>(targetCell.getTokens());
                    tokensToKill.forEach(t -> t.sendToStart());
                    tokensToKill.forEach(t -> targetCell.removeToken(t));
                }
                
                moveToken(token, currentPosition, potentialNewPosition);
                targetCell.applyEffect(token, board); // Apply snake or ladder
            }
        }
        
        // Move to the next player for the subsequent turn
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    private void moveToken(PlayerToken token, int from, int to) {
        if (from > 0) {
            board.getCell(from).removeToken(token);
        }
        token.moveTo(to);
        board.getCell(to).addToken(token);
        System.out.println("Token for " + findPlayer(token).getName() + " moved from " + from + " to " + to);
    }
    
    private Player findPlayer(PlayerToken token) {
        return players.stream().filter(p -> p.getTokens().contains(token)).findFirst().orElse(null);
    }
}