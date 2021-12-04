package com.mycompany.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;
import com.mycompany.screens.LoseScreen;

public class LoseUi extends Stage {

    public LoseUi(Viewport viewport, ResourceManager manager, final LoseScreen loseScreen) {
        super(viewport);

        Table table = new Table();
        table.setFillParent(true);
        table.setBackground(new TextureRegionDrawable(manager.getTextureRegion(ResourceManager.background)));
        addActor(table);

        Label title =  new Label("ВЫ ПРОИГРАЛИ", manager.getSkin(), ResourceManager.label_style_big);

        TextButton menu = new TextButton(Setting.name_menu_button, manager.getSkin(), ResourceManager.button_style);

        table.add(title).expand().row();
        table.add(menu).padBottom(20);

        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                loseScreen.dispose();
                loseScreen.getGame().setStateScreen(MyGdxGame.State.MENU);
            }
        });
    }
}
