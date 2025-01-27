/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tron_beadando_lhdn48;

import java.util.Random;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import java.sql.SQLException;

/**
 *
 * Gameengine osztály kezel szinte minden játékbeli logikát
 * @author nagybalazs
 */
public class gameEngine extends JPanel{
    private final int FPS = 240;
    private final int BIKE_WIDTH = 40;
    private final int BIKE_HEIGHT = 20;
    private final int BIKE_MOVEMENT = 1;
    
    private boolean paused = false;
    private Image background;
    private int levelNum;
    private level level;
    private Bike bike1;
    private Bike bike2;
    private Timer newFrameTimer;
    /**
     * konstruktornál kezelem le a mozgásokat, valamint elindítom a timert,
     * amivel lehet megfelelő időközönként frissíteni a játékot
     */
    public gameEngine() {
        super();
        background = new ImageIcon("data/images/background.jpg").getImage();
        //blue bike movement change
        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "pressed left");
        this.getActionMap().put("pressed left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                bike1.changeDir("left", BIKE_MOVEMENT);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "pressed right");
        this.getActionMap().put("pressed right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                bike1.changeDir("right", BIKE_MOVEMENT);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "pressed down");
        this.getActionMap().put("pressed down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                bike1.changeDir("down", BIKE_MOVEMENT);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "pressed up");
        this.getActionMap().put("pressed up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                bike1.changeDir("up", BIKE_MOVEMENT);
            }
        });
        //orange bike movement change
        this.getInputMap().put(KeyStroke.getKeyStroke("A"), "pressed a");
        this.getActionMap().put("pressed a", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                bike2.changeDir("left", BIKE_MOVEMENT);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("D"), "pressed d");
        this.getActionMap().put("pressed d", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                bike2.changeDir("right", BIKE_MOVEMENT);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("S"), "pressed s");
        this.getActionMap().put("pressed s", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                bike2.changeDir("down", BIKE_MOVEMENT);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("W"), "pressed w");
        this.getActionMap().put("pressed w", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                bike2.changeDir("up", BIKE_MOVEMENT);
            }
        });
        
        restart("def1","def2", "Kék", "Piros");
        newFrameTimer = new Timer(1000 / FPS, new NewFrameListener());
        newFrameTimer.start();
    }

    /**
     * restart függvény, paraméterként megkapja a két játékos nevét és fénycsíkját
     * választ egy új pályát, valamint visszahelyezi a játékosokat az eredeti helyükre
     * és feloldja a megállított állapotot, ha meg vannak adva a játékosok nevei
     * @param name1
     * @param name2
     * @param color1
     * @param color2 
     */
    
    public void restart(String name1, String name2,String color1,String color2) {
        try {
            Random rand = new Random();
            levelNum = rand.nextInt(10);
            level = new level("data/levels/level0" + levelNum + ".txt");
        } catch (IOException ex) {
            Logger.getLogger(gameEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        if(name1.equals("def1") || name2.equals("def2"))
        {
            paused = true;
        }
        else{
            paused = false;
        }
        Image bikeImage1 = new ImageIcon("data/images/blueBike.png").getImage();
        Image bikeImage2 = new ImageIcon("data/images/orangeBike.png").getImage();
        bike1 = new Bike(750, 520, BIKE_WIDTH, BIKE_HEIGHT, bikeImage1,name1, color1);
        bike2 = new Bike(10, 10, BIKE_WIDTH, BIKE_HEIGHT, bikeImage2,name2,color2);
    }
    
    /**
     * egy switch a választott szín képének megszerzésére
     * @param color
     * @return 
     */
    private Image getBeamImage(String color) {
        switch (color) {
            case "Piros": return new ImageIcon("data/images/red.png").getImage();
            case "Kék": return new ImageIcon("data/images/blue.png").getImage();
            case "Zöld": return new ImageIcon("data/images/green.png").getImage();
            case "Sárga": return new ImageIcon("data/images/orange.jpg").getImage();
            case "Lila": return new ImageIcon("data/images/purple.png").getImage();
            default: return new ImageIcon("data/images/default.png").getImage();
        }
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.drawImage(background, 0, 0, 800, 600, null);
        level.draw(grphcs);
        bike1.draw(grphcs);
        bike2.draw(grphcs);
    }
    
    /**
     * frissíti a képet
     */
    class NewFrameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!paused) {
                bike1.moveX();
                bike1.moveY();
                bike2.moveX();
                bike2.moveY();
                //színváltás
                Image beamImage1 = getBeamImage(bike1.getColor());
                Image beamImage2 = getBeamImage(bike2.getColor());
                level.addLightBeam(new lightBeam(bike1.getX()+20, bike1.getY()+10,2,2,beamImage1, bike1));
                level.addLightBeam(new lightBeam(bike2.getX()+20, bike2.getY()+10,2,2,beamImage2, bike2));
                if (level.collides(bike1) || bike1.isItOut()) {
                    paused = true;
                    addScoreAndShowGameOver(bike2.getName());
                }
                if (level.collides(bike2) || bike2.isItOut()) {
                    paused = true;
                    addScoreAndShowGameOver(bike1.getName());
                }
            repaint();
            }

        }
    }
    /**
     * trycatch miatt van különszedve
     * @param winnerName 
     */
    private void addScoreAndShowGameOver(String winnerName) {
        try {
            HighScores highScores = new HighScores(10);
            highScores.putHighScore(winnerName);
            showGameOverDialog(winnerName);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hiba történt a pontszám rögzítése során: " + ex.getMessage(),
                    "Hiba", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * gameover kiiratás, amit a felette lévő függvény hív meg
     * @param winnerName 
     */
    private void showGameOverDialog(String winnerName) {
    int choice = JOptionPane.showOptionDialog(
        null,
        winnerName + " nyert! Szeretnél új játékot kezdeni?",
        "Játék vége",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.INFORMATION_MESSAGE,
        null,
        new String[]{"Új játék", "Kilépés"},
        "Új játék"
    );

    if (choice == JOptionPane.YES_OPTION) {
        restart(bike1.getName(), bike2.getName(), bike1.getColor(), bike2.getColor());
    } else {
        System.exit(0);
    }
}
}
