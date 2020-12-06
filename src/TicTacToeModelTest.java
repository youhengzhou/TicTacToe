import org.junit.Test;

import static org.junit.Assert.*;

public class TicTacToeModelTest {
    TicTacToeModel tttm;

    @Test
    public void testInitialStatus() {
        tttm = new TicTacToeModel();
        assertEquals(TicTacToeModel.Status.UNDECIDED, tttm.getStatus());
    }

    @Test
    public void testX_WON() {
        tttm = new TicTacToeModel();
        tttm.play(0,0);
        tttm.play(0,1);
        tttm.play(1,1);
        tttm.play(0,2);
        tttm.play(2,2);
        assertEquals(TicTacToeModel.Status.X_WON, tttm.getStatus());
    }

    @Test
    public void testO_WON() {
        tttm = new TicTacToeModel();
        tttm.play(2,1);
        tttm.play(0,0);
        tttm.play(0,1);
        tttm.play(1,1);
        tttm.play(0,2);
        tttm.play(2,2);
        assertEquals(TicTacToeModel.Status.O_WON, tttm.getStatus());
    }

    @Test
    public void testTIE() {
        tttm = new TicTacToeModel();
        tttm.play(0,0);
        tttm.play(0,1);
        tttm.play(1,1);
        tttm.play(2,2);
        tttm.play(1,0);
        tttm.play(2,0);
        tttm.play(2,1);
        tttm.play(1,2);
        tttm.play(0,2);
        assertEquals(TicTacToeModel.Status.TIE, tttm.getStatus());
    }
}