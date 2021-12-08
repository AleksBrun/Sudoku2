package com.mycompany.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;
import com.mycompany.screens.HelloScreen;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class HelloUi extends Stage {

    public HelloUi(Viewport viewport, ResourceManager manager, final HelloScreen helloScreen){
        super(viewport);

        Table table = new Table();
        table.setBackground(new TextureRegionDrawable(manager.getTextureRegion(ResourceManager.heart)));
        table.setFillParent(true);
        addActor(table);

        TextButton menu = new TextButton(Setting.name_menu_button, manager.getSkin(), ResourceManager.button_style);
        Label s  = new Label("", manager.getSkin(), ResourceManager.label_style_small);

        s.setWrap(true);
        table.top();
        table.add(s).expand().fill().top().left().row();
        table.add(menu).padBottom(20);

        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                helloScreen.dispose();
               // helloScreen.game.setStateScreen(MyGdxGame.State.MENU);
            }
        });
    }
}
