package com.mycompany.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Star extends Group {

    public Star(float size, TextureRegion textureRegion){
        setSize(size*5, size);

        final Image[] stars = new Image[5];
        for (int i = 0; i < stars.length; i++) {
            Image star = new Image(textureRegion);
            star.setBounds(size*i, 0, size, size);
            addActor(star);
            stars[i] = star;
        }
    }
}
