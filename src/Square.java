
/**
 * Created by danie on 12/20/2016.
 */
public class Square {

    private int x;
    private int y;
    private boolean flagged = false;
    private boolean revealed = false;

    Square() {
        x = -1;
        y = -1;
    }

    Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public boolean isRevealed() {
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
