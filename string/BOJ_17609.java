import java.util.*;

public class Main {

  static int t;
  static String s;
  static char[] arr;

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    t = sc.nextInt();

    for (int tc = 1; tc <= t; tc++) {
      s = sc.next();
      arr = s.toCharArray();
      int left = 0;
      int right = s.length() - 1;
      if (check(left, right)) {
        System.out.println(0);
        continue;
      }
      if (checkS(left, right)) {
        System.out.println(1);
      } else {
        System.out.println(2);
      }
    }
  }

  private static boolean check(int left, int right) {

    while (left <= right) {
      if (arr[left] != arr[right]) { // 다름
        return false;
      }
      left += 1;
      right -= 1;
    }
    return true;
    
  }

  private static boolean checkS(int left, int right) {

    while (left <= right) {
      if (arr[left] != arr[right]) { // 다름
        boolean a = check(left + 1, right);
        boolean b = check(left, right - 1);
        if (a == false && b == false) return false;
        else return true;
      }
      left += 1;
      right -= 1;
    }

    return true;
  }
}

/* 나의 풀이
import java.util.*;

class Main {
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();

    sc.nextLine();
    while (T-- > 0) {
      String s = sc.nextLine();

      // 회문인가
      if (isPalindrome(s)) {
        System.out.println(0);
        continue;
      }

      // 유사회문인가
      if (isPseudoPalindrome(s)) {
        System.out.println(1);
        continue;
      }

      System.out.println(2);
      StringBuilder sbA = new StringBuilder(s);
      StringBuilder sbB = new StringBuilder(s);
      sbB.reverse();
    }
    
  }

  public static boolean isPalindrome(String s) {
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) != s.charAt(s.length() - 1 - i)) return false;
    }
    return true;
  }

  public static boolean isPseudoPalindrome(String s) {
    StringBuilder sbA = new StringBuilder(s);
    StringBuilder sbB = new StringBuilder(s);
    sbB.reverse();

    for (int i = 0; i < sbA.length(); i++) {
      if (sbA.charAt(i) != sbB.charAt(i)) {
        if (isPalindrome(sbA.deleteCharAt(i).toString())) return true;
        if (isPalindrome(sbB.deleteCharAt(i).toString())) return true;
        break;
      }
    }

    return false;
  }
}
*/