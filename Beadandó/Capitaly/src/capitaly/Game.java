package capitaly;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * game class, which plays the game using a table as a database
 * it could play games with random dice throws, or even with prewritten ones
 * @author nagybalazs
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    
    private Table table = new Table();
    
    /**
     * this function searches for players who do not have positive amount of money
     * thus need to be removed
     * @return it returns a player if someone needs to be removed and null if not
     */
    public Player shouldRemoveSomeone()
    {
        for(Player p : table.getPlayers())
        {
            if(p.getMoney()<=0)
            {
                return p;
            }
        }
        return null;
    }
    
    /**
     * this function generates a random number under 100, which is used as a dicenumber
     * @return it returns the number
     */
    public int randomStep()
    {
        Random random = new Random();
        return random.nextInt(100);
    }
    
    /**
     * this function plays a game with random dice throws until the second player loses the game
     * it starts with printing out the table
     * then proceeds to play the game
     * and finishes with logging the second player to the console
     * @param input this input is a txt file where the fields and players are stored
     */
    public void gameWithRandomValues(String input)
    {
        table.clear();
        try {
            table.read(input);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            System.exit(-1);
        } catch (InvalidInputException ex) {
            System.out.println("Invalid input!");
            System.exit(-1);
        }
        int initialLength = table.getPlayers().size();
        System.out.println("Initial stance of the fields and the players:");
        System.out.println("______________________________________________");
        this.table.report();
        System.out.println("______________________________________________");
        while(table.getPlayers().size() > initialLength-2)
        {
            /*
            without this type of cycle I would have to break the cycle each time
            a player lost and restart it afterwards because iteration is not
            possible after you delete the player from the collection, but with this
            structure it is
            */
            for(int i = 0; i< table.getPlayers().size(); i++)
            {
                Player helperplayer = table.getPlayers().get(i);
                helperplayer.setWhichField((helperplayer.getWhichField()+randomStep())%table.getFields().size());
                table.getFields().get(helperplayer.getWhichField()).interactWithPlayer(helperplayer);
                if(!(this.shouldRemoveSomeone() == (null))){
                    if(initialLength-table.getPlayers().size()+1 ==2)
                    {
                        StringBuilder sb = new StringBuilder();
                        sb.append(this.shouldRemoveSomeone().name);
                        sb.append(" has lost the game as ");
                        sb.append(initialLength-table.getPlayers().size()+1);
                        sb.append(". player.\n\n");
                        System.out.println(sb.toString());
                    }
                    this.table.getPlayers().remove(helperplayer);
                    i-=1;
                }
            }
        }
    }
    
    /**
     * this function plays a game with pregiven dice throws until the second player loses the game
     * it starts with printing out the table
     * then proceeds to play the game
     * and finishes with logging the second player to the console
     * @param input this input is a txt file where the fields and players are stored
     * @param dices this input is a txt file where the dice throws are stored
     */
    public void gameWithGivenValues(String input, String dices)
    {
        table.clear();
        ArrayList<Integer> dicesValues = new ArrayList<>();
        try(Scanner sc = new Scanner(new BufferedReader(new FileReader(dices))))
        {
            while(sc.hasNext())
            {
                dicesValues.add(sc.nextInt());
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println("Dice values file not found!");
            System.exit(-1);
        }
        
        try {
            table.read(input);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            System.exit(-1);
        } catch (InvalidInputException ex) {
            System.out.println("Invalid input!");
            System.exit(-1);
        }
        int initialLength = table.getPlayers().size();
        System.out.println("Initial stance of the fields and the players:");
        System.out.println("______________________________________________");
        this.table.report();
        System.out.println("______________________________________________");
        int whichDiceValue = 0;
        while(table.getPlayers().size() > initialLength-2)
        {
            for(int i = 0; i< table.getPlayers().size(); i++)
            {
                Player helperplayer = table.getPlayers().get(i);
                helperplayer.setWhichField((helperplayer.getWhichField()+dicesValues.get(whichDiceValue % dicesValues.size()))
                %table.getFields().size());
                table.getFields().get((helperplayer.getWhichField())).interactWithPlayer(helperplayer);
                whichDiceValue += 1;
                if(!(this.shouldRemoveSomeone() == (null))){
                    if(initialLength-table.getPlayers().size()+1 ==2)
                    {
                        StringBuilder sb = new StringBuilder();
                        sb.append(this.shouldRemoveSomeone().name);
                        sb.append(" has lost the game as ");
                        sb.append(initialLength-table.getPlayers().size()+1);
                        sb.append(". player.\n\n");
                        System.out.println(sb.toString());
                    }
                    this.table.getPlayers().remove(helperplayer);
                    i-=1;
                }
            }
        }
    }
}
