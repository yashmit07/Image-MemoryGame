import java.awt.Dimension;
import javax.swing.JFrame;

public class MemoryGame{
    public static void main(String[] args){
        GameBoard board = new GameBoard();
        board.setSize( new Dimension (700, 700));
        board.setLocation(600, 170);
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setVisible(true);
    }
}