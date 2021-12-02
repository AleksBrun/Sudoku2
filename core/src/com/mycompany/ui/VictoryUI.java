package com.mycompany.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mycompany.mygame.AppPreference;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;
import com.mycompany.screens.VictoryScreen;

public class VictoryUI extends Stage {

    public VictoryUI(Viewport viewport, ResourceManager manager, final VictoryScreen victoryScreen) {
        super(viewport);

        Table table = new Table();
        table.setFillParent(true);
        table.setBackground(new TextureRegionDrawable(manager.getTextureRegion(ResourceManager.background)));
        addActor(table);

        Label title  = new Label("Результат", manager.getSkin(), ResourceManager.label_style_big);
        Label time = new Label("Время игры -  "+ AppPreference.getTimeMinute()+":"+AppPreference.getTimeSecond(), manager.getSkin(), ResourceManager.label_style_normal);
        Label error = new Label("Ошибки - "+AppPreference.getErrorGame(), manager.getSkin(), ResourceManager.label_style_normal);
        Label stars= new Label("Звезд за игру - "+AppPreference.getDifficultyLevel(), manager.getSkin(), ResourceManager.label_style_normal);
        Label allStars = new Label("Всего звезд - "+AppPreference.getAllStars(), manager.getSkin(), ResourceManager.label_style_normal);

        TextButton menu = new TextButton(Setting.name_menu_button, manager.getSkin(), ResourceManager.button_style);

        table.add(title).row();
        table.add(time).padTop(20).row();
        table.add(error).row();
        table.add(stars).row();
        table.add(allStars).row();
        table.add(menu).padTop(20);

        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                victoryScreen.dispose();
                victoryScreen.getGame().setStateScreen(MyGdxGame.State.MENU);
            }
        });
    }
}
