package programmers;

public class PG_258705 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int result = solution.solution(4, new int[]{1, 1, 0 ,1});
            System.out.println(result);
        }
}

class Solution {

    public int[] a;
    public int[] b;
    public final int MOD = 10007;

    public int solution(int n, int[] tops) {

        a = new int[n + 1];
        b = new int[n + 1];
        a[1] = 1;
        b[1] = tops[0] == 1 ? 3 : 2;

        for (int i = 2; i <= n; i++) {
            if (tops[i - 1] == 1) { // 꼭다리 O
                a[i] = (a[i - 1] + b[i - 1]) % MOD;
                b[i] = (b[i - 1] * 3 + a[i - 1] * 2) % MOD;
            }
            else { // 꼭다리 X
                a[i] = (a[i - 1] + b[i - 1]) % MOD;
                b[i] = (a[i - 1] * 1 + b[i - 1] * 2) % MOD;
            }
        }

        return (a[n] + b[n]) % MOD;
    }
}

/* 나의 풀이
class Solution {

    public int[] dp;

    public int solution(int n, int[] tops) {

        dp = new int[2 * n + 2];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= 2 * n + 1; i++) {
            if (i % 2 == 0 && tops[i / 2 - 1] == 1) // 꼭따리 있으면
                dp[i] = (dp[i - 1] * 2 + dp[i - 2]) % 10007;
            else
                dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
        }

        int answer = dp[2 * n + 1];
        return answer;
    }
} */