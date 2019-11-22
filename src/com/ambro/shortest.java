package com.ambro;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
public class shortest {

        static int[] tab = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        static boolean[] isVisited;
        static int minDistance = Integer.MAX_VALUE;
        static int numberOfPoints;
        static int[] pointsX;
        static int[] pointsY;

        public static void main(String[] args) throws FileNotFoundException {
            System.setIn(new FileInputStream("src/com/ambro/text"));
            Scanner input = new Scanner(System.in);
            numberOfPoints = input.nextInt();
            isVisited = new boolean[numberOfPoints];
            pointsX = new int[numberOfPoints];
            pointsY = new int[numberOfPoints];

            for (int i = 0; i < numberOfPoints; i++) {
                pointsX[i] = input.nextInt();
                pointsY[i] = input.nextInt();
            }
            solve(new int[numberOfPoints], 0);
            System.out.println(minDistance);
        }

        static void solve(int[] calcTab, int depth) {
            boolean isLast = true;
            for (int i = 0; i < numberOfPoints; i++) {
                if (!isVisited[i]) {
                    isLast = false;
                    isVisited[i] = true;
                    calcTab[depth] = tab[i];
                    solve(calcTab, depth + 1);
                    isVisited[i] = false;
                }
            }
            if (isLast) {
                System.out.println(Arrays.toString(calcTab));
                int distance = calculateDistance(0, 0, pointsX[calcTab[0]], pointsY[calcTab[0]]);
                for (int i = 1; i < numberOfPoints; i++) {
                    if (distance > minDistance) continue;
                    distance += calculateDistance(pointsX[calcTab[i - 1]], pointsY[calcTab[i - 1]], pointsX[calcTab[i]], pointsY[calcTab[i]]);
                }
                distance += calculateDistance(pointsX[calcTab[numberOfPoints - 1]], pointsY[calcTab[numberOfPoints - 1]], 10, 10);
                if (distance < minDistance) minDistance = distance;

            }
        }

        static int calculateDistance(int x1, int y1, int x2, int y2) {
            return Math.abs(x2 - x1) + Math.abs(y2 - y1);
        }
    }


