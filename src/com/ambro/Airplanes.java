package com.ambro;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Airplanes {

    int result =-100;

    public static void main(String[] args) throws FileNotFoundException {
        int[][] allSteps_events = LOAD_DATA();
        //int[] planeSetting = LOCATE_PLANE();
        int init_step =0;
        int index_of_plane =2;
        int current_result =0;


        System.out.println(Arrays.deepToString(allSteps_events));
/*        for (int i = 0; i < 5;i++){
            System.out.println(allSteps_events[0][i]);
        }*/
        //System.out.println(Arrays.toString(planeSetting));

        Airplanes airplanes = new Airplanes();
        airplanes.proceed(allSteps_events,init_step, index_of_plane,current_result);


      //  for (int p= 0; p < tables.length)
    }

    private void proceed(int[][] allSteps_events, int step, int index_of_plane, int current_result) {

        if (step == allSteps_events.length) {
            System.out.println(current_result);
            if (current_result > result) result = current_result;
        }
        for (int l = step; l < allSteps_events.length; l++){
            int stepValue = allSteps_events[l][index_of_plane];
            if (stepValue == 2)
            current_result -= 1;
            else current_result += stepValue;
        }
        System.out.println(current_result);

    }

  /*  private static int[] LOCATE_PLANE() {
        int [] plane_setting = new int[5];

        for (int g = 0; g < 5; g++) {
            if (g == 2) {
                plane_setting[g] = 5;
            } else {
                plane_setting[g] = 0;
            }
        }
        return plane_setting;
    }
*/
    public static int[][] LOAD_DATA() throws FileNotFoundException {


        System.setIn(new FileInputStream("src/com/ambro/airplanes"));
        Scanner input = new Scanner(System.in);
        int no_of_rows = input.nextInt();

        int[][] allSteps_events = new int[no_of_rows][5];

        for (int k = no_of_rows-1; k >= 0; k--) {
            for (int l = 0; l < 5; l++) {
                allSteps_events[k][l] = input.nextInt();
            }
        }



        return allSteps_events;
    }

}
