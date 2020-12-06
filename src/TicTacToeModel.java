import java.util.ArrayList;
import java.util.List;

public class TicTacToeModel {

    public static final int SIZE = 3;
    public static final boolean X = true;
    public static final boolean O = false;

    public enum Status {X_WON, O_WON, TIE, UNDECIDED};

    private char[][] grid;
    private boolean turn;
    private Status status;
    private int currentTurn = 0;

    private List<TicTacToeView> ticTacToeViews; // list of views

    public TicTacToeModel() {
        grid = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = ' ';
            }
        }
        turn = X;
        status = Status.UNDECIDED;

        ticTacToeViews = new ArrayList<>();
    }

    public void addTicTacToeView(TicTacToeView tttv) { // add view
        ticTacToeViews.add(tttv);
    }

    public void removeTicTacToeView(TicTacToeView tttv) { // remove view
        ticTacToeViews.remove(tttv);
    }

    private void changeTurn() {
        turn = !turn;
        currentTurn++;
    }

    private Status updateStatus() {
        int columnCountX = 0;
        int columnCountO = 0;
        int rowCountX = 0;
        int rowCountO = 0;

        if(grid[0][0] == 'X' && grid[1][1] == 'X' && grid[2][2] == 'X') {
            status = Status.X_WON;
            return status;
        }
        if(grid[0][0] == 'O' && grid[1][1] == 'O' && grid[2][2] == 'O') {
            status = Status.O_WON;
            return status;
        }
        if(grid[0][2] == 'X' && grid[1][1] == 'X' && grid[2][0] == 'X') {
            status = Status.X_WON;
            return status;
        }
        if(grid[0][2] == 'O' && grid[1][1] == 'O' && grid[2][0] == 'O') {
            status = Status.O_WON;
            return status;
        }

        for(int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 'X') {
                    columnCountX++;
                }
                if (grid[i][j] == 'O') {
                    columnCountO++;
                }
                if (columnCountX == 3) {
                    status = Status.X_WON;
                    return status;
                }
                if (columnCountO == 3) {
                    status = Status.O_WON;
                    return status;
                }
            }
            columnCountX = 0;
            columnCountO = 0;
        }
        for(int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[j][i] == 'X') {
                    rowCountX++;
                }
                if (grid[j][i] == 'O') {
                    rowCountO++;
                }
                if (rowCountX == 3) {
                    status = Status.X_WON;
                    return status;
                }
                if (rowCountO == 3) {
                    status = Status.O_WON;
                    return status;
                }
            }
            rowCountX = 0;
            rowCountO = 0;
        }
        if (currentTurn == 8){
            status = Status.TIE;
            return status;
        }
        return status;
    }

    public boolean getTurn() {return turn;}

    public Status getStatus() {return status;}

    public void play(int x, int y) { // gets called by controller when button is pressed in view/frame and an action event happens
        if (grid[x][y] != ' ') return;
        grid[x][y] = turn? 'X' : 'O';
        updateStatus();
		
        for (TicTacToeView tttv: ticTacToeViews)
            tttv.handleTicTacToeUpdate(new TicTacToeEvent(this, status, x, y)); // calls the view to update with the new event, with necessary information about x and y in the call
		
		changeTurn();
		
    }
}