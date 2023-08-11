import java.util.*;

public class Main {

  static int n, delete;
  static int[] parent;
  static int count;
  static boolean[] visited;

  public static void main(String[] args) {

    Scanner scan = new Scanner(System.in);

    n = scan.nextInt();
    parent = new int[n];
    int root = 0;
    for (int i = 0; i < n; i++) {
      parent[i] = scan.nextInt();
      if (parent[i] == -1) root = i;
    }
    delete = scan.nextInt();

    deleteNode(delete);

    count = 0;
    visited = new boolean[n];
    countLeaf(root);

    System.out.println(count);
  }

  public static void deleteNode(int d) {
    parent[d] = -2;
    for (int i = 0; i < n; i++) {
      if (parent[i] == d) deleteNode(i);
    }
  }

  public static void countLeaf(int s) {
    boolean isLeaf = true;
    visited[s] = true;
    if (parent[s] != -2) { // 삭제된 노드가 아니라면
      for (int i = 0; i < n; i++) {
        if (parent[i] == s && visited[i] == false) { // 자식이 있다면
          countLeaf(i);
          isLeaf = false;
        }
      }
      if (isLeaf) count++; // 자식이 없는 경우
    }
  }
}

/* 나의 풀이
import java.util.*;

class Main {

  public static int N;
  public static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
  public static int[] parent;
  public static int deletedCnt = 1;
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    parent = new int[N];
    for (int i = 0; i < N; i++) {
      tree.add(new ArrayList<Integer>());
    }

    // 트리 구성
    for (int i = 0; i < N; i++) {
      int x = sc.nextInt();
      parent[i] = x;
      if (x == -1) continue;
      tree.get(x).add(i);
    }

    // 노드 삭제 (부모에게서 삭제 + 자식들 삭제)
    int deleteNode = sc.nextInt();
    if (parent[deleteNode] != -1) {
      tree.get(parent[deleteNode]).remove(Integer.valueOf(deleteNode));
    } 
    delete(deleteNode); // 자식들 삭제

    // 리프 노드 개수 출력
    int result = 0;
    for (int i = 0; i < N; i++) {
      if (tree.get(i).size() == 0) result++;
    }
    System.out.println(result - deletedCnt);
  }

  public static void delete(int x) {

    for (int i = 0; i < tree.get(x).size(); i++) {
      deletedCnt++;
      int child = tree.get(x).get(i);
      if (tree.get(child).size() == 0) continue;
      else delete(child);
    }

    tree.get(x).clear();
  }
}
*/