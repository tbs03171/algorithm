/* 누적합 */
import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int h = Integer.parseInt(st.nextToken());

    int[] down = new int[h + 2];
    int[] up = new int[h + 2];

    for (int i = 1; i <= (n / 2); i++) {
      int a = Integer.parseInt(br.readLine());
      int b = h - Integer.parseInt(br.readLine()) + 1;
      down[a]++;
      up[b]++;
    }

    for (int i = 1; i <= h; i++) {
      down[i] += down[i - 1];
    }

    for (int i = h; i >= 1; i--) {
      up[i] += up[i + 1];
    }

    int min = n;
    int cnt = 0;
    for (int i = 1; i < h + 1; i++) {
      int dif = (down[h] - down[i - 1]) + (up[1] - up[i + 1]);

      if (dif < min) {
        min = dif;
        cnt = 1;
      } else if (dif == min) cnt++;
    }

    System.out.println(min + " " + cnt);
  }
}

/* 이진탐색
import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int h = Integer.parseInt(st.nextToken());

    int[] down = new int[n / 2];
    int[] up = new int[n / 2];

    for(int i = 0; i < n/2; i++) {
      int a = Integer.parseInt(br.readLine());
      int b = Integer.parseInt(br.readLine());
      down[i] = a;
      up[i] = b;
    }
    Arrays.sort(up);
    Arrays.sort(down);
    
    int min = n;
    int cnt = 0;
    for (int i = 0; i < h + 1; i++) {
      int conflict = binarySearch(0, n / 2, i, down) + binarySearch(0, n / 2, h - i + 1, up);
      if (min == conflict) {
        cnt++;
        continue;
      }
      if (min > conflict) {
        min = conflict;
        cnt = 1;
      }
    } 
    System.out.println(min + " " + cnt);
  }

  static int binarySearch(int left, int right, int h, int[] arr) {

    while (left < right) {
      int mid = (left + right) / 2;

      if (arr[mid] >= h) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }

    return arr.length - right;
  }
}
/*

/* 나의 풀이
import java.util.*;

class Main {

  public static int N, H;
  public static int[] arrA; // 석순
  public static int[] arrB; // 종유석
  public static ArrayList<Integer> result = new ArrayList<>(); // 각 구간의 파괴해야 하는 장애물 수 저장
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    H = sc.nextInt();
    arrA = new int[N / 2];
    arrB = new int[N / 2];

    // 종유석, 석순 길이들 입력
    for (int i = 0; i < N; i++) {
      int x = sc.nextInt();
      if (i % 2 == 0) { // 석순
        arrA[i / 2] = x;
      } else { // 종유석 (-1 해서 저장)
        arrB[i / 2] = x - 1;
      }
    }

    // 정렬
    Arrays.sort(arrA);
    Arrays.sort(arrB);

    // 각 구간 파괴해야 하는 장애물 수 계산
    for (int i = 1; i <= H; i++) {
      result.add(binarySearch(arrA, i) + binarySearch(arrB, H - i));
    }

    // 결과 정렬 후 카운트
    Collections.sort(result);
    int min = result.get(0);
    int count = 0;
    for (int val : result) {
      if (val == min) count++;
    }

    System.out.println(min + " " + count);
  }

  // key 이상인 갯수 반환
  // 큰 것들 중에 제일 작은 것의 인덱스 찾기
  public static int binarySearch(int[] arr, int key) {

    int lo = 0;
    int hi = arr.length - 1;
    int result = hi + 1;
    
    while (lo <= hi) {
      int mid = (lo + hi) / 2;

      if (arr[mid] < key) lo = mid + 1;
      else {
        result = Math.min(result, mid);
        hi = mid - 1;
      }
    }

    return arr.length - result;
  }
}
*/