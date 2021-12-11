package com.mycompany.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mycompany.draw.DrawGame;
import com.mycompany.models.Star;
import com.mycompany.mygame.AppPreference;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;
import com.mycompany.update.UpdateGame;

public class MainScreen extends CommonScreen {

    private final UpdateGame updateGame;
    private final DrawGame drawGame;
    private Label labelClock, labelError;
    private Star star;

    public MainScreen(MyGdxGame game) {
        super(720, game);
        updateGame = new UpdateGame(this);
        drawGame = new DrawGame(updateGame);
    }

    @Override
    public void show() {
        super.show();
        updateGame.playGame(game.getSudoku());
        updateGame.setVolume();
        
        Image starIcon = new Image(getManager().getTextureAtlas(ResourceManager.ICON_STAR));
        Image crossIcon = new Image(getManager().getTextureAtlas(ResourceManager.ICON_CROSS));
        Image backIcon = new Image(getManager().getTextureAtlas(ResourceManager.ICON_BACK));
        Image pauseIcon = new Image(getManager().getTextureAtlas(ResourceManager.ICON_PAUSE));
        Image musicIcon = new Image(getManager().getTextureAtlas(ResourceManager.ICON_MUSIC));

        Label stars = new Label(""+AppPreference.getAllStars(), getManager().getSkin(), ResourceManager.label_style_big);
        Label title = new Label(Setting.label_lvl, getManager().getSkin(), ResourceManager.label_style_normal);
        labelClock = new Label(Setting.label_time_game, getManager().getSkin(), ResourceManager.label_style_normal);
        labelError = new Label(Setting.label_error+AppPreference.getErrorGame(), getManager().getSkin(), ResourceManager.label_style_normal);

        star = new Star(Setting.size_icon/2, getManager().getTextureAtlas(ResourceManager.ICON_STAR));

        float size = Setting.size_icon;
        table.top().add(starIcon).width(size).height(size).left().padTop(5).padLeft(10);
        table.add(stars).expandX().left().padTop(5);
        table.add(musicIcon).width(size).height(size).padTop(5);
        table.add(pauseIcon).width(size).height(size).padTop(5);
        table.add(backIcon).width(size).height(size).padTop(5);
        table.add(crossIcon).width(size).height(size).padTop(5).padRight(10);
        table.row();
        table.add(title).colspan(2).top().right().padTop(5);
        table.add(star).colspan(4).top().left().padTop(5);
        table.row();
        table.add(labelClock).colspan(2).top().left().padTop(5).padLeft(10);
        table.add(labelError).colspan(4).top().left().padTop(5);

        musicIcon.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (getManager().getMusic().isPlaying()){
                        updateGame.pauseMusic();
                    } else {
                        updateGame.playMusic();
                    }
                }
            });
        pauseIcon.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    pause();
                }
            });
        crossIcon.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    dispose();
                    game.setStateScreen(MyGdxGame.State.MENU);
                }
            });
        InputMultiplexer multiplexer = new InputMultiplexer(stage, updateGame);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        updateGame.update(delta);
        drawGame.draw(game.getBatch(), game.getRender());
        stage.draw();
    }

    public MyGdxGame getGame() {
        return game;
    }


    @Override
    public void pause() {
        updateGame.pause();
    }

    @Override
    public void hide() {
        super.hide();
        updateGame.saveGame();
    }
    
    public void setTime(int minute, int second){
        labelClock.setText(Setting.label_time_game+minute+":"+second);
    }

    public void setStars(int _stars){
        star.setStars(_stars);
    }

    public void setLabelError(int _errors){
        labelError.setText(Setting.label_error+_errors);
    }
}
