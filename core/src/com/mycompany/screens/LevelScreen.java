package com.mycompany.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mycompany.mygame.AppPreference;
import com.mycompany.mygame.ExampleGrid;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;
import com.mycompany.utils.Sudoku;

public class LevelScreen extends CommonScreen {

    public LevelScreen(final MyGdxGame game) {
        super(500, game);
    }

    @Override
    public void show() {
        super.show();

        final Label levelLabel = new Label("Уровень", getSkin(), ResourceManager.label_style_normal);

        TextButton easy = new TextButton(Setting.level_1, getSkin(), ResourceManager.button_style);

        TextButton average = new TextButton(Setting.level_2, getSkin(), ResourceManager.button_style);

        TextButton difficult = new TextButton(Setting.level_3, getSkin(), ResourceManager.button_style);

        TextButton random = new TextButton(Setting.level_random, getSkin(), ResourceManager.button_style);

        TextButton menu = new TextButton(Setting.name_menu_button, getSkin(), ResourceManager.button_style);

        table.add(levelLabel).row();
        table.add(easy).fillX().padTop(20).row();
        table.add(average).fillX().padTop(10).row();
        table.add(difficult).fillX().padTop(10).row();
        table.add(random).fillX().padTop(10).row();
        table.add(menu).fillX().padTop(10);

        easy.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    dispose();
                    game.reset();
                    game.setSudoku(ExampleGrid.getSudoku(ExampleGrid.Level.minimum));
                    game.setStateScreen(MyGdxGame.State.MAIN);
                    AppPreference.setDifficultyLevel(2);
                }
            });
        average.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    dispose();
                    game.reset();
                    game.setSudoku(ExampleGrid.getSudoku(ExampleGrid.Level.moderate));
                    game.setStateScreen(MyGdxGame.State.MAIN);
                    AppPreference.setDifficultyLevel(3);
                }
            });
        difficult.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    dispose();
                    game.reset();
                    game.setSudoku(ExampleGrid.getSudoku(ExampleGrid.Level.maximum));
                    game.setStateScreen(MyGdxGame.State.MAIN);
                    AppPreference.setDifficultyLevel(4);
                }
            });
        random.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    dispose();
                    game.reset();
                    int mission_digits = AppPreference.getMissingDigits();
                    AppPreference.setDifficultyLevel(getDifficultyLevel(mission_digits));
                    game.setSudoku(Sudoku.getRandomSudoku(mission_digits));
                    game.setStateScreen(MyGdxGame.State.MAIN);
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
    
    private int getDifficultyLevel(int mission_digits) {
        if (mission_digits >= 25 && mission_digits < 30) {
            return 1;
        } else if (mission_digits >= 30 && mission_digits < 35) {
            return 2;
        } else if (mission_digits >= 35 && mission_digits < 40) {
            return 3;
        } else if (mission_digits >= 40 && mission_digits < 50) {
            return 4;
        }
        return 5;
    }
}
