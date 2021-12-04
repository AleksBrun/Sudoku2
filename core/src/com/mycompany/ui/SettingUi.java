package com.mycompany.ui;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mycompany.mygame.AppPreference;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;
import com.mycompany.screens.SettingScreen;

public class SettingUi extends Stage {

    public SettingUi(Viewport viewport, final ResourceManager manager, final SettingScreen settingScreen){
        super(viewport);

        Image background = new Image(manager.getTextureRegion(ResourceManager.background));
        background.setBounds(0, 0, getWidth(), getHeight());
        addActor(background);

        Table table = new Table();
        table.setFillParent(true);
        addActor(table);

        final Label title = new Label(Setting.name_setting_button, manager.getSkin(), ResourceManager.label_style_big);

        final Label levelInfo = new Label("Сложность", manager.getSkin(), ResourceManager.label_style_small);

        final Label labelInfoMusic = new Label("Громкость", manager.getSkin(), ResourceManager.label_style_small);

        final TextButton color_ui = new TextButton(Setting.name_colorUi_button, manager.getSkin(), ResourceManager.button_style);

        final TextButton reset = new TextButton(Setting.name_reset_button, manager.getSkin(), ResourceManager.button_style);
        reset.setVisible(false);

        final TextButton menu = new TextButton(Setting.name_menu_button, manager.getSkin(), ResourceManager.button_style);

        final TextField textField = new TextField("", manager.getSkin(), ResourceManager.textbox_style);
        textField.setAlignment(1);

        final Image color = new Image(manager.getTextureAtlas(ResourceManager.ICON_CIRCLE));

        final Slider slider = new Slider(1, 60, 5, false, manager.getSkin(), ResourceManager.slider_style_hor);
        slider.setValue(AppPreference.getMissingDigits());

        final Slider sliderVolume = new Slider(0, 1, .1f, false, manager.getSkin(), ResourceManager.slider_style_hor);
        sliderVolume.setValue(AppPreference.getMusicVolume());
        
        final Label sliderInfo = new Label(""+AppPreference.getMissingDigits(), manager.getSkin(), ResourceManager.label_style_normal);

        table.add(title).colspan(2).padTop(10).row();
        table.add(color_ui).fillX().padTop(40);
        table.add(color).padTop(40).row();
        table.add(levelInfo).colspan(2).padTop(10).row();
        table.add(slider).padTop(10).fillX();
        table.add(sliderInfo).padTop(10).padLeft(10).fillX().row();
        table.add(labelInfoMusic).colspan(2).padTop(10).row();
        table.add(sliderVolume).colspan(2).padTop(10).fillX().row();
        table.add(menu).colspan(2).top().padTop(10).fillX().row();
        table.add(textField).colspan(2).padTop(10).fillX().row();
        table.add(reset).colspan(2).padTop(10).fillX().row();

        color_ui.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int indexColor = AppPreference.getColorUI();
                indexColor++;
                manager.setUiNew(indexColor);
                color.setDrawable(new TextureRegionDrawable(manager.getTextureAtlas(ResourceManager.ICON_CIRCLE)));
                color_ui.setChecked(false);
                if (indexColor > 4) indexColor = 0;
                AppPreference.setColorUI(indexColor);
            }
        });
        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                settingScreen.dispose();
                settingScreen.getGame().setStateScreen(MyGdxGame.State.MENU);
            }
        });
        reset.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                AppPreference.setContinuationEnabled(false);
                AppPreference.setMissingDigits(25);
                AppPreference.setColorUI(0);
                AppPreference.setAllStars(0);
                sliderInfo.setText(String.valueOf(AppPreference.getMissingDigits()));
                slider.setValue(AppPreference.getMissingDigits());
                reset.setVisible(false);
            }
        });
        slider.addListener(new InputListener() {
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                AppPreference.setMissingDigits((int)slider.getValue());
                sliderInfo.setText(String.valueOf(AppPreference.getMissingDigits()));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });
        sliderVolume.addListener(new InputListener(){
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                AppPreference.setMusicVolume(sliderVolume.getValue());
                manager.getMusic().setVolume(AppPreference.getMusicVolume());
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
}
