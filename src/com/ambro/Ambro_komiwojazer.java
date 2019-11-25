package com.ambro;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ambro_komiwojazer {


    int result = 1000000;


    public static void main(String[] args) throws FileNotFoundException {

        long a = System.currentTimeMillis();
        int [][] POINTS = LOAD_DATA();

        int [] INIT_ORDER = new int [POINTS.length];
        for (int i = 0; i < POINTS.length; i++){
            INIT_ORDER[i] = i;
        }
        Ambro_komiwojazer testObject = new Ambro_komiwojazer();
        int result = testObject.GET_ORDER(POINTS, INIT_ORDER, 0);


        System.out.println(result);
        long b = System.currentTimeMillis();

        System.out.println("runtime was : " + (b - a) );

    }

    public static int[][] LOAD_DATA() throws FileNotFoundException {

        int NO_OF_POINTS;

        int [][] XY_VALUES;

        System.setIn(new FileInputStream("src/com/ambro/text"));
        Scanner input = new Scanner(System.in);
        NO_OF_POINTS = input.nextInt();

        XY_VALUES = new int[NO_OF_POINTS][2];

        for (int i = 0; i < NO_OF_POINTS; i++) {
            for (int j = 0; j < 2; j++){
                if (j==0) XY_VALUES[i][0] = input.nextInt();
                else XY_VALUES[i][1] = input.nextInt();
            }

        }
        return XY_VALUES;
    }
    public int GET_ORDER (int [][] POINTS, int [] ORDER, int start){

        if (start >= ORDER.length) {
            int newResult = CALCUALTE_ROAD(POINTS, ORDER);
            //System.out.println(Arrays.toString(ORDER));
            //System.out.println(newResult);
            if (newResult<result) {
                System.out.println("result changed from: "+ result +" to "+ newResult);
                result = newResult;
            }
        } else {
            for (int i = start; i < ORDER.length; i++) {
                swap(ORDER, start, i);
                GET_ORDER(POINTS,ORDER, start+1);
                swap(ORDER, start, i);
            }
        }
    return result;
    }


    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }



    public static int CALCUALTE_ROAD (int [][] POINTS, int [] ORDER ){
        int result =0;
        int [] FIXED_START = {0,0};
        int [] FIXED_END = {10,10};

        int init_step_xdiff = POINTS[ORDER[0]][0] - FIXED_START[0];
        int init_step_ydiff = POINTS[ORDER[0]][1] - FIXED_START[1];

        int init_step = init_step_xdiff + init_step_ydiff;
        result+=init_step;

        for (int i = 1; i < ORDER.length; i++){
            int diffX = POINTS[ORDER[i]][0] - POINTS[ORDER[i-1]][0];
            if (diffX < 0) diffX=diffX-2*diffX;
            int diffY = POINTS[ORDER[i]][1] - POINTS[ORDER[i-1]][1];
            if (diffY < 0) diffY=diffY-2*diffY;
            int difference = diffX + diffY;
            result += difference;
        }
        int final_step_xdiff = FIXED_END[0] - POINTS[ORDER[ORDER.length-1]][0];
        int final_step_ydiff = FIXED_END[1] - POINTS[ORDER[ORDER.length-1]][1];

        int final_step = final_step_xdiff + final_step_ydiff;
        result+=final_step;
        return result;
    }


}
