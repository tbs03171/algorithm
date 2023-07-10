import java.util.*;

class Node {
  private int index, count;

  public Node (int index, int count) {
    this.index = index;
    this.count = count;
  }

  public int getIndex() {
    return this.index;
  }

  public int getCount() {
    return this.count;
  }
}

class Main {
  public static int result;
  public static int n, k;
  public static Queue<Node> q = new LinkedList<Node>();
  public static boolean[] visited = new boolean[100001];
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    k = sc.nextInt();

      // bfs 탐색
      q.offer(new Node(n, 0));
      visited[n] = true;

      while (!q.isEmpty()) {
        Node node = q.poll();
        int now = node.getIndex();
        // 동생 위치에 도착
        if (now == k) {
          result = node.getCount();
          break;
        }
        // 이동할 수 있는 위치 탐색
        if (0 <= now - 1 && now - 1 <= 100000 && visited[now - 1] == false) {
          visited[now - 1] = true;
          q.offer(new Node(now - 1, node.getCount() + 1));
        }
        if (0 <= now + 1 && now + 1 <= 100000 && visited[now + 1] == false) {
          visited[now + 1] = true;
          q.offer(new Node(now + 1, node.getCount() + 1));
        }
        if (0 <= now * 2 && now * 2 <= 100000 && visited[now * 2] == false) {
          visited[now * 2] = true;
          q.offer(new Node(now * 2, node.getCount() + 1));
        }
      }

      // 결과 출력
      System.out.println(result);
  }
}
