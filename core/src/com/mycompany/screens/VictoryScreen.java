package com.mycompany.screens;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mycompany.mygame.*;
import com.mycompany.utils.Utils;

public class VictoryScreen extends CommonScreen {

    public VictoryScreen(MyGdxGame game) {
        super(500, game);
    }

    @Override
    public void show() {
        super.show();
        Parameter parameter = game.getParameter();
        AppPreference.setAllStars(AppPreference.getAllStars()+parameter.stars);
        AppPreference.setContinuationEnabled(false);

        Label title  = new Label("Результат", getSkin(), ResourceManager.label_style_big);
        GridPoint2 timeGame = Utils.getTime(parameter.time);
        Label time = new Label("Время игры -  "+timeGame.y+":"+timeGame.x , getSkin(), ResourceManager.label_style_normal);
        Label error = new Label("Ошибки - "+parameter.error, getSkin(), ResourceManager.label_style_normal);
        Label allError = new Label("Всего ошибок - "+AppPreference.getAllError(), getSkin(), ResourceManager.label_style_normal);
        Label stars= new Label("Звезд за игру - "+parameter.stars, getSkin(), ResourceManager.label_style_normal);
        Label allStars = new Label("Всего звезд - "+AppPreference.getAllStars(), getSkin(), ResourceManager.label_style_normal);

        TextButton menu = new TextButton(Setting.name_menu_button, getSkin(), ResourceManager.button_style);

        table.setBackground(new TextureRegionDrawable(getManager().getTextureRegionAtlas(ResourceManager.background4)));
        table.add(title).row();
        table.add(time).padTop(20).row();
        table.add(error).row();
        table.add(allError).row();
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
