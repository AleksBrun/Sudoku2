package com.mycompany.models;

public class Bonus extends Cell{

    private int counter_bonus;

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

    public void update(){
        if (isActive() && counter_bonus < 60){
           counter_bonus++;
        } else {
            counter_bonus = 0;
            setActive(false);
        }
    }
}
