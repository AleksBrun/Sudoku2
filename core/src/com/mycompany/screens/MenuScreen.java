package com.mycompany.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.Setting;
import com.mycompany.ui.MenuUI;

public class MenuScreen implements Screen {

    private final MyGdxGame game;
    private MenuUI menuUI;

    public MenuScreen(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        menuUI = new MenuUI(new FillViewport(Setting.width_menu_ui, Setting.getHeight_Ui(Setting.width_menu_ui)), game.getManager(), this);
        Gdx.input.setInputProcessor(menuUI);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.5f, .5f, .5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        menuUI.draw();
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
        menuUI.dispose();
    }
}
