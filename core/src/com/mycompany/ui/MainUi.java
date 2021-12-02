package com.mycompany.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mycompany.mygame.AppPreference;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;
import com.mycompany.screens.MainScreen;
import com.mycompany.models.Star;

public class MainUi extends Stage {

    private final Label time;
    private final Star star;

    public MainUi(Viewport viewport, ResourceManager manager, final MainScreen mainScreen){
        super(viewport);

        Table table = new Table();
        table.setFillParent(true);
        //table.setDebug(true);
        addActor(table);


        Image starIcon = new Image(manager.getTextureAtlas(ResourceManager.ICON_STAR));

        Image crossIcon = new Image(manager.getTextureAtlas(ResourceManager.ICON_CROSS));
        Image backIcon = new Image(manager.getTextureAtlas(ResourceManager.ICON_BACK));
        Image pauseIcon = new Image(manager.getTextureAtlas(ResourceManager.ICON_PAUSE));
        Image playIcon = new Image(manager.getTextureAtlas(ResourceManager.ICON_PLAY));

        Label stars = new Label(""+AppPreference.getAllStars(), manager.getSkin(), ResourceManager.label_style_big);
        Label title = new Label("Уровень сложности ", manager.getSkin(), ResourceManager.label_style_normal);
        Label labelClock = new Label("Время игры: ", manager.getSkin(), ResourceManager.label_style_normal);
        time = new Label("00:00", manager.getSkin(), ResourceManager.label_style_normal);
        
        
        star = new Star(Setting.size_icon/2, manager.getTextureAtlas(ResourceManager.ICON_STAR));

        float size = Setting.size_icon;
        table.top().add(starIcon).width(size).height(size).left().padTop(5).padLeft(10);
        table.add(stars).expandX().left().padTop(5);
        table.add(playIcon).width(size).height(size).padTop(5);
        table.add(pauseIcon).width(size).height(size).padTop(5);
        table.add(backIcon).width(size).height(size).padTop(5);
        table.add(crossIcon).width(size).height(size).padTop(5).padRight(10);
        table.row();
        table.add(title).colspan(2).top().right().padTop(5);
        table.add(star).colspan(4).top().left().padTop(5);
        table.row();
        table.add(labelClock).colspan(2).top().right().padTop(5);
        table.add(time).colspan(4).top().left().padTop(5);
       

        playIcon.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainScreen.resume();
            }
        });
        pauseIcon.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainScreen.pause();
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
    
    public void setStars(int _stars){
        star.setStars(_stars);
    }
}
