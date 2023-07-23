import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JButton[] buttons = new JButton[9];
    private boolean xTurn = true;
    private int move = 0;

    public TicTacToe() {
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font(Font.SERIF, Font.PLAIN, 40));
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String temp =  xTurn ? "X" : "O";
        button.setText(temp); 
        button.setEnabled(false);  
        xTurn = !xTurn;
        ++move;
        
        checkForWinner();
        
    }

    private void checkForWinner() {    	
        // Check rows
        checkWin(0,1,2);
        checkWin(3,4,5);
        checkWin(6,7,8);

        // Check columns
        checkWin(0,3,6);
        checkWin(1,4,7);
        checkWin(2,5,8);
        
        // Check diagonals
        checkWin(0,4,8);
        checkWin(2,4,6);
 
        // Check for tie     
        if (move == 9) {
            JOptionPane.showMessageDialog(frame, "Tie game!");
            resetGame();
        }
    }
    private void checkWin(int pos1,int pos2,int pos3) {
    	if (buttons[pos1].getText().equals(buttons[pos2].getText()) 
        && buttons[pos1].getText().equals(buttons[pos3].getText()) 
        && !buttons[pos1].isEnabled()) {
            JOptionPane.showMessageDialog(frame, buttons[pos1].getText() + " wins!");
            resetGame();          
    	}
    }

    private void resetGame() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
        }
        move = 0;
        xTurn = true;
    }

    public static void main(String[] args) {
    	new TicTacToe();
    }
}
