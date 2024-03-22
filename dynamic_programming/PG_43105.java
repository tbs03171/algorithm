package programmers;

import java.util.Arrays;

public class PG_43105 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int result = solution.solution(new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}});
            System.out.println(result);
        }
}

class Solution {

    // j 가 0 ~ i 범위에 있는지 여부
    public boolean isRange(int i , int j) {
        return j >= 0 && j < i + 1;
    }

    public int solution(int[][] triangle) {

        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < i + 1; j++) {
                int x = 0;
                int y = 0;
                if (isRange(i - 1, j - 1)) {
                    x = triangle[i - 1][j - 1];
                }
                if (isRange(i - 1, j)) {
                    y = triangle[i - 1][j];
                }
                triangle[i][j] += Math.max(x, y);
            }
        }

        int answer = Arrays.stream(triangle[triangle.length - 1]).max().getAsInt();
        return answer;
    }
}

/* 나의 풀이
class Solution {

    public int solution(int[][] triangle) {

//         삼각형 꼭대기~바닥 경로 중 거쳐간 수들의 합이 가장 큰 경우
//         대각선 방향 한칸 오른/왼 이동 가능
//         triangle : 삼각형 정보
//         거쳐간 숫자의 최댓값 return

//         **
//         삼각형 높이 1 ~ 500
//         각 숫자 0 ~ 9999

        // 꼭대기 ~ 맨 밑층까지 (삼각형 높이만큼)
        for (int i = 1; i < triangle.length; i++) {
            triangle[i][0] += triangle[i - 1][0]; // 첫 번째
            for (int j = 1; j < triangle[i].length - 1; j++) {
                triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
            }
            triangle[i][triangle[i].length - 1] += triangle[i - 1][triangle[i - 1].length - 1]; // 마지막
        }

        // 최댓값 찾기
        int answer = 0;
        for (int i = 0; i < triangle[triangle.length - 1].length; i++) {
            answer = Math.max(answer, triangle[triangle.length - 1][i]);
        }

        return answer;
    }
} */