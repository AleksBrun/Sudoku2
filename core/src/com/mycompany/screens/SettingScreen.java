package com.mycompany.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mycompany.mygame.AppPreference;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;
import com.mycompany.utils.XMLparse;

public class SettingScreen extends CommonScreen {

    public SettingScreen(MyGdxGame game) {
        super(500, game);
    }

    @Override
    public void show() {
        super.show();

        final Label title = new Label(Setting.name_setting_button, getSkin(), ResourceManager.label_style_big);

        final Label labelInfoMusic = new Label("Громкость", getSkin(), ResourceManager.label_style_small);

        final TextButton color_ui = new TextButton(Setting.name_colorUi_button, getSkin(), ResourceManager.button_style);

        final TextButton color_font = new TextButton(Setting.name_colorFont_button, getSkin(), ResourceManager.button_style);

        final TextButton color_topic = new TextButton("     Кнопки ", getSkin(), ResourceManager.button_style);

        final TextButton reset = new TextButton(Setting.name_reset_button, getSkin(), ResourceManager.button_style);
        reset.setVisible(false);

        final TextButton menu = new TextButton(Setting.name_menu_button, getSkin(), ResourceManager.button_style);

        final TextField textField = new TextField("", getSkin(), ResourceManager.textbox_style);
        textField.setAlignment(1);

        final Slider sliderVolume = new Slider(0, 1, .1f, false, getSkin(), ResourceManager.slider_style_hor);
        sliderVolume.setValue(AppPreference.getMusicVolume());



        table.setBackground(new TextureRegionDrawable(getManager().getTextureRegionAtlas(ResourceManager.background1)));
        table.add(title).colspan(2).padTop(10);
        table.row();
        table.add(color_ui).colspan(2).fillX().padTop(40);
        table.row();
        table.add(color_font).colspan(2).padTop(10).fillX();
        table.row();
        table.add(color_topic).colspan(2).padTop(10).fillX();
        table.row();
        table.add(labelInfoMusic).colspan(2).padTop(10);
        table.row();
        table.add(sliderVolume).colspan(2).padTop(10).fillX();
        table.row();
        table.add(menu).colspan(2).top().padTop(10).fillX();
        table.row();
        table.add(textField).colspan(2).padTop(10).fillX();
        table.row();
        table.add(reset).colspan(2).padTop(10).fillX();

        color_ui.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int indexColor = AppPreference.getColorUI();
                indexColor++;
                if (indexColor > 6) {
                    indexColor = 0;
                }
                AppPreference.setColorUI(indexColor);
                getManager().setUiNew();
                dispose();
                game.setStateScreen(MyGdxGame.State.SETTING);
            }
        });
        color_font.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int index_color = AppPreference.getColorFont();
                if (index_color == 0){
                    AppPreference.setColorFont(1);
                } else {
                    AppPreference.setColorFont(0);
                }
                getManager().setUiNew();
                dispose();
                game.setStateScreen(MyGdxGame.State.SETTING);
                
            }
        });
        color_topic.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int indexColor = AppPreference.getColorTopic();
                indexColor++;
                if (indexColor > 3) {
                    indexColor = 0;
                }
                AppPreference.setColorTopic(indexColor);
            }
        });
        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setStateScreen(MyGdxGame.State.MENU);
            }
        });
        reset.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                AppPreference.setContinuationEnabled(false);
                AppPreference.setMissingDigits(40);
                AppPreference.setDifficultyLevel(1);
                AppPreference.setColorUI(Setting.start_color);
                AppPreference.setColorFont(Setting.start_color_font);
                AppPreference.setAllStars(0);
                AppPreference.setAllError(0);
                AppPreference.setAllTime(0);
                reset.setVisible(false);
                getManager().setUiNew();
                XMLparse.delete();
                Gdx.app.exit();
            }
        });

        sliderVolume.addListener(new InputListener(){
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                AppPreference.setMusicVolume(sliderVolume.getValue());
                getManager().getMusic().setVolume(AppPreference.getMusicVolume());
                AppPreference.setMusicEnabled(sliderVolume.getValue() != 0);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        textField.addListener(new InputListener(){

            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.ENTER && textField.getText().equals("2018")){
                    reset.setVisible(true);
                }
                return false;
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.draw();
    }
    
    
}
