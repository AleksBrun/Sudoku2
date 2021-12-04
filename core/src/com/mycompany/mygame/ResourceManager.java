package com.mycompany.mygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;

public class ResourceManager implements Disposable {

    private final AssetManager manager;
    private final BitmapFont fontBig;
    private final BitmapFont fontNormal;
    private final BitmapFont fontSmall;
    private Skin skin;
    private final String numbers = "images/numbers.pack";
    public static final String grid = "images/grid2.png";
    public static final String fon_menu = "images/fon_menu.png";
    public static final String mark = "images/mark.png";
    public static final String mark1 = "images/mark1.png";
    public static final String mark2 = "images/mark4.png";
    public static final String mark3 = "images/mark3.png";
    public static final String heart = "images/heart.jpg";
    public static final String background = "images/background.jpg";
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

    public final static String button_style = "buttonStyle";
    public final static String label_style_normal = "labelStyle_normal";
    public final static String label_style_big = "labelStyle_big";
    public final static String label_style_small = "labelStyle_small";
    public final static String window_style = "window_01";

    public final static String slider_style_hor =  "slider_hor";

    public final static String textbox_style = "textbox_01";

    public final String BUTTON_DOWN = "button_05";
    public final String BUTTON_UP = "button_06";


    public ResourceManager() {
        manager = new AssetManager();
        fontSmall = new BitmapFont(Gdx.files.internal("font/font-white-small.fnt"));
        fontNormal = new BitmapFont(Gdx.files.internal("font/font-white-normal.fnt"));
        fontBig = new BitmapFont(Gdx.files.internal("font/font-white-big.fnt"));
        loadTextureAtlas();
        loadTexture();
        manager.finishLoading();
        setUiNew(5);
    }

    private void loadTexture(){
        manager.load(grid, Texture.class);
        manager.load(mark, Texture.class);
        manager.load(mark1, Texture.class);
        manager.load(mark2, Texture.class);
        manager.load(mark3, Texture.class);
        manager.load(fon_menu, Texture.class);
        manager.load(heart, Texture.class);
        manager.load(background, Texture.class);
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

    public TextureRegion getTextureAtlas(String nameTexture){
        return new TextureRegion(manager.get(name_ui, TextureAtlas.class).findRegion(nameTexture));
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
        skin = new Skin();

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = new TextureRegionDrawable(manager.get(name_ui, TextureAtlas.class).findRegion(BUTTON_UP));
        style.down = new TextureRegionDrawable(manager.get(name_ui, TextureAtlas.class).findRegion(BUTTON_DOWN));
        style.font = fontNormal;
        skin.add(button_style, style, TextButton.TextButtonStyle.class);

        Label.LabelStyle labelStyle_normal = new Label.LabelStyle();
        labelStyle_normal.font = fontNormal;
        labelStyle_normal.fontColor = Color.BLACK;
        skin.add(label_style_normal, labelStyle_normal, Label.LabelStyle.class);

        Label.LabelStyle labelStyle_big = new Label.LabelStyle();
        labelStyle_big.font = fontBig;
        labelStyle_big.fontColor = Color.BLACK;
        skin.add(label_style_big, labelStyle_big, Label.LabelStyle.class);

        Label.LabelStyle labelStyle_small = new Label.LabelStyle();
        labelStyle_small.font = fontSmall;
        labelStyle_small.fontColor = Color.BLACK;
        skin.add(label_style_small, labelStyle_small, Label.LabelStyle.class);

        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
        sliderStyle.background = new TextureRegionDrawable(manager.get(name_ui, TextureAtlas.class).findRegion("slider_back_hor"));
        sliderStyle.knob = new TextureRegionDrawable(manager.get(name_ui, TextureAtlas.class).findRegion("knob_05"));
        skin.add(slider_style_hor, sliderStyle, Slider.SliderStyle.class);

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.background = new TextureRegionDrawable(manager.get(name_ui, TextureAtlas.class).findRegion("textbox_01"));
        textFieldStyle.cursor = new TextureRegionDrawable(manager.get(name_ui, TextureAtlas.class).findRegion("textbox_cursor_02"));
        textFieldStyle.font = fontNormal;
        textFieldStyle.fontColor = Color.BLACK;
        skin.add(textbox_style, textFieldStyle, TextField.TextFieldStyle.class);

        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.background = new TextureRegionDrawable(manager.get(name_ui, TextureAtlas.class).findRegion("window_01"));
        windowStyle.titleFont = fontSmall;
        skin.add(window_style, windowStyle, Window.WindowStyle.class);
    }

    public Skin getSkin(){
        return this.skin;
    }


    @Override
    public void dispose() {
        fontNormal.dispose();
        fontBig.dispose();
        fontSmall.dispose();
        skin.dispose();
        manager.dispose();
    }
}
