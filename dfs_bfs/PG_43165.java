package programmers;

public class PG_43165 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int result = solution.solution(new int[]{4, 1, 2, 1}, 4);
            System.out.println(result);
        }
}


class Solution {

    public int answer = 0;

    public int solution(int[] numbers, int target) {

        /*
        n개의 음이 아닌 정수
        순서 바꾸지 않고 적절히 더하거나 빼서 타켓 넘버
        numbers : 사용할 수 있는 숫자들
        target : 타겟 넘버
        타겟 넘버 만드는 방법 수 return

        **
        numbers 길이 2 ~ 20
        각 숫자 1 ~ 50
        타겟 넘버 1 ~ 1000
        */

        dfs(numbers, 0, 0, target);

        return answer;
    }

    // 배열, 현재 위치, 현재까지의 계산 결과, 타겟 넘버
    public void dfs(int[] numbers, int index, int value, int target) {

        // 배열 길이 넘으면 종료
        if (index == numbers.length) {
            if (value == target)
                answer++;
            return;
        }

        // numbers[index(현재 위치)]를 더하거나, 빼거나
        dfs(numbers, index + 1, value + numbers[index], target);
        dfs(numbers, index + 1, value - numbers[index], target);
    }
}