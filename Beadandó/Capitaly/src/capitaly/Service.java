package capitaly;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *Service field, which makes the player pay a specific amount of money
 * @author nagybalazs
 */
public class Service extends Field {
    private int servicePrice;

    public Service(int servicePrice) {
        this.servicePrice = servicePrice;
    }
    /**
     * this method is invoked by a player standing on the field, it makes the player pay the
     * amount of servicePrice and then interact with the player to alter it's behaviour
     * @param player -> the player which landed on this field
     */
    
    @Override
    public void interactWithPlayer(Player player)
    {
        player.setMoney(player.getMoney()-this.servicePrice);
        player.interactWithField();
    }
    
    /**
     * basic toString override
     * @return 
     */
    
    @Override
    public String toString()
    {
        return "Service field with fee of " + this.servicePrice;
    }
}
