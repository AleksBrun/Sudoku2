package com.mycompany.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mycompany.draw.DrawGame;
import com.mycompany.mygame.AppPreference;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.Setting;
import com.mycompany.ui.MainUi;
import com.mycompany.unils.Clock;
import com.mycompany.unils.LoaderSudoku;
import com.mycompany.update.UpdateGame;
import com.badlogic.gdx.math.GridPoint2;
import com.mycompany.unils.TimeUtils;

public class MainScreen implements Screen {

    private final MyGdxGame game;
    private MainUi mainUi;
    private final UpdateGame updateGame;
    private final DrawGame drawGame;
    private final Clock clock = new Clock();

    public MainScreen(MyGdxGame game) {
        this.game = game;
        updateGame = new UpdateGame(this);
        drawGame = new DrawGame(updateGame);
    }

    @Override
    public void show() {
        mainUi = new MainUi(new FitViewport(Setting.width_main_ui, Setting.getHeight_Ui(Setting.width_main_ui)), game.getManager(), this);
        mainUi.setStars(AppPreference.getDifficultyLevel());
        InputMultiplexer multiplexer = new InputMultiplexer(mainUi, updateGame);
        Gdx.input.setInputProcessor(multiplexer);
        updateGame.playGame(game.getSudoku());
        clock.setTime(AppPreference.getTimeMinute(), AppPreference.getTimeSecond());
        game.getManager().getMusic().setVolume(AppPreference.getMusicVolume());
        game.getManager().getMusic().setLooping(true);
        if (AppPreference.isMusicEnabled()){
            game.getManager().getMusic().play();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.8f, .8f, .8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        clock.update();
        mainUi.setTime(clock.getMinute(), clock.getSecond());
        drawGame.draw(game.getBatch());
        mainUi.draw();
    }

    public MyGdxGame getGame() {
        return game;
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        updateGame.pause();
        if (updateGame.isPause()){
            clock.pause();
            game.getManager().getMusic().pause();
        } else {
            clock.start();
            if (AppPreference.isMusicEnabled()){
                game.getManager().getMusic().play();
            }
        }
    }

    @Override
    public void resume() {
        System.out.println("resume|");

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        AppPreference.setTimeMinute(clock.getMinute());
        AppPreference.setTimeSecond(clock.getSecond());
        int allTime = TimeUtils.setTime(clock.getMinute(), clock.getSecond());
        allTime += AppPreference.getAllTime();
        AppPreference.setAllTeme(allTime);
        AppPreference.saveSudoku(LoaderSudoku.getStringSudoku(updateGame.getGrid().getSudoku()));
        game.getManager().getMusic().pause();
        
    }

    @Override
    public void dispose() {
        mainUi.dispose();
    }

    public MainUi getMainUi() {
        return mainUi;
    }
}
