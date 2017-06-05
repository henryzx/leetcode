package youku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Created by zx on 2017/6/5.
 */
public class FindPrime {

    /**
     * 找到小于 n 的所有质数
     * @param n 小于 n 的质数
     * @return 质数的个数
     */
    public static long findPrimeCountTill(long n){
        List<Long> primes = new ArrayList<>();

        outer: for (long i = 2; i <= n; ++i){

            for(Long prime: primes) {
                if (i % prime == 0) {
                    continue outer;
                }
            }

            // found a prime
            primes.add(i);
        }

        System.out.println(Arrays.toString(primes.toArray()));

        return primes.size();

    }

    public static void main(String[] args) {
        System.out.println(findPrimeCountTill(100000));
    }
}
