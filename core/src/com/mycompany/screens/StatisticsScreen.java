package com.mycompany.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.ui.StatisticsUi;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mycompany.mygame.Setting;

public class StatisticsScreen implements Screen {

    private MyGdxGame game;
    private StatisticsUi statisticsUi;

    public StatisticsScreen(MyGdxGame game) {
        this.game = game;
    }
    
    public MyGdxGame getGame() {
        return game;
    }
    @Override
    public void show() {
        statisticsUi = new StatisticsUi(new FitViewport(Setting.width_main_ui, Setting.getHeight_Ui(Setting.width_main_ui)), game.getManager(), this);
        Gdx.input.setInputProcessor(statisticsUi);
    }

    @Override
    public void render(float p1) {
        Gdx.gl.glClearColor(.8f, .8f, .8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        statisticsUi.draw();
    }

    @Override
    public void resize(int p1, int p2) {
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
        statisticsUi.dispose();
    }

    
    
    
}
