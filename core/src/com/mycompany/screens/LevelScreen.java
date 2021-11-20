package com.mycompany.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mycompany.mygame.ExampleGrid;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.Setting;
import com.mycompany.ui.LevelUI;

public class LevelScreen implements Screen {

    private final MyGdxGame game;
    private LevelUI levelUI;

    public LevelScreen(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        levelUI = new LevelUI(new FitViewport(Setting.width_Ui, Setting.getHeight_Ui()), game.getManager(), this);
        Gdx.input.setInputProcessor(levelUI);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.8f, .8f, .8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        levelUI.draw();
    }

    public MyGdxGame getGame() {
        return game;
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
        levelUI.dispose();
    }
}
