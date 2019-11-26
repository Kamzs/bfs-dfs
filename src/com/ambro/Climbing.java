package com.ambro;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Climbing {

    public static void main(String[] args) throws FileNotFoundException {
        int [][] POINTS = LOAD_DATA();
        System.out.println(Arrays.deepToString(POINTS));
    }

    public static int[][] LOAD_DATA() throws FileNotFoundException {

        int DIM_VERTICAL;
        int DIM_HORIZONTAL;

        int [][] XY_VALUES;

        System.setIn(new FileInputStream("src/com/ambro/case"));
        Scanner input = new Scanner(System.in);
        DIM_VERTICAL = input.nextInt();
        DIM_HORIZONTAL = input.nextInt();

        XY_VALUES = new int[DIM_VERTICAL][DIM_HORIZONTAL];

        for (int k = DIM_VERTICAL-1; k >= 0 ; k--){
        for (int l = 0; l < DIM_HORIZONTAL; l++) {
            XY_VALUES[k][l] = input.nextInt();
            }
        }
        return XY_VALUES;
    }
}
