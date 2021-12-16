package com.mycompany.mygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;

public class ResourceManager implements Disposable {

    private final String music = "music/music.mp3";
    private final AssetManager manager;
    private final BitmapFont fontBig;
    private final BitmapFont fontNormal;
    private final BitmapFont fontSmall;
    private Skin skin;
    private final String numbers = "images/numbers.pack";
    private final String textureAtlas =  "atlas/Texture.atlas";

    public static final String grid = "grid1";
    public static final String mark = "mark";
    public static final String mark1 = "mark1";
    public static final String mark2 = "mark4";
    public static final String mark3 = "mark3";
    public static final String mark4 = "mark2";
    public static final String love = "love";
    public static final String background1 = "background1";
    public static final String background2 = "background2";
    public static final String background3 = "background3";
    public static final String background4 = "background4";
    public static final String background5 = "background5";
    public static final String coin = "Coin";
    public static final String crystal = "Crystal";
    public static final String paper = "Paper";
    public static final String skull = "Skull";
    public static final String minerals = "Minerals";
    public static final String star = "Star";
    
    private String name_ui;
    private final String ui_blue  = "skin/ui-blue.atlas";
    private final String ui_green  = "skin/ui-green.atlas";
    private final String ui_red  = "skin/ui-red.atlas";
    private final String ui_orange  = "skin/ui-orange.atlas";
    private final String ui_gray = "skin/ui-gray.atlas";
    private final String ui_white = "skin/ui-white.atlas";
    private final String ui_yellow = "skin/ui-yellow.atlas";

    public final static String ICON_STAR = "icon_star";
    public final static String ICON_BACK = "icon_back";
    public final static String ICON_PAUSE = "icon_pause";
    public final static String ICON_CROSS = "icon_cross";
    public final static String ICON_SOUND_ON = "icon_sound_on";
    public final static String ICON_SOUND_OFF = "icon_sound_off";
    
    public final static String white_musicOff = "white_musicOff";
    public final static String white_musicOn = "white_musicOn";
    public final static String black_musicOn = "black_musicOn";
    public final static String black_MusicOff = "black_MusicOff";

    public final static String button_style = "buttonStyle";
    public final static String label_style_normal = "labelStyle_normal";
    public final static String label_style_big = "labelStyle_big";
    public final static String label_style_small = "labelStyle_small";
    public final static String window_style = "window_01";
    public final static String image_button_music = "imageButtonStyle|";
    public final static String image_button_pause = "imageButtonPause";
    public final static String image_button_home = "imageButtonHome";
    public final static String image_button_setting = "imageButtonSetting";
    public final static String slider_style_hor =  "slider_hor";
    public final static String textbox_style = "textbox_01";

    public final String BUTTON_DOWN = "button_05";
    public final String BUTTON_UP = "button_06";

    private final TextureRegion[] cups = new TextureRegion[3];


    public ResourceManager() {
        manager = new AssetManager();
        fontSmall = new BitmapFont(Gdx.files.internal("font/font-white-small.fnt"));
        fontNormal = new BitmapFont(Gdx.files.internal("font/font-white-normal.fnt"));
        fontBig = new BitmapFont(Gdx.files.internal("font/font-white-big.fnt"));
        loadTextureAtlas();
        loadMusic();
        manager.finishLoading();
        getCups();
        setUiNew();
    }

    private void loadMusic(){
        manager.load(music, Music.class);
    }

    private void loadTextureAtlas(){
        manager.load(numbers, TextureAtlas.class);
        manager.load(ui_blue, TextureAtlas.class);
        manager.load(ui_red, TextureAtlas.class);
        manager.load(ui_green, TextureAtlas.class);
        manager.load(ui_orange, TextureAtlas.class);
        manager.load(ui_gray, TextureAtlas.class);
        manager.load(ui_white, TextureAtlas.class);
        manager.load(ui_yellow, TextureAtlas.class);
        manager.load(textureAtlas, TextureAtlas.class);

    }

