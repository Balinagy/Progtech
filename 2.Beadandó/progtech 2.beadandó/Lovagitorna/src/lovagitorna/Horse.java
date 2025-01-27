/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lovagitorna;
import java.awt.Color;

/**
 * horse class, which stores the position and the color of the horse, 
 * and also determines, if the horse can move to a specific position
 * @author nagybalazs
 */
public class Horse {
    private Color color;
    private int[] position = new int[2];

    public Horse(Color color, int posX, int posY) {
        this.color = color;
        this.position[0] = posX;
        this.position[1] = posY;
    }
    
    public boolean canItMoveThere(int a, int b)
    {
        if(a-this.position[0] == 2 || a-this.position[0] == -2)
        {
            if(b-this.position[1] == 1 || b-this.position[1]==-1)
            {
                return true;
            }
        }
        if(b-this.position[1] == 2 || b-this.position[1] == -2)
        {
            if(a-this.position[0] == 1 || a-this.position[0]==-1)
            {
                return true;
            }
        }
        return false;
    }

    public Color getColor() {
        return color;
    }

    public int[] getPosition() {
        return position;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }
}
