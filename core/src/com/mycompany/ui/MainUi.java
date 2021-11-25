package com.mycompany.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;
import com.mycompany.screens.MainScreen;

public class MainUi extends Stage {

    private Label time, title;

    public MainUi(Viewport viewport, ResourceManager manager, final MainScreen mainScreen){
        super(viewport);

        Table table = new Table();
        table.setFillParent(true);
        addActor(table);

        Image crossIcon = new Image(manager.getIconTexture(ResourceManager.ICON_CROSS));
        Image backIcon = new Image(manager.getIconTexture(ResourceManager.ICON_BACK));
        Image pauseIcon = new Image(manager.getIconTexture(ResourceManager.ICON_PAUSE));
        Image playIcon = new Image(manager.getIconTexture(ResourceManager.ICON_PLAY));

        title = new Label("Легкий - 1", manager.getSkin(), Setting.font_white_big, Color.BLACK);
        Label labelClock = new Label("Время игры: ", manager.getSkin(), Setting.font_white_small, Color.BLACK);
        time = new Label("00:00", manager.getSkin(), Setting.font_white_small, Color.BLACK);
        float size = Setting.size_icon;

        table.top();
        table.add(labelClock).left().padLeft(20);
        table.add(time).expandX().left();
        table.add(playIcon).width(size).height(size).right();
        table.add(pauseIcon).width(size).height(size);
        table.add(backIcon).width(size).height(size);
        table.add(crossIcon).width(size).height(size).padRight(20).row();
        table.add(title).colspan(6).top();


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
