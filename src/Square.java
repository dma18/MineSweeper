/**
 * Created by danie on 12/20/2016.
 */

public class Square {

    private int x;
    private int y;
    private boolean flagged = false;
    private boolean revealed = false;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getFlagged() {
        return flagged;
    }

    public boolean getRevealed() {
        return revealed;
    }

    public boolean changeFlag() {
        flagged = !flagged;
        return flagged;
    }

    public void reveal() {
        revealed = true;
    }
}
