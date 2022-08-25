#include <iostream>

using namespace std;

typedef long long LL;

const int N = 100010;

int n, amount;
int q[N], tmp[N];

/**
 * 使用分治法解决问题
 * 
 * 我们将序列从中间分开，将逆序对分成三类：
 *      两个元素都在左边；
 *      两个元素都在右边；
 *      两个元素一个在左一个在右；
 * 
 * 因此这就是我们算法的大致框架：
 * 计算逆序对的数量（序列）：
 *      1. 递归算左边的；
 *      2. 递归算右边的；
 *      3. 算一个在左一个在右的；
 *      4. 把他们加到到一起。
 * 
 * 这个时候我们注意到一个很重要的性质：
 *      左右半边的元素在各自任意调换顺序，是不影响第三步计数的，因此我们可以数完就给它排序。
 *      这么做的好处在于，如果序列是有序的，会让第三步计数很容易
 * 
 * 如果无序暴力数的话这一步是O(n^2)的
 * 
 * 比如序列是这样的 4 5 6 | 1 2 3
 * 当你发现 4 比 3 大的时候，也就是说右边最大的元素都小于左边最小的元素，
 * 那么左边剩下的5和6都必然比右边的所有元素大，因此就可以不用数5和6的情形了，
 * 直接分别加上右半边的元素个数就可以了，这一步就降低到了O(n)
 **/
LL merge_sort(int q[], int l, int r) 
{
	// 递归终止条件
	if (l >= r) return 0;
	
	// 确定分界点(中间点)
	int mid = (l + r) >> 1;
	
	// 递归排序分界点的左侧数据和右侧数组
	LL res = merge_sort(q, l, mid) + merge_sort(q, mid + 1, r);
	
	// 将两个有序数组合并成一个，放到临时数组tmp中
	// k指向临时数组头部，i指向左侧数组的头部，j指向右侧数组的头部
	// i、j双指针同时遍历
	int k = 0, i = l, j = mid + 1;
	while (i <= mid && j <= r) 
	{
		if (q[i] <= q[j]) tmp[k++] = q[i++];
		else 
		{
			// 此时必定满足: i < j && q[i] > q[j]
			res += mid - i + 1;
			tmp[k++] = q[j++];
		}
	}
	// 看左侧数组、右侧数组，哪一个未遍历完，挂到临时数组的最后
	while (i <= mid) tmp[k++] = q[i++];
	while (j <= r) tmp[k++] = q[j++];
	
	// 将临时数组中的内容拷贝到q[]
	for (i = l, j = 0; i <= r; i ++, j ++ ) q[i] = tmp[j];
	
	return res;
}

int main() 
{
	scanf("%d", &n);
	for (int i = 0; i < n; i++) scanf("%d", &q[i]);
	
	printf("%lld", merge_sort(q, 0, n - 1));
}
