package leetcode;

/**
 * Created by zhengxiao on 15/11/2016.
 */
public class PowXN {

    public static void main(String[] args) {
        double result = new PowXN().myPow2(2.00000, -2147483648);
        System.out.println(result);
    }

    /**
     * 递归实现
     * @param x
     * @param n
     * @return
     */
    public double myPow3(double x, int n){
        if (n == 0) {
            return 1.0d;
        }
        if (n < 0) {
            x = 1.0d / x;
            n = -n;
        }

        return x * myPow3(x, n - 1);
    }

    public double myPow2(double x, int n) {
        if (n == 0) {
            return 1.0d;
        }
        if (n < 0) {
            x = 1.0d / x;
            n = -n;
        }

        if (n % 2 == 0 && n / 2 > 1){
            // 可整除
            return myPow2(x * x, n / 2);
        } else {
            return x * myPow2(x, n - 1);
        }


    }

    public double myPow(double x, int n) {

        double result = x;

        // 粗旷
        while (n / 2 > 0) {
            // 可以 double
            result = result * result;
            n = n / 2;
        }

        // 微调
        while (n >= 1) {
            result = result * x;
            n--;
        }

        return result;
    }
}
