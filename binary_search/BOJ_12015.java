import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();

    int[] seq = new int[N];
    int[] LIS = new int[N];

    for (int i = 0; i < N; i++) {
      seq[i] = sc.nextInt();
    }

    // LIS 초기 값으로 첫 번째 수열의 값
    LIS[0] = seq[0];
    int lengthOfLIS = 1;

    for (int i = 1; i < N; i++) {
      int key = seq[i];

      // 만약 key가 LIS의 마지막 값보다 클 경우 추가
      if (LIS[lengthOfLIS - 1] < key) {
        lengthOfLIS++;
        LIS[lengthOfLIS - 1] = key;
      }
      else {
        // Lower Bound 이분 탐색
        int lo = 0;
        int hi = lengthOfLIS;
        while (lo < hi) {
          int mid = (lo + hi) / 2;

          if (LIS[mid] < key) {
            lo = mid + 1;
          }
          else {
            hi = mid;
          }
        }
        /*
        * lo가 LIS에서 대치될 원소의 위치가 될 것이고
        * 해당 위치에 key값으로 원소를 바꿔줌
        */

        LIS[lo] = key;
        
      }
    }

    // 위 과정을 통해 나온 LIS의 길이 출력
    System.out.println(lengthOfLIS);
  }
}
