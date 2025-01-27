/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lovagitorna;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 *this class visualizes the board class and implements a popup stating the winner,
 * while giving an option to start a new game
 * @author nagybalazs
 */
public class BoardGUI {
    private JButton[][] buttons;
    private Board board;
    private JPanel boardPanel;

    public BoardGUI(int boardSize) {
        board = new Board(boardSize);
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(board.getBoardSize(), board.getBoardSize()));
        buttons = new JButton[board.getBoardSize()][board.getBoardSize()];
        Border border;
        
        for (int i = 0; i < board.getBoardSize(); ++i) {
            for (int j = 0; j < board.getBoardSize(); ++j) {
                JButton button = new JButton();
                button.addActionListener(new ButtonListener(i, j));
                button.setPreferredSize(new Dimension(80, 40));
                buttons[i][j] = button;
                boardPanel.add(button);
                buttons[i][j].setBackground(board.getField(i, j).getColor());
                    if(board.getField(i, j).getWhoIsStandingOnIt()== 1)
                    {
                        buttons[i][j].setText("♞");
                    }
                    else if(board.getField(i, j).getWhoIsStandingOnIt()== 2)
                    {
                        buttons[i][j].setText("♘");
                    }
                    else
                    {
                        buttons[i][j].setText("");
                    }
                //minden keretet eltűntetek
                border = BorderFactory.createLineBorder(null);
                buttons[i][j].setBorder(border);
            }
        }
        //aktuálisan mozgatható ló cellájának a keretének megváltoztatása
        border = BorderFactory.createLineBorder(Color.RED, 3);
        buttons[board.whichHorseCoordinates()[0]][board.whichHorseCoordinates()[1]].setBorder(border);
    }
    
    public JPanel getBoardPanel() {
        return boardPanel;
    }
    
    //ez csak arra van, ha új játékot kezdünk, akkor ezt hívom meg
    public void startNewGame() {
    board = new Board(board.getBoardSize()); // Reset the board data model
    Border border;
    
    //minden gomb frissítése
    for (int i = 0; i < board.getBoardSize(); i++) {
        for (int j = 0; j < board.getBoardSize(); j++) {
            buttons[i][j].setBackground(board.getField(i, j).getColor());
            if(board.getField(i, j).getWhoIsStandingOnIt()== 1)
            {
                buttons[i][j].setText("♞");
            }
            else if(board.getField(i, j).getWhoIsStandingOnIt()== 2)
            {
                buttons[i][j].setText("♘");
            }
            else
            {
                buttons[i][j].setText("");
            }
            border = BorderFactory.createLineBorder(null); // Clear borders
            buttons[i][j].setBorder(border);
        }
    }
    border = BorderFactory.createLineBorder(Color.RED, 3);
    buttons[board.whichHorseCoordinates()[0]][board.whichHorseCoordinates()[1]].setBorder(border);

    //ezek frissítik a panelokat
    boardPanel.revalidate();
    boardPanel.repaint();
}

    private class ButtonListener implements ActionListener {
        private int x, y;

        public ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //itt deklarálom a bordert, hogy használható legyen
            Border border;
            if (board.moveHorse(board.getWhoIsNext(), x, y)) {
            for (int i = 0; i < board.getBoardSize();i++) {
                for (int j = 0; j < board.getBoardSize(); j++) {
                    buttons[i][j].setBackground(board.getField(i, j).getColor());
                    //minden keretet eltűntetek
                    
                    border = BorderFactory.createLineBorder(null);
                    buttons[i][j].setBorder(border);
                    if(board.getField(i, j).getWhoIsStandingOnIt()== 1)
                    {
                        buttons[i][j].setText("♞");
                    }
                    else if(board.getField(i, j).getWhoIsStandingOnIt()== 2)
                    {
                        buttons[i][j].setText("♘");
                    }
                    else
                    {
                        buttons[i][j].setText("");
                    }
                }
            }
            //aktuálisan mozgatható ló cellájának a keretének megváltoztatása
            border = BorderFactory.createLineBorder(Color.RED, 3);
            buttons[board.whichHorseCoordinates()[0]][board.whichHorseCoordinates()[1]].setBorder(border);
                if (board.isOver() || board.isThereAWinner())
                {
                        int option = JOptionPane.showOptionDialog(
                        boardPanel,
                        "The game has ended! Winner: " + board.getWhoWon(),
                        "Game Over",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        new String[]{"New Game", "Exit"},
                        "New Game"
                    );

                    if (option == 0) {
                        startNewGame();
                    } else {
                        System.exit(0);
                    }
                }
                
            }
            else {
                System.out.println("Érvénytelen lépés.");
            }
        }
    }
}