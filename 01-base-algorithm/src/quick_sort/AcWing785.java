package quick_sort;

import java.util.Scanner;

/**
 * <b>快速排序模板</b> <br><br>
 *
 * 给定你一个长度为 n 的整数数列。<br>
 *
 * 请你使用快速排序对这个数列按照从小到大进行排序。<br>
 *
 * 并将排好序的数列按顺序输出。<br><br>
 *
 * <b>输入格式</b><br>
 * 输入共两行，第一行包含整数 n。<br>
 *
 * 第二行包含 n 个整数（所有整数均在 1∼10e9 范围内），表示整个数列。<br><br>
 *
 * <b>输出格式</b><br>
 * 输出共一行，包含 n 个整数，表示排好序的数列。<br><br>
 *
 * <b>数据范围</b><br>
 * 1≤n≤100000<br><br>
 *
 * <b>输入样例：</b><br>
 * 5<br>
 * 3 1 2 4 5<br><br>
 *
 * <b>输出样例：</b><br>
 * 1 2 3 4 5<br>
 */
public class AcWing785 {

    public static final int N = 100010;

    public static int n = 0;

    public static int[] q = new int[N];

    public static void main(String[] args) {
        // 输入数据
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            q[i] = sc.nextInt();
        }

        // 快排
        quick_sort(q, 0, n - 1);

        // 打印排序结果
        for (int i = 0; i < n; i++) {
            System.out.print(q[i] + " ");
        }
    }

    /**
     * 1. 确定分界点x: 可以选择 q[l]、q[(l+r)/2]、q[r]、随机等
     * 2. 调整区间：使左半边<=x，右半边>=x
     * 3. 递归处理左右两段，分别排序
     *
     * 特性 (3, 4是与归并排序的本质区别)：
     * 1. 基于分治的思想
     * 2. 根据分界点拆分成左右区间
     * 3. 使用双指针，左右区间各自与分界点x比较，而不是左右区间的元素比较大小，只是左右区间的元素进行值交换
     * 4. 递归时，在压栈过程中调整顺序
     */
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
            // 交换q[i]和q[j]，来保证q[l, i]<=x, q[j, r]>=x
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
