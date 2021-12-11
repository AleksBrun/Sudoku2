package com.mycompany.screens;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mycompany.models.Reward;
import com.mycompany.mygame.AppPreference;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;

public class TrophyScreen extends CommonScreen {

    private Reward reward;

    public TrophyScreen(MyGdxGame game) {
        super(500, game);
    }

    @Override
    public void show() {
        super.show();

        Label title = new Label("Награды",getSkin(), ResourceManager.label_style_big);

        reward = new Reward(0, 0, stage.getWidth()-80, stage.getWidth(), getManager());

        TextButton menu = new TextButton(Setting.name_menu_button, getSkin(), ResourceManager.button_style);


        table.setBackground(new TextureRegionDrawable(getManager().getTextureRegion(ResourceManager.background)));
        table.top();
        table.add(title).padTop(20).row();
        table.add(reward).expand().top().padTop(10).row();
        table.add(menu).bottom().padBottom(20);

        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setStateScreen(MyGdxGame.State.MENU);
            }
        });

        String[] score = {"1", "5", "10", "20", "50", "100", "250", "500", "1000"};
        for (int i = 0; i < 9; i++) {
            if (AppPreference.getAllStars() >= Integer.parseInt(score[i])){
                setTextureCell(i, getManager().getCup(0));
            }

        }
        for (int i = 0; i < 9; i++) {
            setLabelCell(i, score[i]);
        }
    }
    private void setTextureCell(int cell, TextureRegion textureRegion){
        reward.getCells()[cell].setDrawable(new TextureRegionDrawable(textureRegion));
    }

    private void setLabelCell (int cell, String text){
        reward.getNames()[cell].setText(text);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.draw();
    }

}
