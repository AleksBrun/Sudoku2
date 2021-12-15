package com.mycompany.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;

public class HelloScreen extends CommonScreen {

    public HelloScreen( MyGdxGame game) {
        super(500, game);
    }

    @Override
    public void show() {
        super.show();
        
        TextButton menu = new TextButton(Setting.name_menu_button, game.getManager().getSkin(), ResourceManager.button_style);
        
        table.setBackground(new TextureRegionDrawable(game.getManager().getTextureRegion(ResourceManager.love)));
        table.top();
        table.add(menu);

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
