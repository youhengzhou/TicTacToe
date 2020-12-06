import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeController implements ActionListener {
    private TicTacToeModel tttm;
    public TicTacToeController(TicTacToeModel tttm){
        this.tttm = tttm;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) { // gets called by view/frame when button is pressed
        String[] coordinates = actionEvent.getActionCommand().split(" "); // split string input into string array items
        tttm.play(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])); // x at 0, and y at 1 and calls play in model
    }
}