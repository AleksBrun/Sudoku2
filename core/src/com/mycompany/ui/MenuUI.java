package com.mycompany.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mycompany.mygame.AppPreference;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;
import com.mycompany.screens.MenuScreen;
import com.mycompany.unils.LoaderSudoku;

public class MenuUI extends Stage {

    public MenuUI(Viewport viewport, final ResourceManager manager, final MenuScreen menuScreen){
        super(viewport);

        Table table = new Table();
        table.setBackground(new TextureRegionDrawable(manager.getTextureRegion(ResourceManager.fon_menu)));
        table.setFillParent(true);
        addActor(table);

        TextButton continuation = new TextButton(Setting.name_continuation_button, manager.getSkin(), ResourceManager.button_style);
        continuation.setVisible(AppPreference.isContinuationEnabled());
        TextButton start = new TextButton(Setting.name_play_button, manager.getSkin(), ResourceManager.button_style);
        TextButton exit = new TextButton(Setting.name_exit_button, manager.getSkin(), ResourceManager.button_style);
        TextButton color = new TextButton(Setting.name_setting_button, manager.getSkin(), ResourceManager.button_style);
        TextButton trophy = new TextButton(Setting.name_trophy_button, manager.getSkin(), ResourceManager.button_style);
        TextButton test = new TextButton(Setting.name_love_button, manager.getSkin(), ResourceManager.button_style);
        TextButton statistics = new TextButton(Setting.name_statistics_button, manager.getSkin(), ResourceManager.button_style);

        table.bottom();
        table.add(continuation).row();
        table.add(start).fillX().padTop(Setting.pad_ui_menu).row();
        table.add(color).fillX().padTop(Setting.pad_ui_menu).row();
        table.add(test).fillX().padTop(Setting.pad_ui_menu).row();
        table.add(statistics).fillX().padTop(Setting.pad_ui_menu).row();
        table.add(trophy).fillX().padTop(Setting.pad_ui_menu).row();
        table.add(exit).fillX().padTop(Setting.pad_ui_menu).padBottom(Setting.pad_ui_menu_bottom);

        start.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuScreen.dispose();
                menuScreen.getGame().setStateScreen(MyGdxGame.State.LEVEL);
            }
        });
        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuScreen.dispose();
                Gdx.app.exit();
            }
        });
        color.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuScreen.dispose();
                menuScreen.getGame().setStateScreen(MyGdxGame.State.SETTING);
            }
        });
        test.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuScreen.dispose();
                menuScreen.getGame().setStateScreen(MyGdxGame.State.HELLO);
            }
        });
        continuation.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuScreen.dispose();
                menuScreen.getGame().setSudoku(LoaderSudoku.getIntegerSudoku(AppPreference.loadSudoku()));
                menuScreen.getGame().setStateScreen(MyGdxGame.State.MAIN);
            }
        });
        trophy.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuScreen.dispose();
                menuScreen.getGame().setStateScreen(MyGdxGame.State.TROPHY);
            }
        });
        statistics.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                menuScreen.dispose();
                menuScreen.getGame().setStateScreen(MyGdxGame.State.STATISTICS);
            }
        });
    }
}
