package com.mycompany.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mycompany.mygame.AppPreference;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.Setting;
import com.mycompany.ui.VictoryUI;

public class VictoryScreen implements Screen {

    private MyGdxGame game;
    private VictoryUI victoryUI;

    public VictoryScreen(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        AppPreference.setAllStars(AppPreference.getAllStars()+AppPreference.getDifficultyLevel());
        AppPreference.setContinuationEnabled(false);
        victoryUI =new VictoryUI( new FitViewport(Setting.width_menu_ui, Setting.getHeight_Ui(Setting.width_menu_ui)), game.getManager(), this);
        Gdx.input.setInputProcessor(victoryUI);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.8f, .8f, .8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        victoryUI.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        victoryUI.dispose();
    }

    public MyGdxGame getGame() {
        return game;
    }
}
