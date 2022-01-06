package com.mycompany.screens;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mycompany.mygame.*;
import com.mycompany.utils.Utils;

public class InfoScreen extends CommonScreen{
    public InfoScreen(MyGdxGame _game) {
        super(720, _game);
    }

    @Override
    public void show() {
        super.show();

        float size = Setting.size_icon/2f;
        Parameter parameter = game.getParameter();

        table.setBackground(new TextureRegionDrawable(getManager().getTextureRegionAtlas(ResourceManager.background4)));

        final Label labelTitle = new Label(Setting.label_pause, getSkin(), ResourceManager.label_style_big);

        final Image imageStar = new Image(getManager().getTextureRegionAtlas(ResourceManager.star));
        final Label labelStar = new Label(String.valueOf(AppPreference.getAllStars()), getSkin(), ResourceManager.label_style_big);

        final Image imageCoin = new Image(getManager().getTextureRegionAtlas(ResourceManager.coin));
        final Label labelCoin = new Label(String.valueOf(parameter.coin), getSkin(), ResourceManager.label_style_big);

        final Image imageBonus = new Image(getManager().getTextureRegionAtlas(ResourceManager.chest));
        final Label labelBonus = new Label(String.valueOf(parameter.bonus), getSkin(), ResourceManager.label_style_big);

        final Image imageError = new Image(getManager().getTextureRegionAtlas(ResourceManager.skull));
        final Label labelError = new Label(String.valueOf(parameter.error), getSkin(), ResourceManager.label_style_big);

        final Image imageLive = new Image(getManager().getTextureRegionAtlas(ResourceManager.heart1));
        final Label labelLive = new Label(String.valueOf(parameter.max_error - parameter.error), getSkin(), ResourceManager.label_style_big);

        final GridPoint2 time = Utils.getTime(parameter.time);
        final Image imageTime = new Image(getManager().getTextureRegionAtlas(ResourceManager.clock));
        final Label labelTime = new Label(time.y+":"+time.x, getSkin(), ResourceManager.label_style_big);

        final Image imageProgress = new Image(getManager().getTextureRegionAtlas(ResourceManager.progress));
        final Label labelProgress = new Label(parameter.progress+"%", getSkin(), ResourceManager.label_style_big);

        TextButton continuum = new TextButton(Setting.name_continuation_button, getSkin(), ResourceManager.button_style);

        table.add(labelTitle).colspan(2);
        table.row();
        table.add(imageStar).width(size).height(size).padTop(40);
        table.add(labelStar);
        table.row();
        table.add(imageCoin).width(size).height(size).padTop(10);
        table.add(labelCoin);
        table.row();
        table.add(imageBonus).width(size).height(size).padTop(10);
        table.add(labelBonus);
        table.row();
        table.add(imageError).width(size).height(size).padTop(10);
        table.add(labelError);
        table.row();
        table.add(imageLive).width(size).height(size).padTop(10);
        table.add(labelLive);
        table.row();
        table.add(imageTime).width(size).height(size).padTop(10);
        table.add(labelTime);
        table.row();
        table.add(imageProgress).width(size).height(size).padTop(10);
        table.add(labelProgress);
        table.row();
        table.add(continuum).colspan(2).padTop(40);

        continuum.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setStateScreen(MyGdxGame.State.MAIN);
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.draw();
    }
}
