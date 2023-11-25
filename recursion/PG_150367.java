import java.util.*;

class Solution {
    static int result;
    static boolean[] binary;
    static int treeLen;

    public int[] solution(long[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            String b = Long.toBinaryString(numbers[i]);

            int length = b.length();
            int exp = 1;
            do {
                treeLen = (int) Math.pow(2, exp++) - 1;
            } while (treeLen < length);

            binary = new boolean[treeLen];
            int idx = treeLen - length;
            for (int j = 0; j < length; j++) {
                binary[idx++] = b.charAt(j) == '1';
            }

            result = 1;
            possible(0, treeLen - 1, false); // s, e, 해당 루트가 더미인지
            answer[i] = result;
        }
        return answer;
    }

    public static void possible(int s, int e, boolean check) {
        int mid = (s + e) / 2;

        // 더미인데 1이 나왔다면 불가능
        if (check && binary[mid]) { // 루트가 0이면 자식 노드들에서 1이 나오면 안됨
            result = 0;
            return;
        }

        // 내가 마지막 노드가 아니라면 재귀
        if (s != e) {
            possible(s, mid - 1, !binary[mid]); // 왼쪽, 현재 루트가 더미이면 check = true;
            possible(mid + 1, e, !binary[mid]); // 오른쪽            
        }
    }
}

/* 나의 풀이
class Solution {

    public String[] possible = {"000", "010", "011", "110", "111"};
    public int[] solution(long[] numbers) {
        // 결과 담을 리스트
        List<Integer> result = new ArrayList<>();

        // 모든 number를 이진수로 변환
        List<StringBuilder> binaryNumbers = new ArrayList<>();
        for (long number : numbers) {
            StringBuilder binaryString = new StringBuilder(Long.toBinaryString(number));
            StringBuilder sb = new StringBuilder();
            if (binaryString.length() == 1) {

            } else if (binaryString.length() == 2 || binaryString.length() == 3) {
                while (sb.length() + binaryString.length() < 3) {
                    sb.append(0);
                }
            } else if (binaryString.length() <= 7) {
                while (sb.length() + binaryString.length() < 7) {
                    sb.append(0);
                }
            } else if (binaryString.length() <= 15) {
                while (sb.length() + binaryString.length() < 15) {
                    sb.append(0);
                }
            } else if (binaryString.length() <= 31) {
                while (sb.length() + binaryString.length() < 31) {
                    sb.append(0);
                }
            } else {
                while (sb.length() + binaryString.length() < 63) {
                    sb.append(0);
                }
            }
            binaryNumbers.add(sb.append(binaryString));
        }

        // 각 이진수에 대해 가능한지 검사하고 결과에 담기
        for (StringBuilder number : binaryNumbers) {
            if (isTree(number)) result.add(1);
            else result.add(0);
        }

        // result -> answer
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    private boolean isTree(StringBuilder number) {
        int start = 0;
        int end = number.length() - 1;
        int root = (start + end) / 2;
        if (number.length() == 1) return true;

        if (number.length() == 3) {
            for (String s : possible) {
                if (s.equals(number.toString())) return true;
            }
            return false;
        } else {
            if (number.charAt(root) - '0' == 0) {
                if (isZero(new StringBuilder(number.substring(start, root))) && isZero(new StringBuilder(number.substring(root + 1, end + 1)))) return true;
                return false;
            }
            else return isTree(new StringBuilder(number.substring(start, root))) && isTree(new StringBuilder(number.substring(root + 1, end + 1)));
        }
    }

    private boolean isZero(StringBuilder number) {
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) - '0' == 1) return false;
        }
        return true;
    }
} */