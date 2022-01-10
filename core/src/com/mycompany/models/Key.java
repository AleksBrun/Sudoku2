package com.mycompany.models;

import com.mycompany.mygame.ResourceManager;

public class Key {

    private final ResourceManager manager;
    private float x;
    private float y;
    private final float sizeCell;
    private final Cell[] keys;
    private boolean active;

    public Key(float _x, float _y, float _sizeCell,  ResourceManager _manager) {
        this.x = _x;
        this.y = _y;
        this.sizeCell = _sizeCell;
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
            Cell cell = new Cell(x+column* sizeCell, y, sizeCell, column);
            cell.setNumber(column);
            cell.setMarkRegion(manager.getTextureRegionAtlas(ResourceManager.mark2));
            cell.setMark(true);
            cell.setRegion(manager.getNumber(column));
            keys[column] = cell;
        }
    }

    public void setPosition(float _x, float _y){
        this.x = _x;
        this.y = _y;
        for (int column = 0; column < keys.length; column++){
            keys[column].setPosition(x+column* sizeCell, y);
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public float getSize() {
        return sizeCell;
    }
}
