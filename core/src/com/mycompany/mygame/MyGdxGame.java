package com.mycompany.mygame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mycompany.screens.LevelScreen;
import com.mycompany.screens.MainScreen;
import com.mycompany.screens.MenuScreen;
import com.mycompany.screens.SettingScreen;

public class MyGdxGame extends Game {

	public enum State {MAIN, MENU, LEVEL, SETTING}
	private MainScreen mainScreen;
	private MenuScreen menuScreen;
	private LevelScreen levelScreen;
	private SettingScreen settingScreen;
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	private ResourceManager manager;
	private int[][] sudoku;

	
	@Override
	public void create () {
		manager = new ResourceManager();
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);
		setStateScreen(State.MENU);
	}

	public void setStateScreen(State state){
		switch (state){
			case MAIN: if (mainScreen == null) mainScreen =new MainScreen(this);
			setScreen(mainScreen);
			break;
			case MENU: if (menuScreen == null) menuScreen = new MenuScreen(this);
			setScreen(menuScreen);
			break;
			case LEVEL: if (levelScreen == null) levelScreen = new LevelScreen(this);
			setScreen(levelScreen);
			break;
			case SETTING: if (settingScreen == null) settingScreen = new SettingScreen(this);
			setScreen(settingScreen);
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

	public int[][] getSudoku() {
		return sudoku;
	}

	public void setSudoku(int[][] sudoku) {
		this.sudoku = sudoku;
	}

	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
		manager.dispose();
	}
}
