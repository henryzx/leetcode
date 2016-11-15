package leetcode;

/**
 * Created by zhengxiao on 15/11/2016.
 */
public class ImplementStrStr {

    public static void main(String[] args) {
        int result = new ImplementStrStr().strStr("mississippi", "issipi");
        System.out.println(result);
        if(result != -1){
            throw new IllegalStateException("wrong");
        }
    }


    public int strStr(String haystack, String needle) {
        if(haystack.length() < needle.length()){
            return -1;
        }

        if (needle.length() == 0){
            return 0;
        }

        outer: for (int hayI = 0; hayI < haystack.length(); hayI++){

            for (int needleI = 0; needleI < needle.length(); needleI++) {
                if (hayI + needleI >= haystack.length() || needle.charAt(needleI) != haystack.charAt(hayI + needleI)){
                    continue outer;
                }
            }

            // reach the end
            // found
            return hayI;
        }

        return -1;
    }




}
