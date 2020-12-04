package com.company.object;

import java.awt.*;

public interface Collision {

    default boolean collision(int x, int y, Image image, int xP, int yP, Image imageP){
        if ( ((x>=xP && x<=xP+imageP.getWidth(null)-50)
                || (x+image.getWidth(null)>=xP && x+image.getWidth(null)<=xP+imageP.getWidth(null)))
                && y+image.getHeight(null)>=yP
                && y+image.getHeight(null)<=yP+imageP.getHeight(null)) return true;
        else return false;
    }
}
