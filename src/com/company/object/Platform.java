package com.company.object;

import javax.swing.*;
import java.awt.*;

public class Platform implements Collision{
    private int x,y;
    private Image image = new ImageIcon("image/object/platform.png").getImage();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move(int direction, boolean pressD){
        if (pressD) x-=direction;
        else x-=2*direction;

    }
}
