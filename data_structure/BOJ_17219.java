import java.util.*;

class Main {

  public static HashMap<String, String> map = new HashMap<>();
  public static int n, m; // 사이트 수 N, 찾으려는 사이트 수 M
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    m = sc.nextInt();

    // N개의 사이트 주소와 비밀번호 입력
    sc.nextLine();
    for (int i = 0; i < n; i++) {
      String s = sc.nextLine();
      String[] arr = s.split(" ");
      map.put(arr[0], arr[1]);
    }

    //
    for (int i = 0; i < m; i++) {
      String s = sc.nextLine();
      System.out.println(map.get(s));
    }
  }
}
