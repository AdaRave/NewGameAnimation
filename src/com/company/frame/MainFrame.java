package com.company.frame;

import com.company.hero.Hero;
import com.company.object.Fon;
import com.company.object.Platform;
import com.company.object.Squirrel;

import javax.swing.*;
import java.awt.*;


public class MainFrame {
    public static JFrame frame = new JFrame();
    public static JPanel panel;
    public static Hero hero = new Hero();

    public static Fon fon = new Fon();

    public static Platform platform[] = new Platform[5];

    public static Squirrel squirrel[] = new Squirrel[5];


    public MainFrame() {
        frame.setLocation(100,100);
        frame.setSize(500, 300);

        createPlatform();
        creteSquirrel();



        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fon.getFon1(), fon.getX1(),0, null);
                g.drawImage(fon.getFon2(), fon.getX2(), 0, null);
                g.drawImage(fon.getFon3(), fon.getX3(), 0, null);


                for (int i=0; i<platform.length; i++){
                    g.drawImage(platform[i].getImage(), platform[i].getX(), platform[i].getY(), null);
                    g.drawImage(squirrel[i].getImage(), squirrel[i].getX(), squirrel[i].getY(), null);
                }

                g.drawImage(hero.getImage(), hero.getX(), hero.getY(), null);
                            }
        };

        panel.setBackground(Color.BLACK);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
    }

    private void creteSquirrel() {
        for (int i=0; i<squirrel.length; i++){
            squirrel[i] = new Squirrel();

            if (i<2){
                squirrel[i].setX(100 + (int)(Math.random() * fon.getFon1().getWidth(null)));
                squirrel[i].setY(200);
            }else {
               squirrel[i].setX(platform[i].getX()+platform[i].getImage().getWidth(null)/2);
               squirrel[i].setY(platform[i].getY());

            }

            squirrel[i].animation();
        }
    }

    private void createPlatform() {
        for (int i=0; i<platform.length; i++){
            platform[i] = new Platform();
            if (i==0) platform[i].setX(100 + (int)(Math.random() * 500));
            else platform[i].setX(platform[i-1].getX() + platform[i-1].getImage().getWidth(null)+100);

            platform[i].setY(80 + (int)(Math.random() * 150));

        }
    }
    public static void resPlatform(){
        for (int i=0; i<platform.length; i++){
            if (platform[i].getX()<-MainFrame.fon.getFon1().getWidth(null)/2){
                if (i==0) platform[i].setX(500 + (int)(Math.random() * 800));
                else platform[i].setX(platform[i-1].getX() + platform[i-1].getImage().getWidth(null)+100);

                platform[i].setY(80 + (int)(Math.random() * 150));

            }

        }
    }
    public static void resSquirrel(){
        for (int i=0; i<squirrel.length; i++){
            if (squirrel[i].getX()<-MainFrame.fon.getFon1().getWidth(null)/2){
                if (i<2){
                    squirrel[i].setX(500 + (int)(Math.random() * fon.getFon1().getWidth(null)));
                    squirrel[i].setY(200);
                }else {
                    squirrel[i].setX(platform[i].getX()+platform[i].getImage().getWidth(null)/2);
                    squirrel[i].setY(platform[i].getY());

                }

            }

        }
    }


}
