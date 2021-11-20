package com.mycompany.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Cell {

    private float x, y, size;
    private TextureRegion region;
    private TextureRegion markRegion;
    private int number, index;
    private boolean mark;

    public Cell(float _x, float _y, float _size, int _index) {
        this.x = _x;
        this.y = _y;
        this.size = _size;
        this.index = _index;
    }

    public Cell hit(float touchX, float touchY){
        return touchX > x && touchX < x+size && touchY > y && touchY < y+size ? this:null;
    }

    public TextureRegion getRegion() {
        return region;
    }

    public void setRegion(TextureRegion region) {
        this.region = region;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public TextureRegion getMarkRegion() {
        return markRegion;
    }

    public void setMarkRegion(TextureRegion markRegion) {
        this.markRegion = markRegion;
    }
}