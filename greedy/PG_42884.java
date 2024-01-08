package programmers;
import java.util.*;

public class PG_42884 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int result = solution.solution(new int[][]{{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}});
            System.out.println("result = " + result);

        }
}

class Solution {
    public int solution(int[][] routes) {

        int answer = 0;

        // 진출 지점 오름차순 정렬
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] r1, int[] r2) {
                return r1[1] - r2[1];
            }
        });

        // 현재 카메라 설치 위치보다 진입 지점이 큼 -> 카메라 설치, 카운팅 하고 카메라 위치 업데이트
        // 현재 카메라 설치 위치보다 진입 지점이 작거나 같음 -> 무조건 카메라를 지나가게 됨
        int pos = Integer.MIN_VALUE;
        for (int[] r : routes) {
            if (pos < r[0]) {
                pos = r[1];
                answer++;
            }
        }
        return answer;
    }
}

/* 나의 풀이
class Solution {
    public int solution(int[][] routes) {

        // 우선순위 큐 생성
        PriorityQueue<Route> pq = new PriorityQueue<>();

        // route를 pq에 넣기
        for (int[] r : routes) {
            pq.offer(new Route(r[0], r[1]));
        }

        // pq에서 하나씩 꺼내면서 카운팅
        int answer = 0;
        int start = -30001;
        int end = -30001;
        while (!pq.isEmpty()) {
            Route route = pq.poll();
            if (end < route.start) { // 겹치지 않음
                answer++;
                start = route.start;
                end = route.end;
            }
            else { // 겹침 -> 범위 좁히기
                start = route.start;
                end = Math.min(end, route.end);
            }
        }

        return answer;
    }

    class Route implements Comparable<Route> {
        int start;
        int end;

        public Route (int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Route other) {
            if (this.start == other.start)
                return Integer.compare(other.end, this.end);
            else
                return Integer.compare(this.start, other.start);
        }
    }
} */