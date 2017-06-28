package lang;

/**
 * Created by zx on 2017/6/28.
 */
public class SelfRecursiveDepend {

    static class Self{
        Self self;
        Self(){
            self = new Self();
        }
    }

    static class Them{
        static boolean isInConstruction = false;
        Them them;
        Them(){

        }

        void crash(){
            if (!isInConstruction) {
                isInConstruction = true;
                if (them == null) {
                    them = new Them();
                }
                them.crash();
            }
            isInConstruction = false;
        }
    }

    public static void main(String[] args) {
        System.out.println("add a static flag will prevent overflow");
        new Them().crash();

        System.out.println("java will try construct such one");
        new Self();
    }
}
