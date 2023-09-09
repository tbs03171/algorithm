import java.io.*;

public class Main {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuffer s = new StringBuffer(br.readLine());
    StringBuffer t = new StringBuffer(br.readLine());

    while (s.length() < t.length()) {
      if (t.charAt(t.length() - 1) == 'A') {
        t.deleteCharAt(t.length() - 1);
      } else if (t.charAt(t.length() - 1) == 'B') {
        t.deleteCharAt(t.length() - 1);
        t.reverse();
      }
    }

    if (s.toString().equals(t.toString())) {
      System.out.println(1);
    } else {
      System.out.println(0);
    }
  }
}

/* 나의 풀이
import java.util.*;

class Main {
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    StringBuilder s1 = new StringBuilder(sc.nextLine());
    StringBuilder s2 = new StringBuilder(sc.nextLine());

    while (true) {
      if (s1.length() == s2.length()) {
        if (s1.toString().equals(s2.toString())) {
          System.out.println(1);
          return;
        }
        else {
          System.out.println(0);
          return;
        }
      }
      
      if (s2.charAt(s2.length() - 1) == 'A') { // 문자열 뒤에 A 삭제
        s2.delete(s2.length() - 1, s2.length());
      } else { // 문자열 뒤에 B 삭제 후 뒤집기
        s2.delete(s2.length() - 1, s2.length());
        s2.reverse();
      }
    }
  }
}
*/