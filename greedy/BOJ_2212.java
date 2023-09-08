import java.util.*;

public class Main {
  
   public static void main(String[] args) {
     
     Scanner sc= new Scanner(System.in);

     // 입력
     int n = sc.nextInt(); // 센서 갯수
     int k = sc.nextInt(); // 집중국 갯수

     // 집중국 갯수(k) >= 센서 갯수(n) 이면, 0 출력하고 종료
     if (k >= n) {
       System.out.println(0);
       return;
     }
     int sum = 0;

     // 센서 위치 입력
     int[] sensorArr = new int[n];
     for (int i = 0; i < n; i++) {
       sensorArr[i] = sc.nextInt();
     }

     // 센서 위치 오름차순 정렬
     Arrays.sort(sensorArr);

     // 각 센서 거리 차이 계산
     Integer[] diffArr = new Integer[n - 1];
     for (int i = 0; i < n - 1; i++) {
       diffArr[i] = sensorArr[i + 1] - sensorArr[i];
     }

     // 차이 배열 내림차순 정렬
     Arrays.sort(diffArr, Collections.reverseOrder());

     // 차이 배열의 k - 1 ~ 마지막 까지의 합 출력
     for (int i = k - 1; i < n - 1; i++) {
       sum += diffArr[i];
     }
     System.out.println(sum);
     
   }
}

/* 나의 풀이
import java.util.*;

class Main {

  public static int N, K;
  public static ArrayList<Integer> senserList = new ArrayList<>();
  public static PriorityQueue<Integer> distancePQ = new PriorityQueue<>(Collections.reverseOrder());
  public static ArrayList<Integer> zipList = new ArrayList<>();
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    K = sc.nextInt();

    // 센서 위치 정렬
    for (int i = 0; i < N; i++) {
      senserList.add(sc.nextInt());
    }
    Collections.sort(senserList);

    // 센서가 하나이거나, 집중국이 하나인 경우
    if (N == 1) {
      System.out.println(0);
      return;
    }
    if (K == 1) {
      System.out.println(senserList.get(senserList.size() - 1) - senserList.get(0));
      return;
    }

    // 차이 계산 후 정렬
    for (int i = 1; i < N; i++) {
      distancePQ.offer(senserList.get(i) - senserList.get(i - 1));
    }

    // K - 1 개 고르기
    for (int i = 0; i < K - 1; i++) {
      for (int j = 1; j < N; j++) {
        if (distancePQ.peek() == senserList.get(j) - senserList.get(j - 1)) {
          distancePQ.poll();
          zipList.add(j);
          break;
        }
      }
    }

    // 합 계산
    int sum = senserList.get(zipList.get(0) - 1) - senserList.get(0);
    for (int i = 1; i < zipList.size(); i++) {
      sum = sum + senserList.get(zipList.get(i) - 1) - senserList.get(zipList.get(i - 1));
    }
    sum = sum + (senserList.get(senserList.size() - 1) - senserList.get(zipList.get(zipList.size() - 1)));

    System.out.println(sum);
  }
}
*/