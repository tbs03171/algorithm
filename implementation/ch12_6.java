import java.util.*;

class Node implements Comparable<Node> {
    
    private int x;
    private int y;
    private int stuff;
    
    public Node(int x, int y, int stuff) {
        this.x = x;
        this.y = y;
        this.stuff = stuff;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getStuff() {
        return this.stuff;
    }
    
    // 정렬 기준 설정 (x, y, stuff 순서대로 오름차순)
    @Override
    public int compareTo(Node other) {
        if (this.x == other.x && this. y == other.y) {
            return Integer.compare(this.stuff, other.stuff);
        }
        if (this.x == other.x) {
            return Integer.compare(this.y, other.y);
        }
        return Integer.compare(this.x, other.x);
    }
}

class Solution {
    
    // 현재 설치된 구조물이 가능한 구조물인지 확인하는 함수
    public boolean possible(ArrayList<ArrayList<Integer>> answer) {
        for (int i = 0; i < answer.size(); i++) {
            int x = answer.get(i).get(0);
            int y = answer.get(i).get(1);
            int stuff = answer.get(i).get(2);
            if (stuff == 0) { // 설치된 것이 기둥인 경우
                boolean check = false;
                // 바닥 위
                if (y == 0) check = true;
                // '보의 한쪽 끝부분 위' 혹은 '다른 기둥 위'
                for (int j = 0; j < answer.size(); j++) {
                    if (answer.get(j).get(0) == x && answer.get(j).get(1) == y && answer.get(j).get(2) == 1) { // 보의 왼쪽 끝부분 위
                        check = true;
                    }
                    if (answer.get(j).get(0) == x - 1 && answer.get(j).get(1) == y && answer.get(j).get(2) == 1) { // 보의 오른쪽 끝부분 위
                        check = true;
                    }
                    if (answer.get(j).get(0) == x && answer.get(j).get(1) == y - 1 && answer.get(j).get(2) == 0) { // 다른 기둥 위
                        check = true;
                    }
                }
                if (!check) return false; // 아니라면 false 반환
            }
            else if (stuff == 1) { // 설치된 것이 보인 경우
                boolean check = false;
                boolean left = false;
                boolean right = false;
                // '한쪽 끝부분이 기둥 위' 이거나 '양쪽 끝부분이 다른 보와 동시 연결'
                for (int j = 0; j < answer.size(); j++) {
                    if (answer.get(j).get(0) == x && answer.get(j).get(1) == y - 1 && answer.get(j).get(2) == 0) { // 왼쪽 끝부분이 기둥 위
                        check = true;
                    }
                    if (answer.get(j).get(0) == x + 1 && answer.get(j).get(1) == y - 1 && answer.get(j).get(2) == 0) { // 오른쪽 끝부분이 기둥 위
                        check = true;
                    }
                    if (answer.get(j).get(0) == x - 1 && answer.get(j).get(1) == y && answer.get(j).get(2) == 1) { // 양쪽 끝부분이 다른 보와 동시 연결
                        left = true;
                    }
                    if (answer.get(j).get(0) == x + 1 && answer.get(j).get(1) == y && answer.get(j).get(2) == 1) {
                        right = true;
                    }
                }
                if (left && right) check = true;
                if (!check) return false; // 아니라면 false 반환
            }
        }
        return true;
    }
    
    public int[][] solution(int n, int[][] build_frame) {
        ArrayList<ArrayList<Integer>> answer = new ArrayList<ArrayList<Integer>>();
        
        // build_frame 원소 하나씩 읽으면서
        for (int i = 0; i < build_frame.length; i++) {
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            int stuff = build_frame[i][2]; // 기둥? 보?
            int operate = build_frame[i][3]; // 설치? 삭제?
            if (operate == 0) { // 삭제
                // 일단 삭제
                int index = 0;
                for (int j = 0; j < answer.size(); j++) {
                    if (answer.get(j).get(0) == x && answer.get(j).get(1) == y && answer.get(j).get(2) == stuff) {
                        index = j;
                    }
                }
                ArrayList<Integer> erased = answer.get(index);
                answer.remove(index);
                // 확인
                if (!possible(answer)) {
                    answer.add(erased);
                }
            }
            if (operate == 1) { // 설치
                // 일단 설치
                ArrayList<Integer> inserted = new ArrayList<Integer>();
                inserted.add(x);
                inserted.add(y);
                inserted.add(stuff);
                answer.add(inserted);
                // 확인
                if (!possible(answer)) {
                    answer.remove(answer.size() - 1);
                }
            }
        }
        
        // 정렬
        ArrayList<Node> ans = new ArrayList<Node>();
        for (int i = 0; i < answer.size(); i++) {
            ans.add(new Node(answer.get(i).get(0), answer.get(i).get(1), answer.get(i).get(2)));
        }
        Collections.sort(ans);
        
        // 배열로 변환
        int[][] res = new int[ans.size()][3];
        for (int i = 0; i < ans.size(); i++) {
            res[i][0] = ans.get(i).getX();
            res[i][1] = ans.get(i).getY();
            res[i][2] = ans.get(i).getStuff();
        }
        
        return res;
    }
}