package com.ambro;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Airplanes {
    int result=-1;

    public static void main(String[] args) throws FileNotFoundException {

        long a = System.currentTimeMillis();
        Airplanes airplanes = new Airplanes();

        Object[] listOfCases = airplanes.LOAD_DATA();
        int init_step = 0;
        int index_of_plane = 2;
        int current_result = 0;
        boolean detonated = false;

        for (int f=0; f < listOfCases.length;f++) {
            airplanes.result = -1;

            int[][] allSteps_events = (int[][])listOfCases[f];

            airplanes.proceed(airplanes, allSteps_events, init_step, index_of_plane, current_result, detonated);
            System.out.println(airplanes.result);

        }

        long b = System.currentTimeMillis();
        System.out.println("runtime was : " + (b - a) + " ms");

    }

    private void proceed(Airplanes airplanes, int[][] allSteps_events, int step, int index_of_plane, int current_result, boolean detonated) {
        if (current_result>-1) {
            if (step == allSteps_events.length) {
       //         System.out.println(current_result);
                if (current_result>result) result = current_result;
            }
            else {

                    int[] newIndexesOfPlane;
                    if (index_of_plane == 4) {
                        newIndexesOfPlane = new int[2];
                        newIndexesOfPlane[0] = index_of_plane - 1;
                        newIndexesOfPlane[1] = index_of_plane;
                    } else if (index_of_plane == 0) {
                        newIndexesOfPlane = new int[2];
                        newIndexesOfPlane[0] = 0;
                        newIndexesOfPlane[1] = 1;
                    } else {
                        newIndexesOfPlane = new int[3];
                        newIndexesOfPlane[0] = index_of_plane - 1;
                        newIndexesOfPlane[1] = index_of_plane;
                        newIndexesOfPlane[2] = index_of_plane + 1;
                    }

                if (detonated==true) {
                    for (int index : newIndexesOfPlane) {

                        int stepValue = allSteps_events[step][index];
                        int newResult;
                        if (stepValue == 2)
                            newResult = current_result - 1;
                        else newResult = current_result + stepValue;
                        airplanes.proceed(airplanes, allSteps_events, step + 1, index, newResult, detonated);
                    }
                }
                else {
                    for (int z = 0; z < 2; z++) {
                        if (z == 0) {
                            for (int index : newIndexesOfPlane) {

                                int stepValue = allSteps_events[step][index];
                                int newResult;
                                if (stepValue == 2)
                                    newResult = current_result - 1;
                                else newResult = current_result + stepValue;
                                airplanes.proceed(airplanes, allSteps_events, step + 1, index, newResult, detonated);
                            }
                        } else {
                            int[][] tableAfterBombDetonation = new int[allSteps_events.length][5];
                            for (int k = 0; k < allSteps_events.length; k++) {
                                for (int l = 0; l < 5; l++) {
                                   tableAfterBombDetonation[k][l] = allSteps_events[k][l];
                                }
                            }
                  //          System.out.println(Arrays.deepToString(allSteps_events));
                 //           System.out.println(Arrays.deepToString(tableAfterBombDetonation));
                            for (int k = step; k < step + 5 && k < allSteps_events.length; k++) {
                                for (int l = 0; l < 5; l++) {
                                    if (allSteps_events[k][l] == 2) tableAfterBombDetonation[k][l] = 0;
                                }
                            }
               //             System.out.println(Arrays.deepToString(tableAfterBombDetonation));

                            for (int index : newIndexesOfPlane) {

                                int stepValue = tableAfterBombDetonation[step][index];
                                int newResult;
                                if (stepValue == 2)
                                    newResult = current_result - 1;
                                else newResult = current_result + stepValue;
                                detonated = true;
                                airplanes.proceed(airplanes, tableAfterBombDetonation, step + 1, index, newResult,detonated);
                            }

                        }
                    }
                }

                }
            }
   //     else System.out.println("ship destroyed");
    }

    public Object[] LOAD_DATA() throws FileNotFoundException {


        System.setIn(new FileInputStream("src/com/ambro/airplanes"));
        Scanner input = new Scanner(System.in);

        int no_of_cases = input.nextInt();
     //   ListFiFO listOfCases = new ListFiFO();
        Object[] objects = new Object[no_of_cases];

        for (int no =0; no < no_of_cases; no++){
            int no_of_rows = input.nextInt();
            int[][] allSteps_events = new int[no_of_rows][5];

            for (int k = no_of_rows-1; k >= 0; k--) {
                for (int l = 0; l < 5; l++) {
                    allSteps_events[k][l] = input.nextInt();
                }
            }
            objects[no] = allSteps_events;
        }


        return objects;
    }

    class ListFiFO{
        int SIZE = 1;
        Object [] list = new Object[SIZE];
        int index_of_first_el = 0;
        int current_index_for_new = 0;

        void add_back(Object x){
            if (current_index_for_new<SIZE){
            list[current_index_for_new]=x;
            current_index_for_new++;
            }
            else {
                make_bigger();
                list[current_index_for_new]=x;
                current_index_for_new++;
            }
        }

        Object pop_first_el (){
            index_of_first_el++;
            return list[index_of_first_el-1];
        }


        Object get_by_index (int index){
            return list[index];
        }

        void make_bigger(){
            SIZE *=2;
            Object [] newTable = new Object [SIZE];
            for (int i=0; i < list.length; i++){
                newTable[i] = list[i];
            }
            list = newTable;
        }

    }

}
