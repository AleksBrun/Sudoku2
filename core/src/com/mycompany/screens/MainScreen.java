package com.mycompany.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mycompany.draw.DrawGame;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.Setting;
import com.mycompany.ui.MainUi;
import com.mycompany.update.UpdateGame;

public class MainScreen implements Screen {

    private final MyGdxGame game;
    private MainUi mainUi;
    private final UpdateGame updateGame;
    private final DrawGame drawGame;

    public MainScreen(MyGdxGame game) {
        this.game = game;
        updateGame = new UpdateGame(this);
        drawGame = new DrawGame(updateGame);
    }

    @Override
    public void show() {
        mainUi = new MainUi(new FitViewport(Setting.width_Ui, Setting.getHeight_Ui()), game.getManager(), this);
        InputMultiplexer multiplexer = new InputMultiplexer(mainUi, updateGame);
        Gdx.input.setInputProcessor(multiplexer);
        updateGame.playGame(game.getSudoku());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.8f, .8f, .8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        drawGame.draw(game.getBatch(), game.getShapeRenderer());
        mainUi.draw();
    }

    public MyGdxGame getGame() {
        return game;
    }

    public UpdateGame getUpdateGame() {
        return updateGame;
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
        mainUi.dispose();
    }
}
