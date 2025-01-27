package lovagitorna;

import java.awt.Color;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * This class implements the core functions of the playboard, containing the fields
 * and the horses
 * it also moves the horses on the fields, changes the values of the fields and
 * decides who is the winner
 * @author nagybalazs
 */
public class Board {
    private final Field[][] board;
    private final int boardSize;
    private final Horse[] horses;
    private int whoIsNext;
    private String whoWon = "Tie";

    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.whoIsNext = 0;
        board = new Field[this.boardSize][this.boardSize];
        for (int i = 0; i < this.boardSize; ++i) {
            for (int j = 0; j < this.boardSize; ++j) {
                board[i][j] = new Field();
            }
        }
        //mindig felső bal sarokban fekete
        //bw
        //wb
        board[0][0].setColor(Color.black);
        board[0][0].setWhoIsStandingOnIt(1);
        board[0][boardSize-1].setColor(Color.white);
        board[0][boardSize-1].setWhoIsStandingOnIt(2);
        
        board[boardSize-1][0].setColor(Color.white);
        board[boardSize-1][0].setWhoIsStandingOnIt(2);
        board[boardSize-1][boardSize-1].setColor(Color.black);
        board[boardSize-1][boardSize-1].setWhoIsStandingOnIt(1);
        
        //lovak létrehozása és kezdeti helyzetük beállítása
        horses=new Horse[4];
        horses[0] = new Horse(Color.black, 0,0);
        horses[1] = new Horse(Color.white, 0,boardSize-1);
        horses[2] = new Horse(Color.black, boardSize-1,boardSize-1);
        horses[3] = new Horse(Color.white, boardSize-1,0);
    }
    
    public boolean moveHorse(int whichHorse, int x, int y)
    {
        if(horses[whichHorse].canItMoveThere(x, y) && board[x][y].getWhoIsStandingOnIt()==0)
        {
            //az aktuális táblában levesszük a játékost a helyről
            this.board[horses[whichHorse].getPosition()[0]][horses[whichHorse].getPosition()[1]].setWhoIsStandingOnIt(0);
            //a léptetett ló helyzetét átállítjuk
            int[] pos = {x,y};
            horses[whichHorse].setPosition(pos);
            //a táblán átállítjuk a ló színére a mezőt, amire lépett
            this.board[x][y].setColor(horses[whichHorse].getColor());
            if(horses[whichHorse].getColor()==Color.black){
                this.board[x][y].setWhoIsStandingOnIt(1);
            }
            else
            {
                this.board[x][y].setWhoIsStandingOnIt(2);
            }
            //következő player
            this.whoIsNext = (this.whoIsNext+1)%4;
            return true;
        }
        return false;
    }

    public int getWhoIsNext() {
        return whoIsNext;
    }
    
    public String getWhoWon() {
        return whoWon;
    }
    
    public boolean isOver()
    {
        for (int i = 0; i < this.boardSize; ++i) {
            for (int j = 0; j < this.boardSize; ++j) {
                if(board[i][j].getColor() == null)
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean isThereAWinner() {
        //sorok
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j <= this.boardSize - 4; j++) {
                Color color = board[i][j].getColor();
                if (color != null &&
                    color.equals(board[i][j + 1].getColor()) &&
                    color.equals(board[i][j + 2].getColor()) &&
                    color.equals(board[i][j + 3].getColor())) {
                    whoWon = color.equals(Color.BLACK) ? "Black" : "White";
                    return true;
                }
            }
        }

        //oszlopok
        for (int i = 0; i <= this.boardSize - 4; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                Color color = board[i][j].getColor();
                if (color != null &&
                    color.equals(board[i + 1][j].getColor()) &&
                    color.equals(board[i + 2][j].getColor()) &&
                    color.equals(board[i + 3][j].getColor())) {
                    whoWon = color.equals(Color.BLACK) ? "Black" : "White";
                    return true;
                }
            }
        }

        //átló (bal felső -> jobb alsó)
        for (int i = 0; i <= this.boardSize - 4; i++) {
            for (int j = 0; j <= this.boardSize - 4; j++) {
                Color color = board[i][j].getColor();
                if (color != null &&
                    color.equals(board[i + 1][j + 1].getColor()) &&
                    color.equals(board[i + 2][j + 2].getColor()) &&
                    color.equals(board[i + 3][j + 3].getColor())) {
                    whoWon = color.equals(Color.BLACK) ? "Black" : "White";
                    return true;
                }
            }
        }

        //átló (jobb felső -> bal alsó)
        for (int i = 0; i <= this.boardSize - 4; i++) {
            for (int j = 3; j < this.boardSize; j++) {
                Color color = board[i][j].getColor();
                if (color != null &&
                    color.equals(board[i + 1][j - 1].getColor()) &&
                    color.equals(board[i + 2][j - 2].getColor()) &&
                    color.equals(board[i + 3][j - 3].getColor())) {
                    whoWon = color.equals(Color.BLACK) ? "Black" : "White";
                    return true;
                }
            }
        }

        return false;
    }
    public int[] whichHorseCoordinates()
    {
        return horses[whoIsNext].getPosition();
    }
    
    public Field getField(int x, int y)
    {
        return this.board[x][y];
    }
    
    public int getBoardSize() {
        return boardSize;
    }
}
