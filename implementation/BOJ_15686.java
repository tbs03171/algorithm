import java.util.*;

class Position {
    private int x, y;

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

class Main {

    public static int N, M;
    public static ArrayList<Position> chickenList = new ArrayList<>();
    public static ArrayList<Position> homeList = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> distList = new ArrayList<>();
    public static int[] comb;
    public static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int x = sc.nextInt();
                if (x == 2) { // 치킨집
                    chickenList.add(new Position(i, j));
                }
                else if (x == 1) { // 집
                    homeList.add(new Position(i, j));
                }
            }
        }

        // 치킨 거리 미리 구해놓기
        for (int i = 0; i < homeList.size(); i++) {
            Position p = homeList.get(i);
            distList.add(new ArrayList<Integer>());
            for (int j = 0; j < chickenList.size(); j++) {
                int dist = Math.abs(p.getX() - chickenList.get(j).getX()) + Math.abs(p.getY() - chickenList.get(j).getY());
                distList.get(i).add(dist);
            }
        }

        // 치킨집 조합해서 거리 구하기
        comb = new int[M];
        dfs(0, 0);

        // 결과 출력
        System.out.println(min);
    }

    public static void dfs(int at, int depth) {
        if (at > chickenList.size()) return;

        if (depth == M) { // 도시의 치킨 거리 구하기
            int result = 0;
            for (ArrayList<Integer> list : distList) {
                int dist = Integer.MAX_VALUE;
                for (int i : comb) {
                    dist = Math.min(dist, list.get(i));
                }
                result = result + dist;
            }

            min = Math.min(result, min);
            return;
        }

        comb[depth] = at;
        dfs(at + 1, depth + 1);
        dfs(at + 1, depth);
    }
}
