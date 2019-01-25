package com.example.utils.algorithm.impl;

import com.example.utils.algorithm.IArraySort;
import com.example.utils.json.JacksonUtil;

import java.util.Arrays;

/**
 * @Description 经典排序算法
 * @Author blake
 * @Date 2019-01-10 10:57
 * @Version 1.0
 */
public class CArraySort implements IArraySort {

    /**
     * @return int[]
     * @throws
     * @description 插入排序
     * @params [sourceArray]
     */
    @Override
    public int[] insertSort(int[] sourceArray) throws Exception {

        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        // 从下标等于1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，将其视为有序的
        for (int i = 1; i < arr.length; i++) {

            // 记录要插入的数据
            int tmp = arr[i];

            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }

            // 存在比其小的数，插入
            if (j != i) {
                arr[j] = tmp;
            }

        }

        return arr;
    }

    /**
     * @return int[]
     * @throws
     * @description 希尔排序：不断选择增量，进行交换，接着对每一分组进行插入排序，最后，总体进行一轮插入排序。
     * @params [sourceArray]
     */
    @Override
    public int[] shellSort(int[] sourceArray) throws Exception {


        return new int[0];
    }

    public static void main(String[] args) throws Exception {

        CArraySort cArraySort = new CArraySort();

        int[] arr = new int[]{3, 5, 4, 7, 2};

        int[] sortRes = cArraySort.insertSort(arr);

        System.out.println("未排序前：" + JacksonUtil.toJSon(arr));
        System.out.println("完成排序后：" + JacksonUtil.toJSon(sortRes));
    }

}
