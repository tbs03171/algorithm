import java.util.*;

class Main {
  // 나무의 수 N, 가져가려는 나무 길이 M
  public static int n, m;

  // 나무들 길이
  public static ArrayList<Integer> arrayList = new ArrayList<>();

  // 나무 길이 최대값
  public static int result = 0;
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 나무의 수 N, 가져가려는 나무 길이 M 입력
    n = sc.nextInt();
    m = sc.nextInt();

    for (int i = 0; i < n; i++) {
      arrayList.add(sc.nextInt());
    }

    // 나무들 정렬
    Collections.sort(arrayList);

    // 이진 탐색
    int lo = 0;
    int hi = arrayList.get(n - 1);
    
    while (lo <= hi) {
      int mid = (lo + hi) / 2;
      long sum = 0;
      for (int i = 0; i < arrayList.size(); i++) {
        if (arrayList.get(i) > mid) sum = sum + (arrayList.get(i) - mid);
      }

      if (sum >= m) { // 조건 만족
        result = Math.max(result, mid);
        lo = mid + 1;
      } else { // 조건 불만족
        hi = mid - 1;
      }
    }

    System.out.println(result);
  }
}
