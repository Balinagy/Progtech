package capitaly;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *the three types of fields derive from this class
 * @author nagybalazs
 */

public abstract class Field {
    /**
     * this abstract method is invoked when a player lands on its field and also
     * alters the behaviour of the player
     * @param p -> the player, which landed on the field
     */
    public abstract void interactWithPlayer(Player p);
}
