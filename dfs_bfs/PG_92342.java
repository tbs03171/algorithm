import java.util.*;

public class Solution {
    int len = 11, maxDiff = 0;
    int[] maxRyan = new int[len];

    public int[] solution(int n, int[] info) {
        int[] ryan = new int[len];
        getArcheryScore(n, info, ryan, 0);

        return maxDiff > 0 ? maxRyan : new int[] {-1};
    }

    public void getArcheryScore(int n, int[] info, int[] ryan, int score) {
        // 과녁을 다 썼거나, 마지막 점수까지 도달
        if (n == 0 || score == len) {
            setMaxRyanAndDiff(info, ryan);
            return;
        }

        int arrow = 0;
        if (info[score] < n) arrow = info[score] + 1;
        else if (score == len - 1) arrow = n; // 남은 과녁 0점에 다 꽂기

        // 이기는 경우
        int tmp = info[score];
        if (arrow > 0) {
            info[score] = 0;
            ryan[score] = arrow;
            getArcheryScore(n - arrow, info, ryan, score + 1);
        }

        // 지는 경우 (원상 복구 후 호출)
        info[score] = tmp;
        ryan[score] = 0;
        getArcheryScore(n, info, ryan, score + 1);
    }

    public void setMaxRyanAndDiff(int[] info, int[] ryan) {
        // 점수 차 계산
        int sum = 0;
        for (int i = 0; i < len; i++) {
            if (info[i] > 0) sum -= 10 - i;
        }
        for (int i = 0; i < len; i++) {
            if (ryan[i] > 0) sum += 10 - i;
        }

        // 점수 차가 기존의 것보다 크다면
        if (this.maxDiff < sum) {
            for (int r = 0; r < len; r++) {
                this.maxRyan[r] = ryan[r];
                this.maxDiff = sum;
            }
        }
        // 기존의 것과 같다면
        else if (this.maxDiff == sum) {
            for (int i = len - 1; i >= 0; i--) {
                if (this.maxRyan[i] < ryan[i]) { // 갱신
                    for (int r = 0; r < len; r++) {
                        this.maxRyan[r] = ryan[r];
                    }
                    break;
                } else if (this.maxRyan[i] > ryan[i]) { // 갱신하지 않음
                    break;
                }
            }
        }
    }
}

/* 나의 풀이
class Solution {
    public int[] temp = new int[11];
    public int[] result = new int[11];
    public boolean isWinner = false;
    public int diff = -1;

    public int[] solution(int n, int[] info) {
        dfs(0, n, info);
        if (isWinner) return result;
        else return new int[]{-1};
    }

    // at = 현재 위치, remain = 남은 과녁 수, aPeach = 어피치 과녁 정보
    public void dfs(int at, int remain, int[] aPeach) {  
        // 끝까지 다 왔다면 점수 계산하고 비교
        if (at == 11) {
            // 과녁을 다 쓰지 않음
            if (remain != 0) {
                temp[10] = remain;
            }
            // 점수 계산
            int pRion = 0;
            int pAPeach = 0;
            for (int i = 0; i <= 10; i++) {
                if (aPeach[i] == 0 && temp[i] == 0) continue;
                if (aPeach[i] < temp[i]) { // 라이언 점수
                    pRion += (10 - i);
                } else { // 어피치 점수
                    pAPeach += (10 - i);
                }
            }

            // 라이언이 이긴 경우 점수 차 계산 후 result 갱신
            if (pRion > pAPeach) {
                isWinner = true;
                if (pRion - pAPeach == diff) {
                    for (int i = 10; i >= 0; i--) {
                        if (temp[i] > result[i]) break;
                        if (result[i] > temp[i]) return;
                    }
                }
                if (pRion - pAPeach >= diff) {
                    diff = pRion - pAPeach;
                    for (int i = 0; i < 11; i++) {
                        result[i] = temp[i];
                    }
                }
            }

            return;
        }

        // 이기거나, 지거나
        // 이긴다면?
        if (aPeach[at] + 1 <= remain) { // 이길 수 있음
            temp[at] = aPeach[at] + 1;
            dfs(at + 1, remain - (aPeach[at] + 1), aPeach);
        }

        // 진다면?
        temp[at] = 0;
        dfs(at + 1, remain, aPeach);
    }
} */