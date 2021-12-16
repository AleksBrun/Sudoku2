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
                    dispose();
                    AppPreference.setDifficultyLevel(1);
                    game.reset();
                    game.createSudoku(35);
                    game.setStateScreen(MyGdxGame.State.MAIN);
                }
            });

        easy.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    dispose();
                    AppPreference.setDifficultyLevel(2);
                    game.reset();
                    game.createSudoku(40);
                    game.setStateScreen(MyGdxGame.State.MAIN);
                }
            });
        average.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    dispose();
                    AppPreference.setDifficultyLevel(3);
                    game.reset();
                    game.createSudoku(45);
                    game.setStateScreen(MyGdxGame.State.MAIN);
                }
            });
        difficult.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    dispose();
                    AppPreference.setDifficultyLevel(4);
                    game.reset();
                    game.createSudoku(50);
                    game.setStateScreen(MyGdxGame.State.MAIN);
                }
            });
        difficult_max.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    dispose();
                    AppPreference.setDifficultyLevel(5);
                    game.reset();
                    game.createSudoku(55);
                    game.setStateScreen(MyGdxGame.State.MAIN);
                }
            });
        random.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    dispose();
                    game.reset();
                    game.setStateScreen(MyGdxGame.State.CREATE);
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



    @Override
    public void render(float delta) {
        super.render(delta);
        stage.draw();
    }
}
