package com.ambro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationAlgorithm {
    public static void main(String[] args) {
        PermutationAlgorithm PermutationAlgorithm = new PermutationAlgorithm();
        PermutationAlgorithm.listPermutations(new int [] {1,2,3,4});
    }
    public void listPermutations(int[] a) {
        ArrayList<int[]> results= new ArrayList<int[]>();
        listPermutations(a, 0, results);
    }

    private void listPermutations(int[] a, int start, List<int[]> result) {
        if (start >= a.length) {
            System.out.println(Arrays.toString(a));
        } else {
            for (int i = start; i < a.length; i++) {
                swap(a, start, i);
                listPermutations(a, start+1, result);
                swap(a, start, i);
            }
        }
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
