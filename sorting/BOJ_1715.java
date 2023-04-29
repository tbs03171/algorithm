import java.util.*;

class Main {

  public static int n, result;
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 숫자 카드 묶음 수 N
    n = sc.nextInt();

    // 힙 (Heap)에 초기 카드 묶음을 모두 삽입
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 0; i < n; i++) {
      pq.offer(sc.nextInt());
    }

    // 힙에 원소가 1개 남을 때까지
    while (pq.size() != 1) {
      // 가장 작은 2개의 카드 묶음 꺼내기
      int one = pq.poll();
      int two = pq.poll();
      // 카드 묶음 합쳐서 다시 삽입
      int summary = one + two;
      result += summary;
      pq.offer(summary);
    }

    // 결과 출력
    System.out.println(result);
  }
}
