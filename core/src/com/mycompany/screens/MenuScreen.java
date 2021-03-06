package com.mycompany.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mycompany.mygame.AppPreference;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;


public class MenuScreen extends CommonScreen {

    public MenuScreen(MyGdxGame game) {
        super(500, game);
    }

    @Override
    public void show() {
        super.show();
        AppPreference.setContinuationEnabled(!game.isEmpty());
        TextButton continuation = new TextButton(Setting.name_continuation_button, getSkin(), ResourceManager.button_style);
        continuation.setVisible(AppPreference.isContinuationEnabled());
        TextButton load = new TextButton(Setting.load_sudoku, getSkin(), ResourceManager.button_style);
        TextButton start = new TextButton(Setting.name_play_button, getSkin(), ResourceManager.button_style);
        TextButton exit = new TextButton(Setting.name_exit_button, getSkin(), ResourceManager.button_style);
        TextButton color = new TextButton(Setting.name_setting_button, getSkin(), ResourceManager.button_style);
        TextButton trophy = new TextButton(Setting.name_trophy_button, getSkin(), ResourceManager.button_style);
        TextButton test = new TextButton(Setting.name_love_button, getSkin(), ResourceManager.button_style);
        TextButton statistics = new TextButton(Setting.name_statistics_button, getSkin(), ResourceManager.button_style);

        table.setBackground(new TextureRegionDrawable(getManager().getBackground(ResourceManager.background2)));
        table.bottom().right().padRight(10).padBottom(10);
        table.add(continuation).row();
        table.add(load).fillX().padTop(Setting.pad_ui_menu).row();
        table.add(start).fillX().padTop(Setting.pad_ui_menu).row();
        table.add(color).fillX().padTop(Setting.pad_ui_menu).row();
        table.add(test).fillX().padTop(Setting.pad_ui_menu).row();
        table.add(statistics).fillX().padTop(Setting.pad_ui_menu).row();
        table.add(trophy).fillX().padTop(Setting.pad_ui_menu).row();
        table.add(exit).fillX().padTop(Setting.pad_ui_menu).padBottom(Setting.pad_ui_menu_bottom);

        start.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setStateScreen(MyGdxGame.State.LEVEL);
            }
        });
        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                Gdx.app.exit();
            }
        });
        color.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setStateScreen(MyGdxGame.State.SETTING);
            }
        });
        test.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setStateScreen(MyGdxGame.State.HELLO);
            }
        });
        continuation.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setPeekParameter();
                game.setStateScreen(MyGdxGame.State.MAIN);
            }
        });
        trophy.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setStateScreen(MyGdxGame.State.TROPHY);
            }
        });
        statistics.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                dispose();
                game.setStateScreen(MyGdxGame.State.STATISTICS);
            }
        });
        load.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setStateScreen(MyGdxGame.State.LOAD);
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.draw();
    }
    
    
}
