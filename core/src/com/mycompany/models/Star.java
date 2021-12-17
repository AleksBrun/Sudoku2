package com.mycompany.models;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Star extends CommonGroup {
    
    private final Image[] stars;
    public Star(float sizeCell, TextureRegion texture){
        super(sizeCell*6, sizeCell);
        
        stars = new Image[6];
        for (int i = 0; i < stars.length; i++) {
            Image tmp = new Image(texture);
            tmp.setSize(sizeCell, sizeCell);
            //tmp.setBounds(sizeCell * i, 0, sizeCell, sizeCell);
            getTable().add(tmp);
            stars[i] = tmp;
        }
    }
    
    public void setStars(int _star){
        for (Image star:stars){
            star.setVisible(false);
        }
        for (int i = 0; i < _star; i++) {
            stars[i].setVisible(true);
        }
    }
    
    
}
