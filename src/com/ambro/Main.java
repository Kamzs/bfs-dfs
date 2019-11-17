package com.ambro;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Graph graph = new Graph(11);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(0,3);
        graph.addEdge(2,6);
        graph.addEdge(5,6);
        graph.addEdge(1,5);
        graph.addEdge(1,4);
        graph.addEdge(5,7);
        graph.addEdge(5,9);
        graph.addEdge(4,7);
        graph.addEdge(7,8);
        graph.addEdge(8,9);
        graph.addEdge(9,10);

        System.out.println("BFS");
        System.out.println("min number of steps: "+ Main.BFS(graph,0,10));
        System.out.println("--------------");
        System.out.println("DFS");
        System.out.println("traversing depth first road to end included 5 steps: "+ Main.DFS(graph,0,10));

    }

    static class ListFifo {

        private int SIZE = 1;
        private int[] table = new int[SIZE];
        private int index_for_new_el = 0;
        private int index_of_front_el = 0;

        void add_back_el(int x) {
            if (index_for_new_el < SIZE) table[index_for_new_el] = x;
            else {
                makeBigger();
                table[index_for_new_el] = x;
            }
            index_for_new_el++;
        }

        int pull_first_el() {
            if (index_of_front_el < index_for_new_el) {
                index_of_front_el++;
                return table[index_of_front_el - 1];
            } else {
                System.out.println("error-pull");
                return -1000000000;
            }
        }


        int get(int x) {
            if (x >= index_of_front_el && x < index_for_new_el) return table[x];
            else {
                System.out.println("error-get");
                return -1000000000;
            }
        }

        int length (){return index_for_new_el-index_of_front_el;}

        void makeBigger() {
            SIZE *= 2;
            int[] newTable = new int[SIZE];
            for (int z = 0; z < table.length; z++) {
                newTable[z] = table[z];
            }
            table = newTable;
        }

        @Override
        public String toString() {
            return "ListFifo{" +
                    "SIZE=" + SIZE +
                    ", table=" + Arrays.toString(table) +
                    ", index_for_new_el=" + index_for_new_el +
                    '}';
        }
    }

    static class ListLifo {

        private int SIZE = 1;
        private int[] table = new int[SIZE];
        private int index_for_new_el = 0;

        void add_back_el(int x) {
            if (index_for_new_el < SIZE) table[index_for_new_el] = x;
            else {
                makeBigger();
                table[index_for_new_el] = x;
            }
            index_for_new_el++;
        }

        int pull_last_el() {
            if (index_for_new_el>0) {
                index_for_new_el--;
                return table[index_for_new_el];
            } else {
                System.out.println("error-pull");
                return -1000000000;
            }
        }


        int get(int x) {
            if (x > index_for_new_el) return table[x];
            else {
                System.out.println("error-get");
                return -1000000000;
            }
        }

        int length (){return index_for_new_el;}

        void makeBigger() {
            SIZE *= 2;
            int[] newTable = new int[SIZE];
            for (int z = 0; z < table.length; z++) {
                newTable[z] = table[z];
            }
            table = newTable;
        }

        @Override
        public String toString() {
            return "ListFifo{" +
                    "SIZE=" + SIZE +
                    ", table=" + Arrays.toString(table) +
                    ", index_for_new_el=" + index_for_new_el +
                    '}';
        }
    }

    static class Graph {
        int NoOfNodes;
        ListFifo[] tableOfNodes;

        Graph(int NoOfNodes) {
            this.NoOfNodes = NoOfNodes;
            this.tableOfNodes = new ListFifo[NoOfNodes];
            for (int k=0; k < tableOfNodes.length; k++) {
                tableOfNodes[k] = new ListFifo();
            }
        }

        void addEdge(int from, int to){
            tableOfNodes[from].add_back_el(to);
            tableOfNodes[to].add_back_el(from);

        }

        @Override
        public String toString() {
            return "Graph{" +
                    "NoOfNodes=" + NoOfNodes +
                    ", tableOfNodes=" + Arrays.toString(tableOfNodes) +
                    '}';
        }
    }

    static int BFS (Graph graph, int start, int end){

       System.out.println("graph: "+ graph);

        boolean[] visitedNodesTable = new boolean[graph.NoOfNodes];
        int[] distanceOnNode = new int[graph.NoOfNodes];

        ListFifo listOfNodesToExamine = new ListFifo();

        listOfNodesToExamine.add_back_el(start);
        visitedNodesTable[start] =true;
        System.out.println("visited node"+start);

        distanceOnNode[start]=0;


        while (listOfNodesToExamine.length()>0){
            System.out.println(listOfNodesToExamine.toString());
            int nodeToCheck = listOfNodesToExamine.pull_first_el();
            System.out.println("pulled to check: "+ nodeToCheck);

            for (int i =0; i < graph.tableOfNodes[nodeToCheck].length();i++){
                int nodeAdjacent = graph.tableOfNodes[nodeToCheck].get(i);
                if (visitedNodesTable[nodeAdjacent]==false) {
                    System.out.println("visited node"+nodeAdjacent);
                    listOfNodesToExamine.add_back_el(nodeAdjacent);
                    distanceOnNode[nodeAdjacent] = distanceOnNode[nodeToCheck] + 1;
                    visitedNodesTable[nodeAdjacent]=true;
                    if (nodeAdjacent == end) return distanceOnNode[nodeAdjacent];
                }
            }
        }
        return -100000000;
    }

    static int DFS (Graph graph, int start, int end){

        System.out.println("graph: "+ graph);

        boolean[] visitedNodesTable = new boolean[graph.NoOfNodes];
        int[] distanceOnNode = new int[graph.NoOfNodes];

        ListLifo listOfNodesToExamine = new ListLifo();

        listOfNodesToExamine.add_back_el(start);
        visitedNodesTable[start] =true;
        System.out.println("visited node"+start);

        distanceOnNode[start]=0;


        while (listOfNodesToExamine.length()>0){
            System.out.println(listOfNodesToExamine.toString());
            int nodeToCheck = listOfNodesToExamine.pull_last_el();
            System.out.println("pulled to check: "+ nodeToCheck);

            for (int i = 0; i < graph.tableOfNodes[nodeToCheck].length();i++){
                int nodeAdjacent = graph.tableOfNodes[nodeToCheck].get(i);
                if (visitedNodesTable[nodeAdjacent]==false) {
                    System.out.println("visited node"+nodeAdjacent);
                    listOfNodesToExamine.add_back_el(nodeAdjacent);
                    distanceOnNode[nodeAdjacent] = distanceOnNode[nodeToCheck] + 1;
                    visitedNodesTable[nodeAdjacent]=true;
                    if (nodeAdjacent == end) return distanceOnNode[nodeAdjacent];
                }
            }
        }
        return -100000000;
    }

}
