package com.mycompany.draw;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

    public void draw(SpriteBatch batch, ShapeRenderer renderer){
        renderKeys(renderer);
        batch.begin();
        drawGrid(batch);
        drawCells(batch);
        drawKeys(batch);
        batch.end();

    }

    private void drawKeys(SpriteBatch batch){
        Key key = updateGame.getKey();
        for (Cell cell:key.getKeys()){
            TextureRegion region = cell.getRegion();
            if (region != null){
                batch.draw(region, cell.getX()+ Setting.pad_cell, cell.getY()+Setting.pad_cell,
                        cell.getSize()-Setting.pad_cell*2, cell.getSize()-Setting.pad_cell*2);
            }
        }
    }

    private void renderKeys(ShapeRenderer renderer){
        Key key = updateGame.getKey();

        renderer.begin();
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.GRAY);
        renderer.rect(key.getX(), key.getY(), key.getWidth(), key.getHeight());
        renderer.end();
    }

    private void drawGrid(SpriteBatch batch){
        Grid grid = updateGame.getGrid();
        batch.draw(grid.getBackground(), grid.getX(), grid.getY(), grid.getSize(), grid.getSize());
    }

    private void drawCells(SpriteBatch batch){
        Cell[][] cells = updateGame.getGrid().getCells();
        for (Cell[] cellRow:cells){
            for (Cell cell:cellRow){
                TextureRegion region = cell.getRegion();
                TextureRegion markRegion = cell.getMarkRegion();
                if (markRegion != null ){
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
