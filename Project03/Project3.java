import java.io.*;
import java.util.*;

public class Project3 {
  public static void cashiers(int coin_amounts[], int num_coins, int target){
    // Initialize result as a vector
    Vector<Integer> coin_purse = new Vector<>();

    // Check all denominations and find desired result. Add count to coin_purse
    for (int i=num_coins-1; i>=0; i--){
      while (target >= coin_amounts[i]){
        target -= coin_amounts[i];
        coin_purse.add(coin_amounts[i]);
      }
    }

    System.out.print("cashier's: "+coin_purse.size());
  }

  public static void dynamic(int coin_amounts[], int num_coins, int target){
    int[] coin_purse = new int[1000];
    for (int i=0; i<=target; i++){
      coin_purse[i] = Integer.MAX_VALUE;
    }

    // Base case
    coin_purse[0] = 0;

    // Check outer loop i for possible values between 1 - N
    // Inner loop j obtains index of coin array to find optimal solution
    for (int i=1; i<=target; i++){
      for (int j=0; j<num_coins; j++){
        if (coin_amounts[j] <= i){
          coin_purse[i] = Math.min(coin_purse[i], (1+coin_purse[i-coin_amounts[j]]));
        }
      }
    }
    System.out.print("\ndynamic: "+coin_purse[target]);
  }

  public static void main(String[] args) throws FileNotFoundException {
    File file = new File("input.txt");
    Scanner scan = new Scanner(file);
    int num_coins = scan.nextInt();
    int[] coin_amounts = new int[num_coins];

    scan.nextLine();
    for (int i=0; i<num_coins; i++){
      coin_amounts[i] = scan.nextInt();
    }
    int target = scan.nextInt();

    cashiers(coin_amounts, num_coins, target);
    dynamic(coin_amounts, num_coins, target);
  }
}
