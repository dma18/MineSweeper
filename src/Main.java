/**
 * Created by danie on 12/23/2016.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private final int MIN_DIM = 10;
    private final int MAX_DIM = 30;
    private final int MIN_MINES = 15;
    private final int MAX_MINES = 15;
    private JFrame frame;
    private JPanel mainPanel, defaultPanel, customPanel, customWidth, customHeight, customMines;
    private JLabel currWidth, currHeight, currMines;
    private JButton[][] squares;
    private GridLayout field;

    public static void main(String[] args) {
        Main game = new Main();
        game.go();
    }

    void go() {
        frame = new JFrame();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setMaximumSize(new Dimension(500, 400));
        defaultPanel = new JPanel();
        defaultPanel.setLayout(new BoxLayout(defaultPanel, BoxLayout.Y_AXIS));
        customPanel = new JPanel();
        customPanel.setLayout(new BoxLayout(customPanel, BoxLayout.Y_AXIS));
        customWidth = new JPanel();
        customHeight = new JPanel();
        customMines = new JPanel();
        customWidth.setLayout(new FlowLayout(FlowLayout.LEFT));
        customHeight.setLayout(new FlowLayout(FlowLayout.LEFT));
        customMines.setLayout(new FlowLayout(FlowLayout.LEFT));
        mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        defaultPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        customWidth.setAlignmentX(Component.LEFT_ALIGNMENT);
        customHeight.setAlignmentX(Component.LEFT_ALIGNMENT);
        customMines.setAlignmentX(Component.LEFT_ALIGNMENT);

        currWidth = new JLabel(Integer.toString(MIN_DIM));
        currHeight = new JLabel(Integer.toString(MIN_DIM));
        currMines = new JLabel(Integer.toString(MIN_MINES));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("MineSweeper");

        JLabel welcome = new JLabel("Welcome to MineSweeper!");
        JButton easy = new JButton("Easy Mode: 9 x 9");
        JButton medium = new JButton("Medium Mode: 16 x 16");
        JButton difficult = new JButton("Difficult Mode: 30 x 30");
        JButton custom = new JButton("Custom Mode");
        JSlider width = new JSlider(JSlider.HORIZONTAL, MIN_DIM, MAX_DIM, MIN_DIM);
        JSlider height = new JSlider(JSlider.HORIZONTAL, MIN_DIM, MAX_DIM, MIN_DIM);
        JSlider mines = new JSlider(JSlider.HORIZONTAL, 15, 100, 15);

        easy.addActionListener(new EasyListener());
        medium.addActionListener(new MedListener());
        difficult.addActionListener(new DiffListener());
        custom.addActionListener(new CustomListener());

        welcome.setFont(new Font("Arial", Font.BOLD, 20));
        width.setSnapToTicks(true);
        width.setMinorTickSpacing(1);
        width.setPaintTicks(true);
        height.setSnapToTicks(true);
        height.setMinorTickSpacing(1);
        height.setPaintTicks(true);
        mines.setSnapToTicks(true);
        mines.setMinorTickSpacing(5);
        mines.setPaintTicks(true);

        JLabel customWidthLabel = new JLabel("Width");
        JLabel customHeightLabel = new JLabel("Height");
        JLabel customMinesLabel = new JLabel("Number of Mines");

        defaultPanel.add(Box.createRigidArea(new Dimension(10, 20)));
        defaultPanel.add(welcome);
        defaultPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        defaultPanel.add(easy);
        defaultPanel.add(Box.createRigidArea(new Dimension(10, 20)));
        defaultPanel.add(medium);
        defaultPanel.add(Box.createRigidArea(new Dimension(10, 20)));
        defaultPanel.add(difficult);
        defaultPanel.add(Box.createRigidArea(new Dimension(10, 20)));
        defaultPanel.add(custom);
        customWidth.add(width);
        customWidth.add(currWidth);
        customHeight.add(height);
        customHeight.add(currHeight);
        customMines.add(mines);
        customMines.add(currMines);

        customPanel.setMinimumSize(new Dimension(250, 200));
        customPanel.setMaximumSize(new Dimension(250, 200));
        customPanel.add(customWidthLabel);
        customPanel.add(customWidth);
        customPanel.add(customHeightLabel);
        customPanel.add(customHeight);
        customPanel.add(customMinesLabel);
        customPanel.add(customMines);

        mainPanel.add(defaultPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        mainPanel.add(customPanel);

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
        frame.setSize(500, 500);
    }

    void startGame(int width, int height, int numMines) {
        frame = new JFrame();
        squares = new JButton[width][height];
    }

    class EasyListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            startGame(9, 9, 10);
        }
    }

    class MedListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            startGame(16, 16, 40);
        }
    }

    class DiffListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            startGame(30, 30, 99);
        }
    }

    class CustomListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

        }
    }

    void gameEasy() {
        startGame(9, 9, 10);
    }

    void gameMedium() {
        startGame(16, 16, 40);
    }

    void gameDifficult() {
        startGame(30, 30, 99);
    }

}
