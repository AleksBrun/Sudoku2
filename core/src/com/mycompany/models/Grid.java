package com.mycompany.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.mycompany.mygame.ResourceManager;

public class Grid {

    private final float x, y, size;
    private final TextureRegion background;
    private final ResourceManager manager;
    private Cell[][] cells = new Cell[9][9];

    public Grid(float _x, float _y, float _size, ResourceManager _manager){
        this.x = _x;
        this.y = _y;
        this.size = _size;
        this.manager = _manager;
        this.background = manager.getGrid();

        createCells();
    }


    public Cell getCell(int index){
        for (Cell[] cellRow:cells){
            for (Cell cell:cellRow){
                if (index == cell.getIndex()) return cell;
            }
        }
        return null;
    }

    public Cell getHit(int x, int y){
        for (Cell[] cellRow:cells){
            for (Cell cell:cellRow){
                if (cell.hit(x, y) != null) return cell;
            }
        }
        return null;
    }

    private void createCells(){
        float sizeCell = size/9;
        int index = 0;
        for (int row = 0; row < cells.length; row++){
            for (int column = 0;column < cells[0].length; column++){
                Cell cell = new Cell(x+column*sizeCell, y+row*sizeCell, sizeCell, index++);
                cells[column][row] = cell;
            }
        }
    }

    public void loadSudoku(){
        for (Cell[] cellRow: cells){
            for (Cell cell:cellRow){
                cell.setNumber(MathUtils.random(0, 9));
                cell.setRegion(manager.getNumber(cell.getNumber()));
            }
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getSize() {
        return size;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public TextureRegion getBackground() {
        return background;
    }
}
