package com.company.object;

import javax.swing.*;
import java.awt.*;

public class Fon {
    private Image fon1 = new ImageIcon("image/forest.jpg").getImage();
    private Image fon2 = new ImageIcon("image/forest.jpg").getImage();
    private Image fon3 = new ImageIcon("image/forest.jpg").getImage();
    private int x1 =0, x2 = fon1.getWidth(null), x3 = -fon1.getWidth(null);

    public Image getFon3() {
        return fon3;
    }

    public int getX3() {
        return x3;
    }

    public void move(Integer direction, Boolean pressD){
        if (x2<=0 || x3>=0){
            x1=0;
            x2 = fon1.getWidth(null);
            x3 = -fon1.getWidth(null);
        }

        else {
            if (pressD == true){
                x1-=direction;
                x2-=direction;
                x3-=direction;
            }else {
                x1-=2*direction;
                x2-=2*direction;
                x3-=2*direction;
            }

        }
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public Image getFon1() {
        return fon1;
    }

    public Image getFon2() {
        return fon2;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }
}
