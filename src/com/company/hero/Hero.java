package com.company.hero;

import com.company.frame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Hero implements KeyListener {
    private int x, y=200;
    private Image image = new ImageIcon("image/persr0.png").getImage();
    private Image imageRight[] = new Image[9];
    private Image imageLeft[] = new Image[9];
    private Image imageJumpRight[] = new Image[8];
    private Image imageJumpLeft[] = new Image[8];
    private Image imageJump[] = imageJumpRight;
    private Boolean pressD  = false;



    private Image imageX[] = imageRight;

    private int directionX = 1;

    public Hero() {
        MainFrame.frame.addKeyListener(this);
        for (int i =0; i<imageRight.length; i++){
            imageRight[i] = new ImageIcon("image/persr"+i+".png").getImage();
            imageLeft[i] = new ImageIcon("image/persl"+i+".png").getImage();
        }
        for(int i =0; i<imageJumpRight.length; i++){
            imageJumpRight[i] = new ImageIcon("image/persr"+i+".png").getImage();
            imageJumpLeft[i] = new ImageIcon("image/persl"+i+".png").getImage();
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_D:
                directionX =1;
                imageX = imageRight;
                imageJump = imageJumpRight;
                if (timerAnim == null || !timerAnim.isRunning()) move();
                pressD = true;

                MainFrame.resPlatform();
                MainFrame.resSquirrel();
                break;
            case KeyEvent.VK_A:
                imageX = imageLeft;
                directionX=-1;
                imageJump = imageJumpLeft;
                if (timerAnim == null || !timerAnim.isRunning()) move();
                pressD = true;

                MainFrame.resPlatform();
                MainFrame.resSquirrel();
                break;
            case KeyEvent.VK_SPACE:
                if ((timerJUp == null && timerJDown ==null) || (!timerJUp.isRunning() && !timerJDown.isRunning()))  jumpUp();

                break;
        }
        MainFrame.panel.repaint();
    }

    Timer timerJUp = null;
    private int yNow;
    private int animJumpUp = 0;
    private void jumpUp() {
        yNow = y;
        timerJUp = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (y>yNow-80){
                    y-=2;
                    if (pressD == true) fonMotion();

                    if (animJumpUp<imageJump.length-1) {
                        animJumpUp++;
                        image = imageJump[animJumpUp];
                    }
                    else {
                        animJumpUp = 0;
                        image = imageJump[animJumpUp];
                    }
                }else {
                    animJumpUp =0;
                    timerJUp.stop();
                    jumpDown();
                }
                MainFrame.panel.repaint();
            }
        });
        timerJUp.start();
    }

    Timer timerJDown =null;
    private int yMin = 200;
    private int animJumpDown = 0;

    private void jumpDown(){
        timerJDown = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (y<yMin){
                    y+=2;
                    if (animJumpDown<imageJump.length-1){
                        animJumpDown++;
                        image = imageJump[animJumpDown];
                    }else {
                        animJumpDown =0;
                        image = imageJump[animJumpDown];
                    }
                }else {
                    animJumpDown =0;
                    timerJDown.stop();
                }

                collision();

                MainFrame.panel.repaint();


            }
        });
        timerJDown.start();
    }


    Timer timerAnim = null;
    int animCheck =0;
    void move(){

        timerAnim = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (animCheck<imageX.length-1) animCheck++;
                else {
                    animCheck = 0;
                    timerAnim.stop();
                }
                image = imageX[animCheck];


                fonMotion();
                drop();

                MainFrame.panel.repaint();
            }
        });
        timerAnim.start();
    }

    void fonMotion(){
        if (x<200 && x+2*directionX>0) {
            if (pressD==true) x+=directionX;
            else x+=2*directionX;
        }else{
            if (x<=2) return;
            else {
                MainFrame.fon.move(directionX, pressD);
                for (int i=0; i<MainFrame.platform.length; i++) {
                    MainFrame.platform[i].move(directionX, pressD);
                }
                for (int i=0; i<MainFrame.squirrel.length; i++){
                    MainFrame.squirrel[i].move(directionX, pressD);
                }

            }
        }
    }

    void collision(){

        for (int i=0; i<MainFrame.platform.length; i++){
            if (MainFrame.platform[i].collision(x-20,y,image,
                    MainFrame.platform[i].getX(), MainFrame.platform[i].getY(), MainFrame.platform[i].getImage())) {
                timerJDown.stop();
                y=MainFrame.platform[i].getY() - image.getHeight(null)+15;

            }
        }

    }

    private void drop(){
        for (int i=0; i<MainFrame.platform.length; i++){
            if (!MainFrame.platform[i].collision(x-20,y,image,
                    MainFrame.platform[i].getX(), MainFrame.platform[i].getY(), MainFrame.platform[i].getImage()) && y!=yMin
                && (timerJDown==null || !timerJDown.isRunning()) && (timerJUp==null || !timerJUp.isRunning())) jumpDown();


        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_A) pressD = false;
    }
}
