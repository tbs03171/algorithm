/* 감소하는 방향 */
import java.util.*;
import java.io.*;

public class Main {
  static List<Long> list = new ArrayList<>();
  static int N;
  static int count = 0;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    if (N <= 10) {
      System.out.print(N);
      return;
    } else if (N >= 1023) {
      System.out.print(-1);
      return;
    } 

    for (int i = 0; i < 10; i++) {
      DFS(i);
    }

    Collections.sort(list);
    System.out.print(list.get(N));
  }

  private static void DFS(long num) {
    list.add(num);
    long modValue = num % 10;
    if (modValue == 0) {
      return;
    }

    for (long i = modValue - 1; i >= 0; i--) {
      long newValue = num * 10 + i;
      DFS(newValue);
    }
  }
}

/* 증가하는 방향
import java.util.*;

public class Main {

  static ArrayList<Long> list;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    list = new ArrayList<>();

    if (n <= 10) System.out.println(n);
    else if (n > 1022) System.out.println(-1);
    else {
      for (int i = 0; i < 10; i++) {
        bp(i, 1);
      }
      Collections.sort(list);

      System.out.println(list.get(n));
    }
  }

  public static void bp(long num, int idx) {
    if (idx > 10) return;

    list.add(num);
    for (int i = 0; i < num % 10; i++) {
      bp((num * 10) + i, idx + 1);
    }
  }
}
*/

/* 나의 풀이
import java.util.*;

class Main {

    public static int N;
    public static ArrayList<ArrayList<Long>> list = new ArrayList<>();
    public static long result = -1;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        // 0 ~ 9번째 감소하는 수
        list.add(new ArrayList<Long>());
        for (long i = 0; i <= 9; i++) {
            list.get(0).add(i);
        }

        // 10번째부터
        for (int i = 1; i <= 9; i++) { // 자릿수
            list.add(new ArrayList<Long>());
            long unit = (long)Math.pow(10, i);
            for (int j = i; j <= 9; j++) { // 첫째 자릿수
                for (int k = 0; j < list.get(i - 1).size(); k++) {
                    if (list.get(i - 1).get(k) * 10 >= j * unit) break;
                    list.get(i).add(j * unit + list.get(i - 1).get(k));
                }
            }
        }

        int cnt = -1;
        for (ArrayList<Long> temp : list) {
          for (int i = 0; i < temp.size(); i++) {
            cnt++;
            if (cnt == N) {
              result = temp.get(i);
              break;
            }
          }
        }

        // for (int i = 0; i < list.size(); i++) {
        //     System.out.println(list.get(i));
        // }

      System.out.println(result);

    }
}
*/