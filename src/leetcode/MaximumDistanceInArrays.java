package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given m arrays, and each array is sorted in ascending order. Now you can pick up two integers from two
 * different arrays (each array picks one) and calculate the distance. We define the distance between two
 * integers a and b to be their absolute difference |a-b|. Your task is to find the maximum distance.
 * Note:
 * Each given array will have at least 1 number. There will be at least two non-empty arrays.
 * The total number of the integers in all the m arrays will be in the range of [2, 10000].
 * The integers in the m arrays will be in the range of [-10000, 10000].
 * Created by zx on 2017/6/22.
 */
public class MaximumDistanceInArrays {
    public int maxDistance(List<List<Integer>> arrays) {

        /*
                当作取值即可，找到最小的first index。找到最大的last index 即可
                ------------
                  ---------
                   ---
                        --


                     如果是
                     ----------------
                         -------
        */

        int res = 0, min = arrays.get(0).get(0), max = arrays.get(0).get(arrays.get(0).size() - 1);
        for (int i = 1; i < arrays.size(); ++i) {
            res = Math.max(res, Math.max(Math.abs(arrays.get(i).get(0) - max), Math.abs(arrays.get(i).get(arrays.get(i).size() - 1) - min)));
            max = Math.max(max, arrays.get(i).get(arrays.get(i).size() - 1));
            min = Math.min(min, arrays.get(i).get(0));
        }

        return res;
    }

    public static void main(String[] args) {
        ArrayList<List<Integer>> arrays = new ArrayList<>();
        ArrayList<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        ArrayList<Integer> b = new ArrayList<>();
        b.add(4);
        b.add(5);
        ArrayList<Integer> c = new ArrayList<>();
        c.add(1);
        c.add(2);
        c.add(3);
        arrays.add(a);
        arrays.add(b);
        arrays.add(c);
        int max = new MaximumDistanceInArrays().maxDistance(arrays);
        System.out.println(max);
    }
}
