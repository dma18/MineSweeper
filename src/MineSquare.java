import javax.swing.*;
import java.awt.*;

/**
 * Created by danie on 12/20/2016.
 */

public class MineSquare extends Square {

    //TODO set icons
    public MineSquare(int x, int y) {
        super(x, y);
        setSelectedIcon(new ImageIcon(this.getClass().getResource("/mine.png")));
    }

    public int hashCode() {
        return (int)(Math.pow(this.getX(), 3) + Math.pow(this.getY(), 3));
    }
}
