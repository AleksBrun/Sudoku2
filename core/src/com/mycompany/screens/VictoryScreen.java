package com.mycompany.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mycompany.mygame.AppPreference;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;

public class VictoryScreen extends CommonScreen {

    public VictoryScreen(MyGdxGame game) {
        super(500, game);
    }

    @Override
    public void show() {
        super.show();
        AppPreference.setAllStars(AppPreference.getAllStars()+AppPreference.getDifficultyLevel());
        AppPreference.setContinuationEnabled(false);

        Label title  = new Label("Результат", getSkin(), ResourceManager.label_style_big);
        Label time = new Label("Время игры -  "+ AppPreference.getTimeMinute()+":"+AppPreference.getTimeSecond(), getSkin(), ResourceManager.label_style_normal);
        Label error = new Label("Ошибки - "+AppPreference.getErrorGame(), getSkin(), ResourceManager.label_style_normal);
        Label stars= new Label("Звезд за игру - "+AppPreference.getDifficultyLevel(), getSkin(), ResourceManager.label_style_normal);
        Label allStars = new Label("Всего звезд - "+AppPreference.getAllStars(), getSkin(), ResourceManager.label_style_normal);

        TextButton menu = new TextButton(Setting.name_menu_button, getSkin(), ResourceManager.button_style);

        table.add(title).row();
        table.add(time).padTop(20).row();
        table.add(error).row();
        table.add(stars).row();
        table.add(allStars).row();
        table.add(menu).padTop(20);

        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setStateScreen(MyGdxGame.State.MENU);
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.draw();
    }
    
    
}
