package programmers;
import java.util.*;

public class PG_181188 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int result = solution.solution(new int[][]{{4,5},{4,8},{10,14},{11,13},{5,12},{3,7},{1,4}});
            System.out.println(result);
        }
}

class Solution {

    public int solution(int[][] targets) {

        int answer = 0;

        // 정렬
        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);

        // 처음부터 요격
        int before = 0;
        for (int i = 0; i < targets.length; i++) {
            if (before <= targets[i][0]) {
                before = targets[i][1];
                answer++;
            }
        }

        return answer;
    }
}


/* 나의 풀이
class Solution {

    public class Node implements Comparable<Node> {
        int s;
        int e;

        public Node(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Node other) {
            if (this.e == other.e) return this.s - other.s;
            else return this.e - other.e;
        }
    }

    public int solution(int[][] targets) {

//         미사일을 최소로 사용해서 모든 폭격 미사일 요격
//         A 나라가 발사한 폭격 미사일 : x축에 평행한 직선 형태, 개구간 나타내는 (s, e)로 표현
//         B 나라는 특정 x 좌표에서 y축에 수평이 되도록 미사일 발사, 발사된 미사일은 해당 x좌표 모든 폭격 미사일 한 번에 요격
//         요격은 실수 x 좌표에서도 발사 가능

//         targets : 각 폭격 미사일의 x좌표 범위 목록
//         return 모든 폭격 미사일 요격 위해 필요한 요격 미사일 수의 최솟값

//         **
//         targets 1 ~ 50만
//         s, e 0 ~ 1억


//         1. 끝부분 기준 정렬
//         2. 처음부터 읽으면서.. 구간 포함 안되면 +1

        // 초기화 후 정렬
        int answer = 0;
        List<Node> list = new ArrayList<>();
        for (int[] target : targets) {
            list.add(new Node(target[0], target[1]));
        }
        Collections.sort(list);

        // 처음부터 읽으면서 구간에 포함되지 않으면 +1
        int x = 0;
        for (int i = 0; i < list.size(); i++) {
            // 구간에 포함 X -> x 갱신하고 answer++
            if (list.get(i).s >= x) {
                answer++;
                x = list.get(i).e;
            }
        }

        return answer;
    }
} */