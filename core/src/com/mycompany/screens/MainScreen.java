package com.mycompany.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mycompany.draw.DrawGame;
import com.mycompany.models.CommonGroup;
import com.mycompany.models.Star;
import com.mycompany.mygame.AppPreference;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;
import com.mycompany.update.UpdateGame;

public class MainScreen extends CommonScreen {

    private final UpdateGame updateGame;
    private final DrawGame drawGame;
    private Label labelClock, stars;
    private Star skull;

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
        
        float size = Setting.size_icon;
        
        CommonGroup row1 = new CommonGroup(stage.getWidth(), size);
        
        CommonGroup row2 = new CommonGroup(stage.getWidth(), size/1.5f);
       
        CommonGroup row3 = new CommonGroup(stage.getWidth(), size/1.5f);
        
        CommonGroup row4 = new CommonGroup(stage.getWidth(), size/1.5f);
       
        table.top().padTop(20);
        table.add(row1).row();
        table.add(row2).padTop(10).row();
        table.add(row3).row();
        table.add(row4);
        
        final Image starIcon = new Image(getManager().getTextureRegionAtlas(ResourceManager.star));
        stars = new Label(String.valueOf(AppPreference.getStarsGame()), getManager().getSkin(), ResourceManager.label_style_big);
        final ImageButton musicIcon = new ImageButton(getSkin(), ResourceManager.image_button_music);
        final ImageButton pauseIcon = new ImageButton(getSkin(), ResourceManager.image_button_pause);
        final ImageButton settingIcon = new ImageButton(getSkin(), ResourceManager.image_button_setting);
        final ImageButton homeIcon = new ImageButton(getSkin(), ResourceManager.image_button_home);
        
        final Label title = new Label(Setting.label_lvl, getManager().getSkin(), ResourceManager.label_style_big);
        labelClock = new Label(Setting.label_time_game, getManager().getSkin(), ResourceManager.label_style_big);
        final Label labelError = new Label(Setting.label_error, getManager().getSkin(), ResourceManager.label_style_big);

        Star star = new Star(size / 2, getManager().getTextureRegionAtlas(ResourceManager.star));
        star.setStars(AppPreference.getDifficultyLevel());
        
        skull = new Star(size/2, getManager().getTextureRegionAtlas(ResourceManager.skull));
        skull.setStars(AppPreference.getErrorGame());

        
        row1.getTable().add(starIcon).width(size/1.5f).height(size/1.5f).left().padLeft(30);
        row1.getTable().add(stars).expandX().left().padLeft(5);
        row1.getTable().add(musicIcon).width(size).height(size).padRight(5).fill();
        row1.getTable().add(pauseIcon).width(size).height(size).padRight(5);
        row1.getTable().add(settingIcon).width(size).height(size).padRight(5);
        row1.getTable().add(homeIcon).width(size).height(size).padRight(20);
        
        row2.getTable().add(title).padLeft(30);
        row2.getTable().add(star).expandX().left().padLeft(10);
        
        row3.getTable().add(labelClock).expandX().left().padLeft(30);
        
        row4.getTable().add(labelError).padLeft(30);
        row4.getTable().add(skull).expandX().left().padLeft(10);

        settingIcon.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                dispose();
                game.setStateScreen(MyGdxGame.State.SETTING);
            }
        });
        musicIcon.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (!updateGame.isPause()){
                        if (musicIcon.isChecked()){
                            updateGame.pauseMusic();
                        } else {
                            updateGame.playMusic();
                        }
                    }
                }
            });
        pauseIcon.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    musicIcon.setChecked(!musicIcon.isChecked());
                    pause();
                }
            });
        homeIcon.addListener(new ClickListener(){
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
        AppPreference.setStarGame(AppPreference.getStarsGame()+_stars);
        stars.setText(String.valueOf(AppPreference.getStarsGame()));
    }

    public void setLabelError(int _errors){
        skull.setStars(_errors);
    }

}
