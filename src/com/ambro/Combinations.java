package com.ambro;

public class Combinations {

    public static void main(String[] args) {
        int[] table = {0,1,2,3};
        printAllCombinations(table);
    }

    private static void printAllCombinations(int[] table) {

        int [] newTable = new int[table.length];

        for (int i = 0; i < newTable.length; i++ ) {
            newTable[i] = table[0];
        }

        int line = 0 ;

        for (int k =0; k < newTable.length; k++) {
            int index =0;
            while (index < newTable.length) {
                newTable[line] = table[index];
                index++;
            }

            line ++;
        }
        for (int i = 0; i < newTable.length; i++ ) {
            newTable[i] = table[0];
        }

    }
}
