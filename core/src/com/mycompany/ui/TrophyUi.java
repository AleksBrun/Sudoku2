package com.mycompany.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mycompany.models.Reward;
import com.mycompany.mygame.AppPreference;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;
import com.mycompany.screens.TrophyScreen;

public class TrophyUi extends Stage {

    private final Reward reward;

    public TrophyUi(Viewport viewport, ResourceManager manager, final TrophyScreen trophyScreen) {
        super(viewport);

        AppPreference.setAllStars(99);

        Table table = new Table();
        table.setFillParent(true);
        table.setBackground(new TextureRegionDrawable(manager.getTextureRegion(ResourceManager.background)));
        addActor(table);

        Label title = new Label("Награды", manager.getSkin(), ResourceManager.label_style_big);

        reward = new Reward(0, 0, getWidth()-80, getWidth(), manager);

        TextButton menu = new TextButton(Setting.name_menu_button, manager.getSkin(), ResourceManager.button_style);


        table.top();
        table.add(title).padTop(20).row();
        table.add(reward).expand().top().padTop(10).row();
        table.add(menu).bottom().padBottom(20);

        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                trophyScreen.dispose();
                trophyScreen.getGame().setStateScreen(MyGdxGame.State.MENU);
            }
        });

        String[] score = {"1", "5", "10", "20", "50", "100", "250", "500", "1000"};
        for (int i = 0; i < 9; i++) {
            if (AppPreference.getAllStars() >= Integer.parseInt(score[i])){
                setTextureCell(i, manager.getCup(0));
            }

        }
        System.out.print(AppPreference.getAllStars());
        for (int i = 0; i < 9; i++) {
            setLabelCell(i, score[i]);
        }
    }

    public void setTextureCell(int cell, TextureRegion textureRegion){
        reward.getCells()[cell].setDrawable(new TextureRegionDrawable(textureRegion));
    }

    public void setLabelCell (int cell, String text){
        reward.getNames()[cell].setText(text);
    }
}
