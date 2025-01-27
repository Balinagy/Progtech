package capitaly;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *property field, which the players can buy, build a house on, and tax other players
 * afterwards
 * @author nagybalazs
 */
public class Property extends Field {
    private boolean isOwned;
    private boolean containsHouse;
    private Player owner;

    public Property() {
        this.isOwned = false;
        this.containsHouse = false;
        this.owner = null;
    }
    

    public boolean isIsOwned() {
        return isOwned;
    }

    public boolean isContainsHouse() {
        return containsHouse;
    }

    public void setIsOwned(boolean isOwned) {
        this.isOwned = isOwned;
    }

    public void setContainsHouse(boolean containsHouse) {
        this.containsHouse = containsHouse;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
    
    /**
     * this method buys the property for the player if it is empty, then builds a house on
     * it and also makes the other players pay for the owner afterwards
     * it also alters the behaviour of the players
     * @param player -> player, which landed on the field
     */
    
    @Override
    public void interactWithPlayer(Player player)
    {
        if(this.isOwned)
        {
            if(!this.owner.equals(player)){
                if(this.containsHouse)
                {
                    if(player.getMoney()>2000){
                        player.setMoney(player.getMoney()-2000);
                        this.owner.setMoney(this.owner.getMoney()+2000);
                    }
                    else
                    {
                        player.setMoney(-1);
                    }
                }
                else
                {
                    if(player.getMoney()>500){
                        player.setMoney(player.getMoney()-500);
                        this.owner.setMoney(this.owner.getMoney()+500);
                    }
                    else
                    {
                        player.setMoney(-1);
                    }
                }
                player.interactWithField();
            }
        }
        if(!this.isOwned){
            if(player.getDoesBuyThisRound() && player.getMoney() > 1000)
            {
                player.addProperties(this);
                player.setMoney(player.getMoney()-1000);
                this.owner=player;
                this.isOwned = true;
                player.interactWithField();
            }
        }
        if(!this.containsHouse && this.isOwned){
            if(this.owner.equals(player) && player.getDoesBuildThisRound()
                && player.getMoney() > 4000)
        {
            this.containsHouse = true;
            player.setMoney(player.getMoney()-4000);
            player.interactWithField();
        }
        }
    }
    
    /**
     * basiic toString override
     * @return 
     */
    
    @Override
    public String toString()
    {
        if(this.isOwned)
        {
            if(this.containsHouse){
                return "Property field with house owned by " + this.owner.name;
            }
            return "Property field without house owned by " + this.owner.name;
        }
        return "Empty property field";
    }
}
