package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by zhengxiao on 03/02/2017.
 */
public class QueueReconstructionbyHeight {




    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) -> {
            final int dif = o2[0] - o1[0];
            if (dif == 0) {
                return o1[1] - o2[1];
            }
            return dif;
        });

        int iSorted = 0;

        while(iSorted < people.length) {

            // find insert point (count from left)
            int iPoint = people[iSorted][1];
            for (int j = iSorted - 1; j >= iPoint; j--) {
                swap(people, j, j + 1);
            }

            iSorted++;
        }

        return people;
    }

    private void swap(int[][] people, int j, int i) {
        if (j == i) return;
        int[] person = people[j];
        people[j] = people[i];
        people[i] = person;
    }

    public static void main(String[] args) {
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        System.out.println(toString(new QueueReconstructionbyHeight().reconstructQueue(people)));
    }

    static String toString(int[][] array) {
        StringBuilder buffer = new StringBuilder("[");
        for (int[] ints : array) {
            if (buffer.length() != 1) {
                buffer.append(", ");
            }
            buffer.append(Arrays.toString(ints));
        }
        buffer.append("]");
        return buffer.toString();
    }
}
