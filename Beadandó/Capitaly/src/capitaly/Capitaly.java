/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package capitaly;

/**
 * this class just calls the functions of the game class in the main function
 * next to the function calls is the expected 2nd loser
 * @author nagybalazs
 */
public class Capitaly {

    public static void main(String[] args) {
        Game game = new Game();
        //there should be no constant loser since the dice throw values are always changing
        game.gameWithRandomValues("input1.txt");
        
        //Csilla should be the second player to lose as she and Tamás both loses in the first round, but
        //Csilla is the latter
        game.gameWithGivenValues("input2.txt", "dices2.txt");
        
        //Óvatos should be the second player to lose according to input3solver.txt
        game.gameWithGivenValues("input3.txt", "dices3.txt");
        
        //Dénes should lose as the 2nd player
        game.gameWithGivenValues("input4.txt", "dices4.txt");
        
        //Cecíl should lose as the 2nd player
        game.gameWithGivenValues("input5.txt", "dices5.txt");
    } 
}
