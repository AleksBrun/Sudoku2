package com.mycompany.update;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.mycompany.models.Cell;
import com.mycompany.models.Grid;
import com.mycompany.models.Key;
import com.mycompany.mygame.Setting;
import com.mycompany.screens.MainScreen;

public class UpdateGame extends InputAdapter {

    private final MainScreen mainScreen;
    private final Grid grid;
    private  final Key key;

    public UpdateGame(final MainScreen _mainScreen){
        this.mainScreen = _mainScreen;

        grid = new Grid(Setting.getPositionGrid_X(),Setting.getPositionGrid_Y(),
                Setting.getSizeGrid(), mainScreen.getGame().getManager());
        key = new Key(Setting.getPositionGrid_X(), Setting.getPositionGrid_Y()/2 -Setting.getSizeGrid()/20,
                Setting.getWidthKeys(), Setting.getHeightKeys(), mainScreen.getGame().getManager());

        grid.loadSudoku();
    }



    public void update(float delta){

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Cell cell = grid.getHit(screenX, Gdx.graphics.getHeight()-screenY);
        if (cell != null){
            cell.setMarkRegion(mainScreen.getGame().getManager().getMark());
        }
        return false;
    }

    public Grid getGrid() {
        return grid;
    }

    public Key getKey() {
        return key;
    }
}
