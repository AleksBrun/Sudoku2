package com.mycompany.screens;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.mycompany.models.GroupImage;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.Parameter;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;
import com.mycompany.utils.Utils;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;

public class LoadScreen extends CommonScreen{

    public LoadScreen(final MyGdxGame game) {
        super(720, game);
    }

    @Override
    public void show() {
        super.show();
        int index = 0;
        float size = Setting.size_icon;
        final Array<Parameter> parameters = game.getParameters();
        Array<Label> labelArray = new Array<Label>();
        for (Parameter parameter:parameters){
            GridPoint2 time = Utils.getTime(parameter.time);
            final Label label = new Label((parameter.index+1)+"   "+parameter.data+"    "+ parameter.progress+"%", getManager().getSkin(), ResourceManager.label_style_big);
            final Label labelTime = new Label(time.y+":"+time.x, getSkin(), ResourceManager.label_style_big);
            table.add(label).padTop(20).fillX();
            table.add(labelTime).padTop(20).padLeft(10);
            table.add(new GroupImage(parameter.difficulty_level, size/3, getManager().getTextureRegionAtlas(ResourceManager.star))).left().padLeft(10).padTop(20);
            table.row();

            final int finalIndex = index;
            label.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    start(label.getY(), parameters.get(finalIndex));
                }
            });
            labelArray.add(label);
            index++;
        }

        TextButton menu = new TextButton(Setting.name_menu_button, getManager().getSkin(), ResourceManager.button_style);
        table.setBackground(new TextureRegionDrawable(getManager().getTextureRegionAtlas(ResourceManager.background4)));
        table.add(menu).padTop(100).colspan(2);
        
        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setStateScreen(MyGdxGame.State.MENU);
            }
        });
    }

    private void start(float _y, final Parameter parameter){
        final Dialog dialog = new Dialog("", getSkin(), ResourceManager.window_style);
        stage.addActor(dialog);
        dialog.setSize(450, 150);
        dialog.setPosition(stage.getWidth()/2-dialog.getWidth()/2, _y);
        dialog.text(new Label("Что сделать с", getSkin(), ResourceManager.label_style_big));
        dialog.text(new Label("Sudoku "+(parameter.index+1)+"?", getSkin(), ResourceManager.label_style_big));
        TextButton loadButton = new TextButton("  Загрузить", getSkin(), ResourceManager.button_style);
        TextButton deleteButton = new TextButton("  Удалить",getSkin(), ResourceManager.button_style);
        TextButton closeButton = new TextButton("  Закрыть", getSkin(), ResourceManager.button_style);
        dialog.button(loadButton).padBottom(20);
        dialog.button(deleteButton).padBottom(20);
        dialog.button(closeButton).padBottom(20);
        
        loadButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                dispose();
                game.createSudoku(parameter, false);
                game.setStateScreen(MyGdxGame.State.MAIN);
            }
        });
        deleteButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.deleteParameter(parameter);
                dispose();
                game.setStateScreen(MyGdxGame.State.LOAD);
            }
        });
        closeButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                dialog.cancel();
                dialog.remove();
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.draw();
    }
}