    public TextureRegion getTextureRegionAtlas(String nameTexture){
        return new TextureRegion(manager.get(textureAtlas, TextureAtlas.class).findRegion(nameTexture));
    }

    public TextureRegion getTextureAtlas(String nameTexture){
        return new TextureRegion(manager.get(name_ui, TextureAtlas.class).findRegion(nameTexture));
    }

    public TextureRegion getNumber(int _number){
        return new TextureRegion(manager.get(numbers, TextureAtlas.class).findRegion("number"+_number));
    }

    public TextureRegion getCup(int index){
        return cups[index];
    }

    private void getCups(){
        cups[0] = manager.get(textureAtlas, TextureAtlas.class).findRegion("cup1");
        cups[1] = manager.get(textureAtlas, TextureAtlas.class).findRegion("cup2");
        cups[2] = manager.get(textureAtlas, TextureAtlas.class).findRegion("cup3");
    }

    public void setUiNew(){
        String[] tmp = {ui_white, ui_blue, ui_green, ui_red, ui_orange, ui_gray, ui_yellow};
        name_ui = tmp[AppPreference.getColorUI()];
        createSkin(AppPreference.getColorFont());
    }

    private void createSkin(int index_color_font){
        skin = new Skin();

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = new TextureRegionDrawable(manager.get(name_ui, TextureAtlas.class).findRegion(BUTTON_UP));
        style.down = new TextureRegionDrawable(manager.get(name_ui, TextureAtlas.class).findRegion(BUTTON_DOWN));
        style.font = fontNormal;
        Color color;
        if (index_color_font == 1) {
            color = Color.WHITE;
        } else {
            color = Color.BLACK;
        }
        style.fontColor = color;
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
        
        ImageButton.ImageButtonStyle imageButtonMusic = new ImageButton.ImageButtonStyle();
        imageButtonMusic.up = new TextureRegionDrawable(getTextureRegionAtlas("black_musicOn"));
        imageButtonMusic.down = new TextureRegionDrawable(getTextureRegionAtlas("white_musicOn"));
        imageButtonMusic.checked = new TextureRegionDrawable(getTextureRegionAtlas("black_MusicOff"));
        skin.add(image_button_music, imageButtonMusic, ImageButton.ImageButtonStyle.class);

        ImageButton.ImageButtonStyle imageButtonPause = new ImageButton.ImageButtonStyle();
        imageButtonPause.up = new TextureRegionDrawable(getTextureRegionAtlas("black_pause"));
        imageButtonPause.down = new TextureRegionDrawable(getTextureRegionAtlas("white_pause"));
        skin.add(image_button_pause, imageButtonPause, ImageButton.ImageButtonStyle.class);

        ImageButton.ImageButtonStyle imageButtonSetting = new ImageButton.ImageButtonStyle();
        imageButtonSetting.up = new TextureRegionDrawable(getTextureRegionAtlas("black_config"));
        imageButtonSetting.down = new TextureRegionDrawable(getTextureRegionAtlas("white_config"));
        skin.add(image_button_setting, imageButtonSetting, ImageButton.ImageButtonStyle.class);

        ImageButton.ImageButtonStyle imageButtonHome = new ImageButton.ImageButtonStyle();
        imageButtonHome.up = new TextureRegionDrawable(getTextureRegionAtlas("black_home"));
        imageButtonHome.down = new TextureRegionDrawable(getTextureRegionAtlas("white_home"));
        skin.add(image_button_home, imageButtonHome, ImageButton.ImageButtonStyle.class);
    }

    public Skin getSkin(){
        return this.skin;
    }

    public Music getMusic(){
        return manager.get(music, Music.class);
    }
    
    public TextureRegion getBackground(String nameBackground){
        int height = manager.get(textureAtlas, TextureAtlas.class).findRegion(nameBackground).getRegionHeight();
        return new TextureRegion(manager.get(textureAtlas,TextureAtlas.class).findRegion(nameBackground), 200, 0, (int)Setting.getWidth(height), height);
        
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
