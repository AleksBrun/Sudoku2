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
import com.mycompany.utils.XMParse;

public class UpdateGame extends InputAdapter {

    private final MainScreen mainScreen;
    private final Grid grid;
    private  final Key key;

    private boolean pause;
    private final Clock clock;
    private final Bonus bonus;
    private int indexCell;
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
        bonus.update();
    }

    public void loadGame(Parameter _parameter) {
        this.parameter = _parameter;
        mainScreen.setAll_coin(AppPreference.getAllStars());
        mainScreen.setLevel(parameter.difficulty_level);
        mainScreen.setError(parameter.error);
        mainScreen.setBonus(parameter.bonus);
        clock.setTime(TimeUtils.getTime(parameter.time));
        grid.load(LoaderSudoku.getIntegerSudoku(parameter.sudokuSave));
        setNumberCell();
        setBonus();
    }

    public void saveGame() {
        parameter.sudokuSave = LoaderSudoku.getStringSudoku(grid.getSudoku());
        parameter.time = TimeUtils.setTime(clock.getMinute(), clock.getSecond());
        parameter.progress = (int)((float)(parameter.start_progress)/100*(parameter.start_progress-grid.getCellNumber(0).size));
        AppPreference.setAllTime(AppPreference.getAllTime()+parameter.time);
        stopMusic();
        XMParse.save(mainScreen.game.getParameters());
    }

    private void victoryGame() {
        mainScreen.dispose();
        mainScreen.getGame().setStateScreen(MyGdxGame.State.VICTORY);
    }
    private void loseGame() {
        parameter.sudokuSave = parameter.sudokuGame;
        parameter.error = 0;
        mainScreen.dispose();
        mainScreen.getGame().setStateScreen(MyGdxGame.State.LOSE);
    }

    private void setBonus(){
        mainScreen.setBonus(parameter.max_bonus);
        Array<Cell> tmp = grid.getCellNumber(0);
        for (int i = 0; i < parameter.max_bonus; i++){
            tmp.random().setBonusId(5);
        }
    }

    private void bonusActivation(Cell cell){
        bonus.init(cell.getX(), cell.getY(), cell.getSize(), cell.getBonusId());
        bonus.setMarkRegion(getTextureBonus(cell.getBonusId()));
        cell.setBonusId(0);
        mainScreen.setBonus(++parameter.bonus);
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

    private void setNumberCell(){
        for (Cell[] rowCell:grid.getCells()) {
            for (Cell cell:rowCell) {
                cell.setRegion(mainScreen.getGame().getManager().getNumber(cell.getNumber()));
                if (cell.getNumber() == 0) {
                    cell.setActive(true);
                }
            }
        }
    }

    private TextureRegion getTextureBonus(int id){
        switch (id){
            case 1: return mainScreen.getManager().getTextureRegionAtlas(ResourceManager.chest);
            case 2: return mainScreen.getManager().getTextureRegionAtlas(ResourceManager.coin);
            case 3: return mainScreen.getManager().getTextureRegionAtlas(ResourceManager.minerals);
            case 4: return mainScreen.getManager().getTextureRegionAtlas(ResourceManager.crystal);
            case 5: return mainScreen.getManager().getTextureRegionAtlas(ResourceManager.key);
        }
        return null;
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
                mainScreen.setError(++parameter.error);
                AppPreference.setAllError(AppPreference.getAllError() + 1);
            } else if (cell.getBonusId() != 0){
                bonusActivation(cell);
            }
            if (parameter.error >= 5) {
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

    public void playMusic() {
        mainScreen.getGame().getManager().getMusic().play();
        mainScreen.getGame().getManager().getMusic().setVolume(AppPreference.getMusicVolume());
        mainScreen.getGame().getManager().getMusic().setLooping(true);
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
        return mainScreen.getGame().getManager().getTextureRegionAtlas(ResourceManager.background4);
    }

    public boolean isPause() {
        return pause;
    }
    
    public Bonus getBonus(){
        return this.bonus;
    }

    public void visibleAllBonus(){
        for (Cell cell: grid.getBonusCell()){
            cell.setMark(true);
            cell.setMarkRegion(getTextureBonus(cell.getBonusId()));
        }
    }
}
