import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static Set<String> set = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }

        List<String> list = new ArrayList<>(set);


        Collections.sort(list, (a, b) -> {
            if(a.length() != b.length())
                return a.length()-b.length();
            return a.compareTo(b);
        });

        for(String s : list){
            System.out.println(s);
        }

    }
}