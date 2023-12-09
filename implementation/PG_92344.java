import java.util.*;

class Solution {

    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int colSize = board[0].length;
        int rowSize = board.length;

        int[][] save = new int[rowSize + 1][colSize + 1];
        for (int[] o : skill) {
            int x1 = o[1];
            int y1 = o[2];
            int x2 = o[3];
            int y2 = o[4];
            int c;
            if (o[0] == 1) {
                c = -o[5];
            } else {
                c = o[5];
            }
            save[x1][y1] += c;
            save[x1][y2 + 1] -= c;
            save[x2 + 1][y1] -= c;
            save[x2 + 1][y2 + 1] += c;
        }

        for (int j = 0; j < colSize; j++) {
            for (int i = 1; i < rowSize; i++) {
                save[i][j] += save[i - 1][j];
            }
        }

        for (int i = 0; i < rowSize; i++) {
            for (int j = 1; j < colSize; j++) {
                save[i][j] += save[i][j - 1];
            }
        }

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                board[i][j] += save[i][j];
                if (board[i][j] > 0) answer++;
            }
        }

        return answer;
    }
}

/* 나의 풀이
class Solution {

    public int solution(int[][] board, int[][] skill) {

        // skill 분리
        int[][] result = new int[board.length][board[0].length]; // 공격, 회복 결과 저장
        for (int[] s : skill) {
            int type = s[0];
            int x1 = s[1];
            int y1 = s[2];
            int x2 = s[3];
            int y2 = s[4];
            int degree;
            if (type == 1) { // 공격인 경우
                degree = -s[5];
            } else {
                degree = s[5];
            }

            result[x2][y2] += degree;
            if (y1 > 0) {
                result[x2][y1 - 1] -= degree;
            }
            if (x1 > 0) {
                result[x1 - 1][y2] -= degree;
            }
            if (y1 == 0 || x1 == 0) continue; // 한개만 제거한 경우는 마지막 작업 필요 X
            result[x1 - 1][y1 - 1] += degree;
        }

        // 오른쪽에서 가져오기
        for (int i = 0; i < result.length; i++) {
            for (int j = result[0].length - 1; j >= 1; j--) {
                result[i][j - 1] += result[i][j];
            }
        }

        // 밑에서 가져오기
        for (int i = result.length - 1; i >= 1; i--) {
            for (int j = 0; j < result[0].length; j++) {
                result[i - 1][j] += result[i][j];
            }
        }

        // 결과 계산
        int answer = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = board[i][j] + result[i][j];
                if (board[i][j] > 0) answer++;
            }
        }

        return answer;
    }
} */