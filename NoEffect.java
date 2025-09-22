public class NoEffect implements ICellEffect {
    @Override
    public void apply(PlayerToken token, Board board) {
        System.out.println("Landed on a normal cell. No special effect.");
    }
}