package com.mycompany.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;
import com.mycompany.screens.SettingScreen;

public class SettingUi extends Stage {

    public SettingUi(Viewport viewport, ResourceManager manager, final SettingScreen settingScreen){
        super(viewport);

        Table table = new Table();
        table.setFillParent(true);
        addActor(table);

        TextButton menu = new TextButton("Меню", manager.getSkin(), Setting.rus_white_big);

        table.add(menu);

        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                settingScreen.dispose();
                settingScreen.getGame().setStateScreen(MyGdxGame.State.MENU);
            }
        });
    }
}
