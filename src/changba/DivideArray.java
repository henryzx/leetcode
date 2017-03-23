package changba;

import java.util.Arrays;

/**
 * Created by zhengxiao on 11/18/16.
 */
public class DivideArray {

    /**
     * 左 单数, 右 偶数
     *
     * @param ints
     */
    public void divide1(int[] ints) {
        int[] temp = new int[ints.length];
        int temp_l = 0;
        int temp_r = ints.length - 1;

        for (int i = 0; i < ints.length; i++) {
            if (ints[i] % 2 == 0) {
                temp[temp_r--] = ints[i];
            } else {
                temp[temp_l++] = ints[i];
            }
        }

        for (int i = 0; i < temp.length; i++) {
            ints[i] = temp[i];
        }
    }

    /**
     * 把输入数组分拨,左侧奇数,右侧偶数
     * @param ints 输入
     */
    public void divide(int[] ints) {
        if(ints == null){
            throw new IllegalArgumentException("ints is null");
        }

        int pivot = 0; // 分界线, 当前为第一个偶数, 他的左侧都为奇数

        for (int seeker = 0; seeker < ints.length; seeker++) {
            if (ints[seeker] % 2 != 0) {
                // 发现奇数, 移动到 pivot 当前位置
                if (pivot == 0 && seeker == 0){
                    pivot++;
                    continue;
                }

                swap(ints, seeker, pivot++);
            }
        }
    }

    private void swap(int[] ints, int from, int to) {
        int temp = ints[to];
        ints[to] = ints[from];
        ints[from] = temp;
    }

    public static void main(String[] args) {
        int[] input = {2, 2, 2, 2, 1, 1, 1};
        new DivideArray().divide(input);
        System.out.println(Arrays.toString(input));
    }
}
