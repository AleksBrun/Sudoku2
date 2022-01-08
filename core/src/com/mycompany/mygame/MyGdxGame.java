package com.mycompany.mygame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mycompany.screens.*;
import com.mycompany.utils.XMParse;

public class MyGdxGame extends Game {

	public enum State {MAIN, MENU, LEVEL, SETTING, HELLO, VICTORY, LOSE,TROPHY, STATISTICS, CREATE, LOAD, INFO}
	private MainScreen mainScreen;
	private MenuScreen menuScreen;
	private LevelScreen levelScreen;
	private SettingScreen settingScreen;
	private HelloScreen helloScreen;
	private VictoryScreen victoryScreen;
	private LoseScreen loseScreen;
	private TrophyScreen trophyScreen;
    private StatisticsScreen statisticsScreen;
	private LoadScreen loadScreen;
	private ShopScreen createScreen;
	private InfoScreen infoScreen;
	private SpriteBatch batch;
	private ResourceManager manager;
	private Array<Parameter> parameters;
	private Parameter parameter;

	@Override
	public void create () {
		parameters = XMParse.load();
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
            case STATISTICS: if (statisticsScreen == null) statisticsScreen = new StatisticsScreen(this);
            setScreen(statisticsScreen);
            break;
			case CREATE: if (createScreen == null) createScreen = new ShopScreen(this);
			setScreen(createScreen);
			break;
			case LOAD: if (loadScreen == null) loadScreen = new LoadScreen(this);
			setScreen(loadScreen);
			break;
			case INFO: if (infoScreen == null) infoScreen = new InfoScreen(this);
			setScreen(infoScreen);
			break;
		}
	}

	public boolean isEmpty(){
		return parameters.size == 0;
	}

	public boolean isFull(){
		return parameters.size == 9;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public ResourceManager getManager() {
		return manager;
	}

	public Parameter getParameter() {
		return parameter;
	}

	public void setPeekParameter(){
		this.parameter = parameters.peek();
	}

	public void deleteParameter(Parameter parameter){
		this.parameters.removeIndex(parameter.index);
	}

	public void createSudoku(Parameter _parameter , boolean append){
		if (append){
			parameters.add(_parameter);
		}
		this.parameter = _parameter;
		saveParameters();
    }
	public void saveParameters(){
		XMParse.save(parameters);
	}

	public Array<Parameter> getParameters() {
		return parameters;
	}

	@Override
	public void dispose () {
		batch.dispose();
		manager.dispose();
		System.out.println("All dispose!!!");
	}
}
