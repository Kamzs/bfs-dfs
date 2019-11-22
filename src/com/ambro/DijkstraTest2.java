package com.ambro;

import java.util.Arrays;

public class DijkstraTest2 {

    public static void main(String[] args) {

        Graph graph = new Graph(6);
        graph.addEdge(new Edge(0, 1, 3));
        graph.addEdge(new Edge(1, 2, 2));
        graph.addEdge(new Edge(1, 4, 10));
        graph.addEdge(new Edge(2, 4, 8));
        graph.addEdge(new Edge(2, 3, 1));
        graph.addEdge(new Edge(4, 3, 4));
        graph.addEdge(new Edge(3, 5, 6));
        graph.addEdge(new Edge(4, 5, 5));


        System.out.println("BFS");
        System.out.println("min number of steps: " + Arrays.deepToString(DijkstraTest2.BFS(graph, 0, 10)));

    }

    public static class Edge {
        int from;
        int to;
        int length;

        public Edge(int from, int to, int length) {
            this.from = from;
            this.to = to;
            this.length = length;
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }

        public int getLength() {
            return length;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", length=" + length +
                    '}';
        }
    }

    static class ListFifo {

        private int SIZE = 1;
        private Object[] table = new Object[SIZE];
        private int index_for_new_el = 0;
        private int index_of_front_el = 0;

        void add_back_el(Object x) {
            if (index_for_new_el < SIZE) table[index_for_new_el] = x;
            else {
                makeBigger();
                table[index_for_new_el] = x;
            }
            index_for_new_el++;
        }

        Object pull_first_el() {
            if (index_of_front_el < index_for_new_el) {
                index_of_front_el++;
                return table[index_of_front_el - 1];
            } else {
                System.out.println("error-pull");
                return -1000000000;
            }
        }


        Object get(int x) {
            if (x >= index_of_front_el && x < index_for_new_el) return table[x];
            else {
                System.out.println("error-get");
                return -1000000000;
            }
        }

        int length() {
            return index_for_new_el - index_of_front_el;
        }

        void makeBigger() {
            SIZE *= 2;
            Object[] newTable = new Object[SIZE];
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
                    ", index_of_front_el=" + index_of_front_el +
                    '}';
        }
    }


    static class Graph {
        int NoOfNodes;
        //nie ma znaczenia chyba tutaj czy to jest tablica 1. list fifo 2. lifo czy 3. zwyklych tablic
        ListFifo[] tableOfVerticesToPinEdges;

        Graph(int NoOfNodes) {
            this.NoOfNodes = NoOfNodes;
            this.tableOfVerticesToPinEdges = new ListFifo[NoOfNodes];
            for (int k = 0; k < tableOfVerticesToPinEdges.length; k++) {
                tableOfVerticesToPinEdges[k] = new ListFifo();
            }
        }

        void addEdge(Edge edge) {
            tableOfVerticesToPinEdges[edge.from].add_back_el(edge);
        }

        @Override
        public String toString() {
            return "Graph{" +
                    "NoOfNodes=" + NoOfNodes +
                    ", tableOfVerticesToPinEdges=" + Arrays.toString(tableOfVerticesToPinEdges) +
                    '}';
        }
    }


    static int[][] BFS(Graph graph, int start, int end) {

        System.out.println("graph: " + graph);

        int[][] distancesToNodes = new int[graph.NoOfNodes][2];
        for (int i = 0; i < graph.NoOfNodes; i++) {
            for (int l = 0; l < graph.NoOfNodes; l++) {
                for (int k = 0; k < 2; k++) {
                    if (k == 0) {
                        distancesToNodes[l][k] = 100000;
                    } else distancesToNodes[l][k] = -1;
                }
            }
        }
        System.out.println(Arrays.deepToString(distancesToNodes));

        //odleglosc 0 dla punktu startowego - zeby potem w petli nie bylo problemu z dodawaniem poprzednich wartosci
        distancesToNodes[start][0] = 0;

        ListFifo listOfNodesToExamine = new ListFifo();

        listOfNodesToExamine.add_back_el(start);

        while (listOfNodesToExamine.length() > 0) {

            int nodeToCheck = (int) listOfNodesToExamine.pull_first_el();
            System.out.println("pulled to check: " + nodeToCheck);

            for (int z = 0; z < graph.tableOfVerticesToPinEdges[nodeToCheck].length(); z++) {


                //przypisanie krawedzi z node ToCheck do sasiadujacego noda do zmiennej
                Edge edge = (Edge) graph.tableOfVerticesToPinEdges[nodeToCheck].get(z);

                //wrzucenie do listy kropek do przegaldania kropek sasiadujacych
                listOfNodesToExamine.add_back_el(edge.getTo());

                //ustalenie jaki indeks na liscie sasiedztwa dla noda nodeToCheck ma edge laczacy go z nodem z

                int possiblyNewDist = distancesToNodes[nodeToCheck][0] + edge.getLength();
                System.out.println("sprawdzanie odlegosci do:"+ edge.getTo() + " przez " + edge.from);

                //stary dystans do noda edge.to
                int currentDist = distancesToNodes[edge.getTo()][0];
                if (possiblyNewDist < currentDist) {
                    distancesToNodes[edge.getTo()][0] = possiblyNewDist;
                    System.out.println("odleglosc zmodyfikowana - nowa najkrotsza: "+ possiblyNewDist);
                    distancesToNodes[edge.getTo()][1] = nodeToCheck;
                    System.out.println("odleglosc zmodyfikowana - nowa najkrotsza droga przez : "+ nodeToCheck);
                }
            }

        }
        return distancesToNodes;
    }
}