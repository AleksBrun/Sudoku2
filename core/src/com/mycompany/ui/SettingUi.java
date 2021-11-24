package com.mycompany.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mycompany.mygame.AppPreference;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;
import com.mycompany.screens.SettingScreen;

public class SettingUi extends Stage {

    private final Image color;
    private int indexColor = AppPreference.getColorUI();

    public SettingUi(Viewport viewport, final ResourceManager manager, final SettingScreen settingScreen){
        super(viewport);

        Table table = new Table();
        table.setFillParent(true);
        addActor(table);

        Label title = new Label("Настройки", manager.getSkin(), Setting.font_white_big, Color.BLACK);

        final TextButton color_ui = new TextButton("Цвет UI", manager.getSkin(), Setting.button_style);


        TextButton menu = new TextButton("Меню", manager.getSkin(), Setting.button_style);

        color = new Image(manager.getIconTexture(ResourceManager.ICON_STAR));

        table.top();
        table.add(title).padTop(10).row();
        table.add(color).top().padTop(40).row();
        table.add(color_ui).top().padTop(10).fillX().row();
        table.add(menu).top().padTop(10).fillX().row();


        color_ui.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                manager.setUiNew(indexColor++);
                color.setDrawable(new TextureRegionDrawable(manager.getIconTexture(ResourceManager.ICON_STAR)));
                color_ui.setChecked(false);
                if (indexColor > 5) indexColor = 0;
            }
        });
        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                settingScreen.dispose();
                settingScreen.getGame().setStateScreen(MyGdxGame.State.MENU);
            }
        });
    }
}
