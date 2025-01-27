package capitaly;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *this is a player with a greedy tactic, which means it buys properties anytime it
 * has enough money to do so
 * @author nagybalazs
 */
public class Greedy extends Player {
    /**
     * since the player buys any time this method does not alter its behaviour, but
     * it was needed to be implemented because of the abstraction
     */
    @Override
    public void interactWithField(){
        
    }
    
    public Greedy(String name) {
        super(name);
    }
    
    /**
     * basic toString override
     * @return 
     */
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Greedy: ").append(name).append("\n");
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
