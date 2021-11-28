package com.mycompany.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
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

        Label title = new Label(Setting.name_setting_button, manager.getSkin(), ResourceManager.label_style_normal);

        final TextButton color_ui = new TextButton(Setting.name_colorUi_button, manager.getSkin(), ResourceManager.button_style);


        TextButton menu = new TextButton(Setting.name_menu_button, manager.getSkin(), ResourceManager.button_style);

        color = new Image(manager.getTextureAtlas(ResourceManager.ICON_STAR));

        final Slider slider = new Slider(30, 70, 5, false, manager.getSkin(), ResourceManager.slider_style_hor);

        table.top();
        table.add(title).padTop(10).row();
        table.add(color).top().padTop(40).row();
        table.add(color_ui).top().padTop(10).fillX().row();
        table.add(slider).padTop(10).row();
        table.add(menu).top().padTop(10).fillX().row();


        color_ui.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                manager.setUiNew(indexColor++);
                color.setDrawable(new TextureRegionDrawable(manager.getTextureAtlas(ResourceManager.ICON_STAR)));
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
        slider.addListener(new InputListener() {
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                AppPreference.setMissingDigits((int)slider.getValue());
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.print(AppPreference.getMissingDigits());
            }
        });
    }
}
