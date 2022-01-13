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
import com.mycompany.models.GroupImage;
import com.mycompany.mygame.AppPreference;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.Parameter;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;
import com.mycompany.update.UpdateGame;
import com.mycompany.utils.Clock;
import com.mycompany.utils.Utils;

public class MainScreen extends CommonScreen {

    private final UpdateGame updateGame;
    private final DrawGame drawGame;
    private final Clock clock;
    private Label labelClock, labelAllStars, labelCoins, labelHeart, labelProgress;
    private GroupImage error, bonus, level;

    public MainScreen(MyGdxGame game) {
        super(Setting.width_main_ui, game);
        updateGame = new UpdateGame(this);
        drawGame = new DrawGame(updateGame);
        clock = new Clock();
    }

    @Override
    public void show() {
        super.show();
        Parameter parameter = game.getParameter();
        float size = Setting.size_icon;
        float sizeButton = size *2f;

        CommonGroup row1 = new CommonGroup(stage.getWidth(), size);
        
        CommonGroup row2 = new CommonGroup(stage.getWidth(), size/2f);

        CommonGroup row3 = new CommonGroup(stage.getWidth(), size/2f);

        CommonGroup row4 = new CommonGroup(stage.getWidth(), size/2f);

        CommonGroup row5 = new CommonGroup(stage.getWidth(), size/2f);

        CommonGroup row6 = new CommonGroup(stage.getWidth(), size*1.5f);
        
        Label infoLabel = new Label("The product was created by Aleks Brun", getSkin(), ResourceManager.label_style_normal);

        table.top().padTop(20);
        table.add(row1).row();
        table.add(row2).padTop(20).row();
        table.add(row3).padTop(5).row();
        table.add(row4).padTop(5).row();
        table.add(row5).padTop(5).row();
        table.add(row6).padTop(40).row();
        table.add(infoLabel).expand().bottom().padBottom(10);



        final Image starIcon = new Image(getManager().getTextureRegionAtlas(ResourceManager.star));

        final Image coinIcon = new Image(getManager().getTextureRegionAtlas(ResourceManager.coin));

        final Image heartIcon = new Image(getManager().getTextureRegionAtlas(ResourceManager.heart1));

        final Image clockIcon = new Image(getManager().getTextureRegionAtlas(ResourceManager.clock));

        final ImageButton musicIcon = new ImageButton(getSkin(), ResourceManager.image_button_music);
        musicIcon.setChecked(AppPreference.isMusicEnabled());

        final ImageButton pauseIcon = new ImageButton(getSkin(), ResourceManager.image_button_pause);

        final ImageButton settingIcon = new ImageButton(getSkin(), ResourceManager.image_button_setting);

        final ImageButton homeIcon = new ImageButton(getSkin(), ResourceManager.image_button_home);

        final ImageButton restartIcon = new ImageButton(getSkin(), ResourceManager.image_button_restart);

        final Image fullSudokuButton = new Image(getManager().getTextureRegionAtlas(ResourceManager.tree));

        final Image  shopButton = new Image(getManager().getTextureRegionAtlas(ResourceManager.shop));

        final Image hint1 = new Image(getManager().getTextureRegionAtlas(ResourceManager.hint1));

        final Image  hint2 = new Image(getManager().getTextureRegionAtlas(ResourceManager.hint2));

        final Image  hint3 = new Image(getManager().getTextureRegionAtlas(ResourceManager.hint3));
        
        final Image  hint4 = new Image(getManager().getTextureRegionAtlas(ResourceManager.hint4));
        

        final Label title = new Label(Setting.label_lvl, getManager().getSkin(), ResourceManager.label_style_big);

        final Label labelError = new Label(Setting.label_error, getManager().getSkin(), ResourceManager.label_style_big);

        level = new GroupImage(5,size / 2, getManager().getTextureRegionAtlas(ResourceManager.star));
        
        error = new GroupImage(5,size/2, getManager().getTextureRegionAtlas(ResourceManager.skull));

        labelClock = new Label("", getSkin(), ResourceManager.label_style_big);

        labelAllStars = new Label("", getSkin(), ResourceManager.label_style_big);

        labelCoins = new Label("", getSkin(), ResourceManager.label_style_big);

        labelHeart = new Label("", getSkin(), ResourceManager.label_style_big);

        labelProgress = new Label("", getSkin(), ResourceManager.label_style_big);

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
        
        row2.getTable().add(title).padLeft(20);
        row2.getTable().add(level).expandX().left().padLeft(5);

        row3.getTable().add(bonusLabel).padLeft(20);
        row3.getTable().add(bonus).expandX().left().padLeft(5);

        row4.getTable().add(labelError).padLeft(20);
        row4.getTable().add(error).expandX().left().padLeft(5);

        row5.getTable().add(labelProgress).expandX().left().padLeft(20);

        row6.getTable().padLeft(20).padRight(20);
        row6.getTable().add(fullSudokuButton).width(sizeButton).height(sizeButton).expandX();
        row6.getTable().add(hint1).width(sizeButton).height(sizeButton).expandX();
        row6.getTable().add(hint2).width(sizeButton).height(sizeButton).expandX();
        row6.getTable().add(hint3).width(sizeButton).height(sizeButton).expandX();
        row6.getTable().add(hint4).width(sizeButton).height(sizeButton).expandX();
        row6.getTable().add(shopButton).width(sizeButton).height(sizeButton).expandX();

        hint1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                updateGame.visibleAllBonus();
            }
        });
        hint3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                dispose();
                game.setStateScreen(MyGdxGame.State.INFO);
            }
        });
        shopButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setStateScreen(MyGdxGame.State.CREATE);
            }
        });
        fullSudokuButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                updateGame.visibleAllActiveCell();
            }
        });
        restartIcon.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setStateScreen(MyGdxGame.State.INFO);
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
                    if (musicIcon.isChecked()){
                        updateGame.pauseMusic();
                        AppPreference.setMusicEnabled(true);
                    } else {
                        updateGame.playMusic();
                        AppPreference.setMusicEnabled(false);
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

        updateGame.loadGame(parameter);
        InputMultiplexer multiplexer = new InputMultiplexer(stage, updateGame);
        Gdx.input.setInputProcessor(multiplexer);
        if (musicIcon.isChecked()){
            updateGame.pauseMusic();
        } else {
            updateGame.playMusic();
        }

        stage.draw();
        updateGame.setTransformer(Utils.getScreenLocation(row6));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        clock.update();
        setTime(clock.getMinute(), clock.getSecond());
        drawGame.draw(game.getBatch());
        stage.draw();
    }

    @Override
    public void pause() {
        dispose();
        game.setStateScreen(MyGdxGame.State.INFO);
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
    public void setStars(int stars){
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
    public void setProgress(int _progress){
        this.labelProgress.setText(Setting.label_progress+ _progress +"%");
    }
    public MyGdxGame getGame() {
        return this.game;}
    
    public Clock getClock(){
        return this.clock;}
}
