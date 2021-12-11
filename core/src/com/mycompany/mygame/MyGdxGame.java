package com.mycompany.mygame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mycompany.screens.HelloScreen;
import com.mycompany.screens.LevelScreen;
import com.mycompany.screens.LoseScreen;
import com.mycompany.screens.MainScreen;
import com.mycompany.screens.MenuScreen;
import com.mycompany.screens.SettingScreen;
import com.mycompany.screens.StatisticsScreen;
import com.mycompany.screens.TrophyScreen;
import com.mycompany.screens.VictoryScreen;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MyGdxGame extends Game {

	public enum State {MAIN, MENU, LEVEL, SETTING, HELLO, VICTORY, LOSE,TROPHY, STATISTICS}
	private MainScreen mainScreen;
	private MenuScreen menuScreen;
	private LevelScreen levelScreen;
	private SettingScreen settingScreen;
	private HelloScreen helloScreen;
	private VictoryScreen victoryScreen;
	private LoseScreen loseScreen;
	private TrophyScreen trophyScreen;
    private StatisticsScreen statisticsScreen;
	private SpriteBatch batch;
    private ShapeRenderer render;
	private ResourceManager manager;
	private int[][] sudoku;

	@Override
	public void create () {
        manager = new ResourceManager();
		batch = new SpriteBatch();
        render = new ShapeRenderer();
        render.setAutoShapeType(true);
		setStateScreen(State.MENU);
	}

	public void setStateScreen(State state){
		switch (state){
			case MAIN: if (mainScreen == null) mainScreen =new MainScreen(this);
				if (AppPreference.isMusicEnabled()){
					getManager().getMusic().play();
				}
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
            case STATISTICS: if (statisticsScreen == null) statisticsScreen = new StatisticsScreen(this);
            setScreen(statisticsScreen);
            break;
		}
	}

	public SpriteBatch getBatch() {
		return batch;
	}
    
    public ShapeRenderer getRender(){
        return this.render;
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
    
    public void reset() {
        AppPreference.setContinuationEnabled(true);
        AppPreference.setTimeMinute(0);
        AppPreference.setTimeSecond(0);
        AppPreference.setErrorGame(0);
    }

	@Override
	public void dispose () {
		batch.dispose();
        render.dispose();
		manager.dispose();
	}
}
