package com.mycompany.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.Setting;
import com.mycompany.ui.SettingUi;

public class SettingScreen implements Screen {

    private final MyGdxGame game;
    private SettingUi settingUi;

    public SettingScreen(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        settingUi = new SettingUi(new FitViewport(Setting.width_Ui, Setting.getHeight_Ui()), game.getManager(), this);
        Gdx.input.setInputProcessor(settingUi);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.8f, .8f, .8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        settingUi.draw();
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
        settingUi.dispose();
    }

    public MyGdxGame getGame() {
        return game;
    }
}
