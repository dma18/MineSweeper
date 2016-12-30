import javax.swing.*;

/**
 * Created by danie on 12/20/2016.
 */

public class Square extends JToggleButton {

    private int x;
    private int y;
    private int adj;
    private boolean flagged = false;
    private boolean revealed = false;

    //TODO Set icons
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

    public boolean isFlagged() {
        return flagged;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public boolean changeFlag() {
        if (flagged) {
            setEnabled(false);
        } else {
            setEnabled(true);
        }
        flagged = !flagged;
        return flagged;
    }

    public void reveal() {
        setSelected(true);
        revealed = true;
    }

    public int getAdj() {
        return adj;
    }

    public void setAdj(int i) {
        adj = i;
    }
}
