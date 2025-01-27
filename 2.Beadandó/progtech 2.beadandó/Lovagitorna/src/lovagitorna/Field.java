/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lovagitorna;

import java.awt.Color;
/**
 * simple class implementing the fields of the board
 * @author nagybalazs
 */
public class Field {
    private Color color;
    //ez 0, ha senki, 1,ha fekete, 2, ha fehér
    private int whoIsStandingOnIt;

    public Field() {
        this.color = null;
        this.whoIsStandingOnIt = 0;
    }

    public Color getColor() {
        return color;
    }

    public int getWhoIsStandingOnIt() {
        return whoIsStandingOnIt;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setWhoIsStandingOnIt(int whoIsStandingOnIt) {
        this.whoIsStandingOnIt = whoIsStandingOnIt;
    } 
}
