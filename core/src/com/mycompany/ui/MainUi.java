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

        Image circleIcon = new Image(manager.getIconStar());
        Image crossIcon = new Image(manager.getIconCross());
        Image backIcon = new Image(manager.getIconBack());
        Image pauseIcon = new Image(manager.getIconPause());
        Image playIcon = new Image(manager.getIconPlay());

        float size = Setting.size_icon;
        table.add(playIcon).width(size).height(size).expand().top().right().padTop(Setting.pad_cell);
        table.add(circleIcon).width(size).height(size).top().padTop(Setting.pad_cell);
        table.add(crossIcon).width(size).height(size).top().padTop(Setting.pad_cell);
        table.add(pauseIcon).width(size).height(size).top().padTop(Setting.pad_cell);
        table.add(backIcon).width(size).height(size).top().padTop(Setting.pad_cell);

        crossIcon.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainScreen.getUpdateGame().getGrid().resetGrid();
            }
        });

        circleIcon.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
               System.out.print(mainScreen.getUpdateGame().checkingAllGrid());
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
        backIcon.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainScreen.dispose();
                mainScreen.getGame().setStateScreen(MyGdxGame.State.MENU);
            }
        });
    }
}
