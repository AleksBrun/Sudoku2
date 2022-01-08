package com.mycompany.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mycompany.mygame.*;

public class LoseScreen extends CommonScreen {

    public LoseScreen(MyGdxGame game) {
        super(500, game);
    }

    @Override
    public void show() {
        super.show();
        Parameter parameter = game.getParameter();
        Label title =  new Label("ВЫ ПРОИГРАЛИ",getSkin(), ResourceManager.label_style_big);

        TextButton menu = new TextButton(Setting.name_menu_button, getSkin(), ResourceManager.button_style);

        table.setBackground(new TextureRegionDrawable(getManager().getTextureRegionAtlas(ResourceManager.background4)));

        table.add(title).expand().row();
        table.add(menu).padBottom(20);

        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setStateScreen(MyGdxGame.State.MENU);
            }
        });

        game.deleteParameter(parameter);
    }

    @Override
    public void hide() {
        super.hide();
        AppPreference.setContinuationEnabled(false);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.draw();
    }
    
    
}
