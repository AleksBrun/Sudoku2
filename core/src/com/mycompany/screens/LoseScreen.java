package com.mycompany.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mycompany.mygame.AppPreference;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;

public class LoseScreen extends CommonScreen {

    public LoseScreen(MyGdxGame game) {
        super(500, game);
    }

    @Override
    public void show() {
        super.show();
        Label title =  new Label("ВЫ ПРОИГРАЛИ",getSkin(), ResourceManager.label_style_big);

        TextButton menu = new TextButton(Setting.name_menu_button, getSkin(), ResourceManager.button_style);

        table.add(title).expand().row();
        table.add(menu).padBottom(20);

        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setStateScreen(MyGdxGame.State.MENU);
            }
        });
    }

    @Override
    public void hide() {
        super.hide();
        AppPreference.setTimeMinute(0);
        AppPreference.setTimeSecond(0);
        AppPreference.setErrorGame(0);
        AppPreference.setContinuationEnabled(false);
    }
}
