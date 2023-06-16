import java.util.*;

public class Main {

  public static int testCase, n, m;
  // 모든 노드에 대한 진입차수는 0으로 초기화
  public static int[] indegree = new int[501];
  // 각 노드에 연결된 간선 정보를 담기 위한 배열 초기화
  public static boolean[][] graph = new boolean[501][501];

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    testCase = sc.nextInt();

    for (int tc = 0; tc < testCase; tc++) {
      Arrays.fill(indegree, 0);
      for (int i = 0; i < 501; i++) {
        Arrays.fill(graph[i], false);
      }

      n = sc.nextInt();
      // 작년 순위 정보 입력
      ArrayList<Integer> arrayList = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        int x = sc.nextInt();
        arrayList.add(x);
      }
      // 방향 그래프의 간선 정보 초기화
      for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
          graph[arrayList.get(i)][arrayList.get(j)] = true;
          indegree[arrayList.get(j)] += 1;
        }
      }

      // 올해 변경된 순위 정보 입력
      m = sc.nextInt();
      for (int i = 0; i < m; i++) {
        int a = sc.nextInt();
        int b = sc.nextInt();
        // 간선의 방향 뒤집기
        if (graph[a][b]) {
          graph[a][b] = false;
          graph[b][a] = true;
          indegree[a] += 1;
          indegree[b] -= 1;
        }
        else {
          graph[a][b] = true;
          graph[b][a] = false;
          indegree[a] -= 1;
          indegree[b] += 1;
        }
      }

      // 위상 정렬 시작
      ArrayList<Integer> result = new ArrayList<>(); // 알고리즘 수행 결과를 담을 리스트
      Queue<Integer> q = new LinkedList<>(); // 큐 라이브러리 사용

      // 처음 시작할 때는 진입차수가 0인 노드를 큐에 삽입
      for (int i = 1; i <= arrayList.size(); i++) {
        if (indegree[i] == 0) {
          q.offer(i);
        }
      }

      boolean certain = true; // 위상 정렬 결과가 오직 하나인지의 여부
      boolean cycle = false; // 그래프 내 사이클이 존재하는지 여부

      // 정확히 노드의 개수만큼 반복
      for (int i = 0; i < n; i++) {
        // 큐가 비어 있다면 사이클이 발생했다는 의미
        if (q.size() == 0) {
          cycle = true;
          break;
        }
        // 큐의 원소가 2개 이상이라면 가능한 정렬 결과가 여러 개라는 의미
        if (q.size() >= 2) {
          certain = false;
          break;
        }
        // 큐에서 원소 꺼내기
        int now = q.poll();
        result.add(now);
        // 해당 원소와 연결된 노드들의 진입차수에서 1 빼기
        for (int j = 1; j <= n; j++) {
          if (graph[now][j]) {
            indegree[j] -= 1;
            // 새롭게 진입차수가 0이 되는 노드를 큐에 삽입
            if (indegree[j] == 0) {
              q.offer(j);
            }
          }
        }
      }

      // 사이클이 발생하는 경우(일관성이 없는 경우)
      if (cycle) System.out.println("IMPOSSIBLE");
      // 위상 정렬 결과가 여러 개인 경우
      else if (!certain) System.out.println("?");
      // 위상 정렬을 수행한 결과 출력
      else {
        for (int i = 0; i < result.size(); i++) {
          System.out.print(result.get(i) + " ");
        }
        System.out.println();
      }
    }
  }
}

/* 나의 코드
import java.util.*;

class Main {
  // 테스트 케이스 개수
  public static int t;
  // 팀의 수 n
  public static int n;
  // 모든 팀에 대한 진입차수
  public static int[] indegree;
  // 각 노드에 연결된 간선 정보를 담기 위한 연결 리스트
  public static ArrayList<ArrayList<Integer>> graph;

  // 위상 정렬 함수
  public static void topologySort() {
    ArrayList<Integer> result = new ArrayList<>(); // 알고리즘 수행 결과를 담을 리스트
    Queue<Integer> q = new LinkedList<>(); // 큐 라이브러리 사용

    // 처음 시작할 때는 진입차수가 0인 노드를 큐에 삽입
    for (int i = 1; i <= n; i++) {
      if (indegree[i] == 0) {
        q.offer(i);
      }
    }

    // 큐가 빌 때까지 반복
    while(!q.isEmpty()) {
      // 큐에서 원소 꺼내기
      int now = q.poll();
      result.add(now);
      // 해당 원소와 연결된 노드들의 진입차수에서 1 빼기
      for (int i = 0; i < graph.get(now).size(); i++) {
        indegree[graph.get(now).get(i)] -= 1;
        // 새롭게 진입차수가 0이 되는 노드를 큐에 삽입
        if (indegree[graph.get(now).get(i)] == 0) {
          q.offer(graph.get(now).get(i));
        }
      }
    }

    // 위상 정렬을 수행한 결과 출력
    if (result.size() != n) {
      System.out.print("IMPOSSIBLE");
    } else {
      for (int i = 0; i < result.size(); i++) {
      System.out.print(result.get(i) + " ");
      }
    }
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 테스트 케이스 개수 t 입력
    t = sc.nextInt();

    // t 만큼 반복
    for (int tc = 0; tc < t; tc++) {
      // 모든 팀에 대한 진입차수는 0으로 초기화
      indegree = new int[501];
      
      // 팀의 수 n 입력
      n = sc.nextInt();

      // 그래프 초기화
      graph = new ArrayList<ArrayList<Integer>>();

      // 작년 순위 정보 입력
      ArrayList<Integer> arrayList = new ArrayList<>();
      graph.add(new ArrayList<Integer>());
      for (int i = 0; i < n; i++) {
        arrayList.add(sc.nextInt());
        graph.add(new ArrayList<Integer>());
      }

      // 그래프 구성
      for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
          indegree[arrayList.get(j)] += 1;
          graph.get(arrayList.get(i)).add(arrayList.get(j));
        }
      }

      // 바뀐 순위 입력
      int k = sc.nextInt();
      for (int i = 0; i < k; i++) {
        int a = sc.nextInt();
        int b = sc.nextInt();
        if (graph.get(a).contains(b)) {
          for (int j = 0; j < graph.get(a).size(); j++) {
            if (graph.get(a).get(j) == b) graph.get(a).remove(j);
          }
          graph.get(b).add(a);
          indegree[b] -= 1;
          indegree[a] += 1;
        } else {
          for (int j = 0; j < graph.get(b).size(); j++) {
            if (graph.get(b).get(j) == a) graph.get(b).remove(j);
          }
          graph.get(a).add(b);
          indegree[a] -= 1;
          indegree[b] += 1;
        }
      }

      topologySort();
    }
  }
}
*/