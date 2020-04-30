package Toolee;

import java.util.Arrays;

/**
 * @author jing
 * @create 2020-03-30-17:49
 */
public class SortDemo {
    public static void main(String[] args) {
        int[] ints = {3, 1, 7, 8, 9, 2, 4, 6, 0};
//        int[] ints = {1, 2, 3, 4, 6, 7, 8, 9, 10};
//        int[] sort = BubbleSort(ints);
//        int[] sort = selectSort(ints);
//        int[] sort = insertSort(ints);
//        int[] sort = shellSort(ints);
        int[] sort = QuickSort(ints, 0, ints.length - 1);
//        int[] sort = mergeSort(ints, 0, ints.length - 1);
//        int[] sort = radixSort(ints, 100);
//        int[] sort = heapSort(ints);
//        System.out.println(Arrays.toString(sort));
//        int num = binSearch(3, ints, 0, ints.length - 1);
//        System.out.println(num);
    }

    public static int[] BubbleSort(int[] args) {
//        O(n²)
        int temp = 0;
        for (int i = 0; i < args.length - 1; i++) {
            for (int j = 0; j < args.length - 1 - i; j++) {
                if (args[j] > args[j + 1]) {
                    temp = args[j];
                    args[j] = args[j + 1];
                    args[j + 1] = temp;
                }
            }
        }
        return args;
    }

    public static int[] selectSort(int[] arr) {
//        O(n²)
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i; // 用来记录最小值的索引位置，默认值为i
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j; // 遍历 i+1~length 的值，找到其中最小值的位置
                }
            }
            // 交换当前索引 i 和最小值索引 minIndex 两处的值
            if (i != minIndex) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
            // 执行完一次循环，当前索引 i 处的值为最小值，直到循环结束即可完成排序
        }
        return arr;
    }

    public static int[] insertSort(int[] elem) {
//        O(n²)
        int i, j;
        for (i = 2; i < elem.length; i++) {
            if (elem[i] < elem[i - 1]) {   //需要将elem[i]插入到有序子表
                elem[0] = elem[i];  //设置哨兵
                for (j = i - 1; elem[j] > elem[0]; j--) {
                    elem[j + 1] = elem[j];   //记录后移
                }
                elem[j + 1] = elem[0];   //插入到正确位置
            }
        }
        return elem;
    }

    public static int[] shellSort(int[] ins) {
//      O(nLog n)
        int n = ins.length;
        int gap = n / 2;
        while (gap > 0) {
            for (int j = gap; j < n; j++) {
                int i = j;
                while (i >= gap && ins[i - gap] > ins[i]) {
                    int temp = ins[i - gap] + ins[i];
                    ins[i - gap] = temp - ins[i - gap];
                    ins[i] = temp - ins[i - gap];
                    i -= gap;
                }
            }
            gap = gap / 2;
        }
        return ins;
    }

    public static int[] QuickSort(int[] arr, int low, int high) {
//      O(nLog n)
        if (low > high) return null;
        int i = low, j = high, temp = arr[low], t;
        //temp就是基准位
        while (i < j) {
            while (temp <= arr[j] && i < j) {//先看右边，依次往左递减
                j--;
            }
            while (temp >= arr[i] && i < j) {//再看左边，依次往右递增
                i++;
            }
            if (i < j) {//如果满足条件则交换
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        QuickSort(arr, low, j - 1);//递归调用左半数组
        QuickSort(arr, j + 1, high);//递归调用右半数组
        return arr;
    }

    //两路归并算法，两个排好序的子序列合并为一个子序列
    public static void merge(int[] a, int left, int mid, int right) {
        int[] tmp = new int[a.length];//辅助数组
        int p1 = left, p2 = mid + 1, k = left;//p1、p2是检测指针，k是存放指针
        while (p1 <= mid && p2 <= right) {
            if (a[p1] <= a[p2])
                tmp[k++] = a[p1++];
            else
                tmp[k++] = a[p2++];
        }
        while (p1 <= mid) tmp[k++] = a[p1++];//如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
        while (p2 <= right) tmp[k++] = a[p2++];//同上
        //复制回原素组
        for (int i = left; i <= right; i++)
            a[i] = tmp[i];
    }

    public static int[] mergeSort(int[] a, int start, int end) {
        //      O(nLog n)
        if (start < end) {//当子序列中只有一个元素时结束递归
            int mid = (start + end) / 2;//划分子序列
            mergeSort(a, start, mid);//对左侧子序列进行递归排序
            mergeSort(a, mid + 1, end);//对右侧子序列进行递归排序
            merge(a, start, mid, end);//合并
        }
        return a;
    }

    public static int[] radixSort(int[] array, int d) {
        //      O(n*k)
        int n = 1;//代表位数对应的数：1,10,100...
        int k = 0;//保存每一位排序后的结果用于下一位的排序输入
        int length = array.length;
        int[][] bucket = new int[10][length];//排序桶用于保存每次排序后的结果，这一位上排序结果相同的数字放在同一个桶里
        int[] order = new int[length];//用于保存每个桶里有多少个数字
        while (n < d) {
            for (int num : array) //将数组array里的每个数字放在相应的桶里
            {
                int digit = (num / n) % 10;
                bucket[digit][order[digit]] = num;
                order[digit]++;
            }
            for (int i = 0; i < length; i++)//将前一个循环生成的桶里的数据覆盖到原数组中用于保存这一位的排序结果
            {
                if (order[i] != 0)//这个桶里有数据，从上到下遍历这个桶并将数据保存到原数组中
                {
                    for (int j = 0; j < order[i]; j++) {
                        array[k] = bucket[i][j];
                        k++;
                    }
                }
                order[i] = 0;//将桶里计数器置0，用于下一次位排序
            }
            n *= 10;
            k = 0;//将k置0，用于下一轮保存位排序结果
        }
        return array;
    }
    public static int [] heapSort(int[] arr) {
//        o(n*log n)
        //创建堆
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }

        //调整堆结构+交换堆顶元素与末尾元素
        for (int i = arr.length - 1; i > 0; i--) {
            //将堆顶元素与末尾元素进行交换
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;

            //重新对堆进行调整
            adjustHeap(arr, 0, i);
        }
        return arr;
    }

    /**
     * 调整堆
     * @param arr 待排序列
     * @param parent 父节点
     * @param length 待排序列尾元素索引
     */
    public static void adjustHeap(int[] arr, int parent, int length) {
        //将temp作为父节点
        int temp = arr[parent];
        //左孩子
        int lChild = 2 * parent + 1;

        while (lChild < length) {
            //右孩子
            int rChild = lChild + 1;
            // 如果有右孩子结点，并且右孩子结点的值大于左孩子结点，则选取右孩子结点
            if (rChild < length && arr[lChild] < arr[rChild]) {
                lChild++;
            }

            // 如果父结点的值已经大于孩子结点的值，则直接结束
            if (temp >= arr[lChild]) {
                break;
            }

            // 把孩子结点的值赋给父结点
            arr[parent] = arr[lChild];

            //选取孩子结点的左孩子结点,继续向下筛选
            parent = lChild;
            lChild = 2 * lChild + 1;
        }
        arr[parent] = temp;
    }

    public static int binSearch(int key, int[] array, int low, int high) {
        //防越界
        if (key < array[low] || key > array[high] || low > high) return -1;
        int middle = (low + high) / 2;
        if (array[middle] > key) {
            //大于关键字
            return binSearch(key, array, low, middle - 1);
        } else if (array[middle] < key) {
            //小于关键字
            return binSearch(key, array, middle + 1, high);
        } else {
            return middle;
        }
    }
}
