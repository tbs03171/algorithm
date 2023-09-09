import java.util.*;
import java.io.*;

public class Main {

  // N, 만족도 총 합
  static int N, sum;
  // 학생 배열, 상 우 하 좌
  static int[] students, dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
  // 학생들이 앉은 map
  static int[][] map;
  // 학생별 좋아하는 학생들
  static Map<Integer, Set<Integer>> preferences;

  public static void main(String[] args) throws Exception {

    // 입력
    init();

    // 풀이
    solution();

    // 결과 출력
    printResult();
  }

  static void init() throws Exception {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = null;
    N = Integer.parseInt(br.readLine());
    sum = 0;
    map = new int[N][N];
    students = new int[N * N];
    preferences = new HashMap<>();

    for (int i = 0; i < N * N; i++) {
      st = new StringTokenizer(br.readLine());
      int student = Integer.parseInt(st.nextToken());
      // 학생 배열에 기록
      students[i] = student;
      // 학생별 좋아하는 학생들 기록
      preferences.put(student, new HashSet<>());
      for (int j = 0; j < 4; j++) {
        preferences.get(student).add(Integer.parseInt(st.nextToken()));
      }
    }
    
  }

  static void solution() {
    // 1. 학생들 자리 배치
    for (int i = 0; i < students.length; i++) {
      Seat seat = findSeat(students[i]);
      map[seat.x][seat.y] = students[i];
    }

    // 2. 점수 합산
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        // 주변 학생 수에 따라 점수 합산
        int count = getStudentSum(i, j, map[i][j]);
        if (count > 0) {
          sum += (int) Math.pow(10, count - 1);
        }
      }
    }
  }

  // 앉을 좌석 찾기
  static Seat findSeat(int student) {

    Seat seat = null;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        // 이미 자리에 누구 앉아있으면 skip
        if (map[i][j] > 0) continue;
        // 현재 자리의 정보 (x y 좌표, 인접한 좋아하는 학생 수, 빈칸 수)
        Seat cur = new Seat(i, j, getStudentSum(i, j, student), getEmptySum(i, j));
        // 비교할 seat이 null이라면 초기화 후 skip
        if (seat == null) {
          seat = cur;
          continue;
        }

        // 이전 좌석과 현재 좌석 비교
        if (seat.compareTo(cur) > 0) seat = cur;
      }
    }

    return seat;
  }

  // 인접한 좋아하는 학생 수
  static int getStudentSum(int x, int y, int student) {

    int count = 0;

    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      // 범위 벗어나면 skip
      if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
      // 인접한 학생이 좋아하는 학생에 포함되면 count 증가
      if (preferences.get(student).contains(map[nx][ny])) count++;
    }

    return count;
    
  }

  // 빈칸 수
  static int getEmptySum(int x, int y) {
    int count = 0;

    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      // 범위 벗어나면 skip
      if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
      // 빈칸이면 count 증가
      if (map[nx][ny] == 0) count++;
    }

    return count;
  }

  // 결과 출력
  static void printResult() {
    System.out.println(sum);
  }

  // 좌석 정보 저장할 Seat 클래스
  static class Seat implements Comparable<Seat> {

    // x y 좌표, 주변 좋아하는 학생 수, 주변 빈칸 수
    int x, y, studentSum, emptySum;

    public Seat(int x, int y, int studentSum, int emptySum) {
      this.x = x;
      this.y = y;
      this.studentSum = studentSum;
      this.emptySum = emptySum;
    }

    // 다른 좌석과 비교
    @Override
    public int compareTo(Seat other) {
      // 인접 좋아하는 학생 수로 비교
      if (studentSum != other.studentSum) return -(studentSum - other.studentSum);

      // 인접 빈칸 수로 비교
      if (emptySum != other.emptySum) return -(emptySum - other.emptySum);

      // 행으로 비교
      if (x != other.x) return x - other.x;

      // 열로 비교
      return y - other.y;
    }
  }
}

/* 나의 풀이
import java.util.*;

class Node implements Comparable<Node> {

  int r, c;
  int love;
  int blank;

  Node (int r, int c, int love, int blank) {
    this.r = r;
    this.c = c;
    this.love = love;
    this.blank = blank;
  }

  @Override
  public int compareTo(Node other) {
    if (this.love == other.love) {
      if (this.blank == other.blank) {
        if (this.r == other.r) return Integer.compare(this.c, other.c);
        else return Integer.compare(this.r, other.r);
      }
      else return Integer.compare(other.blank, this.blank);
    }
    else return Integer.compare(other.love, this.love);
  }
  
}

class Main {

  public static int N;
  public static boolean[][] graph;
  public static int[][] classRoom;
  public static ArrayList<Integer> order = new ArrayList<>();
  public static int[] dx = {-1, +1, 0, 0};
  public static int[] dy = {0, 0, -1, +1};
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    graph = new boolean[N * N + 1][N * N + 1];
    classRoom = new int[N][N];

    // 친구 정보 입력
    for (int i = 0; i < N * N; i++) {
      int from = sc.nextInt();
      order.add(from);
      for (int j = 0; j < 4; j++) {
        int to = sc.nextInt();
        graph[from][to] = true;
      }
    }

    // 자리 배치
    for (int student : order) {
      PriorityQueue<Node> pq = new PriorityQueue<>();
      // 모든 빈 자리에 대해 계산
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (classRoom[i][j] != 0) continue;
          pq.offer(cal(student, i, j));
        }
      }
      Node result = pq.poll();
      classRoom[result.r][result.c] = student;
    }

    // 배치 결과 출력
    // for (int i = 0; i < N; i++) {
    //   for (int j = 0; j < N; j++) {
    //     System.out.print(classRoom[i][j] + " ");
    //   }
    //   System.out.println();
    // }

    // 만족도 계산
    int sumSatis = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        sumSatis = sumSatis + getSatisfaction(i, j);
      }
    }

    // 결과 출력
    System.out.println(sumSatis);
  }

  public static int getSatisfaction(int r, int c) {
    int student = classRoom[r][c];
    int cnt = 0;

    for (int i = 0; i < 4; i++) {
      int nx = r + dx[i];
      int ny = c + dy[i];
      if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
      int x = classRoom[nx][ny];
      if (graph[student][x] == true) cnt++;
    }

    if (cnt == 0) return 0;
    else return (int)Math.pow(10, cnt - 1);
  }

  public static Node cal(int student, int r, int c) {

    int blank = 0;
    int love = 0;

    for (int i = 0; i < 4; i++) {
      int nx = r + dx[i];
      int ny = c + dy[i];
      if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
      int x = classRoom[nx][ny];
      if (x == 0) blank++; // 빈 칸인 경우
      else {
        if (graph[student][x] == true) love++; // 좋아하는 학생일 경우
      }
      
    }

    return new Node(r, c, love, blank);
  }
}
*/