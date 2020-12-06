import javax.swing.*;
import java.awt.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class TicTacToeFrame extends JFrame implements TicTacToeView {

    private JButton[][] buttons; // JButton array grid of buttons

    public TicTacToeFrame() {
        super("Tic Tac Toe"); // frame title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set exit
        this.setLayout(new GridLayout(TicTacToeModel.SIZE,TicTacToeModel.SIZE)); // set layout

        TicTacToeModel tttm = new TicTacToeModel(); // new TitTacToe game model
        tttm.addTicTacToeView(this); // tttm add view of frame
        TicTacToeController tttc = new TicTacToeController(tttm); // new TicTacToeController on model

        buttons = new JButton[TicTacToeModel.SIZE][TicTacToeModel.SIZE]; // set buttons grid size
		
        for (int i=0; i<TicTacToeModel.SIZE; i++) {
            for (int j=0; j<TicTacToeModel.SIZE; j++) {
                JButton b = new JButton("");
                buttons[i][j] = b;
                b.addActionListener(tttc); // set action listener to button, which gets called when button is pressed
                // b.addActionListener(new TicTacToeController(tttm, i, j));
                b.setActionCommand(i + " " + j); // set action listener command to button
                this.add(b); // adds the button to frame
            }
        }
        this.setSize(300,300); // set frame size
        this.setVisible(true); // set visible
    }

    @Override
    public void handleTicTacToeUpdate(TicTacToeEvent e) { // gets called by model when it needs to update view, when action event happens and controller is called to call model to change game logic
        int x = e.getX();
        int y = e.getY();
        TicTacToeModel.Status status = e.getStatus();

        TicTacToeModel tttm = (TicTacToeModel) e.getSource(); // get updated model
        String label = tttm.getTurn()? "X" : "O"; // get update needed letter
        buttons[x][y].setText(label); // set necessary button to update

        if (status == TicTacToeModel.Status.X_WON) {
            showMessageDialog(null, "Game Won By X");
        }
        if (status == TicTacToeModel.Status.O_WON) {
            showMessageDialog(null, "Game Won By O");
        }
        if (status == TicTacToeModel.Status.TIE) {
            showMessageDialog(null, "Game Tied");
        }
    }

    public static void main(String[] args) {
        new TicTacToeFrame();
    }
}