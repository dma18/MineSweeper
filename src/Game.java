import java.util.HashSet;

/**
 * Created by danie on 12/20/2016.
 */
public class Game {

    private boolean started = false;
    private int width;
    private int height;
    private Square[][] board;
    private int numMines;
    private HashSet<MineSquare> mines;

    Game(int width, int height, int numMines) {
        this.width = width;
        this.height = height;
        this.board = new Square[width][height];
        this.numMines = numMines;
        this.mines = new HashSet<MineSquare>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Square[][] getBoard() {
        return board;
    }

    public int getNumMines() {
        return numMines;
    }

    public int decNumMines() {
        numMines--;
        return numMines;
    }


    /**First click is always safe.
     * Generate MineSquares based on location of first click and assign to MINES. */
    public void generateMines(int x, int y) {
        int n = numMines;
        while (n > 0) {
            int tempX = (int) (Math.random() * x);
            int tempY = (int) (Math.random() * y);
            if ((tempX != x || tempY != y) && !(board[tempX][tempY] instanceof MineSquare)) {
                MineSquare mine = new MineSquare(tempX, tempY);
                board[tempX][tempY] = mine;
                mines.add(mine);
                n--;
            }
        }
    }

    /**Left-click single square to reveal it.
     * If there are no mines adjacent to it, reveal adjacent squares
     * and so on until there are squares with mines adjacent to them.
     */
    public void revealSingleSquare(int x, int y) {
        if (!started) {
            generateMines(x, y);
        }
        board[x][y] = new SafeSquare(x, y);
    }

    public void gameOver() {

    }
}
