public class SnakeEffect implements ICellEffect {
    private final int tail;

    public SnakeEffect(int tail) { this.tail = tail; }

    @Override
    public void apply(PlayerToken token, Board board) {
        System.out.println("🐍 Oh no! A snake! Moving from " + token.getPosition() + " down to " + tail);
        board.getCell(token.getPosition()).removeToken(token);
        token.moveTo(tail);
        board.getCell(tail).addToken(token);
    }
}