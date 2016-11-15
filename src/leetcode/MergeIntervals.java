package leetcode;

import java.util.*;

/**
 * Created by zhengxiao on 15/11/2016.
 */
public class MergeIntervals {

    static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }


    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(2, 3));
        intervals.add(new Interval(5, 5));
        intervals.add(new Interval(2, 2));
        intervals.add(new Interval(3, 4));
        intervals.add(new Interval(3, 4));
        List<Interval> result = new MergeIntervals().merge(intervals);
        for (Interval interval : result) {
            System.out.println(toString(interval));
        }
    }


    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null) {
            intervals = Collections.emptyList();
        }

        TreeMap<Integer, Integer> tree = new TreeMap<>();
        for (Interval interval : intervals) {
            // 发现同样的 key 时，直接融合
            Integer oldEnd = tree.put(interval.start, interval.end);
            if (oldEnd != null && oldEnd > interval.end) {
                // 融合为更大的区间
                tree.put(interval.start, oldEnd);
            }
        }

        ArrayList<Interval> merged = new ArrayList<>();
        Interval unmerged = null;
        for (Map.Entry<Integer, Integer> entry : tree.entrySet()) {
            // last interval
            if (unmerged == null) {
                unmerged = new Interval(entry.getKey(), entry.getValue());
            } else {
                // 比较
                Interval cur = new Interval(entry.getKey(), entry.getValue());
                if (unmerged.end >= cur.start) {
                    // 交错，融合进去
                    unmerged.end = Math.max(entry.getValue(), unmerged.end);
                } else {
                    // 没有交错
                    merged.add(unmerged);
                    unmerged = new Interval(entry.getKey(), entry.getValue());
                }
            }
        }

        // 最后把未被融合的加入进去
        if (unmerged != null) {
            merged.add(unmerged);
        }

        return merged;
    }

    static String toString(Interval i) {
        return "" + i.start + "-" + i.end;
    }

}
