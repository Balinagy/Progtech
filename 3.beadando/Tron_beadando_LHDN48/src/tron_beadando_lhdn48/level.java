/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tron_beadando_lhdn48;

/**
 *szintek beolvasása fájlokból valamint a beamek és az akadályok tárolása
 * @author nagybalazs
 */

import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import javax.swing.ImageIcon;

public class level {
    private final int OBSTACLE_SIZE = 20;
    ArrayList<mapObstacle> obstacles;
    ArrayList<lightBeam> beams = new ArrayList<>();
    
    /**
     * meghívja a fájlbetöltést
     * @param levelPath
     * @throws IOException 
     */
    public level(String levelPath) throws IOException {
        loadLevel(levelPath);
    }
    
    /**
     * beolvassa a pályákat egy txt fájlból
     * @param levelPath
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void loadLevel(String levelPath) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(levelPath));
        obstacles = new ArrayList<>();
        int y = 0;
        String line;
        while ((line = br.readLine()) != null) {
            int x = 0;
            for (char blockType : line.toCharArray()) {
                if (Character.isDigit(blockType)) {
                    Image image = new ImageIcon("data/images/obstacle.png").getImage();
                    obstacles.add(new mapObstacle(x * OBSTACLE_SIZE, y * OBSTACLE_SIZE, OBSTACLE_SIZE, OBSTACLE_SIZE, image));
                }
                x++;
            }
            y++;
        }
    }
    
    /**
     * vizsgálja, hogy bármely obstaclenek vagy beamnek nekimegy-e a bemenetként kapott motor
     * ezt hívja meg a gameengine minden mozgás során
     * @param bike
     * @return 
     */
    public boolean collides(Bike bike) {
        for (mapObstacle obs : obstacles) {
            if (bike.collides(obs)) {
                return true;
            }
        }
        for (lightBeam b : beams){
            if (bike.collides(b) && (b.getBike()!=bike)) {
                return true;
            }
        }
        return false;
    }
    
    public void draw(Graphics g) {
        for (mapObstacle obs : obstacles) {
            obs.draw(g);
        }
        for(lightBeam b : beams){
            b.draw(g);
        }
    }
    
    public void addLightBeam(lightBeam beam)
    {
        beams.add(beam);
    }
}