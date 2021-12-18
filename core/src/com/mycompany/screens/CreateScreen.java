package com.mycompany.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mycompany.models.GroupImage;
import com.mycompany.mygame.AppPreference;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;

public class CreateScreen extends CommonScreen{
    public CreateScreen( MyGdxGame _game) {
        super(500, _game);
    }

    @Override
    public void show() {
        super.show();

        final GroupImage star = new GroupImage(5, Setting.size_icon/2, getManager().getTextureAtlas(ResourceManager.ICON_STAR));
        star.setStars(getDifficultyLevel(AppPreference.getMissingDigits()));

        final Label levelInfo = new Label("Сложность", getSkin(), ResourceManager.label_style_big);

        final Label sliderInfo = new Label(""+AppPreference.getMissingDigits(), getSkin(), ResourceManager.label_style_normal);

        final Slider slider = new Slider(40, 55, 3, false, getSkin(), ResourceManager.slider_style_hor);
        slider.setValue(AppPreference.getMissingDigits());
        final TextButton menu = new TextButton(Setting.name_menu_button, getSkin(), ResourceManager.button_style);

        final TextButton play = new TextButton(Setting.name_play_button, getSkin(), ResourceManager.button_style);

        table.setBackground(new TextureRegionDrawable(getManager().getTextureRegionAtlas(ResourceManager.background1)));
        table.add(levelInfo).colspan(2);
        table.row();
        table.add(star).colspan(2).padTop(20).fillX().center();
        table.row();
        table.add(slider).padTop(10).fillX();
        table.add(sliderInfo).padTop(10).padLeft(10).fillX();
        table.row();
        table.add(play).colspan(2).fillX().padTop(10).fillX();
        table.row();
        table.add(menu).colspan(2).fillX().padTop(10).fillX();

        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setStateScreen(MyGdxGame.State.MENU);
            }
        });
        play.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                int mission_digits = AppPreference.getMissingDigits();
                AppPreference.setDifficultyLevel(getDifficultyLevel(mission_digits));
                game.createSudoku(AppPreference.getMissingDigits());
                game.setStateScreen(MyGdxGame.State.MAIN);
            }
        });
        slider.addListener(new InputListener() {
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                AppPreference.setMissingDigits((int)slider.getValue());
                AppPreference.setDifficultyLevel(getDifficultyLevel(AppPreference.getMissingDigits()));
                sliderInfo.setText(String.valueOf(AppPreference.getMissingDigits()));
                star.setStars(AppPreference.getDifficultyLevel());
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.draw();
    }

    private int getDifficultyLevel(int mission_digits) {
        if (mission_digits >= 40 && mission_digits <=41) {
            return 1;
        } else if (mission_digits >= 42 && mission_digits <=44) {
            return 2;
        } else if (mission_digits >= 45 && mission_digits <=47) {
            return 3;
        } else if (mission_digits >= 48 && mission_digits < 50) {
            return 4;
        } else if (mission_digits >=51 && mission_digits < 53) {
            return 5;
        }
       return 6;
    }
}
