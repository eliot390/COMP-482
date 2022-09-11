/*
 Eliot Pardo
 Project 2
 3-15-22
 Number of Puddles problem, which outputs the number of "puddles" in a 2D array.
 Given a .txt file, the first 2 integers indicate # of rows & columns. The next N sets of integers represent
 the grid to search through. 0's represent water and 1's represent grass. Allowing only up/down/left/right
 movements (no diagonal), collections of 0's are counted as puddles. Any break from a 1 starts a new puddle search.
*/

import java.io.*;
import java.util.*;

class Pair{
    int x, y;
    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Project2{
    // Movement arrays within a cell (up, down, left, right)
    private static final int[] row = {1,-1,0,0};
    private static final int[] col = {0,0,1,-1};

    // Check to see if movement is allowed or position is already processed
    public static boolean isSafe(int[][] puddleGrid, int x, int y, boolean[][] processed){
        return (x >= 0 && x < processed.length) && (y >= 0 && y < processed[0].length)
                && puddleGrid[x][y] == 0 && !processed[x][y];
    }

    public static void BFS(int[][] puddleGrid, boolean[][] processed, int i, int j){
        // create an empty queue and enqueue source node
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(i, j));
        processed[i][j] = true;

        while (!q.isEmpty()){
            // dequeue front node and process it. use peek() to check first element & poll() to return it
            int x = q.peek().x;
            int y = q.peek().y;
            q.poll();

            // check for four possible movements from the current cell and enqueue each valid movement
            int k = 0;
            while (k < row.length) {
                if (isSafe(puddleGrid, x + row[k], y + col[k], processed)){
                    processed[x + row[k]][y + col[k]] = true;
                    q.add(new Pair(x + row[k], y + col[k]));
                }
                k++;
            }
        }
    }

    public static int countPuddles(int[][] puddleGrid){
        if (puddleGrid == null || puddleGrid.length == 0){
            return 0;
        }

        int height = puddleGrid.length;
        int length = puddleGrid[0].length;
        boolean[][] processed = new boolean[height][length];

        int puddles = 0;
        for (int i = 0; i < height; i++){
            for (int j = 0; j < length; j++){
                // start BFS from each unprocessed node and increment puddles count
                if (puddleGrid[i][j] == 0 && !processed[i][j]){
                    BFS(puddleGrid, processed, i, j);
                    puddles++;
                }
            }
        }

        return puddles;
    }

    public static void main(String[] args) throws FileNotFoundException {

        // create file and scanner objects to read from input.txt
        File file = new File("input.txt");
        Scanner scan = new Scanner(file);
        int num_row = scan.nextInt(); // reads first int to establish # of rows
        int num_col = scan.nextInt(); // reads first int to establish # of columns

        int[][] puddleGrid = new int[num_row][num_col];

        scan.nextLine();
        for (int i=0; i < num_row; i++){
            for (int j=0; j < num_col; j++){
                puddleGrid[i][j] = scan.nextInt();
            }
        }
        System.out.print(countPuddles(puddleGrid)+"\n");
    }
}