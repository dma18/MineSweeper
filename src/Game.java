/**
 * Created by danie on 12/20/2016.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;

public class Game {

    private boolean started = false;
    private boolean gameOver = false;
    private int width;
    private int height;

    private Square[][] board;
    private int numMines;
    private JLabel mineLabel;
    private int safes;
    private HashSet<Square> mines;

    public Game(int width, int height, int numMines) {
        this.width = width;
        this.height = height;
        board = new Square[width][height];
        mineLabel = new JLabel(Integer.toString(numMines));
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Square button = new Square(i, j);
                board[i][j] = button;
                button.setMaximumSize(new Dimension(30, 30));
                button.setMinimumSize(new Dimension(30, 30));
                button.addMouseListener(new MouseListener() {
                    public void mouseClicked(MouseEvent e) {
                        if (gameOver) {

                        } else if (SwingUtilities.isRightMouseButton(e) &&
                            !button.isRevealed()) {
                            if (!button.isFlagged()) {
                                flagSquare(button.getX(), button.getY());
                            } else {
                                unflagSquare(button.getX(), button.getY());
                            }
                        } else if (SwingUtilities.isLeftMouseButton(e) &&
                                !button.isRevealed()) {
                            revealSquare(button.getX(), button.getY());
                        } else if (SwingUtilities.isLeftMouseButton(e) &&
                            button.isRevealed()) {
                            button.setSelected(true);
                        } else {
                            return;
                        }
                    }
                   public void mousePressed(MouseEvent e) {
                   }

                   public void mouseReleased(MouseEvent e) {
                   }

                   public void mouseEntered(MouseEvent e) {
                   }

                   public void mouseExited(MouseEvent e) {
                   }
                });
            }
        }
        this.numMines = numMines;
        this.safes = width * height - numMines;
        this.mines = new HashSet<Square>();
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

    public HashSet<Square> getMines() {
        return mines;
    }

    public Square[][] getBoard() {
        return board;
    }

    public void setBoard(int x, int y, Square s) {
        board[x][y] = s;
    }

    public int getNumMines() {
        return numMines;
    }

    public JLabel getMineLabel() {
        return mineLabel;
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
            int tempX = (int) (Math.random() * width);
            int tempY = (int) (Math.random() * height);
            if ((tempX != x || tempY != y) && !(board[tempX][tempY].isMine())) {
                board[tempX][tempY].setMine();
                board[tempX][tempY].setSelectedIcon(new ImageIcon(
                        this.getClass().getResource("/mine.png")));
                mines.add(board[tempX][tempY]);
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
        //Reveal a square if Square is not flagged and not revealed
        if (board[x][y].isMine() && !board[x][y].isFlagged()) {
            gameOver(false);
        } else if (!board[x][y].isFlagged() && !board[x][y].isRevealed()) {
            board[x][y].setAdj(checkForMines(x, y));
            board[x][y].reveal();
            decSafes();
            if (safes == 0) {
                gameOver(true);
            } else if (board[x][y].getAdj() == 0) {
                for (int i = x - 1; i<= x + 1; i++) {
                    if (i < 0 || i >= width) {
                        continue;
                    }
                    for (int j = y - 1; j <= y + 1; j++) {
                        if (j < 0 || j >= height) {
                            continue;
                        }
                        if (!board[i][j].isRevealed()) {
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
                if (board[i][j].isMine()) {
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
        if (!board[x][y].isRevealed()){
            board[x][y].changeFlag();
            decNumMines();
            mineLabel.setText(Integer.toString(numMines));
        }
    }

    /** Unflag a flagged square */
    public void unflagSquare(int x, int y) {

        //Can unflag a Square ONLY if flagged
        if (board[x][y].isFlagged()) {
            board[x][y].changeFlag();
            incNumMines();
            mineLabel.setText(Integer.toString(numMines));
        }
    }

    public void gameOver(boolean win) {
        gameOver = win;
        JFrame end = new JFrame();
        JLabel endLabel = new JLabel();
        if (win) {
            end.setTitle("You won!");
            endLabel.setText("You won!");
        } else {
            end.setTitle("You lose!");
            endLabel.setText("You lost!");
        }
        end.add(BorderLayout.CENTER, endLabel);
        end.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        end.pack();
        end.setLocationRelativeTo(null);
        end.setVisible(true);
    }
}
