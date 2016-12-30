import javax.swing.*;
import java.awt.*;

/**
 * Created by danie on 12/20/2016.
 */

public class Square extends JToggleButton {

    private static final int SIDE = 30;
    private int x;
    private int y;
    private int adj;
    private boolean flagged = false;
    private boolean revealed = false;

    //TODO Set icons
    public Square(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        setIcon(new ImageIcon(this.getClass().getResource("/unrevealed.jpg")));
        setDisabledIcon(new ImageIcon(this.getClass().getResource("/flag.png")));
        setSize(new Dimension(SIDE, SIDE));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static int side() {
        return SIDE;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public boolean changeFlag() {
        if (!flagged) {
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
