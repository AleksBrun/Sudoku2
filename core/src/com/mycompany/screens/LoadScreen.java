package com.mycompany.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.Parameter;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;

public class LoadScreen extends CommonScreen{

    public LoadScreen(final MyGdxGame game) {
        super(720, game);
    }

    @Override
    public void show() {
        super.show();

        Array<Parameter> parameters = game.getParameters();
        for (int index = 0; index < parameters.size; index++){
            TextButton label = new TextButton("     "+(index+1)+"   "+parameters.get(index).data+"     ", getManager().getSkin(), ResourceManager.button_style);
            label.setName(""+index);
            table.add(label).padTop(10).fillX().row();

            label.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println(event.getRelatedActor().getZIndex());
                }
            });
        }

        TextButton menu = new TextButton(Setting.name_menu_button, getManager().getSkin(), ResourceManager.button_style);

        table.add(menu).padTop(20);

        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setStateScreen(MyGdxGame.State.MENU);
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.draw();
    }
}
