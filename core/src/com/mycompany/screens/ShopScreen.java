package com.mycompany.screens;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;

public class ShopScreen extends CommonScreen{
    public ShopScreen(MyGdxGame _game) {
        super(720, _game);
    }

    @Override
    public void show() {
        super.show();

        table.setBackground(new TextureRegionDrawable(getManager().getTextureRegionAtlas(ResourceManager.background4)));

        
        final Dialog dialog = new Dialog("Test", getSkin(), ResourceManager.window_style);
        dialog.setVisible(false);
        TextButton button = new TextButton("Ok", getSkin(), ResourceManager.button_style);
        TextButton button1 = new TextButton("Canel", getSkin(), ResourceManager.button_style);
        dialog.button(button);
        dialog.button(button1);
        
        

        TextButton menuButton = new TextButton(Setting.name_continuation_button, getSkin(), ResourceManager.button_style);
        TextButton settingButton = new TextButton(Setting.name_setting_button, getSkin(), ResourceManager.button_style);

        table.add(menuButton).row();
        table.add(settingButton).row();
        table.add(dialog).width(300).height(300);

        menuButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setStateScreen(MyGdxGame.State.MAIN);
            }
        });

        settingButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dialog.setVisible(true);
            }
        });

        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dialog.setVisible(false);
            }
        });
        button1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dialog.setVisible(false);
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.draw();
    }
}
