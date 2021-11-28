package com.mycompany.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mycompany.mygame.*;
import com.mycompany.screens.LevelScreen;
import com.mycompany.unils.Sudoku;

public class LevelUI extends Stage {

    public LevelUI(Viewport viewport, ResourceManager manager, final LevelScreen levelScreen){
        super(viewport);

        Table table = new Table();
        table.setFillParent(true);
        addActor(table);

        final Label levelLabel = new Label("Уровень", manager.getSkin(), ResourceManager.label_style_normal);

        TextButton easy = new TextButton(Setting.level_1, manager.getSkin(), ResourceManager.button_style);

        TextButton average = new TextButton(Setting.level_2, manager.getSkin(), ResourceManager.button_style);

        TextButton difficult = new TextButton(Setting.level_3, manager.getSkin(), ResourceManager.button_style);

        TextButton random = new TextButton(Setting.level_random, manager.getSkin(), ResourceManager.button_style);

        TextButton menu = new TextButton(Setting.name_menu_button, manager.getSkin(), ResourceManager.button_style);

        table.add(levelLabel).row();
        table.add(easy).fillX().padTop(20).row();
        table.add(average).fillX().padTop(10).row();
        table.add(difficult).fillX().padTop(10).row();
        table.add(random).fillX().padTop(10).row();
        table.add(menu).fillX().padTop(10);

        easy.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                reset();
                levelScreen.dispose();
                levelScreen.getGame().setSudoku(ExampleGrid.getSudoku(ExampleGrid.Level.minimum));
                levelScreen.getGame().setStateScreen(MyGdxGame.State.MAIN);

            }
        });
        average.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                reset();
                levelScreen.dispose();
                levelScreen.getGame().setSudoku(ExampleGrid.getSudoku(ExampleGrid.Level.moderate));
                levelScreen.getGame().setStateScreen(MyGdxGame.State.MAIN);

            }
        });
        difficult.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                reset();
                levelScreen.dispose();
                levelScreen.getGame().setSudoku(ExampleGrid.getSudoku(ExampleGrid.Level.maximum));
                levelScreen.getGame().setStateScreen(MyGdxGame.State.MAIN);

            }
        });
        random.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                levelScreen.dispose();
                levelScreen.getGame().setSudoku(Sudoku.getRandomSudoku(AppPreference.getMissingDigits()));
                levelScreen.getGame().setStateScreen(MyGdxGame.State.MAIN);
            }
        });
        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                levelScreen.dispose();
                levelScreen.getGame().setStateScreen(MyGdxGame.State.MENU);
            }
        });
    }
    private void reset(){
        AppPreference.setContinuationEnabled(true);
        AppPreference.setTimeMinute(0);
        AppPreference.setTimeSecond(0);
    }
}
