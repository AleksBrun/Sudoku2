package com.mycompany.models;

import com.badlogic.gdx.utils.Timer;

public class Bonus extends Cell{

    public Bonus(){
        super(0,0,0,0);
    }

    public void init(float _x, float _y, float _size, int _id){
        setX(_x);
        setY(_y);
        setSize(_size);
        setBonusId(_id);
        setActive(true);
    }

    public void visible(){
        setActive(true);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                setActive(false);
            }
        },2);
    }
}
