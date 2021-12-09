package com.mycompany.screens;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mycompany.mygame.AppPreference;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;
import com.mycompany.utils.TimeUtils;

public class StatisticsScreen extends CommonScreen {

    public StatisticsScreen(MyGdxGame game) {
        super(500, game);
    }

    @Override
    public void show() {
        super.show();

        Label title = new Label("Статистика", getSkin(), ResourceManager.label_style_big);

        Label allStarLabel = new Label("Получено звезд: "+ AppPreference.getAllStars(), getSkin(), ResourceManager.label_style_normal);

        GridPoint2 allTime = TimeUtils.getTime(AppPreference.getAllTime());
        Label allTimeLabel = new Label("Общее время игры: "+allTime.x+":"+allTime.y, getSkin(), ResourceManager.label_style_normal);

        Label allErrorLabel = new Label("Сделано ошибок: ", getSkin(), ResourceManager.label_style_normal);

        TextButton menu = new TextButton(Setting.name_menu_button, getSkin(), ResourceManager.button_style);

        table.add(title).row();
        table.add(allStarLabel).padTop(20).row();
        table.add(allTimeLabel).padTop(10).row();
        table.add(allErrorLabel).padTop(10).row();
        table.add(menu).padTop(10);

        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                dispose();
                game.setStateScreen(MyGdxGame.State.MENU);
            }
        });
    }
}
