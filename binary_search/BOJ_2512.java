import java.util.*;

class Main {
  // 지방의 수 N, 총 예산 M
  public static int n, m;

  // 각 지방의 예산 요청
  public static ArrayList<Integer> arrayList = new ArrayList<>();

  // 결과
  public static int result = 0;
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 지방의 수 N 입력
    n = sc.nextInt();

    // 각 지방의 예산요청 입력
    for (int i = 0; i < n; i++) {
     arrayList.add(sc.nextInt()); 
    }

    // 총 예산 M 입력
    m = sc.nextInt();

    
    int sum = 0;
    int max = 0;
    for (int i = 0; i < arrayList.size(); i++) {
      sum = sum + arrayList.get(i);
      max = Math.max(max, arrayList.get(i));
    }

    if (sum <= m) { // 모든 요청 배정 가능하면 요청 금액 그대로 배정
      result = max;
    } else { // 모든 요청 배정 불가능
      int lo = 1;
      int hi = max;
      while (lo <= hi) {
        int mid = (lo + hi) / 2;
        sum = 0;
        for (int i = 0; i < arrayList.size(); i++) {
          if (arrayList.get(i) > mid) {
            sum = sum + mid;
          } else {
            sum = sum + arrayList.get(i);
          }
        }
        if (sum <= m) {
          result = Math.max(result, mid);
          lo = mid + 1;
        } else {
          hi = mid - 1;
        }
      }
    }
    
    // 결과 출력
    System.out.println(result);
  }
}
