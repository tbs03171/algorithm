import java.util.*;

public class Main {

  static ArrayList<Integer> list;
  static boolean[] visited;
  static int[] num;

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    // n개의 정수를 입력
    int n = sc.nextInt();
    num = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      num[i] = sc.nextInt();
    }

    // 순서대로 사이클 발생하는지 dfs로 확인
    list = new ArrayList<>();
    visited = new boolean[n + 1];
    for (int i = 1; i <= n; i++) {
      visited[i] = true;
      dfs(i, i);
      visited[i] = false;
    }

    Collections.sort(list); // 정렬
    System.out.println(list.size());
    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i));
    }
  }

  public static void dfs(int start, int target) {
    if (visited[num[start]] == false) {
      visited[num[start]] = true;
      dfs(num[start], target);
      visited[num[start]] = false;
    }
    if (num[start] == target) list.add(target); // 사이클 발생시 해당 숫자를 list에 넣기
  }
}

/* 나의 풀이
import java.util.*;

class Main {

  public static int N;
  public static int[] arr;
  public static boolean[] visited;
  public static Set<Integer> resultSet = new HashSet<>();
  public static ArrayList<Integer> resultList = new ArrayList<>();
  public static Set<Integer> setA;
  public static Set<Integer> setB;
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    arr = new int[N + 1];

    // 입력
    for (int i = 1; i <= N; i++) {
      arr[i] = sc.nextInt();
    }

    // dfs 탐색
    for (int i = 1; i <= N; i++) {
      visited = new boolean[N + 1];
      setA = new HashSet<>();
      setB = new HashSet<>();
      dfs(i);
      if (setA.equals(setB)) {
        for (int x : setA) {
          resultSet.add(x);
        }
      }
    }

    // set을 list로
    for (int x : resultSet) {
      resultList.add(x);
    }

    // 결과 정렬
    Collections.sort(resultList);

    // 결과 출력
    System.out.println(resultList.size());
    for (int x : resultList) {
      System.out.println(x);
    }
  }

  public static void dfs(int x) {
    visited[x] = true;
    setA.add(x);
    setB.add(arr[x]);

    if (!visited[arr[x]]) dfs(arr[x]);
  }
}
*/