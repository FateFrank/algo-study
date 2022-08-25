package quick_sort;

import java.util.Scanner;

/**
 * <b>第k个数</b><br><br>
 *
 * 给定一个长度为 n 的整数数列，以及一个整数 k，请用快速选择算法求出数列从小到大排序后的第 k 个数。<br><br>
 *
 * <b>输入格式</b><br>
 * 第一行包含两个整数 n 和 k。<br>
 * 第二行包含 n 个整数（所有整数均在 1∼10e9 范围内），表示整数数列。<br><br>
 *
 * <b>输出格式</b><br>
 * 输出一个整数，表示数列的第 k 小数。<br><br>
 *
 * <b>数据范围</b><br>
 * 1≤n≤100000, <br>
 * 1≤k≤n <br><br>
 *
 * <b>输入样例</b><br>
 * 5 3<br>
 * 2 4 1 5 3<br><br>
 *
 * <b>输出样例</b><br>
 * 3
 */
public class AcWing786 {

    public static final int N = 100010;

    public static int n = 0, k = 0;

    public static int[] q = new int[N];

    public static void main(String[] args) {
        // 输入数据
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            q[i] = sc.nextInt();
        }

        // 快排
        quick_sort(q, 0, n - 1);

        // 打印排序结果
        System.out.println(q[k - 1]);
    }

    public static void quick_sort(int[] q, int l, int r) {
        // 递归的终止条件
        if (l >= r) return;

        // 确定分界点x
        int x = q[(l + r) / 2], i = l - 1, j = r + 1;

        // 调整区间,使左侧数据<=x,右侧数据>=x (左右双指针)
        while(i < j) {
            do i++; while (q[i] < x);
            do j--; while (q[j] > x);
            // i<j代表左右指针未相遇
            // 此时必定q[i]>=x, q[j]<=x
            // 交换q[i]和q[j]，来保证q[l, i]<=x，q[j, r]>=x
            if (i < j) {
                int temp = q[i];
                q[i] = q[j];
                q[j] = temp;
            }
        }

        // 递归处理左右两段
        quick_sort(q, l, j);
        quick_sort(q, j + 1, r);
    }
}
