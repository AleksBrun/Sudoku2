package com.mycompany.mygame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mycompany.screens.MainScreen;
import com.mycompany.screens.MenuScreen;

public class MyGdxGame extends Game {

	public enum State {MAIN, MENU}
	private MainScreen mainScreen;
	private MenuScreen menuScreen;
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	private ResourceManager manager;

	
	@Override
	public void create () {
		manager = new ResourceManager();
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);
		mainScreen = new MainScreen(this);
		menuScreen = new MenuScreen(this);
		setStateScreen(State.MENU);
	}

	public void setStateScreen(State state){
		switch (state){
			case MAIN: setScreen(mainScreen);
			break;
			case MENU: setScreen(menuScreen);
			break;
			default: break;
		}
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public ResourceManager getManager() {
		return manager;
	}

	public ShapeRenderer getShapeRenderer() {
		return shapeRenderer;
	}

	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
		manager.dispose();
	}
}
