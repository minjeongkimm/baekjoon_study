import java.io.*;

public class Main {
    static String[] group, nums;    //각각 -, + 로 쪼갠 그룹
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        //1. - 로 쪼갠 그룹 
        group = s.split("-");

        for (int i = 0; i < group.length; i++) {
            //2. -로 쪼개진 그룹을 다시 +로 쪼갬
            nums = group[i].split("\\+");

            //3. 쪼개진 숫자들 더함
            int sum = 0;
            for (String num : nums) {
                sum += Integer.parseInt(num);
            }
            //4. 더한 값 ans에 갱신
            if (i == 0) ans = sum;
            else ans -= sum;

        }

        System.out.println(ans);

    }
}
