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
            final Label label = new Label((parameter.index)+"   "+parameter.data+"    "+ parameter.progress+"%", getManager().getSkin(), ResourceManager.label_style_big);
            final Label labelTime = new Label(time.y+":"+time.x, getSkin(), ResourceManager.label_style_big);
            table.add(label).padTop(20).fillX();
            table.add(labelTime).padTop(20).padLeft(10);
            table.add(new GroupImage(parameter.difficulty_level, size/3, getManager().getTextureRegionAtlas(ResourceManager.star))).left().padLeft(10).padTop(20);
            table.row();

            final int finalIndex = index;
            label.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    start(parameters.get(finalIndex));
                }
            });
            labelArray.add(label);
            index++;
        }

        TextButton menu = new TextButton(Setting.name_menu_button, getManager().getSkin(), ResourceManager.button_style);

        table.setBackground(new TextureRegionDrawable(getManager().getTextureRegionAtlas(ResourceManager.background4)));
        table.add(menu).padTop(20).colspan(2);



        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setStateScreen(MyGdxGame.State.MENU);
            }
        });

    }

    private void start(Parameter parameter){
        dispose();
        game.createSudoku(parameter, false);
        game.setStateScreen(MyGdxGame.State.MAIN);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.draw();
    }
}
