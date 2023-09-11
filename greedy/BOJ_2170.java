import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) throws NumberFormatException, IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    int[][] pos = new int[n][2];
    int result = 0;

    for (int i = 0; i < n; i++) {
      String line = br.readLine();
      int p1 = Integer.parseInt(line.split(" ")[0]);
      int p2 = Integer.parseInt(line.split(" ")[1]);
      pos[i][0] = p1;
      pos[i][1] = p2;
    }

    Arrays.sort(pos, new Comparator<int[]>() { // x좌표 오름차순, x좌표 같으면 y좌표 오름차순
      @Override
      public int compare(int[] o1, int[] o2) {
        if (o1[0] == o2[0]) return o1[1] - o2[1];
        else return o1[0] - o2[0];
      }
    });

    int min = pos[0][0]; // 이전 선의 x좌표
    int max = pos[0][1]; // 이전 선의 y좌표
    int len = max - min;
    for (int i = 1; i < n; i++) {
      if (min <= pos[i][0] && pos[i][1] <= max) { // 현재 선이 이전 선에 포함된다면
        continue;
      } else if (pos[i][0] < max) { // 현재 선의 시작점이 이전 선에 포함된다면
        len += pos[i][1] - max;
      } else { // 현재 선과 이전 선이 겹치지 않는다면
        len += pos[i][1] - pos[i][0];
      }
      min = pos[i][0];
      max = pos[i][1];
    }

    System.out.println(len);
  }
}

/* 나의 풀이
import java.util.*;
import java.io.*;

class Node implements Comparable<Node> {

  int x, y;

  Node(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int compareTo(Node other) {
    if (this.x < other.x) return -1;
    else if (this.x > other.x) return 1;
    else return Integer.compare(this.y, other.y);
  }
}

class Main {

  public static int N;
  public static PriorityQueue<Node> pq = new PriorityQueue<>();

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    N = Integer.parseInt(st.nextToken());

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      pq.offer(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
    }

    int sum = 0;
    Node now = pq.poll();
    while (true) {
      if (pq.isEmpty()) {
        sum = sum + (now.y - now.x);
        break;
      }
      Node node = pq.poll();
      if (now.x == node.x) now.y = node.y; // now.x == node.x
      else { // now.x < node.x
        if (now.y >= node.y) { // 중복
          
        }
        if (now.y < node.y) {
          if (now.y < node.x) { // 새로운 구간 생성
            sum = sum + (now.y - now.x);
            now = node;
            continue;
          }
          else { // 연장
            now.y = node.y;
          }
        }
      }
    }

    // 결과 출력
    System.out.println(sum);
  }
}
*/