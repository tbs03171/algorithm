package programmers;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PG_42893 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int result = solution.solution("blind", new String[]{"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"});
            System.out.println(result);
        }
}

/* 정규식 사용한 풀이 */
class Solution {

    public int solution(String word, String[] pages) {
        int answer = 0;
        word = word.toLowerCase();
        Matcher mt = null;

        PageInfo[] pageInfos = new PageInfo[pages.length];
        List<String>[] datas = new List[pages.length];

        // 각 페이지 정보 정리
        for (int i = 0; i < pages.length; i++) {
            int score = 0;
            pages[i] = pages[i].toLowerCase();
            datas[i] = new ArrayList<>();
            pageInfos[i] = new PageInfo();

            // 본 페이지 url 구하기
            mt = Pattern.compile("(<meta property=\"og:url\" content=\"https://(\\S*)\")").matcher(pages[i]);
            while (mt.find()) {
                String name = mt.group(2).trim();
                pageInfos[i].name = name;
            }

            // 기본 점수 구하기
            mt = Pattern.compile("(?<=[^a-zA-Z])("+word+")[^a-zA-Z]").matcher(pages[i]);
            while (mt.find()) {
                score++;
            }
            pageInfos[i].basicScore = score;

            // 외부 url 구하기
            mt = Pattern.compile("<a href=\"(\\S*)//(\\S*)\"").matcher(pages[i]);
            while (mt.find()) {
                String url = mt.group(2).trim();
                datas[i].add(url);
            }
            pageInfos[i].linkedOutPage = datas[i];
            pageInfos[i].outerCnt = datas[i].size();
        }

        // 총점 구하기
        for (int i = 0; i < pageInfos.length; i++) {

            for (String url : pageInfos[i].linkedOutPage) {
                for (int k = 0; k < pageInfos.length; k++) {
                    if (i == k) continue;
                    if (url.equals(pageInfos[k].name))
                        pageInfos[k].score += (double)pageInfos[i].basicScore / pageInfos[i].linkedOutPage.size();
                }
            }
        }

        // 최대 스코어의 index 구하기
        double max = 0;
        for(int i = 0; i < pageInfos.length; i++) {
            if(max < (pageInfos[i].basicScore + pageInfos[i].score)) {
                max = (pageInfos[i].basicScore + pageInfos[i].score);
                answer = i;
            }
        }

        return answer;
    }

    class PageInfo {
        // 본 페이지 URL
        String name;

        // 외부 URL 개수
        int outerCnt;

        // 기본 점수
        int basicScore;

        // 외부 URL 리스트
        List<String> linkedOutPage;

        // 기본 점수 + 링크 점수
        double score;
    }
}

/* 직접 파싱 (나의 풀이)
class Page {
    double basePoint; // 기본 점수
    double linkPoint = 0; // 링크 점수
    List<String> linkList; // 외부 링크 리스트

    public Page(double basePoint, List<String> linkList) {
        this.basePoint = basePoint;
        this.linkList = linkList;
    }
}

class Node implements Comparable<Node> {
    int index;
    double point;

    public Node(int index, double point) {
        this.index = index;
        this.point = point;
    }

    @Override
    public int compareTo(Node other) {
        if (other.point == this.point) return Integer.compare(this.index, other.index);
        else {
            if (other.point > this.point) return 1;
            else return -1;
        }
    }
}

class Solution {

    public HashMap<String, Integer> indexMap = new HashMap<>(); // <URL, 인덱스>
    public List<Page> pageList = new ArrayList<>(); // 페이지 리스트
    public List<Node> pointList = new ArrayList<>(); // 최종 매칭 점수

    public int solution(String word, String[] pages) {

        // word를 소문자로 변경
        String lowerWord = word.toLowerCase();

        // indexMap에 <url, index> 저장
        for (int i = 0; i < pages.length; i++) {
            String str = pages[i];

            // url 파싱
            String targetString = "<meta property=\"og:url\" content=\"";
            int targetIndex = str.indexOf(targetString);
            StringBuilder sb = new StringBuilder();
            for (int j = targetIndex + targetString.length(); j < str.length(); j++) {
                if (str.charAt(j) == '\"') break;
                else sb.append(str.charAt(j));
            }

            indexMap.put(sb.toString(), i);
        }

        // pages 탐색해서 모든 페이지 객체(기본 점수, 링크 점수, 외부 링크 리스트) 생성
        for (int i = 0; i < pages.length; i++) {

            // 소문자로 변경
            String str = pages[i].toLowerCase();

            // 기본 점수 계산
            double basePoint = 0;
            StringBuilder sb = new StringBuilder(); // 현재 탐색중인 문자열
            for (int j = 0; j < str.length(); j++) {
                // 알파벳이 아니라면
                if (str.charAt(j) - 'a' < 0 || str.charAt(j) - 'a' > 25) {
                    // 지금까지의 문자열이 lowerWord와 같은지 확인 후 리셋
                    if (lowerWord.equals(sb.toString()))
                        basePoint++;
                    sb = new StringBuilder();
                }
                // 알파벳이라면 계속
                else
                    sb.append(str.charAt(j));
            }

            // 외부 링크 정보 저장
            List<String> linkList = new ArrayList<>();
            String targetString = "<a href=\"";
            String[] splits = str.split(targetString);
            for (int j = 1; j < splits.length; j++) {
                StringBuilder sb2 = new StringBuilder();
                for (int k = 0; k < splits[j].length(); k++) {
                    if (splits[j].charAt(k) == '\"') break;
                    else sb2.append(splits[j].charAt(k));
                }
                linkList.add(sb2.toString());
            }

            // 객체 생성
            pageList.add(new Page(basePoint, linkList));
        }

        // 링크 포인트 분배
        for (int i = 0; i < pageList.size(); i++) {
            Page page = pageList.get(i);

            for (String link : page.linkList) {
                if (indexMap.containsKey(link)) {
                    int index = indexMap.get(link);
                    pageList.get(index).linkPoint += (page.basePoint / page.linkList.size());
                }
            }
        }

        // 객체 하나씩 매칭점수 계산
        for (int i = 0; i < pageList.size(); i++) {
            Page page = pageList.get(i);

            // 매칭 점수 최종 계산해서 저장
            double point = page.linkPoint + page.basePoint;
            pointList.add(new Node(i, point));
        }

        // 결과 정렬
        Collections.sort(pointList);

        return pointList.get(0).index;
    }
} */