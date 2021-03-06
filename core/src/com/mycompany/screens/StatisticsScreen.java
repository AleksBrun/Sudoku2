package com.mycompany.screens;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mycompany.mygame.AppPreference;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.Parameter;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;
import com.mycompany.utils.Utils;

public class StatisticsScreen extends CommonScreen {

    public StatisticsScreen(MyGdxGame game) {
        super(600, game);
    }

    @Override
    public void show() {
        super.show();

        Parameter parameter = game.getParameter();

        final Label title = new Label("Статистика", getSkin(), ResourceManager.label_style_big);
        
        final Label infoGame = new Label((parameter == null? "Игра не открыта":"Suboku "+(parameter.index+1)), getSkin(), ResourceManager.label_style_normal);
        
        final Label allGames = new Label("Всего создано  Sudoku  "+AppPreference.getAllGames(), getSkin(), ResourceManager.label_style_normal);

        final Label allStarLabel = new Label("Получено звезд  "+ AppPreference.getAllStars(), getSkin(), ResourceManager.label_style_normal);
        
        final Label starLabel = new Label("Звезд за игру  "+ (parameter != null? parameter.stars:0), getSkin(), ResourceManager.label_style_normal);

        final GridPoint2 allTime = Utils.getTime(AppPreference.getAllTime());
        final Label allTimeLabel = new Label("Общее время игры  "+allTime.x+":"+allTime.y, getSkin(), ResourceManager.label_style_normal);

        final GridPoint2 timeGame = Utils.getTime(parameter != null?parameter.time:0);
        final Label timeLabel = new Label("Время текущей игры   "+timeGame.y+":"+timeGame.x, getSkin(), ResourceManager.label_style_normal);

        final Label allErrorLabel = new Label("Сделано ошибок  "+AppPreference.getAllError(), getSkin(), ResourceManager.label_style_normal);

        final Label errorLabel = new Label("Сделано ошибок за игру  "+(parameter != null?parameter.error:0), getSkin(), ResourceManager.label_style_normal);

        final TextButton menu = new TextButton(Setting.name_menu_button, getSkin(), ResourceManager.button_style);

        table.setBackground(new TextureRegionDrawable(getManager().getTextureRegionAtlas(ResourceManager.background4)));
        table.add(title);
        table.row();
        table.add(infoGame).padTop(10);
        table.row();
        table.add(allStarLabel).padTop(30);
        table.row();
        
        table.add(allGames).padTop(10);
        table.row();
        table.add(starLabel).padTop(10);
        table.row();
        table.add(allTimeLabel).padTop(10);
        table.row();
        table.add(timeLabel).padTop(10);
        table.row();
        table.add(errorLabel).padTop(10);
        table.row();
        table.add(allErrorLabel).padTop(10);
        table.row();
        table.add(menu).padTop(10);

        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
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
