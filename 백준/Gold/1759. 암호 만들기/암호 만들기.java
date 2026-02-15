import java.io.*;
import java.util.*;

/*
    [문제 요약]
    C가지의 문자들 중 L개의 문자를 뽑아 만들어질 수 있는 모든 암호 출력
    (단, 최소 한 개의 모음과 최소 두개의 자음으로 이루어짐 , 암호는 알파벳순)

    [문제 조건]
    3 ≤ L ≤ C ≤ 15

    [고민]
    모음, 자음 조건을 어떻게 설정하지 ..
    일단 뽑고 모음, 자음 조건 충족하면 저장할까..?
    1. 입력 받고 -> 문자 정렬하기
    2. 조합 케이스 뽑음 (List에 저장?)
    3. 해당 조합이 모음, 자음 조건 만족하는지 체크
    4. 출력

 */


public class Main {
    static int L, C;    //L: 암호의 길이, C:문자의 개수
    static char[] charList;
    static List<Character> combCase;

    static Set<Character> vowelSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //1. 입력
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        String[] input = br.readLine().split(" ");
        charList = new char[C];

        for (int i = 0; i < C; i++) {
            charList[i] = input[i].charAt(0);
        }
        Arrays.sort(charList);

        // 2. 모음  저장
        vowelSet = new HashSet<>();
        vowelSet.add('a');
        vowelSet.add('e');
        vowelSet.add('i');
        vowelSet.add('o');
        vowelSet.add('u');

        // 3. 조합 구하기
        combCase = new ArrayList<>();
        comb(0, 0);


    }//main

    // 조합 경우 만들기
    static void comb(int cur, int depth) {

        if (depth == L) {
            checkValid();
            return;
        }

        for (int i = cur; i < C; i++) {
            combCase.add(charList[i]);
            comb(i + 1, depth + 1);
            combCase.remove(combCase.size() - 1);
        }

    }

    // 유효한 조합인지 체크 -> 출력
    static void checkValid() {
        int vowelCnt = 0;
        int consonantCnt = 0;

        // 1. 모음, 자음 개수 세기
        for (Character c : combCase) {
            if (vowelSet.contains(c)) {
                vowelCnt++;
            } else {
                consonantCnt++;
            }
        }

        // 2. 조건 만족하면 출력
        if (vowelCnt >= 1 && consonantCnt >= 2) {
            for (Character c : combCase) {
                System.out.print(c);
            }
            System.out.println();
        }

    }


}
