package programmers;

public class PG_1832 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int result = solution.solution(3, 6, new int[][]{{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}});
            System.out.println("result = " + result);
        }
}

class Solution {

    int[][][] dp;
    int MOD = 20170805;

    public int solution(int m, int n, int[][] cityMap) {

        // 내려오는 거 0, 왼쪽에서 오는 거 1
        dp = new int[m][n][2];

        dp[0][0][0] = 1;
        dp[0][0][1] = 1;

        // 경계(0, *) 부분 처리
        for (int i = 1; i < m; i++) {
            if (cityMap[i][0] != 1) dp[i][0][0] = dp[i - 1][0][0];
        }
        for (int i = 1; i < n; i++) {
            if (cityMap[0][i] != 1) dp[0][i][1] = dp[0][i - 1][1];
        }

        // DP
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (cityMap[i - 1][j] != 1) {
                    if (cityMap[i - 1][j] == 2)
                        dp[i][j][0] = dp[i - 1][j][0];
                    else
                        dp[i][j][0] = (dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
                }
                if (cityMap[i][j - 1] != 1) {
                    if (cityMap[i][j - 1] == 2)
                        dp[i][j][1] = dp[i][j - 1][1];
                    else
                        dp[i][j][1] = (dp[i][j - 1][0] + dp[i][j - 1][1]) % MOD;
                }
            }
        }

        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }
}

/* 나의 풀이
class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {

        // DP 배열 초기화
        int[][][] dp = new int[m + 1][n + 1][2];
        dp[0][0][0] = 1;
        // dp[0][0][1] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cityMap[i][j] == 0) { // 자유 이동
                    dp[i][j + 1][0] = (dp[i][j + 1][0] + (dp[i][j][0] + dp[i][j][1])) % MOD;
                    dp[i + 1][j][1] = (dp[i + 1][j][1] + (dp[i][j][0] + dp[i][j][1])) % MOD;
                }
                else if (cityMap[i][j] == 1) { // 통행 금지
                    continue;
                }
                else { // 이동 제한
                    dp[i][j + 1][0] = (dp[i][j + 1][0] + dp[i][j][0]) % MOD;
                    dp[i + 1][j][1] = (dp[i + 1][j][1] + dp[i][j][1]) % MOD;
                }
            }
        }


        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }
} */