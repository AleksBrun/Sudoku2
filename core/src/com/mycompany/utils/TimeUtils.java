package com.mycompany.utils;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;

public class TimeUtils {
    
    public static GridPoint2 getTime(int second){
        return new GridPoint2(second/60,second- second/60*60);
    }
    
    public static int setTime(int minute, int second){
        return minute*60+second;
    }
    
}
