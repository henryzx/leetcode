package leetcode;

/**
 * Created by zhengxiao on 15/11/2016.
 */
public class StringToInteger {

    public int myAtoi(String str) {
        // 搜索开始位置
        // 读入符号（可选）
        // 读入数字
        // 读入小数点(可选)
        // 读入数字(可选)
        final int length = str.length();

        int l = 0;
        while(l < length && Character.isSpaceChar(str.charAt(l))){
            l++;
        }

        boolean isNegative = false;
        if(l < length && str.charAt(l) == '-') {
            isNegative = true;
            l++;
        }else if (l < length && str.charAt(l) == '+'){
            l++; //skip
        }

        int num = 0;

        while(l < length && str.charAt(l) <= '9' && str.charAt(l) >= '0'){
            // test if overflow
            int digit = (int)(str.charAt(l) - '0');
            if(!isNegative) {
                if (num > Integer.MAX_VALUE / 10 || num == Integer.MAX_VALUE / 10 && digit > 7){
                    // overflow, use max value
                    return Integer.MAX_VALUE;
                }
            }else {
                if (num > Integer.MAX_VALUE / 10 || num == Integer.MAX_VALUE / 10 && digit > 8){
                    return Integer.MIN_VALUE;
                }
            }
            num = num * 10 + digit;
            l++;
        }

        if (isNegative){
            num = -num;
        }

        return num;

    }

    public static void main(String[] args) {
        int num = new StringToInteger().myAtoi("      -11919730356x");
        System.out.println(num);
        if (num != -2147483648) throw new IllegalStateException("wrong");
    }
}
