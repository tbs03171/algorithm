import java.util.*;

class Main {

  public static int[] arr = new int[10];
  
  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);
    String str = sc.next();

    for (int i = 0; i < str.length(); i++) {
      arr[str.charAt(i) - '0']++;
    }

    for (int i = arr.length - 1; i >= 0; i--) {
      while (arr[i]-- > 0) {
        System.out.print(i);
      }
    }
  }
}