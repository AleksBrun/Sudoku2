package com.mycompany.mygame;
import com.mycompany.utils.Utils;

public class ExampleGrid {

    public enum Difficulty {minimum, moderate, maximum}

    private static final int[][] example3 ={
            {0,9,7,8,0,0,0,0,0},
            {6,4,0,0,3,0,0,0,5},
            {2,0,8,0,0,0,0,9,0},
            {9,0,0,0,0,6,0,0,0},
            {0,0,5,1,0,3,6,0,0},
            {0,0,0,4,0,0,0,0,2},
            {0,3,0,0,0,0,2,0,7},
            {5,0,0,0,1,0,0,6,3},
            {0,0,0,0,0,2,8,4,0}
    };
    private static final int[][] example2 ={
            {0,0,5,6,0,1,3,0,0},
            {0,8,1,0,4,0,0,7,0},
            {7,0,4,0,0,0,1,2,6},
            {8,0,0,4,0,5,0,0,7},
            {0,9,0,0,2,0,0,5,0},
            {5,0,0,7,0,6,0,0,9},
            {1,5,3,0,0,0,7,0,2},
            {0,6,0,0,1,0,5,9,0},
            {0,0,9,8,0,3,4,0,0}
    };
    private static final int[][] example1 = {
            {0,0,2,3,0,1,5,0,0},
            {0,3,0,0,7,0,1,8,0},
            {1,7,9,0,0,0,2,0,6},
            {9,0,0,6,0,7,0,0,1},
            {0,4,0,0,1,0,0,6,0},
            {7,0,0,8,0,5,0,0,3},
            {8,0,4,0,0,0,6,2,7},
            {0,9,6,0,8,0,0,1,0},
            {0,0,7,5,0,2,9,0,0}
    };
    
    public static int [][] getSudoku(Difficulty difficulty, int level){
        String tmp = null;
        if (difficulty == Difficulty.minimum) {
            switch (level) {
                case 1:
                    tmp = minimum1;
                    break;
                case 2:
                    tmp = minimum2;
                    break;
                case 3:
                    tmp = minimum3;
                    break;
            }
        }
        return Utils.getIntegerSudoku(tmp);
    }
    public static int [][] getSudoku(Difficulty difficulty){
        switch (difficulty){
            case minimum: return example1;
            case moderate: return example2;
            case maximum: return example3;
        }
        return null;
    }

    public static String minimum1 = "601000090800004030004060052140050000090000000000000123000040000000027000073000804";
    public static String minimum2 = "000006030400000900900583602008004003000067009000000800000009005200700001160000000";
    public static String minimum3 = "006001000000006000003000490020090000080000503070000800840700000000500060000809050";
}
