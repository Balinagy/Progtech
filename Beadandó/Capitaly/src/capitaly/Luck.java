package capitaly;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *Luck field, which pays the player a specific amount of money
 * @author nagybalazs
 */
public class Luck extends Field{
    private int luckPrice;

    public Luck(int luckPrice) {
        this.luckPrice = luckPrice;
    }
    
    /**
     * this method is invoked by a player standing on the field, it pays the player the
     * amount of luckPrice and then interact with the player to alter it's behaviour
     * @param player -> the player which landed on this field
     */
    
    @Override
    public void interactWithPlayer(Player player)
    {
        player.setMoney(player.getMoney()+this.luckPrice);
        player.interactWithField();
    }
    
    /**
     * basic toString override
     * @return 
     */
    @Override
    public String toString()
    {
        return "Luck field with reward of " + this.luckPrice;
    }
}
