package com.mycompany.utils;

public class Sudoku {

    private final int[][] mat;
    private final int [][] copyMat;
    private int N = 9; // number of columns/rows.
    private final int SRN = 3; // square root of N
    private int K; // No. Of missing digits

    // Constructor
    public Sudoku()
    {

        // Compute square root of N
        mat = new int[9][9];
        copyMat = new int[9][9];
    }

    // Sudoku Generator
    public void fillValues()
    {
        // Fill the diagonal of SRN x SRN matrices
        fillDiagonal();

        // Fill remaining blocks
        fillRemaining(0, SRN);

        for (int row = 0; row < 9; row++){
            for (int column = 0; column < 9; column++){
                copyMat[column][row] = mat[column][row];
            }
        }
        // Remove Randomly K digits to make game
        removeKDigits();
    }

    // Fill the diagonal SRN number of SRN x SRN matrices
    private void fillDiagonal()
    {

        for (int i = 0; i<N; i=i+SRN)

            // for diagonal box, start coordinates->i==j
            fillBox(i, i);
    }

    // Returns false if given 3 x 3 block contains num.
    private boolean unUsedInBox(int rowStart, int colStart, int num)
    {
        for (int i = 0; i<SRN; i++)
            for (int j = 0; j<SRN; j++)
                if (mat[rowStart+i][colStart+j]==num)
                    return false;

        return true;
    }

    // Fill a 3 x 3 matrix.
    private void fillBox(int row,int col)
    {
        int num;
        for (int i=0; i<SRN; i++)
        {
            for (int j=0; j<SRN; j++)
            {
                do
                {
                    num = randomGenerator(N);
                }
                while (!unUsedInBox(row, col, num));

                mat[row+i][col+j] = num;
            }
        }

    }

    // Random generator
    private int randomGenerator(int num)
    {
        return (int) Math.floor((Math.random()*num+1));
    }

    // Check if safe to put in cell
    private boolean CheckIfSafe(int i,int j,int num)
    {
        return (unUsedInRow(i, num) &&
                unUsedInCol(j, num) &&
                unUsedInBox(i-i%SRN, j-j%SRN, num));
    }

    // check in the row for existence
    private boolean unUsedInRow(int i,int num)
    {
        for (int j = 0; j<N; j++)
            if (mat[i][j] == num)
                return false;
        return true;
    }

    // check in the row for existence
    private boolean unUsedInCol(int j,int num)
    {
        for (int i = 0; i<N; i++)
            if (mat[i][j] == num)
                return false;
        return true;
    }
    // A recursive function to fill remaining
    // matrix
    private boolean fillRemaining(int i, int j)
    {
        // System.out.println(i+" "+j);
        if (j>=N && i<N-1)
        {
            i = i + 1;
            j = 0;
        }
        if (i>=N && j>=N)
            return true;

        if (i < SRN)
        {
            if (j < SRN)
                j = SRN;
        }
        else if (i < N-SRN)
        {
            if (j== (i/SRN) *SRN)
                j = j + SRN;
        }
        else
        {
            if (j == N-SRN)
            {
                i = i + 1;
                j = 0;
                if (i>=N)
                    return true;
            }
        }

        for (int num = 1; num<=N; num++)
        {
            if (CheckIfSafe(i, j, num))
            {
                mat[i][j] = num;
                if (fillRemaining(i, j+1))
                    return true;

                mat[i][j] = 0;
            }
        }
        return false;
    }
    // Remove the K no. of digits to
    // complete game
    private void removeKDigits()
    {
        int count = K;
        while (count != 0)
        {
            int cellId = randomGenerator(N*N)-1;
            int i = (cellId/N);
            int j = cellId%10;
            if (j != 0)
                j = j - 1;
            if (mat[i][j] != 0)
            {
                count--;
                mat[i][j] = 0;
            }
        }
    }
    // Driver code
    public int[][] getRandomSudoku(int missing_digits)
    {
        this.N = 9;
        this.K = missing_digits;
        clearMat();
        fillValues();
        return mat;
    }
    
    private void clearMat(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                mat[i][j] = 0;
            }
        }
    }
    
    public int [][] getCopyMat(){
        return copyMat;
    }
}
