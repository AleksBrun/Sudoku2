package com.mycompany.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;
import com.mycompany.screens.MenuScreen;

public class MenuUI extends Stage {

    public MenuUI(Viewport viewport, final ResourceManager manager, final MenuScreen menuScreen){
        super(viewport);

        Table table = new Table();
        table.setFillParent(true);
        addActor(table);

        TextButton start = new TextButton("Игра", manager.getSkin(), Setting.button_style);
        TextButton exit = new TextButton("Выход", manager.getSkin(), Setting.button_style);
        TextButton color = new TextButton("    Настройки   ", manager.getSkin(), Setting.button_style);
        TextButton test = new TextButton("Привет", manager.getSkin(), Setting.button_style);

        table.add(start).fillX().row();
        table.add(color).fillX().padTop(5).row();
        table.add(exit).fillX().padTop(5).row();
        table.add(test).fillX().padTop(5);

        start.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuScreen.dispose();
                menuScreen.getGame().setStateScreen(MyGdxGame.State.LEVEL);
            }
        });
        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuScreen.dispose();
                Gdx.app.exit();
            }
        });
        color.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuScreen.dispose();
                menuScreen.getGame().setStateScreen(MyGdxGame.State.SETTING);
            }
        });
    }
}
