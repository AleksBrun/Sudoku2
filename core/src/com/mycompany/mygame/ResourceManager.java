package com.mycompany.mygame;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;

public class ResourceManager implements Disposable {

    private final AssetManager manager;
    private final BitmapFont font;
    private Skin skin;
    private final String numbers = "images/numbers.pack";
    public static final String grid1 = "images/grid2.png";
    public static final String fon_menu = "images/fon_menu.png";
    public static final String mark = "images/mark.png";
    public static final String mark1 = "images/mark1.png";
    public static final String mark2 = "images/mark2.png";
    public static final String mark3 = "images/mark3.png";
	private final String skinName = "uiskin/uiskin.json";
    private String name_ui;
    private final String ui_blue  = "skin/ui-blue.atlas";
    private final String ui_green  = "skin/ui-green.atlas";
    private final String ui_red  = "skin/ui-red.atlas";
    private final String ui_orange  = "skin/ui-orange.atlas";
    private final String ui_gray = "skin/ui-gray.atlas";
    private final String ui_white = "skin/ui-white.atlas";

    public final static String ICON_STAR = "icon_star";
    public final static String ICON_BACK = "icon_back";
    public final static String ICON_PAUSE = "icon_pause";
    public final static String ICON_CROSS = "icon_cross";
    public final static String ICON_PLAY = "icon_play";


    public ResourceManager() {
        manager = new AssetManager();
        font = new BitmapFont(new FileHandle("uiskin/font-white-normal.fnt"));
        loadTextureAtlas();
        loadTexture();
        loadSkin();
        manager.finishLoading();
        setUiNew(5);


    }

    private void loadSkin(){
        manager.load(skinName, Skin.class);
    }

    private void loadTexture(){
        manager.load(grid1, Texture.class);
        manager.load(mark, Texture.class);
        manager.load(mark1, Texture.class);
        manager.load(mark2, Texture.class);
        manager.load(mark3, Texture.class);
        manager.load(fon_menu, Texture.class);
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

    public void setUiNew(int index){
        String[] tmp = {ui_blue, ui_green, ui_red, ui_orange, ui_white, ui_gray};
        name_ui = tmp[index];
        createSkin();
    }

    private void createSkin(){
        skin = manager.get(skinName, Skin.class);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = new TextureRegionDrawable(manager.get(name_ui, TextureAtlas.class).findRegion("button_06"));
        style.down = new TextureRegionDrawable(manager.get(name_ui, TextureAtlas.class).findRegion("button_05"));
        style.font = font;
        skin.add(Setting.button_style, style, TextButton.TextButtonStyle.class);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.BLACK;
        skin.add(Setting.label_style, labelStyle, Label.LabelStyle.class);

        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.background = new TextureRegionDrawable(manager.get(name_ui, TextureAtlas.class).findRegion("window_03"));
        windowStyle.titleFont = font;
        skin.add(Setting.window_style, windowStyle, Window.WindowStyle.class);
    }

    public Skin getSkin(){
        return this.skin;
    }


    @Override
    public void dispose() {
        manager.dispose();
        font.dispose();
    }
}
