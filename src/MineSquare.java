/**
 * Created by danie on 12/20/2016.
 */

public class MineSquare extends Square {

    public MineSquare(int x, int y) {
        super(x, y);
    }

    public int hashCode() {
        return (int)(Math.pow(this.getX(), 3) + Math.pow(this.getY(), 3));
    }
}
