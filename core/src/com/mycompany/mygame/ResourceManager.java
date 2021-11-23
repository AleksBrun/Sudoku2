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
    public static final String grid1 = "images/grid.png";
    public static final String mark = "images/mark.png";
    public static final String mark1 = "images/mark1.png";
    public static final String mark2 = "images/mark2.png";
    public static final String mark3 = "images/mark3.png";
	private final String skin = "uiskin/uiskin.json";
    private String name_ui;
    private final String ui_blue  = "skin/ui-blue.atlas";
    private final String ui_green  = "skin/ui-green.atlas";
    private final String ui_red  = "skin/ui-red.atlas";
    private final String ui_orange  = "skin/ui-orange.atlas";
    private final String ui_gray = "skin/ui-gray.atlas";
    private final String ui_white = "skin/ui-white.atlas";

    public final static String ICON_STAR = "icon_star";
    public final static String ICON_BACK = "icon_back";
    public final static String ICON_CIRCLE = "icon_circle";
    public final static String ICON_PAUSE = "icon_pause";
    public final static String ICON_CROSS = "icon_cross";
    public final static String ICON_PLAY = "icon_play";


    public ResourceManager() {
        manager = new AssetManager();
        loadTextureAtlas();
        loadTexture();
        loadSkin();
        manager.finishLoading();
        setUiNew(AppPreference.getColorUI());
    }

    private void loadTexture(){
        manager.load(grid1, Texture.class);
        manager.load(mark, Texture.class);
        manager.load(mark1, Texture.class);
        manager.load(mark2, Texture.class);
        manager.load(mark3, Texture.class);
    }

    private void loadSkin(){
        manager.load(skin, Skin.class);
    }

    private void loadTextureAtlas(){
        manager.load(numbers, TextureAtlas.class);
        manager.load(ui_blue, TextureAtlas.class);
        manager.load(ui_red, TextureAtlas.class);
        manager.load(ui_green, TextureAtlas.class);
        manager.load(ui_orange, TextureAtlas.class);
        manager.load(ui_gray, TextureAtlas.class);
        manager.load(ui_white, TextureAtlas.class);

    }

    public TextureRegion getTextureRegion(String nameTexture){
        return new TextureRegion(manager.get(nameTexture, Texture.class));
    }

    public TextureRegion getIconTexture(String nameIcon){
        return new TextureRegion(manager.get(name_ui, TextureAtlas.class).findRegion(nameIcon));
    }

    public TextureRegion getNumber(int _number){
        return new TextureRegion(manager.get(numbers, TextureAtlas.class).findRegion("number"+_number));
    }

    public String setUiNew(int index){
        String[] tmp = {ui_blue, ui_green, ui_red, ui_orange, ui_white, ui_gray};
        return name_ui = tmp[index];
    }

    public Skin getSkin(){
        return manager.get(skin, Skin.class);
    }

    @Override
    public void dispose() {
        manager.dispose();
    }
}
