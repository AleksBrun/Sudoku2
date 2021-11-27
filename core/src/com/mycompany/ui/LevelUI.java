package com.mycompany.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mycompany.mygame.ExampleGrid;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;
import com.mycompany.screens.LevelScreen;

public class LevelUI extends Stage {

    public LevelUI(Viewport viewport, ResourceManager manager, final LevelScreen levelScreen){
        super(viewport);

        Table table = new Table();
        table.setFillParent(true);
        addActor(table);



        final Label levelLabel = new Label("Уровень", manager.getSkin(), ResourceManager.label_style_normal);

        TextButton easy = new TextButton("Легкий", manager.getSkin(), ResourceManager.button_style);

        TextButton average = new TextButton("  Средний  ", manager.getSkin(), ResourceManager.button_style);

        TextButton difficult = new TextButton("Тудный", manager.getSkin(), ResourceManager.button_style);

        TextButton menu = new TextButton("Меню", manager.getSkin(), ResourceManager.button_style);

        table.add(levelLabel).row();
        table.add(easy).fillX().padTop(20).row();
        table.add(average).fillX().padTop(10).row();
        table.add(difficult).fillX().padTop(10).row();
        table.add(menu).fillX().padTop(10);

        easy.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                levelScreen.dispose();
                levelScreen.getGame().setSudoku(ExampleGrid.getSudoku(ExampleGrid.Level.minimum));
                levelScreen.getGame().setStateScreen(MyGdxGame.State.MAIN);
            }
        });
        average.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                levelScreen.dispose();
                levelScreen.getGame().setSudoku(ExampleGrid.getSudoku(ExampleGrid.Level.moderate));
                levelScreen.getGame().setStateScreen(MyGdxGame.State.MAIN);
            }
        });
        difficult.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                levelScreen.dispose();
                levelScreen.getGame().setSudoku(ExampleGrid.getSudoku(ExampleGrid.Level.maximum));
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
}
