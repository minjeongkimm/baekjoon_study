import java.io.*;
import java.util.*;

/*
    [문제 요약]
    K개의 글자를 가르칠 때, 학생들이 읽을 수 있는 단어 개수의 최댓값

    [문제 조건]
    N = 단어의 개수, K = 가르칠 수 있는 글자 수
    1 <= N <= 50,  0 <= K <= 26,  8 <= 단어의 길이 <= 15
    단어는 중복 불가
    모든 단어는 anta 로 시작, tica 로 종료, 모두 소문자

    [고민]
    a, n, t, i, c 는 알아야 읽을 수 있음 -> 즉 K<5 이면 읽을 수 X
    Set을 만들어서 읽을 수 있는 글자를 저장해야 하나
    일단 그러면 K 개수 체크 먼저 하고, 나머지 단어들은 anta, tica 제거하고, 나머지 글자들만 추출?

    a~z 까지 a,c,i,n,t 는 제외하고 21개중에서 K-5개를 뽑음 (조합) -> 다 뽑으면 읽을 수 있는 단어 수 체크
    비트마스킹 ...

 */

public class Main {
    static int N, K;                    //단어의 수 // 글자의 수
    static List<String> wordList;       //단어 리스트
    static List<Integer> wordMaskList;  //단어 마스크 리스트

    static int mask;                    //마스크 (읽을 수 있는 글자)

    static int ans;         //정답 변수
    static int cnt;         //카운팅 변수

    // 21개 알파벳 배열 (a, c, i, n, t 제외)
    static Character[] letterList = {'b', 'd', 'e', 'f', 'g',
            'h', 'j', 'k', 'l', 'm',
            'o', 'p', 'q', 'r', 's',
            'u', 'v', 'w', 'x', 'y', 'z'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        wordList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            wordList.add(word);
        }

        //1. 조기종료 조건 (기본 글자도 못 읽거나 모든 글자 다 읽는 경우)
        if (K < 5) {
            System.out.println(0);
            return;
        }else if(K == 26){
            System.out.println(N);
            return;
        }

        // 2. K >= 5이면 필수 글자 마스크에 추가 ('a', 'c', 'i', 'n', 't')
        mask = mask | 1 << (int) ('a' - 'a');
        mask = mask | 1 << (int) ('c' - 'a');
        mask = mask | 1 << (int) ('i' - 'a');
        mask = mask | 1 << (int) ('n' - 'a');
        mask = mask | 1 << (int) ('t' - 'a');


        // 2. 단어 리스트 저장 (마스크 형태로 저장)
        wordMaskList = new ArrayList<>();
        for (String word : wordList) {
            word = word.substring(4, word.length() - 4);

            int wordMask = 0;
            for (int j = 0; j < word.length(); j++) {
                wordMask |= 1 << (int) (word.charAt(j) - 'a');
            }
            wordMaskList.add(wordMask);
        }

        //2. 백트래킹으로 경우의 수 구하기
        comb(0, 0);

        //3. 정답 출력
        System.out.println(ans);


    }//main


    //조합 구하기
    static void comb(int startIdx, int depth) {

        if (depth == K - 5) {
            countReadable(mask);
            return;
        }

        for (int i = startIdx; i < 21; i++) {
            mask |= 1 << (int) (letterList[i] - 'a');
            comb(i+1, depth+1);
            mask &= ~(1 << letterList[i] - 'a');
        }

    }

    //읽을 수 있는 단어 수 구하기
    static void countReadable(int mask) {
        cnt = 0;
        for (Integer i : wordMaskList) {
            if ((i & mask) == i) {
                cnt++;
            }
        }
        ans = Math.max(cnt, ans);
        return;
    }

}
