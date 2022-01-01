package com.mycompany.models;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GroupImage extends CommonGroup {
    
    private final Image[] stars;
    public GroupImage(int quantityImage, float sizeCell, TextureRegion texture){
        super(sizeCell*quantityImage, sizeCell);
        
        stars = new Image[quantityImage];
        for (int i = 0; i < stars.length; i++) {
            Image tmp = new Image(texture);
            tmp.setSize(sizeCell, sizeCell);
            getTable().add(tmp);
            stars[i] = tmp;
        }
    }
    
    public void setQuantity(int _quantity){
        for (Image star:stars){
            star.setVisible(false);
        }
        for (int i = 0; i < _quantity; i++) {
            stars[i].setVisible(true);
        }
    }
    
    
}
