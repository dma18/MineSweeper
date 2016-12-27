/**
 * Created by danie on 12/23/2016.
 */
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private final int MIN_DIM = 10;
    private final int MAX_DIM = 30;
    private final int MIN_MINES = 15;
    private final int MAX_MINES = 100;
    private JFrame frame;
    private JPanel mainPanel;
    private JSlider width, height, mines;
    private JLabel currWidth, currHeight, currMines;
    private JButton easy, medium, difficult, back;
    private Game game;
    private GridLayout field;

    public static void main(String[] args) {
        Main game = new Main();
        game.go();
    }

    void go() {
        if (frame != null && mainPanel != null) {
            frame.removeAll();
            frame.repaint();
            frame.revalidate();
            frame.add(mainPanel);
        }
        frame = new JFrame();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setMaximumSize(new Dimension(500, 400));
        JPanel defaultPanel = new JPanel();
        defaultPanel.setLayout(new BoxLayout(defaultPanel, BoxLayout.Y_AXIS));
        JPanel customPanel = new JPanel();
        customPanel.setLayout(new BoxLayout(customPanel, BoxLayout.Y_AXIS));
        JPanel customWidth = new JPanel();
        JPanel customHeight = new JPanel();
        JPanel customMines = new JPanel();
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
        easy = new JButton("Easy Mode: 9 x 9");
        medium = new JButton("Medium Mode: 16 x 16");
        difficult = new JButton("Difficult Mode: 20 x 30");
        JButton custom = new JButton("Custom Mode");
        width = new JSlider(JSlider.HORIZONTAL, MIN_DIM, MAX_DIM, MIN_DIM);
        width.addChangeListener(new CustomChangeListener());
        height = new JSlider(JSlider.HORIZONTAL, MIN_DIM, MAX_DIM, MIN_DIM);
        height.addChangeListener(new CustomChangeListener());
        mines = new JSlider(JSlider.HORIZONTAL, MIN_MINES, MAX_MINES, MIN_MINES);
        mines.addChangeListener(new CustomChangeListener());

        easy.addActionListener(new DefaultListener());
        medium.addActionListener(new DefaultListener());
        difficult.addActionListener(new DefaultListener());
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
        frame.setSize(new Dimension(1000, 1000));
        frame.setVisible(true);
    }

    void startGame(int width, int height, int numMines) {
        frame.remove(mainPanel);
        frame.repaint();
        frame.revalidate();
        game = new Game(width, height, numMines);


    }

    class DefaultListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JButton source = (JButton) event.getSource();
            if (source.equals(easy)) {
                startGame(9, 9, 10);
            } else if (source.equals(medium)) {
                startGame(16, 16, 40);
            } else if (source.equals(difficult)) {
                startGame(20, 30, 99);
            }
        }
    }

    class CustomListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            startGame(width.getValue(), height.getValue(), mines.getValue());
        }
    }

    class CustomChangeListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider) e.getSource();
            if (source.equals(width)) {
                currWidth.setText(Integer.toString(source.getValue()));
            } else if (source.equals(height)) {
                currHeight.setText(Integer.toString(source.getValue()));
            } else if (source.equals(mines)) {
                currMines.setText(Integer.toString(source.getValue()));
            }
        }
    }

}
