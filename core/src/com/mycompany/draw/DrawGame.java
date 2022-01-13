package com.mycompany.draw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mycompany.models.Cell;
import com.mycompany.models.Grid;
import com.mycompany.models.Key;
import com.mycompany.mygame.Setting;
import com.mycompany.update.UpdateGame;

public class DrawGame {

    private final UpdateGame updateGame;

    public DrawGame(UpdateGame _updateGame){
        this.updateGame = _updateGame;
    }

    public void draw(SpriteBatch batch){
        batch.begin();
        drawBackground(batch);
        drawGrid(batch);
        drawCells(batch);
        drawKeys(batch);
        batch.end();
    }

    private void drawBackground(SpriteBatch batch){
        batch.draw(updateGame.getBackground(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    private void drawKeys(SpriteBatch batch){
        Key key = updateGame.getKey();
        for (Cell cell:key.getKeys()){
            TextureRegion mark = cell.getMarkRegion();
            TextureRegion region = cell.getRegion();
            if (mark != null && cell.isMark()){
                batch.draw(mark, cell.getX(), cell.getY(), cell.getSize(), cell.getSize());
            }
            if (region != null && cell.getNumber() != 0){
                batch.draw(region, cell.getX()+ Setting.pad_cell, cell.getY()+Setting.pad_cell,
                        cell.getSize()-Setting.pad_cell*2, cell.getSize()-Setting.pad_cell*2);
            }
        }
    }
    
    private void drawGrid(SpriteBatch batch){
        Grid grid = updateGame.getGrid();
        batch.draw(updateGame.getGrid().getBackground(), grid.getX(), grid.getY(), grid.getSize(), grid.getSize());
    }

    private void drawCells(SpriteBatch batch){
        Cell[][] cells = updateGame.getGrid().getCells();
        for (Cell[] cellRow:cells){
            for (Cell cell:cellRow){
                TextureRegion region = cell.getRegion();
                TextureRegion markRegion = cell.getMarkRegion();
                if (cell.isMark() && markRegion != null ){
                    batch.draw(markRegion, cell.getX()+Setting.pad_mark, cell.getY()+Setting.pad_mark,
                            cell.getSize()-Setting.pad_mark*2, cell.getSize()-Setting.pad_mark*2);
                }
                if (region != null && cell.getNumber() != 0){
                    batch.draw(region, cell.getX()+ Setting.pad_cell, cell.getY()+Setting.pad_cell,
                            cell.getSize()-Setting.pad_cell*2, cell.getSize()-Setting.pad_cell*2);
                }
            }
        }
    }
}
