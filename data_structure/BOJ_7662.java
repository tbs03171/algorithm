import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();

    while (T-- > 0) {
      TreeMap<Integer, Integer> map = new TreeMap<>();
      int k = sc.nextInt();
      sc.nextLine();

      // k번 연산
      while (k-- > 0) {
        String s = sc.nextLine();
        String[] arr = s.split(" ");
        char op = arr[0].charAt(0); // 연산 종류
        int n = Integer.parseInt(arr[1]); // 값

        if (op == 'I') { // 삽입
          map.put(n, map.getOrDefault(n, 0) + 1);
        }
        else { // 삭제
          if (!map.isEmpty()) {
            int key = n == 1 ? map.lastKey() : map.firstKey();
            if (map.get(key) > 1) {
              map.put(key, map.get(key) - 1);
            }
            else {
              map.remove(key);
            }
          }
        }
      }

      System.out.println(map.isEmpty() ? "EMPTY" : map.lastKey() + " " + map.firstKey());
    }
  }
}
