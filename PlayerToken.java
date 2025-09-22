public class PlayerToken {
    private int position;

    public PlayerToken() {
        // A position of 0 represents the starting base, off the main board.
        this.position = 0; 
    }

    public void moveTo(int pos) {
        this.position = pos;
    }

    public void sendToStart() {
        this.position = 0;
        System.out.println("A token was sent back to the start base.");
    }

    public int getPosition() {
        return position;
    }
}