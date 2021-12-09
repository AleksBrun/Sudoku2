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
import com.mycompany.utils.Clock;
import com.mycompany.utils.LoaderSudoku;
import com.mycompany.update.UpdateGame;
import com.mycompany.utils.TimeUtils;

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

        updateGame.playGame(game.getSudoku());

        clock.setTime(AppPreference.getTimeMinute(), AppPreference.getTimeSecond());

        game.getManager().getMusic().setVolume(AppPreference.getMusicVolume());
        game.getManager().getMusic().setLooping(true);

        InputMultiplexer multiplexer = new InputMultiplexer(mainUi, updateGame);
        Gdx.input.setInputProcessor(multiplexer);
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
    }

    @Override
    public void hide() {
        saveData();
        Gdx.input.setInputProcessor(null);
        game.getManager().getMusic().pause();
    }

    private void saveData(){
        AppPreference.setTimeMinute(clock.getMinute());
        AppPreference.setTimeSecond(clock.getSecond());
        int allTime = TimeUtils.setTime(clock.getMinute(), clock.getSecond());
        allTime += AppPreference.getAllTime();
        AppPreference.setAllTime(allTime);
        AppPreference.saveSudoku(LoaderSudoku.getStringSudoku(updateGame.getGrid().getSudoku()));
    }

    @Override
    public void dispose() {
        mainUi.dispose();
    }

    public MainUi getMainUi() {
        return mainUi;
    }
}
