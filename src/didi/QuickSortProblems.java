package didi;

import java.util.Arrays;

/**
 * Created by zhengxiao on 23/03/2017.
 */
public class QuickSortProblems {

    static void quickSort(int[] arr){
        quickSortInternal(arr, 0, arr.length - 1);
    }

    /**
     *
     * @param arr
     * @param l
     * @param r
     */
    private static void quickSortInternal(int[] arr, int l, int r){
        // terminate condition
        if (l > r) return;
        // find pivot
        // rearrange sort
        int pivot = arrange(arr, l, r);

        // recursive sort the left and right part
        quickSortInternal(arr, l, pivot - 1);
        quickSortInternal(arr, pivot + 1, r);

    }

    private static int arrange(int[] arr, int l, int r) {
        // pick this one
        int pick = (int)(Math.random() * (r - l)) + l;
        swap(arr, pick, l);

        // find pivot, let it be the l
        int p = l;
        int pValue = arr[l];

        // backward iterate
        while(p < r) {
            if (arr[r] < pValue){
                // swap these two
                swap(arr, p, r);
                swap(arr, p + 1, r);
                p++;
            } else {
                r --;
            }
        }

        // place pValue back to p
        arr[p] = pValue;

        return p;
    }

    private static void swap(int[] arr, int from, int to) {
        if(from == to) return;
        int temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5,2,6,4,0};
        System.out.println(Arrays.toString(arr));
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
