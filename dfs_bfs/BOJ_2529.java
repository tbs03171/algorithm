import java.util.*;

public class Main {

  static boolean[] check = new boolean[10]; // 0 ~ 9 까지 check
  static int n;
  static char[] a = new char[10]; // 부등호는 최대 9개
  static ArrayList ans = new ArrayList<>();

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();

    for (int i = 0; i < n; i++) {
      a[i] = sc.next().toCharArray()[0];
    }

    go(0, "");
    Collections.sort(ans);

    System.out.println(ans.get(ans.size() - 1));
    System.out.println(ans.get(0));
  }

  static void go(int index, String num) {
    if (index == n + 1) {
      ans.add(num);
      return;
    }

    for (int i = 0; i <= 9; i++) {
      if (check[i]) continue;
      if (index == 0 || ck(num.charAt(index - 1), (char) (i + '0'), a[index - 1])) {
        check[i] = true;
        go(index + 1, num + Integer.toString(i));
        check[i] = false;
      }
    }
  }

  static boolean ck(char a, char b, char c) {
    if (c == '<') {
      if (a > b) {
        return false;
      }
    }
    if (c == '>') {
      if (a < b) {
        return false;
      }
    }
    return true;
  }
}

/* 나의 풀이
import java.util.*;

class Main {

  public static int k;
  public static boolean[] visited;
  public static String[] input;
  public static ArrayList<String> list = new ArrayList<>();
  public static int[] result;
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    k = sc.nextInt();
    sc.nextLine();
    input = sc.nextLine().split(" ");
    result = new int[k + 1];
    visited = new boolean[10];

    // 백트래킹
    for (int i = 0; i < 10; i++) {
      result[0] = i;
      visited[i] = true;
      dfs(1);
      visited[i] = false;
    }

    // 결과 출력
    System.out.println(list.get(list.size() - 1));
    System.out.println(list.get(0));
  }

  public static void dfs(int at) {

    if (at == k + 1) {
      // result 를 문자열로 변환해서 저장
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < k + 1; i++) {
        sb.append(result[i]);
      }
      list.add(sb.toString());
      return;
    }

    if (input[at - 1].equals("<")) {
      for (int i = result[at - 1] + 1; i < 10; i++) {
        if (!visited[i]) {
          result[at] = i;
          visited[i] = true;
          dfs(at + 1);
          visited[i] = false;
        }
      }
    }

    if (input[at - 1].equals(">")) {
      for (int i = 0; i < result[at - 1]; i++) {
        if (!visited[i]) {
          result[at] = i;
          visited[i] = true;
          dfs(at + 1);
          visited[i] = false;
        }
      }
    }
  }
}
*/