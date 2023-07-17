import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuffer sb = new StringBuffer();
    
    PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
      @Override
      public int compare(Integer a, Integer b) {
        int A = Math.abs(a);
        int B = Math.abs(b);
        if (A > B) {
          return A - B;
        }
        else if (A == B) {
          if (a > b) return 1;
          else return -1;
        }
        else return -1;
      }
    });

    int N = Integer.parseInt(br.readLine());

    while (N-- > 0) {
      int x = Integer.parseInt(br.readLine());

      if (x != 0) {
        pq.offer(x);
      }
      else {
        if (!pq.isEmpty()) {
          sb.append(pq.poll()).append("\n");
        }
        else {
          sb.append("0\n");
        }
      }
    }

    System.out.print(sb);
  }
}

/* 나의 코드
import java.util.*;

class Main {
  public static int n;
  public static PriorityQueue<Integer> pq = new PriorityQueue<>(new MyComparator());
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();

    for (int i = 0; i < n; i++) {
      int x = sc.nextInt();

      if (x != 0) { // 배열에 x 값 추가
        pq.offer(x);
      } else { // 배열에서 출력하고 제거
        if (pq.isEmpty()) {
          System.out.println(0);
        } else {
          System.out.println(pq.poll());
        }
      }
    }
  }

  static class MyComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer num1, Integer num2) {
      if (Math.abs(num1) == Math.abs(num2)) {
        return num1.compareTo(num2);
      } else if (Math.abs(num1) < Math.abs(num2)) {
        return -1;
      } else {
        return 1;
      }
    }
  }
}
*/