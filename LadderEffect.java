public class LadderEffect implements ICellEffect {
    private final int top;

    public LadderEffect(int top) { this.top = top; }

    @Override
    public void apply(PlayerToken token, Board board) {
        System.out.println("🪜 Yay! A ladder! Moving from " + token.getPosition() + " up to " + top);
        board.getCell(token.getPosition()).removeToken(token);
        token.moveTo(top);
        board.getCell(top).addToken(token);
    }
}