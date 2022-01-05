package com.mycompany.update;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
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
    private final String[] iconArray =  {ResourceManager.chest, ResourceManager.coin, ResourceManager.minerals,
        ResourceManager.crystal, ResourceManager.key6};

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
        mainScreen.setAll_Stars(AppPreference.getAllStars());
        mainScreen.setLevel(parameter.difficulty_level);
        mainScreen.setError(parameter.error);
        mainScreen.setBonus(parameter.bonus);
        mainScreen.setCoins(parameter.coin);
        mainScreen.setHeart(parameter.max_error);
        clock.setTime(TimeUtils.getTime(parameter.time));
        grid.load(LoaderSudoku.getIntegerSudoku(parameter.sudokuSave), LoaderSudoku.getIntegerSudoku(parameter.sudokuGame));
        System.out.println(parameter.sudokuSave);
        System.out.println(parameter.sudokuGame);
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
        mainScreen.setBonus(parameter.bonus);
        Array<Cell> tmp = grid.getCellNumber(0);
        for (int i = 0; i < parameter.bonus; i++){
            tmp.random().setBonusId(4);
        }
    }
    private void errorActivation(final Cell cell){
        setMarkRed(cell);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                cell.setNumber(0);
                cell.setMark(false);
            }
        }, 2f);
        mainScreen.setError(++parameter.error);
        mainScreen.setHeart(parameter.max_error - parameter.error);
        AppPreference.setAllError(AppPreference.getAllError() + 1);
    }

    private void bonusActivation(Cell cell){
        bonus.init(cell.getX(), cell.getY(), cell.getSize(), cell.getBonusId());
        bonus.setRegion(getTextureBonus(cell.getBonusId()));
        parameter.coin += parameter.difficulty_level*10;
        mainScreen.setBonus(--parameter.bonus);
        mainScreen.setCoins(parameter.coin);
        cell.setBonusId(0);
    }

    private void setMarkRed(Cell cell){
        cell.setMark(true);
        cell.setMarkRegion(mainScreen.getGame().getManager().getTextureRegionAtlas(ResourceManager.mark3));
    }

    private void setMarkYellow(Cell cell){
        cell.setMark(true);
        cell.setMarkRegion(mainScreen.getGame().getManager().getTextureRegionAtlas(ResourceManager.mark));
    }

    private void setMarkBlue(Cell cell){
        cell.setMark(true);
        cell.setMarkRegion(mainScreen.getGame().getManager().getTextureRegionAtlas(ResourceManager.mark1));
    }

    private void setNumberCell(){
        for (Cell[] rowCell:grid.getCells()) {
            for (Cell cell:rowCell) {
                cell.setRegion(mainScreen.getGame().getManager().getNumber(cell.getNumber()));
            }
        }
    }

    private TextureRegion getTextureBonus(int id){
        return mainScreen.getManager().getTextureRegionAtlas(iconArray[id]);
    }

    private void updateTouch(int screenX, int screenY) {
        grid.resetMark();
        Cell cell = grid.getHit(screenX, Gdx.graphics.getHeight() - screenY);
        if (grid.isActive() && cell != null) {
            indexCell = cell.getIndex();
            key.setActive(true);
            if (cell.isActive()) {
                setMarkYellow(cell);
            } else {
                setMarkBlue(cell);
            }
        }
        Cell keyHit = key.getHit(screenX, Gdx.graphics.getHeight() - screenY);
        if (key.isActive() && keyHit != null) {
            key.setActive(false);
            cell = grid.getCell(indexCell);
            if (cell.isActive()) {
                cell.setNumber(keyHit.getNumber());
                cell.setRegion(mainScreen.getGame().getManager().getNumber(cell.getNumber()));
            }
            if (grid.errorAllGrid()) {
                errorActivation(cell);
            } else if (cell.getBonusId() != 0){
                bonusActivation(cell);
            }
            if (parameter.error >= parameter.max_error) {
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

    public void visibleAllActiveCell(){
        for (Cell[] cellRow: grid.getCells()){
            for (Cell cell:cellRow){
                if (cell.isActive() && cell.getNumber() != 0){
                    setMarkYellow(cell);
                }
            }
        }
    }
    public void visibleAllBonus(){
        for (Cell cell: grid.getBonusCell()){
            cell.setMark(true);
            cell.setMarkRegion(getTextureBonus(cell.getBonusId()));
        }
    }
}
