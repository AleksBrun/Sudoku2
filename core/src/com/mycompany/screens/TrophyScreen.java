package com.mycompany.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.Setting;
import com.mycompany.ui.TrophyUi;

public class TrophyScreen implements Screen {

    private MyGdxGame game;
    private TrophyUi trophyUi;

    public TrophyScreen(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        trophyUi = new TrophyUi(new FitViewport(Setting.width_menu_ui, Setting.getHeight_Ui(Setting.width_menu_ui)), game.getManager(), this);
        Gdx.input.setInputProcessor(trophyUi);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(.8f, .8f, .8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        trophyUi.draw();
    }

    @Override
    public void resize(int i, int i1) {

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
        trophyUi.dispose();
    }

    public MyGdxGame getGame() {
        return game;
    }
}
