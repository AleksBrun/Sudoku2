package com.mycompany.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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

        Image backIcon = new Image(manager.getIconBack());
        backIcon.setSize(Setting.size_icon, Setting.size_icon);

        Image pauseIcon = new Image(manager.getIconPause());
        pauseIcon.setSize(Setting.size_icon, Setting.size_icon);

        Image playIcon = new Image(manager.getIconPlay());
        playIcon.setSize(Setting.size_icon, Setting.size_icon);

        table.add(playIcon).expand().top().right();
        table.add(pauseIcon).top();
        table.add(backIcon).top();

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
        backIcon.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainScreen.dispose();
                mainScreen.getGame().setStateScreen(MyGdxGame.State.MENU);
            }
        });
    }
}
