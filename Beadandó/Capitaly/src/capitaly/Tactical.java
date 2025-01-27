package capitaly;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *tactical player, which changes its strategy each round
 * @author nagybalazs
 */
public class Tactical extends Player {
    public Tactical(String name){
        super(name);
    }
    
    /**
     * this function is called by the fields, when a tactical lands on them,
     * so it changes each round wether this player buys or not
     */
    @Override
    public void interactWithField(){
        this.doesBuildThisRound = !this.doesBuildThisRound;
        this.doesBuyThisRound = !this.doesBuyThisRound;
    }
    
    /**
     * basic toString override
     * @return 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tactical: ").append(name).append("\n");
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
