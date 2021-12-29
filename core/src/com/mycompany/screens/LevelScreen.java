package com.mycompany.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mycompany.mygame.AppPreference;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;


public class LevelScreen extends CommonScreen {

    public LevelScreen(final MyGdxGame game) {
        super(500, game);
    }

    @Override
    public void show() {
        super.show();

        final Label levelLabel = new Label("Уровень", getSkin(), ResourceManager.label_style_normal);
        
        TextButton continuation = new TextButton(Setting.name_continuation_button, getSkin(), ResourceManager.button_style);
        continuation.setVisible(AppPreference.isContinuationEnabled());
        
        TextButton easy_min = new TextButton(Setting.level_0, getSkin(), ResourceManager.button_style);

        TextButton easy = new TextButton(Setting.level_1, getSkin(), ResourceManager.button_style);

        TextButton average = new TextButton(Setting.level_2, getSkin(), ResourceManager.button_style);

        TextButton difficult = new TextButton(Setting.level_3, getSkin(), ResourceManager.button_style);
        
        TextButton difficult_max = new TextButton(Setting.level_4, getSkin(), ResourceManager.button_style);

        TextButton random = new TextButton(Setting.level_random, getSkin(), ResourceManager.button_style);

        TextButton menu = new TextButton(Setting.name_menu_button, getSkin(), ResourceManager.button_style);

        table.setBackground(new TextureRegionDrawable(getManager().getTextureRegionAtlas(ResourceManager.background1)));
        table.add(levelLabel);
        table.row();
        table.add(easy_min).fillX().padTop(10);
        table.row();
        table.add(easy).fillX().padTop(10);
        table.row();
        table.add(average).fillX().padTop(10);
        table.row();
        table.add(difficult).fillX().padTop(10);
        table.row();
        table.add(difficult_max).fillX().padTop(10);
        table.row();
        table.add(menu).fillX().padTop(10);
        
        continuation.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.dispose();
                game.setStateScreen(MyGdxGame.State.MAIN);
            }
        });
        easy_min.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    startNewGame(1, 35);
                }
            });

        easy.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    startNewGame(2,40);
                }
            });
        average.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    startNewGame(3, 45);
                }
            });
        difficult.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    startNewGame(4, 50);
                }
            });
        difficult_max.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    startNewGame(5, 55);

                }
            });
        random.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {

                }
            });
        menu.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    dispose();
                    game.setStateScreen(MyGdxGame.State.MENU);
                }
            });
    }

    private void startNewGame(int difficulty, int missing_digits){
        dispose();
        AppPreference.setDifficultyLevel(difficulty);
        reset();
        game .createSudoku(missing_digits);
        game.setStateScreen(MyGdxGame.State.MAIN);
    }
    private void reset() {
        AppPreference.setContinuationEnabled(true);
        AppPreference.setTimeMinute(0);
        AppPreference.setTimeSecond(0);
        AppPreference.setErrorGame(0);
        AppPreference.setStarGame(0);
        AppPreference.setBonus(5);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.draw();
    }
}
