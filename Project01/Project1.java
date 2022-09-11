/*
 Eliot Pardo
 Project 1
 2-15-22
 Stable marriage problem which outputs the number of instabilities in a given set.
 Given a .txt file, the first integer indicates N# of pairings. The next N sets of integers represent
 the men & women's preferences. The last N sets of integers represent a predetermined matching. The
 program then compares the predetermined matches to preferences & outputs any instabilities.
*/

import java.io.*;
import java.util.*;

public class Project1 {
  public static void main(String[] args) throws FileNotFoundException {

    // create file and scanner objects to read from input.txt
    File file = new File("input.txt");
    Scanner scan = new Scanner(file);
    int num_total = scan.nextInt(); // reads first int to establish # of pairs

    // initialize multidimensional arrays and global variables
    int[][] menArray = new int[num_total][num_total];
    int[][] womenArray = new int[num_total][num_total];
    int[][] pairingsArray = new int[num_total][2];
    int menSwitch = 0;
    int womenSwitch = 0;
    int pair1;
    int pair2;

    // scan through the next N ints & create multidimensional arrays
    // the first half are the men's preference arrays. the second
    // half are the women's preference arrays.
    scan.nextLine();
    for (int i=0; i < 2*num_total; i++){
      for (int j=0; j < num_total; j++){
        if (i < num_total){
          menArray[i][j] = scan.nextInt();
        }else{
          womenArray[i - num_total][j] = scan.nextInt();
        }
      }
    }

    // scan the remaining N ints for established pairings in pairing array
    // a new loop with an updated j value is needed
    for (int i=0; i < num_total; i++){
      for (int j=0; j < 2; j++){
        pairingsArray[i][j] = scan.nextInt();
      }
    }

    // initialize pair1 as the starting point for women's preference
    // looping through, compare women's array at [pair1-1][0] with pairing array [i][0]
    // if the 2 values don't match, consider it a switch and increase count.
    for (int i=0; i < num_total; i++){
      pair1 = pairingsArray[i][1];
      if (!Objects.equals(womenArray[pair1 - 1][0], pairingsArray[i][0])){
        womenSwitch++;
      }
    }
    // initialize pair2 as the starting point for men's preference
    // looping through, compare men's array at [pair2-1][0] with pairing array [i][1]
    // if the 2 values don't match, consider it a switch and increase count.
    for (int i=0; i < num_total; i++){
      pair2 = pairingsArray[i][0];
      if (!Objects.equals(menArray[pair2 - 1][0], pairingsArray[i][1])){
        menSwitch++;
      }
    }

    // compare switch cases and output instabilities
    if (womenSwitch > menSwitch){
      System.out.println(womenSwitch-menSwitch);
    }else if (womenSwitch == 0){
      System.out.println(womenSwitch);
    } else {
      System.out.println(menSwitch);
    }
    scan.close();
  }
}