package com.mycompany.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;
import com.mycompany.screens.HelloScreen;

public class HelloUi extends Stage {

    public HelloUi(Viewport viewport, ResourceManager manager, final HelloScreen helloScreen){
        super(viewport);

        Image fonImage = new Image(manager.getTextureRegion(ResourceManager.heart));
        fonImage.setBounds(0, 0, getWidth(), getHeight());
        addActor(fonImage);

        Table table = new Table();
        table.setFillParent(true);
        addActor(table);

        TextButton menu = new TextButton(Setting.name_menu_button, manager.getSkin(), ResourceManager.button_style);

        table.bottom();
        table.add(menu).padBottom(20);

        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                helloScreen.dispose();
                helloScreen.getGame().setStateScreen(MyGdxGame.State.MENU);
            }
        });
    }
}
