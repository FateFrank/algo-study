package merge_sort;

import java.util.Scanner;

/**
 * <b>归并排序模板</b><br><br>
 *
 * 给定你一个长度为 n 的整数数列。<br>
 *
 * 请你使用归并排序对这个数列按照从小到大进行排序。<br>
 *
 * 并将排好序的数列按顺序输出。<br><br>
 *
 * <b>输入格式</b><br>
 *
 * 输入共两行，第一行包含整数 n。<br>
 *
 * 第二行包含 n 个整数（所有整数均在 1∼109 范围内），表示整个数列。<br><br>
 *
 * <b>输出格式</b><br>
 *
 * 输出共一行，包含 n 个整数，表示排好序的数列。<br><br>
 *
 * <b>数据范围</b><br>
 *
 * 1≤n≤100000 <br><br>
 *
 * <b>输入样例</b><br>
 * 5 <br>
 * 3 1 2 4 5 <br><br>
 *
 * <b>输出样例</b><br>
 * 1 2 3 4 5
 */
public class AcWing787 {

    public static final int N = 100010;

    public static int n = 0;

    public static int[] q = new int[N];

    public static int[] tmp = new int[N];

    public static void main(String[] args) {
        // 输入数据
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            q[i] = sc.nextInt();
        }

        // 快排
        merge_sort(q, 0, n - 1);

        // 打印排序结果
        for (int i = 0; i < n; i++) {
            System.out.print(q[i] + " ");
        }
    }

    /**
     * 1. 确定分界点x: 可以选择 q[l]、q[(l+r)/2]、q[r]、随机等
     * 2. 递归排序左边和右边
     * 3. 将两个有序数组合并成一个
     *
     * 特性 (3, 4是与快速排序的本质区别)：
     * 1. 基于分治的思想
     * 2. 根据分界点拆分成左右区间
     * 3. 使用双指针，滑动比较左右区间的元素大小，调整元素顺序
     * 4. 递归时，在出栈过程中调整元素顺序
     */
    public static void merge_sort(int[] q, int l, int r) {
        // 递归终止条件
        if (l >= r) return;

        // 确定分界点(中间点)
        int mid = l + r >> 1;

        // 递归排序分界点的左侧数组和右侧数组
        merge_sort(q, l, mid);
        merge_sort(q, mid + 1, r);

        // 将两个有序数组合并成一个，放到临时数组中
        // k指向临时数组头部，i指向左侧数组的头部，j指向右侧数组的头部
        // i、j双指针同时遍历
        int k = 0, i = l, j = mid + 1;
        while (i <= mid && j <= r)
        {
            if (q[i] <= q[j]) tmp[k++] = q[i++];
            else tmp[k++] = q[j++];
        }
        // 看左侧数组、右侧数组，哪一个未遍历完，挂到临时数组的最后
        while (i <= mid) tmp[k++] = q[i++];
        while (j <= r) tmp[k++] = q[j++];

        // 将临时数组中的内容拷贝到q[]
        for (i = l, j = 0; i <= r; i++, j++) q[i] = tmp[j];
    }
}
