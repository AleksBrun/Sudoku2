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

    public MainUi(Viewport viewport, ResourceManager manager, final MainScreen mainScreen){
        super(viewport);

        Table table = new Table();
        table.setFillParent(true);
        addActor(table);

        Image circleIcon = new Image(manager.getIconStar());
        Image crossIcon = new Image(manager.getIconCross());
        Image backIcon = new Image(manager.getIconBack());
        Image pauseIcon = new Image(manager.getIconPause());
        Image playIcon = new Image(manager.getIconPlay());

        Label title = new Label("Sudoku", manager.getSkin(), Setting.font_white_big, Color.GREEN);

        float size = Setting.size_icon;
        table.top();
        table.add(playIcon).width(size).height(size).expandX().top().right();
        table.add(pauseIcon).width(size).height(size).top();
        table.add(circleIcon).width(size).height(size).top();
        table.add(backIcon).width(size).height(size).top();
        table.add(crossIcon).width(size).height(size).top().row();
        table.add(title).colspan(5).top();

        circleIcon.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

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
}
