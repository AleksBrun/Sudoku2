package com.mycompany.update;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mycompany.models.Cell;
import com.mycompany.models.Grid;
import com.mycompany.models.Key;
import com.mycompany.mygame.AppPreference;
import com.mycompany.mygame.MyGdxGame;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;
import com.mycompany.screens.MainScreen;
import com.mycompany.unils.Clock;
import com.mycompany.unils.LoaderSudoku;
import com.mycompany.unils.TimeUtils;

public class UpdateGame extends InputAdapter {

    private final MainScreen mainScreen;
    private final Grid grid;
    private  final Key key;
    private int indexCell;
    private boolean pause;
    private Clock clock;

    public UpdateGame(final MainScreen _mainScreen) {
        this.mainScreen = _mainScreen;

        grid = new Grid(Setting.getPositionGrid_X(), Setting.getPositionGrid_Y(),
                        Setting.getSizeGrid());
        grid.setBackground(mainScreen.getGame().getManager().getTextureRegion(ResourceManager.grid));
        key = new Key(Setting.getPositionGrid_X(), Setting.getPositionGrid_Y() / 2 - Setting.getSizeGrid() / 20,
                      Setting.getWidthKeys(), Setting.getHeightKeys(), mainScreen.getGame().getManager());
        clock = new Clock();
    }

    public void update(float delta) {
        clock.update();
        mainScreen.setTime(clock.getMinute(), clock.getSecond());
    }

    public boolean errorAllGrid() {
        for (int index = 0; index < 9; index++) {
            if (checkingDuplicates(grid.getHorizontalGroup(index)) ||
                checkingDuplicates(grid.getVerticalGroup(index)) ||
                checkingDuplicates(grid.getSquareGrid(index)))
                return true;
        }
        return false;
    }

    private boolean checkingDuplicates(Cell[] group) {
        int index = 0;
        for (int number = 1; number <= 9; number++) {
            for (Cell cell:group) {
                if (cell.getNumber() == number) {
                    index++;
                    if (index > 1) return true;
                }
            }
            index = 0;
        }
        return false;
    }

    public void playGame(int[][] sudoku) {
        loadSudoku(sudoku);
        clock.setTime(AppPreference.getTimeMinute(), AppPreference.getTimeSecond());
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

    private void loadSudoku(int [][] sudoku) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                Cell cell = grid.getCells()[column][row];
                cell.setNumber(sudoku[column][row]);
                cell.setMark(false);
                cell.setRegion(mainScreen.getGame().getManager().getNumber(cell.getNumber()));
                if (cell.getNumber() == 0) {
                    cell.setActive(true);
                }
            }
        }
    }

    private void update(int screenX, int screenY) {
        grid.resetMark();
        Cell cell;
        cell = grid.getHit(screenX, Gdx.graphics.getHeight() - screenY);
        if (cell != null) {
            indexCell = cell.getIndex();

            cell.setMark(true);
            if (cell.isActive()) {
                cell.setMarkRegion(mainScreen.getGame().getManager().getTextureRegion(ResourceManager.mark));
            } else {
                cell.setMarkRegion(mainScreen.getGame().getManager().getTextureRegion(ResourceManager.mark1));
            }
        }

        Cell keyHit = key.getHit(screenX, Gdx.graphics.getHeight() - screenY);
        if (keyHit != null && !isPause()) {
            cell = grid.getCell(indexCell);
            if (cell.isActive()) {
                cell.setNumber(keyHit.getNumber());
                cell.setRegion(mainScreen.getGame().getManager().getNumber(cell.getNumber()));
            }
            if (errorAllGrid()) {
                cell.setMark(true);
                cell.setMarkRegion(mainScreen.getGame().getManager().getTextureRegion(ResourceManager.mark3));
                AppPreference.setErrorGame(AppPreference.getErrorGame() + 1);
                mainScreen.setLabelError(AppPreference.getErrorGame());
            } else if (grid.isFilledIn()) {
                victoryGame();
            }
            if (AppPreference.getErrorGame() >= 5) {
                loseGame();
            }
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        update(screenX, screenY);
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
        return mainScreen.getGame().getManager().getTextureRegion(ResourceManager.background);
    }

    public boolean isPause() {
        return pause;
    }

    public Clock getClock() {
        return clock;
    }
}
