package com.mycompany.update;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mycompany.models.Bonus;
import com.mycompany.models.Cell;
import com.mycompany.models.Grid;
import com.mycompany.models.Key;
import com.mycompany.mygame.AppPreference;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;
import com.mycompany.screens.MainScreen;
import com.mycompany.utils.Clock;
import com.mycompany.utils.LoaderSudoku;
import com.mycompany.utils.TimeUtils;

public class UpdateGame extends InputAdapter {

    private final MainScreen mainScreen;
    private final Grid grid;
    private  final Key key;

    private boolean pause;
    private final Clock clock;
    private final Bonus bonus;
    private int counter_bonus;
    private int indexCell;

    public UpdateGame(final MainScreen _mainScreen) {
        this.mainScreen = _mainScreen;

        grid = new Grid(Setting.getPositionGrid_X(), Setting.getPositionGrid_Y(),
                        Setting.getSizeGrid());
        grid.setBackground(mainScreen.getGame().getManager().getTextureRegionAtlas(ResourceManager.grid));
        key = new Key(Setting.getPositionGrid_X(), Setting.getPositionGrid_Y() / 2 - Setting.getSizeGrid() / 20,
                      Setting.getWidthKeys(), Setting.getHeightKeys(), mainScreen.getGame().getManager());
        clock = new Clock();

        bonus = new Bonus();
    }

    public void update() {
        clock.update();
        mainScreen.setTime(clock.getMinute(), clock.getSecond());
        if (bonus.isActive()){
            if (counter_bonus < 30){
                counter_bonus++;
            } else {
                bonus.setActive(false);
                counter_bonus = 0;
                mainScreen.setStars(10);
            }
        }
    }

    public void playGame(int[][] sudoku) {
        Array<Cell> cellArray = new Array<Cell>();
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                Cell cell = grid.getCells()[column][row];
                cell.setNumber(sudoku[column][row]);
                cell.setMark(false);
                cell.setRegion(mainScreen.getGame().getManager().getNumber(cell.getNumber()));
                if (cell.getNumber() == 0) {
                    cell.setActive(true);
                    cellArray.add(cell);
                }
            }
        }
        grid.resetBonus();
        for (int i = 0; i < AppPreference.getBonus(); i++){
            cellArray.random().setBonusId(1);
        }
        clock.setTime(AppPreference.getTimeMinute(), AppPreference.getTimeSecond());
        mainScreen.setBonus(AppPreference.getBonus());
    }

    public void saveGame() {
        AppPreference.setTimeMinute(clock.getMinute());
        AppPreference.setTimeSecond(clock.getSecond());
        int allTime = TimeUtils.setTime(clock.getMinute(), clock.getSecond());
        allTime += AppPreference.getAllTime();
        AppPreference.setAllTime(allTime);
        AppPreference.saveSudoku(LoaderSudoku.getStringSudoku(grid.getSudoku()));
        stopMusic();
    }

    private void victoryGame() {
        mainScreen.dispose();
        mainScreen.getGame().setStateScreen(MyGdxGame.State.VICTORY);
    }
    private void loseGame() {
        mainScreen.dispose();
        mainScreen.getGame().setStateScreen(MyGdxGame.State.LOSE);
    }

    private void bonusActivation(Cell cell){
        bonus.init(cell.getX(), cell.getY(), cell.getSize(), cell.getBonusId());
        bonus.setRegion(mainScreen.getManager().getTextureRegionAtlas(ResourceManager.crystal));
        cell.setBonusId(0);
        AppPreference.setBonus(AppPreference.getBonus()-1);
        mainScreen.setBonus(AppPreference.getBonus());
    }

    private void updateTouch(int screenX, int screenY) {
        grid.resetMark();
        Cell cell = grid.getHit(screenX, Gdx.graphics.getHeight() - screenY);
        if (cell != null) {
            indexCell = cell.getIndex();
            cell.setMark(true);
            key.setActive(true);
            if (cell.isActive()) {
                cell.setMarkRegion(mainScreen.getGame().getManager().getTextureRegionAtlas(ResourceManager.mark));
            } else {
                cell.setMarkRegion(mainScreen.getGame().getManager().getTextureRegionAtlas(ResourceManager.mark1));
            }
        }
        Cell keyHit = key.getHit(screenX, Gdx.graphics.getHeight() - screenY);
        if (keyHit != null && key.isActive()) {
            key.setActive(false);
            cell = grid.getCell(indexCell);
            if (cell.isActive()) {
                cell.setNumber(keyHit.getNumber());
                cell.setRegion(mainScreen.getGame().getManager().getNumber(cell.getNumber()));
            }
            if (grid.errorAllGrid()) {
                cell.setMark(true);
                cell.setMarkRegion(mainScreen.getGame().getManager().getTextureRegionAtlas(ResourceManager.mark3));
                AppPreference.setErrorGame(AppPreference.getErrorGame() + 1);
                AppPreference.setAllError(AppPreference.getAllError() + 1);
                mainScreen.setLabelError(AppPreference.getErrorGame());
            } else if (cell.getBonusId() != 0){
                bonusActivation(cell);
            }
            if (AppPreference.getErrorGame() >= 5) {
                loseGame();
            }
            if (grid.isFilledIn()) {
                victoryGame();
            }
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        updateTouch(screenX, screenY);
        return false;
    }

    public void pause() {
        pause = !pause;
        if (isPause()) {
            clock.pause();
            pauseMusic();
        } else {
            clock.start();
            if (AppPreference.isMusicEnabled()) {
                playMusic();
            }
        }
    }

    public void setVolume() {
        mainScreen.getGame().getManager().getMusic().setVolume(AppPreference.getMusicVolume());
        mainScreen.getGame().getManager().getMusic().setLooping(true);
    }

    public void playMusic() {
        mainScreen.getGame().getManager().getMusic().play();
    }

    public void stopMusic() {
        mainScreen.getGame().getManager().getMusic().stop();
    }

    public void pauseMusic() {
        mainScreen.getGame().getManager().getMusic().pause();
    }

    public Grid getGrid() {
        return grid;
    }

    public Key getKey() {
        return key;
    }

    public TextureRegion getBackground() {
        return mainScreen.getGame().getManager().getTextureRegionAtlas(ResourceManager.background1);
    }

    public boolean isPause() {
        return pause;
    }
    
    public Bonus getBonus(){
        return this.bonus;
    }
}
