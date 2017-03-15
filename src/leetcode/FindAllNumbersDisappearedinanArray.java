package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengxiao on 03/02/2017.
 */
public class FindAllNumbersDisappearedinanArray {

//    public int binaryFind(List<Integer> list, Integer value, int from, int to) {
//        if (from >= to) {
//            return -1;
//        }
//
//        int mid = (from + to) / 2;
//        if (value < list.get(mid)) {
//            return binaryFind(list, value, from, mid - 1);
//        } else if (value > list.get(mid)) {
//            return binaryFind(list, value, mid + 1, to);
//        }
//
//        return mid;
//    }

public int binarySearch(List<Integer> data, int key)
{
     int low = 0;
     int high = data.size() - 1;

     while(high >= low) {
          int middle = (low + high) / 2;
          if(data.get(middle) == key) {
              return middle;
          }
          if(data.get(middle) < key) {
              low = middle + 1;
          }
          if(data.get(middle) > key) {
              high = middle - 1;
          }
     }
     return -1;
}



    public List<Integer> findDisappearedNumbers(int[] nums) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++) {
            result.add(i);
        }
        for (int num : nums) {
            int index = binarySearch(result, num);
            if (index >= 0){
                result.remove(index);
            }

        }
        return result;
    }

    public static void main(String[] args) {
        System.out
                .println(new FindAllNumbersDisappearedinanArray().findDisappearedNumbers(new int[] {4, 3, 2, 7, 8, 2, 3,
                        1}));
    }
}
