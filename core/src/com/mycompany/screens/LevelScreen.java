package com.mycompany.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mycompany.mygame.*;
import com.mycompany.utils.Sudoku;
import com.mycompany.utils.Utils;


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

        TextButton menu = new TextButton(Setting.name_menu_button, getSkin(), ResourceManager.button_style);

        table.setBackground(new TextureRegionDrawable(getManager().getTextureRegionAtlas(ResourceManager.background4)));
        table.add(levelLabel);
        table.row();
        table.add(easy_min).fillX().padTop(20);
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
                    startNewGame(1, 35, 2, 2);
                }
            });

        easy.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    startNewGame(2,40, 3, 2);
                }
            });

        average.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    startNewGame(3, 45, 4, 3);
                }
            });

        difficult.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    startNewGame(4, 50, 5, 3);
                }
            });

        difficult_max.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    startNewGame(5, 55, 6, 3);
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

    private void startNewGame(int difficulty, int missing_digits, int max_bonus, int _max_error){

        if (!game.isFull()) {
            Sudoku sudoku = new Sudoku();
            Parameter parameter = new Parameter();
            parameter.difficulty_level = difficulty;
            parameter.sudokuGame = parameter.sudokuSave = Utils.getStringSudoku(sudoku.getRandomSudoku(missing_digits));
            parameter.sudokuFull = Utils.getStringSudoku(sudoku.getCopyMat());
            parameter.data = new java.util.Date().toLocaleString();
            parameter.error = 0;
            parameter.id_bonus = 4;
            parameter.max_error = _max_error;
            parameter.max_bonus = max_bonus;
            parameter.bonus = parameter.max_bonus;
            parameter.start_progress = missing_digits;
            parameter.coin = 0;
            parameter.live = 1;
            parameter.stars = difficulty * parameter.live;
            dispose();
            game.createSudoku(parameter, true);
            game.setStateScreen(MyGdxGame.State.MAIN);
            AppPreference.setAllGames(AppPreference.getAllGames()+1);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.draw();
    }
}
