package com.mycompany.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mycompany.draw.DrawGame;
import com.mycompany.models.CommonGroup;
import com.mycompany.models.GroupImage;
import com.mycompany.mygame.*;
import com.mycompany.update.UpdateGame;

public class MainScreen extends CommonScreen {

    private final UpdateGame updateGame;
    private final DrawGame drawGame;
    private Label labelClock, labelAllStars, labelCoins, labelHeart;
    private GroupImage error, bonus, level;

    public MainScreen(MyGdxGame game) {
        super(Setting.width_main_ui, game);
        updateGame = new UpdateGame(this);
        drawGame = new DrawGame(updateGame);
    }

    @Override
    public void show() {
        super.show();
        Parameter parameter = game.getParameter();

        float size = Setting.size_icon;
        
        CommonGroup row1 = new CommonGroup(stage.getWidth(), size);
        
        CommonGroup row2 = new CommonGroup(stage.getWidth(), size/2f);

        CommonGroup row3 = new CommonGroup(stage.getWidth(), size/2f);

        CommonGroup row4 = new CommonGroup(stage.getWidth(), size/2f);
       
        table.top().padTop(20);
        table.add(row1).row();
        table.add(row2).padTop(20).row();
        table.add(row3).padTop(10).row();
        table.add(row4).padTop(10);

        final Image starIcon = new Image(getManager().getTextureRegionAtlas(ResourceManager.star));

        final Image coinIcon = new Image(getManager().getTextureRegionAtlas(ResourceManager.coin));

        final Image heartIcon = new Image(getManager().getTextureRegionAtlas(ResourceManager.heart1));

        final Image clockIcon = new Image(getManager().getTextureRegionAtlas(ResourceManager.clock));

        final ImageButton musicIcon = new ImageButton(getSkin(), ResourceManager.image_button_music);

        final ImageButton pauseIcon = new ImageButton(getSkin(), ResourceManager.image_button_pause);

        final ImageButton settingIcon = new ImageButton(getSkin(), ResourceManager.image_button_setting);

        final ImageButton homeIcon = new ImageButton(getSkin(), ResourceManager.image_button_home);

        final ImageButton restartIcon = new ImageButton(getSkin(), ResourceManager.image_button_restart);

        final Label title = new Label(Setting.label_lvl, getManager().getSkin(), ResourceManager.label_style_big);

        final Label labelError = new Label(Setting.label_error, getManager().getSkin(), ResourceManager.label_style_big);

        level = new GroupImage(5,size / 2, getManager().getTextureRegionAtlas(ResourceManager.star));
        
        error = new GroupImage(5,size/2, getManager().getTextureRegionAtlas(ResourceManager.skull));

        labelClock = new Label(Setting.label_time_game, getSkin(), ResourceManager.label_style_big);

        labelAllStars = new Label(String.valueOf(500), getManager().getSkin(), ResourceManager.label_style_big);

        labelCoins = new Label(String.valueOf(10000), getManager().getSkin(), ResourceManager.label_style_big);

        labelHeart = new Label(String.valueOf(0), getManager().getSkin(), ResourceManager.label_style_big);


        final Label bonusLabel = new Label(Setting.label_bonus, getSkin(), ResourceManager.label_style_big);

        bonus = new GroupImage(parameter.bonus, size/2, getManager().getTextureRegionAtlas(ResourceManager.chest));
        float sizeIcon = size/1.5f;
        row1.getTable().add(starIcon).width(sizeIcon).height(sizeIcon).left().padLeft(30);
        row1.getTable().add(labelAllStars).padLeft(5);
        row1.getTable().add(coinIcon).width(sizeIcon).height(sizeIcon).left().padLeft(20);
        row1.getTable().add(labelCoins).padLeft(5);
        row1.getTable().add(heartIcon).width(sizeIcon).height(sizeIcon).padLeft(20);
        row1.getTable().add(labelHeart).padLeft(5);
        row1.getTable().add(clockIcon).width(sizeIcon).height(sizeIcon).padLeft(20);
        row1.getTable().add(labelClock).expandX().left().padLeft(5);
        row1.getTable().add(restartIcon).width(size).height(size).padRight(5).fillX();
        row1.getTable().add(musicIcon).width(size).height(size).padRight(5);
        row1.getTable().add(pauseIcon).width(size).height(size).padRight(5);
        row1.getTable().add(settingIcon).width(size).height(size).padRight(5);
        row1.getTable().add(homeIcon).width(size).height(size).padRight(20);
        
        row2.getTable().add(title);
        row2.getTable().add(level);

        row3.getTable().add(bonusLabel);
        row3.getTable().add(bonus);

        row4.getTable().add(labelError);
        row4.getTable().add(error);

        restartIcon.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                updateGame.visibleAllBonus();
            }
        });
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

        if (AppPreference.isMusicEnabled()) updateGame.playMusic();
        updateGame.loadGame(parameter);
        InputMultiplexer multiplexer = new InputMultiplexer(stage, updateGame);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        updateGame.update();
        drawGame.draw(game.getBatch());
        stage.draw();
    }

    @Override
    public void pause() {
        super.pause();
        updateGame.pause();
    }

    @Override
    public void hide() {
        super.hide();
        updateGame.saveGame();
    }
    public void setBonus(int _bonus){
        this.bonus.setQuantity(_bonus);
    }
    public void setTime(int minute, int second){
        this.labelClock.setText(minute+":"+second);
    }
    public void setAll_Stars(int stars){
        this.labelAllStars.setText(String.valueOf(stars));
    }
    public void setCoins(int coin){
        this.labelCoins.setText(String.valueOf(coin));
    }
    public void setError(int _errors){
        this.error.setQuantity(_errors);
    }
    public void setLevel(int _level){
        this.level.setQuantity(_level);
    }
    public void setHeart(int heart){
        labelHeart.setText(String.valueOf(heart));
    }
    public MyGdxGame getGame() {
        return game;
    }
}
