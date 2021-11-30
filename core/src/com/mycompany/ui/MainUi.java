package com.mycompany.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mycompany.models.Star;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;
import com.mycompany.screens.MainScreen;

public class MainUi extends Stage {

    private final Label time;


    public MainUi(Viewport viewport, ResourceManager manager, final MainScreen mainScreen){
        super(viewport);

        Table table = new Table();
        table.setFillParent(true);
        addActor(table);

        float size = Setting.size_icon;

        Image crossIcon = new Image(manager.getTextureAtlas(ResourceManager.ICON_CROSS));
        Image backIcon = new Image(manager.getTextureAtlas(ResourceManager.ICON_BACK));
        Image pauseIcon = new Image(manager.getTextureAtlas(ResourceManager.ICON_PAUSE));
        Image playIcon = new Image(manager.getTextureAtlas(ResourceManager.ICON_PLAY));

        Label title = new Label("Уровень", manager.getSkin(), ResourceManager.label_style_small);
        Label labelClock = new Label("Время игры: ", manager.getSkin(), ResourceManager.label_style_small);
        time = new Label("00:00", manager.getSkin(), ResourceManager.label_style_small);

        Star star = new Star(size/2, manager.getTextureAtlas(ResourceManager.ICON_STAR));

        table.top();
        table.add().expandX();
        table.add(playIcon).width(size).height(size).right();
        table.add(pauseIcon).width(size).height(size);
        table.add(backIcon).width(size).height(size);
        table.add(crossIcon).width(size).height(size).padRight(10);
        table.row();
        table.add(title).expandX();
        table.add(star).expandX();
        table.row();
        table.add(labelClock).left().padLeft(20);
        table.add(time).expandX().left();


        playIcon.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });
        pauseIcon.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });
        crossIcon.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainScreen.dispose();
                mainScreen.getGame().setStateScreen(MyGdxGame.State.MENU);
            }
        });
    }

    public void setTime(int minute, int second){
        time.setText(minute+":"+second);
    }
}
