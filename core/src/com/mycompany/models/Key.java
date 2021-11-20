package com.mycompany.models;

import com.mycompany.mygame.ResourceManager;

public class Key {

    private final ResourceManager manager;
    private final float x, y, width, height;
    private final Cell[] keys;

    public Key(float _x, float _y, float _width, float _height,  ResourceManager _manager) {
        this.x = _x;
        this.y = _y;
        this.width = _width;
        this.height = _height;
        this.manager = _manager;

        keys = new Cell[10];

        createKey();
    }

    public Cell getHit(int x, int y){
        for (Cell cell: keys){
            if (cell.hit(x, y) != null) return cell;
        }
        return null;
    }

    private void createKey(){
        for (int column = 0; column < keys.length; column++){
            Cell cell = new Cell(x+column*height, y, height, column);
            cell.setNumber(column);
            cell.setMarkRegion(manager.getMark1());
            cell.setMark(true);
            cell.setRegion(manager.getNumber(column));
            keys[column] = cell;
        }
    }

    public Cell[] getKeys() {
        return keys;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
