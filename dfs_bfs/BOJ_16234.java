import java.util.*;

class Position {
  private int x;
  private int y;

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }
}

public class Main {

  // 땅의 크기(N), L, R 값을 입력받기
  public static int n, l, r;
  public static int totalCount = 0;

  // 전체 나라의 정보 (N X N)을 입력받기
  public static int[][] graph = new int[50][50];
  public static int[][] unions = new int[50][50];

  public static int[] dx = {-1, 0, 1, 0};
  public static int[] dy = {0, -1, 0, 1};

  // 특정 위치에서 출발하여 모든 연합을 체크한 뒤에 데이터 갱신
  public static void process(int x, int y, int index) {
    // (x, y)의 위치와 연결된 나라(연합) 정보를 담는 리스트
    ArrayList<Position> united = new ArrayList<>();
    united.add(new Position(x, y));
    // 너비 우선 탐색 (BFS)을 위한 큐 라이브러리 사용
    Queue<Position> q = new LinkedList<>();
    q.offer(new Position(x, y));
    unions[x][y] = index; // 현재 연합의 번호 할당
    int summary = graph[x][y]; // 현재 연합의 전체 인구 수
    int count = 1; // 현재 연합의 국가 수
    // 큐가 빌 때까지 반복(BFS)
    while (!q.isEmpty()) {
      Position pos = q.poll();
      x = pos.getX();
      y = pos.getY();
      // 현재 위치에서 4가지 방향을 확인하며
      for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        // 바로 옆에 있는 나라를 확인하여
        if (0 <= nx && nx < n && 0 <= ny && ny < n && unions[nx][ny] == -1) {
          // 옆에 있는 나라와 인구 차이가 L명 이상, R명 이하라면
          int gap = Math.abs(graph[nx][ny] - graph[x][y]);
          if (l <= gap && gap <= r) {
            q.offer(new Position(nx, ny));
            // 연합에 추가하기
            unions[nx][ny] = index;
            summary += graph[nx][ny];
            count += 1;
            united.add(new Position(nx, ny));
          }
        }
      }
    }
    // 연합 국가끼리 인구를 분배
    for (int i = 0; i < united.size(); i++) {
      x = united.get(i).getX();
      y = united.get(i).getY();
      graph[x][y] = summary / count;
    }
  }

  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 땅 크기 N, 인구 차이 L R 입력
    n = sc.nextInt();
    l = sc.nextInt();
    r = sc.nextInt();

    // 각 나라의 인구 수 입력
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
       graph[i][j] = sc.nextInt();
      }
    }
    
    // 더 이상 인구 이동을 할 수 없을 때까지 반복
    while (true) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          unions[i][j] = -1;
        }
      }
      int index = 0;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (unions[i][j] == -1) { // 해당 나라가 아직 처리되지 않았다면
            process(i, j, index);
            index += 1;
          }
        }
      }
      // 모든 인구 이동이 끝난 경우
      if (index == n * n) break;
      totalCount += 1;
    }

    // 인구 이동 횟수 출력
    System.out.println(totalCount);
  }
}

/*
내가 푼 코드

import java.util.*;

class Country {
  private int x, y, population; // x y 좌표, 인구 수

  public Country(int x, int y, int population) {
    this.x = x;
    this.y = y;
    this.population = population;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public int getPopulation() {
    return this.population;
  }
}

public class Main {

  public static int n, l, r; // 땅 크기 N, 인구 차이 L R
  public static int[][] A = new int[50][50]; // 각 나라 인구 정보
  public static boolean[][] visited;
  public static ArrayList<Country> temp;
  public static int count = 0; // 인구 이동 횟수

  // 모든 연합국 생성해서 반환
  public static ArrayList<ArrayList<Country>> makeCountries() {
    ArrayList<ArrayList<Country>> countryList = new ArrayList<ArrayList<Country>>();
    visited = new boolean[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (visited[i][j] == false) { // 아직 방문하지 않은 나라
          temp = new ArrayList<Country>();
          dfs(i, j);
          if (temp.size() > 1) countryList.add(temp); // 리스트에 넣기
        }
      }
    }
    return countryList;
  }
  
  // 하나의 연합국 생성
  public static void dfs(int x, int y) {
    visited[x][y] = true;
    temp.add(new Country(x, y, A[x][y]));
    
    // 오른쪽 연결 가능?
    if (y < n - 1 && visited[x][y + 1] == false) {
      int gap = Math.abs(A[x][y + 1] - A[x][y]);
      if (l <= gap && gap <= r) {
        dfs(x, y + 1);
      }
    }

    // 아래쪽 연결 가능?
    if (x < n - 1 && visited[x + 1][y] == false) {
      int gap = Math.abs(A[x + 1][y] - A[x][y]);
      if (l <= gap && gap <= r) {
        dfs(x + 1, y);
      }
    }

    // 왼쪽 연결 가능?
    if (y > 0 && visited[x][y - 1] == false) {
      int gap = Math.abs(A[x][y - 1] - A[x][y]);
      if (l <= gap && gap <= r) {
        dfs(x, y - 1);
      }
    }

    // 위쪽 연결 가능?
    if (x > 0 && visited[x - 1][y] == false) {
      int gap = Math.abs(A[x - 1][y] - A[x][y]);
      if (l <= gap && gap <= r) {
        dfs(x - 1, y);
      }
    }

    return;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 땅 크기 N, 인구 차이 L R 입력
    n = sc.nextInt();
    l = sc.nextInt();
    r = sc.nextInt();

    // 각 나라의 인구 수 입력
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
       A[i][j] = sc.nextInt();
      }
    }
    
    // 인구 이동
    while(true) {
      ArrayList<ArrayList<Country>> countryList = makeCountries(); // 연합국 구성
      if (countryList.size() == 0) break; // 연합국 없음, 인구이동 중단

      // 모든 연합국들에 대해 인구이동 시작
      count++;
      for (int i = 0; i < countryList.size(); i++) {
        // 인구 수 평균 계산
        int sum = 0;
        for (int j = 0; j < countryList.get(i).size(); j++) {
          sum += countryList.get(i).get(j).getPopulation();
        }
        int avg = sum / countryList.get(i).size();

        // 인구 수 다시 셋팅
        for (int j = 0; j < countryList.get(i).size(); j++) {
          A[countryList.get(i).get(j).getX()][countryList.get(i).get(j).getY()] = avg;
        }
      }
    }

    // 결과 출력
    System.out.println(count);
    
  }
}

*/