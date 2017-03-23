package lang;

/**
 * Created by zhengxiao on 15/03/2017.
 */
public class InnerClassConstruction {

    static class OuterClass{

        class InnerClass{

        }
    }



    public static void main(String[] args) {
        // 可以这么写
        new OuterClass().new InnerClass();
    }
}
