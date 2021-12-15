package com.mycompany.screens;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mycompany.mygame.AppPreference;
import com.mycompany.mygame.ExampleGrid;
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

        TextButton easy = new TextButton(Setting.level_1, getSkin(), ResourceManager.button_style);

        TextButton average = new TextButton(Setting.level_2, getSkin(), ResourceManager.button_style);

        TextButton difficult = new TextButton(Setting.level_3, getSkin(), ResourceManager.button_style);

        TextButton random = new TextButton(Setting.level_random, getSkin(), ResourceManager.button_style);

        TextButton menu = new TextButton(Setting.name_menu_button, getSkin(), ResourceManager.button_style);

        table.setBackground(new TextureRegionDrawable(getManager().getTextureRegion(ResourceManager.background1)));
        table.add(levelLabel).row();
        table.add(continuation).fillX().padTop(20).row();
        table.add(easy).fillX().padTop(10).row();
        table.add(average).fillX().padTop(10).row();
        table.add(difficult).fillX().padTop(10).row();
        table.add(random).fillX().padTop(10).row();
        table.add(menu).fillX().padTop(10);
        
        continuation.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.dispose();
                game.setStateScreen(MyGdxGame.State.MAIN);
            }
        });

        easy.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    dispose();
                    game.reset();
                    game.setSudoku(ExampleGrid.getSudoku(ExampleGrid.Difficulty.minimum, MathUtils.random(1, 3)));
                    AppPreference.setDifficultyLevel(2);
                    game.setStateScreen(MyGdxGame.State.MAIN);
                }
            });
        average.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    dispose();
                    game.reset();
                    game.setSudoku(ExampleGrid.getSudoku(ExampleGrid.Difficulty.moderate));
                    AppPreference.setDifficultyLevel(3);
                    game.setStateScreen(MyGdxGame.State.MAIN);
                }
            });
        difficult.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    dispose();
                    AppPreference.setDifficultyLevel(4);
                    game.reset();
                    game.setSudoku(ExampleGrid.getSudoku(ExampleGrid.Difficulty.maximum));
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
