package com.mycompany.mygame;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

public class ResourceManager implements Disposable {

    private final AssetManager manager;
    private final String numbers = "images/numbers.pack";
    private final String grid1 = "images/grid.png";
    private final String mark = "images/mark.png";
    private final String mark1 = "images/mark1.png";
	private final String skin = "uiskin/uiskin.json";
    private final String ui_orange  = "skin/ui-orange.atlas";

    public ResourceManager() {
        manager = new AssetManager();
        loadTextureAtlas();
        loadTexture();
        loadSkin();
        manager.finishLoading();
    }

    private void loadTexture(){
        manager.load(grid1, Texture.class);
        manager.load(mark, Texture.class);
        manager.load(mark1, Texture.class);
    }

    private void loadSkin(){
        manager.load(skin, Skin.class);
    }

    private void loadTextureAtlas(){
        manager.load(numbers, TextureAtlas.class);
        manager.load(ui_orange, TextureAtlas.class);
    }
    public TextureRegion getIconStar(){
        return new TextureRegion(manager.get(ui_orange, TextureAtlas.class).findRegion("icon_star"));
    }

    public TextureRegion getIconBack(){
        return new TextureRegion(manager.get(ui_orange, TextureAtlas.class).findRegion("icon_back"));
    }
    public TextureRegion getIconCircle(){
        return new TextureRegion(manager.get(ui_orange, TextureAtlas.class).findRegion("icon_circle"));
    }
    public TextureRegion getIconPause(){
        return new TextureRegion(manager.get(ui_orange, TextureAtlas.class).findRegion("icon_pause"));
    }
    public TextureRegion getIconPlay(){
        return new TextureRegion(manager.get(ui_orange, TextureAtlas.class).findRegion("icon_play"));
    }
    public TextureRegion getIconCross(){
        return new TextureRegion(manager.get(ui_orange, TextureAtlas.class).findRegion("icon_cross"));
    }
    public TextureRegion getNumber(int _number){
        return new TextureRegion(manager.get(numbers, TextureAtlas.class).findRegion("number"+_number));
    }

    public TextureRegion getGrid(){
        return new TextureRegion(manager.get(grid1, Texture.class));
    }

    public TextureRegion getMark(){
        return new TextureRegion(manager.get(mark, Texture.class));
    }

    public TextureRegion getMark1(){
        return new TextureRegion(manager.get(mark1, Texture.class));
    }

    public Skin getSkin(){
        return manager.get(skin, Skin.class);
    }

    @Override
    public void dispose() {
        manager.dispose();
    }
}
