package programmers;
import java.util.*;

public class PG_150366 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            String[] results = solution.solution(new String[]{"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"});
            for (String result : results) {
                System.out.println(result);
            }
        }
}

class Solution {

    public int[] parent = new int[2501];
    public String[] value = new String[2501];

    // UNION-FIND 알고리즘
    public int find(int a) {
        if (parent[a] == a)
            return a;
        else
            return parent[a] = find(parent[a]);
    }

    public void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b)
            parent[b] = a;
    }

    // 좌표를 번호로 변환
    public int convertNum(int x, int y) {
        int result = 50 * (x - 1);
        return result + y;
    }

    public String[] solution(String[] commands) {

        // 초기화
        for (int i = 1; i <= 2500; i++) {
            parent[i] = i;
            value[i] = "";
        }

        // 명령어 실행
        List<String> result = new ArrayList<>();
        for (int ind = 0; ind < commands.length; ind++) {
            String line = commands[ind];
            StringTokenizer st = new StringTokenizer(line);
            String command = st.nextToken();

            if ("UPDATE".equals(command)) {
                // UPDATE value1 value2
                if (st.countTokens() == 2) {
                    String before = st.nextToken();
                    String after = st.nextToken();
                    for (int i = 1; i <= 2500; i++) {
                        if (before.equals(value[i]))
                            value[i] = after;
                    }
                }
                // UPDATE x y value
                else {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    String after = st.nextToken();
                    int num = convertNum(x, y);
                    value[find(num)] = after;
                }
            }
            else if ("MERGE".equals(command)) {
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                int n1 = convertNum(x1, y1);
                int n2 = convertNum(x2, y2);
                int root1 = find(n1);
                int root2 = find(n2);
                // 같은 그룹이면 무시
                if (root1 == root2)
                    continue;
                // 값을 가진 쪽으로 병합
                String rootString = value[root1].isBlank() ? value[root2] : value[root1];
                value[root1] = "";
                value[root2] = "";
                union(root1, root2);
                value[root1] = rootString;
            }
            else if ("UNMERGE".equals(command)) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int num = convertNum(x, y);
                int root = find(num);
                String rootString = value[root];
                value[root] = "";
                value[num] = rootString;
                List<Integer> deleteList = new ArrayList<>();
                for (int i = 1; i <= 2500; i++) {
                    if (find(i) == root)
                        deleteList.add(i);
                }
                for (Integer t : deleteList)
                    parent[t] = t;
            }
            else if ("PRINT".equals(command)) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int num = convertNum(x, y);
                int root = find(num);
                if (value[root].isBlank())
                    result.add("EMPTY");
                else
                    result.add(value[root]);
            }
        }
        return result.toArray(new String[0]);
    }
}

/* 나의 풀이
class Solution {

    public List<String> result = new ArrayList<>();
    public Node[][] arr = new Node[51][51];

    public Node find(Node node) {
        if (node.parent == node)
            return node;
        else {
            node.value = "";
            return node.parent = find(node.parent);
        }
    }

    public void union(Node node1, Node node2) {
        node1 = find(node1);
        node2 = find(node2);

        if (node1 == node2)
            return;
        else if (!node1.value.equals("")) {
            node2.value = "";
            node2.parent = node1;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    if (arr[i][j].parent == node2)
                        arr[i][j].parent = node1;
                }
            }
        }
        else {
            node1.value = "";
            node1.parent = node2;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    if (arr[i][j].parent == node1)
                        arr[i][j].parent = node2;
                }
            }
        }

    }

    public String[] solution(String[] commands) {

        // 초기화
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = new Node();
            }
        }

        // 명령 수행
        for (String command : commands) {
            String[] splits = command.split(" ");
            if (splits.length == 4) { // UPDATE r c value
                int r = Integer.parseInt(splits[1]);
                int c = Integer.parseInt(splits[2]);
                Node parent = find(arr[r][c]);
                parent.value = splits[3];
            }
            else if (splits.length == 5) { // MERGE r1 c1 r2 c2
                int r1 = Integer.parseInt(splits[1]);
                int c1 = Integer.parseInt(splits[2]);
                int r2 = Integer.parseInt(splits[3]);
                int c2 = Integer.parseInt(splits[4]);
                union(arr[r1][c1], arr[r2][c2]);
            }
            else if (splits[0].charAt(0) == 'P') { // PRINT r c
                int r = Integer.parseInt(splits[1]);
                int c = Integer.parseInt(splits[2]);
                Node parent = find(arr[r][c]);
                if (parent.value.equals(""))
                    result.add("EMPTY");
                else
                    result.add(parent.value);
            }
            else if (splits[0].substring(0, 2).equals("UP")) { // UPDATE value1 value2
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr[0].length; j++) {
                        if (arr[i][j].value.equals(splits[1]))
                            arr[i][j].value = splits[2];
                    }
                }
            }
            else { // UNMERGE r c
                int r = Integer.parseInt(splits[1]);
                int c = Integer.parseInt(splits[2]);
                Node node = arr[r][c];
                Node parent = find(arr[r][c]);
                String value = parent.value;

                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr[0].length; j++) {
                        if (arr[i][j].parent == parent) {
                            arr[i][j].parent = arr[i][j];
                            arr[i][j].value = "";
                        }
                    }
                }

                node.parent = node;
                node.value = value;
            }
        }

        // 배열로 변환해서 반환
        String[] answer = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }

    public class Node {
        String value;
        Node parent;

        public Node() {
            this.value = "";
            this.parent = this;
        }
    }
} */