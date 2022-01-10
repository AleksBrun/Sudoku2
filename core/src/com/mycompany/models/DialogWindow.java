package com.mycompany.models;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mycompany.mygame.ResourceManager;

public class DialogWindow extends Group {

    private Label title;

    public DialogWindow(float width, float height, ResourceManager manager){
        setSize(width, height);
        Table table = new Table();
        table.setFillParent(true);
        addActor(table);

        title = new Label("Действие", manager.getSkin(), ResourceManager.label_style_big);
        TextButton yesButton = new TextButton("Загрузить", manager.getSkin(), ResourceManager.button_style);
        TextButton noButton = new TextButton("Удалить", manager.getSkin(), ResourceManager.button_style);

        table.setBackground(new TextureRegionDrawable(manager.getTextureRegionAtlas(ResourceManager.background3)));
        table.add(title).row();
        table.add(yesButton).fillX().row();
        table.add(noButton).fillX();

        yesButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        noButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

    }
}
