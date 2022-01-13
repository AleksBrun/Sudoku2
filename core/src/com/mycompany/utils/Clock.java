package com.mycompany.utils;

import com.badlogic.gdx.math.GridPoint2;

public class Clock {

    private int second;
    private int minute;
    private int stateTime;
    private boolean active;

    public void setTime(int _minute, int _second){
        this.second = _second;
        this.minute = _minute;
        stateTime = 0;
        active = true;
    }

    public void setTime(GridPoint2 point){
        this.second = point.x;
        this.minute = point.y;
        stateTime = 0;
        active = true;
    }
    public void update(){
        if (active){
            if (stateTime == 60){
                second++;
                stateTime = 0;
                if (second == 60){
                    minute++;
                    second = 0;
                }
            }
            stateTime++;
        }
    }

    public void pause(){
        active = false;
    }

    public void start(){
        active = true;
    }

    public void reset(){
        this.active = false;
        this.minute = 0;
        this.second = 0;
        this.stateTime = 0;
    }

    public int getSecond() {
        return second;
    }

    public int getMinute() {
        return minute;
    }
    
    public int getAllSeconds(){
        return minute*60+second;
    }
}
