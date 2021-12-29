package com.mycompany.mygame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mycompany.screens.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mycompany.utils.XMLparse;

public class MyGdxGame extends Game {

	public enum State {MAIN, MENU, LEVEL, SETTING, HELLO, VICTORY, LOSE,TROPHY, STATISTICS, CREATE, LOAD}
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
	private CreateScreen createScreen;
	private SpriteBatch batch;
    private ShapeRenderer render;
	private ResourceManager manager;
	private Array<Parameter> parameters;
	private Parameter parameter;

	@Override
	public void create () {
		parameters = XMLparse.load();
		System.out.println(parameters.size);
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
			case CREATE: if (createScreen == null) createScreen = new CreateScreen(this);
			setScreen(createScreen);
			break;
			case LOAD: if (loadScreen == null) loadScreen = new LoadScreen(this);
			setScreen(loadScreen);
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

	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	public void createSudoku(Parameter _parameter , boolean append){
		if (append){
			parameters.add(_parameter);
		}
		setParameter(_parameter);
		XMLparse.save(parameters);
    }

	public Array<Parameter> getParameters() {
		return parameters;
	}

	@Override
	public void dispose () {
		batch.dispose();
        render.dispose();
		manager.dispose();
	}
}
