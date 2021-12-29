package com.mycompany.update;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mycompany.models.Bonus;
import com.mycompany.models.Cell;
import com.mycompany.models.Grid;
import com.mycompany.models.Key;
import com.mycompany.mygame.*;
import com.mycompany.screens.MainScreen;
import com.mycompany.utils.Clock;
import com.mycompany.utils.LoaderSudoku;
import com.mycompany.utils.TimeUtils;
import com.mycompany.utils.XMLparse;

public class UpdateGame extends InputAdapter {

    private final MainScreen mainScreen;
    private final Grid grid;
    private  final Key key;

    private boolean pause;
    private final Clock clock;
    private final Bonus bonus;
    private int counter_bonus;
    private int indexCell;
    private boolean bonusVisible = true;
    private Parameter parameter;

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

    public void playGame(Parameter _parameter) {
        this.parameter = _parameter;
        grid.load(LoaderSudoku.getIntegerSudoku(parameter.sudokuGame));
        grid.resetMark();
        grid.resetBonus();
        for (Cell[] rowCell:grid.getCells()) {
            for (Cell cell:rowCell) {
                setNumberCell(cell);
                if (cell.getNumber() == 0) {
                    cell.setActive(true);
                }
            }
        }
        Array<Cell> tmp = grid.getCellNumber(1);
        for (int i = 0; i < AppPreference.getBonus(); i++){
            tmp.random().setBonusId(1);
        }
        clock.setTime(TimeUtils.getTime(parameter.time));
        mainScreen.setBonus(AppPreference.getBonus());
    }

    public void saveGame() {
        parameter.sudokuSave = LoaderSudoku.getStringSudoku(grid.getSudoku());
        parameter.time = TimeUtils.setTime(clock.getMinute(), clock.getSecond());
        AppPreference.setAllTime(AppPreference.getAllTime()+parameter.time);
        stopMusic();
        XMLparse.save(mainScreen.game.getParameters());
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

    private void setMarkRed(Cell cell){
        cell.setMarkRegion(mainScreen.getGame().getManager().getTextureRegionAtlas(ResourceManager.mark3));
    }

    private void setMarkYellow(Cell cell){
        cell.setMarkRegion(mainScreen.getGame().getManager().getTextureRegionAtlas(ResourceManager.mark));
    }

    private void setMarkBlue(Cell cell){
        cell.setMarkRegion(mainScreen.getGame().getManager().getTextureRegionAtlas(ResourceManager.mark1));
    }

    private void setNumberCell(Cell cell){
        cell.setRegion(mainScreen.getGame().getManager().getNumber(cell.getNumber()));
    }

    private void updateTouch(int screenX, int screenY) {
        grid.resetMark();
        Cell cell = grid.getHit(screenX, Gdx.graphics.getHeight() - screenY);
        if (cell != null) {
            indexCell = cell.getIndex();
            cell.setMark(true);
            key.setActive(true);
            if (cell.isActive()) {
                setMarkYellow(cell);
            } else {
                setMarkBlue(cell);
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
                setMarkRed(cell);
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
    public void bonusActive(){
        if (bonusVisible){
            for (Cell[] rowCell:grid.getCells()){
                for (Cell cell:rowCell){
                    if (cell.getBonusId() != 0) {
                        cell.setMark(true);
                        cell.setMarkRegion(mainScreen.getManager().getTextureRegionAtlas(ResourceManager.minerals));
                    }
                }
            }
        } else {
            grid.resetMark();
        }
        System.out.println(bonusVisible);
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
