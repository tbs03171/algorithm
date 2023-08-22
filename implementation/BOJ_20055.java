import java.io.*;
import java.util.*;

public class Main {
  public static int N, K;
  public static int[] A;
  public static boolean[] robot;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    A = new int[2 * N];
    robot = new boolean[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < A.length; i++) {
      A[i] = Integer.parseInt(st.nextToken());
    }

    bw.write(simulation(0) + "\n");

    br.close();
    bw.flush();
    bw.close();
  }

  public static int simulation(int cnt) {
    while(isOK()) {
      int temp = A[A.length - 1]; // 1. 벨트 한 칸 회전
      for (int i = A.length - 1; i > 0; i--) {
        A[i] = A[i - 1];
      }
      A[0] = temp;

      for (int i = robot.length - 1; i > 0; i--) { // 로봇도 벨트와 같이 회전
        robot[i] = robot[i - 1];
      }
      robot[0] = false;
      robot[N - 1] = false;
      
      for (int i = N - 1; i > 0; i--) { // 2. 로봇 이동 가능하면 이동
        if (robot[i - 1] && !robot[i] && A[i] >= 1) {
          robot[i] = true;
          robot[i - 1] = false;
          A[i]--;
        }
      }

      if (A[0] > 0) { // 3. 올라가는 위치에 로봇 올리기
        robot[0] = true;
        A[0]--;
      }

      cnt++;
    }

    return cnt;
  }

  public static boolean isOK() {
    int cnt = 0;

    for (int i = 0; i < A.length; i++) {
      if (A[i] == 0) {
        cnt++;
      }
      if (cnt >= K) {
        return false;
      }
    }
    return true;
  }
}

/* 나의 풀이
import java.util.*;

class Node {
  int power;
  boolean robot;

  public Node (int power) {
    this.power = power;
    this.robot = false;
  }

  public void pushRobot() {
    if (this.robot == false && this.power >= 1) {
      this.robot = true;
      this.power = this.power - 1;
    }
  }

  public void pollRobot() {
    this.robot = false;
  }
}

class Main {

  public static int upIdx;
  public static int downIdx;
  public static int N, K;
  public static int cnt = 0;
  public static ArrayList<Node> list = new ArrayList<>();

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    K = sc.nextInt();

    upIdx = 0;
    downIdx = N - 1;

    // Ai 입력
    for (int i = 0; i < 2 * N; i++) {
      int Ai = sc.nextInt();
      list.add(new Node(Ai));
    }

    // 시작
    int result = 1;
    while (true) {
      play();
      if (cnt >= K) break;
      result++;
    }

    System.out.println(result);
  }

  public static void play() {
    // 회전
    rotate();

    // 이동
    move();

    // 올리는 위치에 있는 칸의 내구도가 0이 아니면, 로봇 올리기
    if (list.get(upIdx).power >= 1) {
      list.get(upIdx).pushRobot();
      if (list.get(upIdx).power == 0) cnt++;
    }

  }

  // 로봇들 이동
  public static void move() {
    
    // 내리는 위치부터 올리는 위치 전까지
    for (int i = 0; i < N - 1; i++) {
      int idx = (downIdx - i + (2 * N)) % (2 * N);
      if (list.get(idx).robot == true || list.get(idx).power < 1) continue;
      else {
        int before = (idx - 1 + (2 * N)) % (2 * N);
        if (list.get(before).robot == true) {
          
        list.get(before).pollRobot();
        list.get(idx).pushRobot();
        if (list.get(idx).power == 0) cnt++;
        }
      }
    }
    
  }

  // 벨트 회전
  public static void rotate() {
    // 내리는 위치에 있는 로봇 내리기
    list.get(downIdx).pollRobot();

    // 올리는 위치, 내리는 위치 변경
    upIdx = (upIdx - 1 + (2 * N)) % (2 * N);
    downIdx = (downIdx - 1 + (2 * N)) % (2 * N);

    // 내리는 위치에 있는 로봇 내리기
    list.get(downIdx).pollRobot();
  }
}
*/