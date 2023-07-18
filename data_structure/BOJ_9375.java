import java.util.*;

class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();

    while (T-- > 0) {
      int n = sc.nextInt();
      sc.nextLine();

      // 의상 모두 입력
      HashMap<String, Integer> map = new HashMap<String, Integer>();
      while (n-- > 0) {
        String[] arr = sc.nextLine().split(" "); // arr[0] : 의상 이름, arr[1] : 의상 종류
        if (map.containsKey(arr[1])) { // 존재하는 키
          map.put(arr[1], map.get(arr[1]) + 1);
        }
        else { // 존재하지 않는 키
          map.put(arr[1], 1);
        }
      }

      // 결과 계산해서 출력
      if (n == 0) {
        System.out.println(0);
        continue;
      }
      int result = 1;
      for (String key : map.keySet()) {
        result = result * (map.get(key) + 1);
      }
      System.out.println(result - 1);
    }
  }
}
