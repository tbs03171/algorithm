package programmers;

public class PG_160585 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int result = solution.solution(new String[]{"O.X", ".O.", "..X"});
            System.out.println("result = " + result);
        }
}

class Solution {

    public int solution(String[] board) {

        int Ocnt = 0;
        int Xcnt = 0;

        // O, X 개수 세기
        for (int i = 0; i < 3; i++) {
            Ocnt += countChar(board[i], 'O');
            Xcnt += countChar(board[i], 'X');
        }

        // 1. X가 O보다 많으면 규칙 위반
        if (Xcnt > Ocnt) return 0;

        // 2. O가 X보다 2개 이상 많으면 규칙 위반
        if (Ocnt > Xcnt + 1) return 0;

        // 3. O가 완성되었을 때, X가 O의 개수와 같으면 규칙 위반
        if (hasWin(board, 'O') && Ocnt == Xcnt) return 0;

        // 4. X가 완성되었을 때, O가 X보다 1개 많으면 규칙 위반
        if (hasWin(board, 'X') && Ocnt == Xcnt + 1) return 0;

        return 1;
    }

    public int countChar(String str, char ch) {
        return str.length() - str.replace(String.valueOf(ch), "").length();
    }

    public boolean hasWin(String[] board, char ch) {

        // 가로 검사
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == ch
                    && board[i].charAt(1) == ch
                    && board[i].charAt(2) == ch)
                return true;
        }

        // 세로 검사
        for (int i = 0; i < 3; i++) {
            if (board[0].charAt(i) == ch
                    && board[1].charAt(i) == ch
                    && board[2].charAt(i) == ch)
                return true;
        }

        // 대각선 검사
        if (board[0].charAt(0) == ch
                && board[1].charAt(1) == ch
                && board[2].charAt(2) == ch)
            return true;
        if (board[0].charAt(2) == ch
                && board[1].charAt(1) == ch
                && board[2].charAt(0) == ch)
            return true;

        return false;
    }
}


/* 나의 풀이
class Solution {
    public int solution(String[] board) {

//         3X3 빈칸
//         선공이 O 후공이 X 표시
//         가로, 세로, 대각선으로 3개가 같은 표시 -> 승리하고 종료
//         9칸 모두 참 -> 무승부 종료

//         혼자 선공, 후공 -> O, X 번갈아 표시하면서 진행
//         머쓱이의 실수
//         1. O 차례인데 X 표시, X 차례인데 O 표시
//         2. 게임 종료됐는데 계속 진행
//         지금 게임판이 규칙 지켰을 때 나올 수 있는 상황인지 판단

//         . : 빈칸, O, X
//         return 가능하면 1 아니면 0

        // 1. 1번 실수 판단
        int countO = 0;
        int countX = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == 'O') countO++;
                else if (board[i].charAt(j) == 'X') countX++;
            }
        }
        if (!(countO == countX || countO == countX + 1)) return 0;

        // 2. 2번 실수 판단
        // 각각 승리 갯수 계산
        int winO = 0;
        int winX = 0;

        // 가로
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == 'O' && board[i].charAt(0) == board[i].charAt(1) && board[i].charAt(1) == board[i].charAt(2)) winO++;
            else if (board[i].charAt(0) == 'X' && board[i].charAt(0) == board[i].charAt(1) && board[i].charAt(1) == board[i].charAt(2)) winX++;
        }

        // 세로
        for (int i = 0; i < 3; i++) {
            if (board[0].charAt(i) == 'O' && board[0].charAt(i) == board[1].charAt(i) && board[1].charAt(i) == board[2].charAt(i)) winO++;
            else if (board[0].charAt(i) == 'X' && board[0].charAt(i) == board[1].charAt(i) && board[1].charAt(i) == board[2].charAt(i)) winX++;
        }

        // 대각선
        if (board[0].charAt(0) == 'O' && board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2)) winO++;
        else if (board[0].charAt(0) == 'X' && board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2)) winX++;
        if (board[0].charAt(2) == 'O' && board[0].charAt(2) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(0)) winO++;
        else if (board[0].charAt(2) == 'X' && board[0].charAt(2) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(0)) winX++;

        // 2-1. 둘 다 승리
        if (winO == 1 && winX == 1) return 0;

        // 2-2. O가 승리했는데 O 개수 == X 개수
        if (winO == 1 && countO == countX) return 0;

        // 2-2. X가 승리했는데 O 개수 != X 개수
        if (winX == 1 && countX != countO) return 0;

        return 1;
    }
} */