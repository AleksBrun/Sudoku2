package com.mycompany.mygame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mycompany.screens.*;

public class MyGdxGame extends Game {

	public enum State {MAIN, MENU, LEVEL, SETTING, HELLO, VICTORY, LOSE,TROPHY}
	private MainScreen mainScreen;
	private MenuScreen menuScreen;
	private LevelScreen levelScreen;
	private SettingScreen settingScreen;
	private HelloScreen helloScreen;
	private VictoryScreen victoryScreen;
	private LoseScreen loseScreen;
	private TrophyScreen trophyScreen;
	private SpriteBatch batch;
	private ResourceManager manager;
	private int[][] sudoku;

	@Override
	public void create () {
		manager = new ResourceManager();
		batch = new SpriteBatch();
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
			case HELLO: if (helloScreen == null) helloScreen = new HelloScreen(this);
			setScreen(helloScreen);
			break;
			case VICTORY: if (victoryScreen == null) victoryScreen = new VictoryScreen(this);
			setScreen(victoryScreen);
			break;
			case LOSE: if (loseScreen == null) loseScreen = new LoseScreen(this);
			setScreen(loseScreen);
			break;
			case TROPHY: if (trophyScreen == null) trophyScreen = new TrophyScreen(this);
			setScreen(trophyScreen);
			break;
		}
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public ResourceManager getManager() {
		return manager;
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
		manager.dispose();
	}
}
