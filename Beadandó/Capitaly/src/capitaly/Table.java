package capitaly;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *table class, which serves as a database, holding both the players and the fields
 * @author nagybalazs
 */
public class Table {

    private ArrayList<Field> fields;
    private ArrayList<Player> players;
    
    public Table(){
        fields = new ArrayList<>();
        players = new ArrayList<>();
    }
    
    /**
     * clears both fields and players
     */
    public void clear(){
        this.fields = new ArrayList<>();
        this.players = new ArrayList<>();
    }
    
    /**
     * reads a database from a txt file
     * @param filename
     * @throws FileNotFoundException
     * @throws InvalidInputException if the input does not suit the required layout,
     * or the available types of fields or players this exception is thrown
     */
    public void read(String filename) throws FileNotFoundException, InvalidInputException{
        Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
        int trackSize = sc.nextInt();
        for(int i = 0; i < trackSize; i++)
        {
            Field field = switch(sc.next())
            {
                case "P" -> new Property();
                case "S" -> new Service(sc.nextInt());
                case "L" -> new Luck(sc.nextInt());
                default -> throw new InvalidInputException();
            };
            fields.add(field);
        }
        while(sc.hasNext()){
            Player player = switch(sc.next())
            {
                case "G" -> new Greedy(sc.next());
                case "T" -> new Tactical(sc.next());
                case "C" -> new Careful(sc.next());
                default -> throw new InvalidInputException();
            };
            players.add(player);
        }
    }
    
    /**
     * creates a report from the content of the table and prints it to the console
     */
    public void report()
    {
        System.out.println("Fields in the game:");
        for(Field f : fields)
        {
            System.out.println(f);
        }
        System.out.println("\n");
        System.out.println("Number of players: ");
        System.out.println(players.size());
        System.out.println("\n");
        System.out.println("Players in the game:");
        for(Player p : players)
        {
            System.out.println(p);
        }
        System.out.println("\n");
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
