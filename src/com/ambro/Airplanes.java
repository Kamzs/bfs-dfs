package com.ambro;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Airplanes {

    int result=-100;

    public static void main(String[] args) throws FileNotFoundException {

        long a = System.currentTimeMillis();

        int[][] allSteps_events = LOAD_DATA();
        //int[] planeSetting = LOCATE_PLANE();
        int init_step =0;
        int index_of_plane =2;
        int current_result =0;


        System.out.println(Arrays.deepToString(allSteps_events));
        Airplanes airplanes = new Airplanes();
        airplanes.proceed(airplanes,allSteps_events,init_step, index_of_plane,current_result);
        System.out.println(airplanes.result);
        long b = System.currentTimeMillis();
        System.out.println("runtime was : " + (b - a) +" ms");

    }

    private void proceed(Airplanes airplanes, int[][] allSteps_events, int step, int index_of_plane, int current_result) {
        if (current_result>-1) {
            if (step == allSteps_events.length) {
                //System.out.println(current_result);
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
                for (int index : newIndexesOfPlane) {

                    int stepValue = allSteps_events[step][index];
                    int newResult;
                    if (stepValue == 2)
                        newResult = current_result -1;
                    else newResult = current_result + stepValue;
                    airplanes.proceed(airplanes,allSteps_events, step + 1, index, newResult);
                }
            }
        }
        //else System.out.println("ship destroyed");
    }

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

    class ListFiFO{
        int SIZE = 1;
        int [] list = new int[SIZE];
        int index_of_first_el = 0;
        int current_index_for_new = 0;

        void add_back(int x){
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

        int pop_first_el (){
            index_of_first_el++;
            return list[index_of_first_el-1];
        }


        int get_by_index (int index){
            return list[index];
        }

        void make_bigger(){
            SIZE *=2;
            int [] newTable = new int [SIZE];
            for (int i=0; i < list.length; i++){
                newTable[i] = list[i];
            }
            list = newTable;
        }

    }

}
