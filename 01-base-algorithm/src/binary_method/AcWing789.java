package binary_method;

import java.util.Scanner;

public class AcWing789 {

    public static final int N = 100010;

    public static int n = 0;

    public static int m = 0;

    public static int[] q = new int[N];

    public static void main(String[] args) {
        // 输入数据
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            q[i] = sc.nextInt();
        }

        while (m-- > 0) {
            int x = sc.nextInt();
            binary_method(n, x, q);
        }
    }

    public static void binary_method(int n, int x, int[] q) {
        int l = 0, r = n - 1;
        while(l < r) {
            int mid = l + r >> 1;
            if (q[mid] >= x) r = mid;
            else l = mid + 1;
        }

        if (q[l] != x) {
            System.out.println("-1 -1");
        }else {
            System.out.print(l + " ");
            int ll = 0, rr = n - 1;
            while(ll < rr)
            {
                int mid = ll + rr + 1 >> 1;
                if (q[mid] <= x) ll = mid;
                else rr = mid - 1;
            }

            System.out.println(ll);
        }
    }
}
