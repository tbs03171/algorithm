import java.util.*;

class Main {

  public static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();

    // 정수 x
    for (int i = 0; i < n; i++) {
      int x = sc.nextInt();
      if (x > 0) { // x가 자연수라면 배열에 x 값 추가
        pq.offer(x);
      } else { // x가 0이라면 배열에서 가장 큰 값 출력하고 제거
        if (pq.isEmpty()) {
          System.out.println(0);
          continue;
        }
        System.out.println(pq.poll());
      }
    }
  }
}
