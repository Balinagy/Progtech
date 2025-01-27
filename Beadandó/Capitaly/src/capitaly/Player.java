package capitaly;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
/**
 *abstract player class which is extended by classes for different strategies
 * @author nagybalazs
 */
public abstract class Player {
    protected String name;
    protected int whichField;
    protected int money;
    protected boolean doesBuyThisRound;
    protected ArrayList<Property> properties;
    protected boolean doesBuildThisRound;

    public void setWhichField(int whichField) {
        this.whichField = whichField;
    }

    public int getWhichField() {
        return whichField;
    }

    public Player(String name) {
        this.name = name;
        this.money = 10000;
        this.doesBuildThisRound = true;
        this.doesBuyThisRound = true;
        this.properties = new ArrayList<>();
        this.whichField = 0;
    }
    

    public boolean getDoesBuyThisRound() {
        return doesBuyThisRound;
    }

    public boolean getDoesBuildThisRound() {
        return doesBuildThisRound;
    }

    public int getMoney() {
        return money;
    }
    
    public void setMoney(int money) {
        this.money = money;
    }
    
    /**
     * this function adds a property to the player's properties
     * @param property 
     */
    public void addProperties(Property property){
        this.properties.add(property);
    }
    
    /**
     * this function clears the properties of the player
     */
    public void clearProperties(){
        for(Property p : properties){
            p.setOwner(null);
            p.setIsOwned(false);
            p.setContainsHouse(false);
        }
    }
    
    /**
     * this function is overwritten by the subclasses
     * with this function can we implement different strategies
     */
    public abstract void interactWithField();
    
}
