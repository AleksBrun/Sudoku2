package com.mycompany.utils;

public class LoaderSudoku {

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
