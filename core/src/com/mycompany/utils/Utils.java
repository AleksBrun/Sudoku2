package com.mycompany.utils;

import com.badlogic.gdx.math.GridPoint2;

public class Utils {

    public static float getProgress(int allCell, int findCell){
        return (float) findCell/allCell*100;
    }

    public static GridPoint2 getTime(int second){
        return new GridPoint2(second- second/60*60, second/60);
    }

    public static int setTime(int minute, int second){
        return minute*60+second;
    }

    public static int[][] getIntegerSudoku(String name){
        int index = 0;
        int[][] tmp = new int[9][9];
        for (int row = 0; row < 9; row++){
            for (int column = 0; column < 9; column++){
                tmp[row][column] = Character.digit(name.charAt(index), 10);
                index++;
            }
        }
        return tmp;
    }

    public static String getStringSudoku(int[][] sudoku){
        StringBuilder tmp = new StringBuilder();
        for (int[] ints : sudoku) {
            for (int column = 0; column < sudoku[0].length; column++) {
                tmp.append(ints[column]);
            }
        }
        return tmp.toString();
    }
}
