package com.mycompany.models;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Star extends Group {
    
    private final Image[] stars;
    public Star(float size, TextureRegion texture){
        setSize(size*6, size);
        
        stars = new Image[6];
        for (int i = 0; i < stars.length; i++) {
            Image tmp = new Image(texture);
            tmp.setBounds(size * i, 0, size, size);
            addActor(tmp);
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
