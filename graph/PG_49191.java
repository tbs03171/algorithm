package programmers;

public class PG_49191 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int result = solution.solution(5, new int[][]{{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}});
            System.out.println(result);
        }
}

/* 플로이드 와샬 */
class Solution {

    public int solution(int n, int[][] results) {

        int answer = 0;
        int[][] rank = new int[n + 1][n + 1];

        // 졌는지 이겼는지 확인
        for (int[] r : results) {
            rank[r[0]][r[1]] = 1; // 이김
            rank[r[1]][r[0]] = -1; // 짐
        }

        // 플로이드 와샬
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    // i가 k를 이기고, k가 j를 이겼다면 -> i가 j를 이긴 것
                    if (rank[i][k] == 1 && rank[k][j] == 1) {
                        rank[i][j] = 1;
                        rank[j][i] = -1;
                    }
                }
            }
        }

        // 모든 선수들에 대해 순위를 정할 수 있는지 확인
        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int j = 1; j <= n; j++) {
                if (rank[i][j] != 0) cnt++; // j 선수와의 순서를 정할 수 있다면 +1
            }

            // 자신 빼고 모두 (n - 1) 와의 순서를 정할 수 있다면
            if (cnt == n - 1) answer++;
        }

        return answer;
    }
}


/* DFS (나의 풀이)
class Solution {

    public ArrayList<ArrayList<Integer>> winList = new ArrayList<>(); // 특정 선수가 이긴 선수 리스트
    public ArrayList<ArrayList<Integer>> loseList = new ArrayList<>(); // 특정 선수가 진 선수 리스트
    public boolean[] visited;

    public int solution(int n, int[][] results) {

//         1 ~ n번 권투선수
//         실력 A 선수 > B 선수 -> A 선수가 항상 이김
//         심판 -> 경기 결과로 선수 순위 매기려 함 but 결과 분실..

//         n : 선수 수
//         results : 경기 결과
//         return 정확하게 순위 매길 수 있는 선수의 수

//         **
//         선수의 수 1~100
//         경기 결과 1~4500
//         results 각 행 [A, B] -> A가 B 이겼다는 의미

        // 리스트 초기화
        for (int i = 0; i <= n; i++) {
            winList.add(new ArrayList<Integer>());
            loseList.add(new ArrayList<Integer>());
        }

        // 이긴 결과, 진 결과 리스트 생성
        for (int[] result : results) {
            int A = result[0]; // 이긴 사람
            int B = result[1]; // 진 사람
            winList.get(A).add(B); // A가 B를 이김
            loseList.get(B).add(A); // B가 A에게 짐
        }

        // 모든 선수들에 대해 두 리스트 탐색
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];
            visited[i] = true;
            dfs(winList, i);
            dfs(loseList, i);

            // 모든 선수가 체크되었다면 순위를 매길 수 있음
            if (allChecked()) answer++;
        }

        return answer;
    }

    // DFS
    public void dfs(ArrayList<ArrayList<Integer>> list, int start) {
        for (int i = 0; i < list.get(start).size(); i++) {
            if (visited[list.get(start).get(i)] == false) {
                visited[list.get(start).get(i)] = true;
                dfs(list, list.get(start).get(i));
            }
        }
    }

    // 모든 선수가 체크되었는지 여부
    public boolean allChecked() {
        for (int i = 1; i < visited.length; i++) {
            if (visited[i] == false) return false;
        }
        return true;
    }
} */