/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tron_beadando_lhdn48;

import java.awt.Image;

/**
 * egy Sprite a fénysugár implementálására
 * @author nagybalazs
 */
public class lightBeam extends Sprite {
    private Bike bike;
    public lightBeam(int x, int y, int width, int height, Image image, Bike bike) {
        super(x, y, width, height, image);
        this.bike = bike;
    }
    public Bike getBike(){
        return this.bike;
    }
}
