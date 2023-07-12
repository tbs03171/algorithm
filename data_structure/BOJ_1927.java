import java.util.*;
import java.io.*;

class Main {

  public static int n;
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());

    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 0; i < n; i++) {
      int x = Integer.parseInt(br.readLine());
      if (x > 0) { // x가 자연수라면 배열에 x값 추가
        pq.offer(x);
      } else { // x가 0이라면 배열에서 가장 작은 값 출력 후 제거
        if (!pq.isEmpty()) {
          System.out.println(pq.poll());
        } else {
          System.out.println(0);
        }
      }
    }
  }
}
