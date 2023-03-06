import java.util.*;

public class ch10_4 {
  
  // 노드(과목)의 개수(V)
  // 노드의 개수는 최대 500
  public static int v
  // 모든 노드에 대한 진입차수는 0으로 초기화
  public static int[] indegree = new int[501];
  // 각 노드에 연결된 간선 정보를 담기 위한 연결 리스트 초기화
  public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
  // 각 노드의 시간 정보 저장
  public static int[] time = new int[501];
  // 결과 저장
  public static int[] result = new int[501];

  // 위상 정렬 함수
  public static void topologySort() {
    Queue<Integer> q = new LinkedList<>();

    // 처음 시작할 때는 진입차수가 0인 노드를 큐에 삽입하고, 
    // 해당 노드의 시간은 자기 자신으로 업데이트 (바로 시작할 수 있기 때문)
    for (int i = 1; i <= v; i++) {
      if (indegree[i] == 0) {
        q.offer(i);
        result[i] = time[i];
      }
    }

    // 큐가 빌 때까지 반복
    while(!q.isEmpty()) {
      // 큐에서 원소 꺼내기
      int now = q.poll();
      // 해당 원소와 연결된 노드들의 진입차수에서 1 빼고, 시간 업데이트
      for (int i = 0; i < graph.get(now).size(); i++) {
        indegree[graph.get(now).get(i)] -= 1;
        result[graph.get(now).get(i)] = Math.max(result[graph.get(now).get(i)], time[graph.get(now).get(i)] + result[now]);
        // 새롭게 진입차수가 0이 되는 노드를 큐에 삽입
        if (indegree[graph.get(now).get(i)] == 0) {
          q.offer(graph.get(now).get(i));
        }
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 강의 수 입력
    v = sc.nextInt();

    // 그래프 초기화
    for (int i = 0; i <= v; i++) {
      graph.add(new ArrayList<Integer>());
    }

    // 방향 그래프의 모든 간선 정보 입력
    for (int i = 1; i <= v; i++) {
      time[i] = sc.nextInt();
      // 선수강 강의 입력
      while (true) {
        int advanced = sc.nextInt();
        if (advanced == -1) break;
        graph.get(advanced).add(i); // advanced -> i
        indegree[i] += 1;
      }
    }

    // 위상 정렬
    topologySort();

    // 결과 출력
    for (int i = 1; i <= v; i++) {
      System.out.println(result[i]);
    }
  }
}