/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tron_beadando_lhdn48;

import java.awt.Image;
/**
 *
 * Bike osztály, ami egy sprite lényegében a motorok implementására
 * @author nagybalazs
 */
public class Bike extends Sprite{
    private double velx;
    private double vely;
    private String name;
    private String color;
    public Bike(int x, int y, int width, int height, Image image, String name, String color)
    {
        super(x, y, width, height, image);
        this.velx = 0;
        this.vely = 0;
        this.color = color;
        this.name = name;
    }
    
    public void changeDir(String dir, int vel){
        if(null != dir)
        switch (dir) {
            case "left":
                this.velx = -vel;
                this.vely = 0;
                break;
            case "right":
                this.velx = vel;
                this.vely = 0;
                break;
            case "up":
                this.vely = -vel;
                this.velx = 0;
                break;
            case "down":
                this.vely = vel;
                this.velx = 0;
                break;
            default:
                break;
        }
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public void moveX() {
        x += velx;
    }
    public void moveY() {
        y += vely;
    }
    
    public boolean isItOut(){
        return this.getX()+this.getWidth() >= 800 || this.getX() <= 0
        || this.getY()+this.getHeight() >= 600 || this.getY() <= 0;
    }
}
