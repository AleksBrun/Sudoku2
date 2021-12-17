package com.mycompany.models;

public class Bonus extends Cell{

    
    public Bonus(float _x, float _y, float _size , int index) {
        super(_x, _y, _size, index);

    }

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
}
