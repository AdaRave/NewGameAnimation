package com.company.object;

import com.company.frame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Squirrel implements Collision {
    private int x,y;
    private Image image = new ImageIcon("image/object/squirrel0.png").getImage();
    private Image images[] = new Image[7];

    public void move(int direction, boolean pressD){
        if (pressD) x-=direction;
        else x-=2*direction;

    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Squirrel() {
        for (int i=0; i< images.length; i++){
            images[i] = new ImageIcon("image/object/squirrel"+i+".png").getImage();
        }
    }
    private int animationI =0;
    public void animation(){
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (animationI<images.length-1) animationI++;
                else animationI=0;
                image = images[animationI];
                MainFrame.panel.repaint();
            }
        });

        timer.start();
    }

    public void collisionSquirrel(int xP, int yP, Image imageP){
        if (collision(x, y, image, xP, yP, imageP)){
            y=-100;
            MainFrame.score.plus(1);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }
}
