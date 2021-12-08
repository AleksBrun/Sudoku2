package com.mycompany.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mycompany.mygame.MyGdxGame;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mycompany.mygame.ResourceManager;

public class CommonScreen extends ScreenAdapter {

    public Stage stage;
    public MyGdxGame game;
    public Table table;
    private final float widthPort;
    
    public CommonScreen(float _widthPort, MyGdxGame _game){
        this.game = _game;
        this.widthPort = _widthPort;
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(widthPort, widthPort/((float)Gdx.graphics.getWidth()/Gdx.graphics.getHeight())));
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }
    
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.8f, .8f, .8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (stage != null) stage.draw();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
    
    public ResourceManager getManager(){
        return game.getManager();
    }

    public Skin getSkin(){
        return getManager().getSkin();
    }
}
