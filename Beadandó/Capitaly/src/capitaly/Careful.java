package capitaly;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *this player buys a house or a property anytime the purchase's price is less than
 * half of the player's money
 * @author nagybalazs
 */
public class Careful extends Player{
    /**
     * this method alters the player's behaviour
     * it is invoked by the fields
     */
    @Override
    public void interactWithField(){
        if(this.getMoney()/2 > 1000){
            this.doesBuyThisRound= true;
        }
        else{
            this.doesBuyThisRound = false;
        }
        if(this.getMoney()/2>4000){
            this.doesBuildThisRound = true;
        }
        else{
            this.doesBuildThisRound = false;
        }
    }
    
    /**
     * since thic class could change its behaviour when its money changes, but tactical
     * does not I can not call the interactWithField function directly when the players are
     * taxed through another player's property, or recieve money from another player landing on their
     * property, because tactical would change its behaviour each time, but it should not
     * that is the reason I am calling interactWithField inside setMoney
     * @param money 
     */
    @Override
    public void setMoney(int money){
        this.money = money;
        this.interactWithField();
    }

    public Careful(String name) {
        super(name);
    }
    
    /**
     * basic toString override
     * @return 
     */
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Careful: ").append(name).append("\n");
        sb.append("Money: ").append(this.money).append("\n");
        sb.append("Properties: ");

        if (properties.isEmpty()) {
            sb.append("None");
        } else {
            for (Property property : properties) {
                sb.append(property.toString()).append(", ");
            }
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }
}
