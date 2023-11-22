import java.util.*;

/* Bottom up DP */
class Solution {
    public int solution(int alp, int cop, int[][] problems) {

         int goal_a = 0;
         int goal_c = 0;

        // 목표치를 구하는 for문
        for (int i = 0; i < problems.length; i++){
            goal_a = Math.max(problems[i][0], goal_a);
            goal_c = Math.max(problems[i][1], goal_c);
        }

        // 코딩력, 알고력 이미 목표치 이상
        if (goal_a <= alp && goal_c <= cop) return 0;

        // 알고력이 목표치 이상인 경우 교정
        if(alp >= goal_a) alp = goal_a;

        // 코딩력이 목표치 이상인 경우 교정
        if(cop >= goal_c) cop = goal_c;

        // dp 테이블 초기화
        int[][] dp = new int[goal_a + 2][goal_c + 2];
        for (int i = alp; i <= goal_a; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        dp[alp][cop] = 0;

        // dp 시작
         for (int i = alp; i <= goal_a; i++) {
            for (int j = cop; j <= goal_c; j++) {

                // 알고, 코딩 공부
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);

                // 문제 풀이
                for (int[] p : problems){
                    if (i >= p[0] && j >= p[1]) { // 풀 수 있는 문제
                        if (i + p[2] > goal_a && j + p[3] > goal_c) {
                          dp[goal_a][goal_c]=Math.min(dp[goal_a][goal_c],dp[i][j]+p[4]);
                        }
                        else if (i + p[2] > goal_a) {
                            dp[goal_a][j + p[3]] = Math.min(dp[goal_a][j + p[3]], dp[i][j] + p[4]);
                        }
                        else if (j + p[3] > goal_c) {
                            dp[i + p[2]][goal_c] = Math.min(dp[i + p[2]][goal_c], dp[i][j] + p[4]);
                        }
                        else if (i + p[2] <= goal_a && j + p[3] <= goal_c) {
                            dp[i + p[2]][j + p[3]] = Math.min(dp[i + p[2]][j + p[3]], dp[i][j] + p[4]); 
                        }
                    }             
                }
            }
         }

        return dp[goal_a][goal_c];
    }


}

/* 나의 풀이
class Solution {

    public int MAX_ALP = 0;
    public int MAX_COP = 0;
    public int min;
    public int[][] memo = new int[10000][10000];

    public int solution(int alp, int cop, int[][] problems) {

        for (int i = 0; i < 10000; i++) {
            Arrays.fill(memo[i], -1);
        }

        // 요구하는 최대 알고력과 코딩력 (경계값)
        for (int[] problem : problems) {
            MAX_ALP = Math.max(MAX_ALP, problem[0]);
            MAX_COP = Math.max(MAX_COP, problem[1]);
        }
        min = ((MAX_ALP > alp) ? MAX_ALP - alp : 0) + ((MAX_COP > cop) ? MAX_COP - cop : 0);

        dfs(alp, cop, 0, problems);

        return min;
    }

    public void dfs(int alp, int cop, int time, int[][] problems) {
        if (alp >= MAX_ALP && cop >= MAX_COP) {
            min = Math.min(min, time);
            return;
        }

        if (time >= min) return;

//         if (memo[alp][cop] != -1 && memo[alp][cop] < time) return;
        memo[alp][cop] = time;

        // 문제 풀거나
        for (int[] problem : problems) {
            if (problem[0] <= alp && problem[1] <= cop) {
                int newAlp = alp + problem[2];
                int newCop = cop + problem[3];
                int newTime = time + problem[4];
                if (memo[newAlp][newCop] == -1 || memo[newAlp][newCop] > newTime) dfs(newAlp, newCop, newTime, problems);
            }
        }

        // 공부 하거나
        if (alp < MAX_ALP) {
            int newAlp = alp + 1;
            int newCop = cop;
            int newTime = time + 1;
            if (memo[newAlp][newCop] == -1 || memo[newAlp][newCop] > newTime) dfs(newAlp, newCop, newTime, problems);
        }
        if (cop < MAX_COP) {
            int newAlp = alp;
            int newCop = cop + 1;
            int newTime = time + 1;
            if (memo[newAlp][newCop] == -1 || memo[newAlp][newCop] > newTime) dfs(newAlp, newCop, newTime, problems);
        }
    }

}*/