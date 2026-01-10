import java.io.*;
import java.util.*;


class Point implements Comparable<Point> {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public int compareTo(Point o) {

        if (this.x > o.x)
            return 1;
        else if (this.x == o.x) {
            if (this.y > o.y) return 1;
            else if (this.y == o.y) return 0;
            else return -1;
        } else
            return -1;
    }
}


public class Main {

    static int N;
    static List<Point> points = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            points.add(new Point(x, y));
        }

        Collections.sort(points);

        for (Point p : points) {
            System.out.println(p.x + " " + p.y);
        }

    }
}
