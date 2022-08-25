#include <iostream>

using namespace std;

const int N = 1000010;

int n;
int q[N], tmp[N];

void merge_sort(int q[], int l, int r)
{
	// 递归终止条件
	if (l >= r) return;
	
	// 确定分界点(中间点)
	int mid = l + r >> 1;
	
	// 递归排序分界点的左边和右边
	merge_sort(q, l, mid), merge_sort(q, mid + 1, r);
	
	// 将两个有序数组合并成一个，放到临时数组中
	int k = 0, i = l, j = mid + 1;
	while (i <= mid && j <= r) 
		if (q[i] <= q[j]) tmp[k++] = q[i++];
	else tmp[k++] = q[j++];
	while (i <= mid) tmp[k++] = q[i++];
	while (j <= r) tmp[k++] = q[j++];
	
	// 将临时数组中的内容拷贝回来
	for (i = l, j = 0; i <= r; i++, j++) q[i] = tmp[j];
}

int main() 
{
	scanf("%d", &n);
	for (int i = 0; i < n; i++) scanf("%d", &q[i]);
	
	merge_sort(q, 0, n - 1);
	
	for (int i = 0; i < n; i++) printf("%d ", q[i]);
	
	return 0;
}
