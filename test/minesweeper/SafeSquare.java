/**
 * Created by danie on 12/20/2016.
 */

public class SafeSquare extends Square {

    /**Number of adjacent mines**/
    private int adj;

    SafeSquare(int x, int y) {
        super(x, y);
        adj = 0;
    }

    public int getAdj() {
        return adj;
    }

    public void setAdj(int i) {
        adj = i;
    }
}
