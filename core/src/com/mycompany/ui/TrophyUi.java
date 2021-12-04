package com.mycompany.ui;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;
import com.mycompany.screens.TrophyScreen;

public class TrophyUi extends Stage {

    private Image[] cells = new Image[9];

    public TrophyUi(Viewport viewport, ResourceManager manager, final TrophyScreen trophyScreen) {
        super(viewport);

        Table table = new Table();
        table.setFillParent(true);
        //table.setDebug(true);
        table.setBackground(new TextureRegionDrawable(manager.getTextureRegion(ResourceManager.background)));
        addActor(table);

        for (int i = 0; i < 9; i++) {
            Image cell = new Image(manager.getCup(MathUtils.random(0,2)));
            //cell.setSize(getWidth()/4, getWidth()/4);
            cells[i] = cell;
        }

        TextButton menu = new TextButton(Setting.name_menu_button, manager.getSkin(), ResourceManager.button_style);

        table.add(cells[0]).width(getWidth()/4).height(getWidth()/4).pad(5);
        table.add(cells[1]).width(getWidth()/4).height(getWidth()/4).pad(5);
        table.add(cells[2]).width(getWidth()/4).height(getWidth()/4).pad(5);
        table.row();
        table.add(cells[3]).width(getWidth()/4).height(getWidth()/4).pad(5);
        table.add(cells[4]).width(getWidth()/4).height(getWidth()/4).pad(5);
        table.add(cells[5]).width(getWidth()/4).height(getWidth()/4).pad(5);
        table.row();
        table.add(cells[6]).width(getWidth()/4).height(getWidth()/4).pad(5);
        table.add(cells[7]).width(getWidth()/4).height(getWidth()/4).pad(5);
        table.add(cells[8]).width(getWidth()/4).height(getWidth()/4).pad(5);
        table.row();
        table.add(menu).colspan(3).padTop(20);

        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                trophyScreen.dispose();
                trophyScreen.getGame().setStateScreen(MyGdxGame.State.MENU);
            }
        });
    }
}
