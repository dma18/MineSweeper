/**
 * Created by danie on 12/20/2016.
 */

import java.util.HashSet;

public class Game {

    private boolean started = true;
    private boolean gameOver = false;
    private int width;
    private int height;
    
    private Square[][] board;
    private int numMines;
    private int safes;
    private HashSet<MineSquare> mines;

    public Game(int width, int height, int numMines) {
        this.width = width;
        this.height = height;
        this.board = new Square[width][height];
        this.numMines = numMines;
        this.safes = width * height - numMines;
        this.mines = new HashSet<MineSquare>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isStarted() {
        return started;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public HashSet<MineSquare> getMines() {
        return mines;
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

    public int incNumMines() {
        numMines++;
        return numMines;
    }

    public int getSafes() {
        return safes;
    }

    public int decSafes() {
        safes--;
        return safes;
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
    public void revealSquare(int x, int y) {

        //First reveal is always safe.  Generate mines if STARTED == false.
        if (!started) {
            generateMines(x, y);
            started = true;
        }

        //Reveal an unflagged mine -> GAME OVER.
        //Reveal a square if null OR Square is not flagged and not revealed
        if (board[x][y] instanceof MineSquare && !board[x][y].getFlagged()) {
            gameOver(false);
        } else if (board[x][y] == null ||
                (!board[x][y].getFlagged() && !board[x][y].getRevealed())) {
            board[x][y] = new SafeSquare(x, y);
            ((SafeSquare) board[x][y]).setAdj(checkForMines(x, y));
            board[x][y].reveal();
            decSafes();
            if (safes == 0) {
                gameOver(true);
            } else if (((SafeSquare) board[x][y]).getAdj() == 0) {
                for (int i = x - 1; i<= x + 1; i++) {
                    if (i < 0 || i >= width) {
                        continue;
                    }
                    for (int j = y - 1; j <= y + 1; j++) {
                        if (j < 0 || j >= height) {
                            continue;
                        }
                        if (board[i][j] == null) {
                            revealSquare(i, j);
                        }
                    }
                }
            }
        }
    }

    /**
     * Find and return number of mines surrounding a SafeSquare at (x,y)
     */
    public int checkForMines(int x, int y) {
        int mineCount = 0;

        //Check surrounding squares.  Ignore coordinates that go offboard.
        for (int i = x - 1; i <= x + 1; i++) {
            if (i < 0 || i >= width) {
                continue;
            }
            for (int j = y - 1; j <= y + 1; j++) {
                if (j < 0 || j >= height) {
                    continue;
                }
                if (board[i][j] instanceof MineSquare) {
                    mineCount++;
                }
            }
        }
        return mineCount;
    }

    /** Flag an unrevealed square */
    public void flagSquare(int x, int y) {

        //If not initialized -> create new Square and flag it
        //The Square has not been revealed -> flag the Square
        if (board[x][y] == null) {
            board[x][y] = new Square(x, y);
        }
        if (!board[x][y].getRevealed()){
            board[x][y].changeFlag();
            decNumMines();
        }
    }

    /** Unflag a flagged square */
    public void unflagSquare(int x, int y) {

        //Can unflag a Square ONLY if initialized and flagged
        if ((board[x][y] != null) && board[x][y].getFlagged()) {
            board[x][y].changeFlag();
            incNumMines();
        }
    }

    public void gameOver(boolean win) {
        gameOver = true;

    }
}
