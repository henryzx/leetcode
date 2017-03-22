package chenhao;

/**
 * Created by zx on 2017/3/22.
 */
public class DivideRock {

    int min = Integer.MAX_VALUE;
    final int[] rocks;
    int bag1;
    int bag2;

    public DivideRock(int[] rocks) {
        this.rocks = rocks;
    }

    public void divide(int bag1, int bag2, int k) {
        if (k == rocks.length) {
            if (Math.abs(bag1 - bag2) < min) {
                min = Math.abs(bag1 - bag2);
                this.bag1 = bag1;
                this.bag2 = bag2;
            }
            return;
        }
        divide(bag1, bag2 + rocks[k], k + 1);
        divide(bag1 + rocks[k], bag2, k + 1);
    }

    @Override
    public String toString() {
        return String.format("{%d, %d, %d}", bag1, bag2, min);
    }

    public static void main(String[] args) {
        DivideRock divideRock = new DivideRock(new int[]{5,1,1,1,1,1});
        divideRock.divide(0, 0, 0);
        System.out.println(divideRock.toString());
    }

}
