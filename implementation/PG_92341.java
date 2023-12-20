package programmers;
import java.util.*;

public class PG_92341 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int[] results = solution.solution(new int[]{180, 5000, 10, 600}, new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"});
            for (int result : results) {
                System.out.print(result + " ");
            }
        }
}

class Solution {

    public int[] solution(int[] fees, String[] records) {

        int[] answer = {};

        int basicTime = fees[0]; // 기본 시간
        int basicFee = fees[1]; // 기본 요금
        int unitTime = fees[2]; // 단위 시간
        int unitFee = fees[3]; // 단위 요금

        // 시간 저장 -> 시간 * 60 + 분
        // 출차 기록 없을 경우 23:59에 출차 되었다고 간주
        int maxTime = 23* 60 + 59;

        HashMap<String, Integer> inTime = new HashMap<>();
        HashMap<String, Integer> carFees = new HashMap<>();
        List<String> cars = new ArrayList<>(); // 차들 목록

        for (int i = 0; i < records.length; i++) {
            String str = records[i];

            int time = (Integer.parseInt(str.substring(0,2))) * 60 + (Integer.parseInt(str.substring(3, 5))); // 시간
            String num = str.substring(6, 10); // 차 번호
            Character status = str.charAt(11); // 상태

            // 입차
            if (status == 'I') {
                // 처음 들어오는 차일 경우
                if (!carFees.containsKey(num)) {
                    carFees.put(num, 0);
                    inTime.put(num, time);
                    cars.add(num);
                }
                // 이미 들어와 있던 차일 경우
                else {
                    inTime.put(num, time);
                }
            }
            // 출차
            else {
                // 누적 시간 계산
                carFees.put(num, carFees.get(num) + (time - inTime.get(num)));
                inTime.remove(num); // 주차장에 있는 차 목록에서 제거
            }
        }

        // 요금 계산
        for (int i = 0; i < cars.size(); i++) {
            String carN = cars.get(i);

            // 아직 출차하지 않음
            if (inTime.containsKey(carN)) {
                carFees.put(carN, (maxTime - inTime.get(carN)) + carFees.get(carN));
            }

            // 요즘 계산하기
            int time = carFees.get(carN);
            if (time > basicTime) {
                int fee = 0;
                if ((time - basicTime) % unitTime != 0) {
                    fee = basicFee + (((time - basicTime) / unitTime) + 1) * unitFee;
                }
                else
                    fee = basicFee + ((time - basicTime) / unitTime) * unitFee;
                carFees.put(carN, fee);
            }
            else carFees.put(carN, basicFee); // 기본 요금만 납부
        }

        // key값을 기준으로 정렬
        List<String> keySet = new ArrayList<>(carFees.keySet());
        Collections.sort(keySet);

        int size = keySet.size();
        answer = new int[size];

        for (int i = 0; i < size; i++) {
            answer[i] = carFees.get(keySet.get(i));
        }

        return answer;
    }
}

/* 나의 풀이
class Solution {

    Map<Integer, Integer> totalTimeMap = new TreeMap<>(); // 누적 주차 시간 저장할 맵 <차 번호, 누적 주차 시간>
    Map<Integer, Integer> recordMap = new HashMap<>(); // 입차시간 기록할 맵 <차 번호, 입차 시간>
    int defaultTime, defaultMoney, unitTime, unitMoney;

    public int[] solution(int[] fees, String[] records) {

        // 초기화
        defaultTime = fees[0];
        defaultMoney = fees[1];
        unitTime = fees[2];
        unitMoney = fees[3];

        // 모든 record에 대해 누적 주차 시간 계산
        for (String record : records) {
            String[] splits = record.split(" ");

            // 시간
            String[] times = splits[0].split(":");
            int time = (Integer.parseInt(times[0]) * 60) + Integer.parseInt(times[1]);

            // 차량 번호
            int number = Integer.parseInt(splits[1]);

            // 입차 or 출차
            String type = splits[2];

            // 입차라면 기록
            if (type.equals("IN")) {
                recordMap.put(number, time);
            }
            // 출차라면 입차 시간 꺼내서 누적 시간에 넣기
            else {
                int timeIn = recordMap.get(number); // 입차 시간
                int totalTime = 0;
                // 존재하는 키 -> 누적 시간 + 현재 주차 시간 저장
                if (totalTimeMap.containsKey(number))
                    totalTime = totalTimeMap.get(number) + (time - timeIn);
                // 존재하지 않는 키 -> 현재 주차 시간 저장
                else
                    totalTime = time - timeIn;
                totalTimeMap.put(number, totalTime);
                recordMap.put(number, -1); // 출차 처리 완료 표시
            }
        }

        // 출차 기록 없는 것 처리
        for (Map.Entry<Integer, Integer> entry : recordMap.entrySet()) {
            if (entry.getValue() != -1) { // 출차 처리되지 않았다면
                int timeIn = entry.getValue();
                int totalTime = 0;
                // 존재하는 키 -> 누적 시간 + 현재 주차 시간 저장
                if (totalTimeMap.containsKey(entry.getKey()))
                    totalTime = totalTimeMap.get(entry.getKey()) + (23 * 60 + 59) - timeIn;
                // 존재하지 않는 키 -> 현재 주차 시간 저장
                else
                    totalTime = (23 * 60 + 59) - timeIn;
                totalTimeMap.put(entry.getKey(), totalTime);
            }
        }

        // 주차 요금 기록
        int[] answer = new int[totalTimeMap.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : totalTimeMap.entrySet()) {
            int totalTime = entry.getValue();

            if (totalTime <= defaultTime) { // 기본 시간 이하
                answer[index] = defaultMoney;
            } else { // 기본 시간 초과
                int totalMoney = defaultMoney;
                totalTime -= defaultTime;
                if (totalTime % unitTime == 0) { // 나누어 떨어지면 바로 계산
                    totalMoney += (totalTime / unitTime) * unitMoney;
                } else { // 나누어 떨어지지 않으면 올림
                    totalMoney += (totalTime / unitTime + 1) * unitMoney;
                }
                answer[index] = totalMoney;
            }
            index++;
        }

        return answer;
    }
} */