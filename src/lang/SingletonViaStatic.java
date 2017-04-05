package lang;

/**
 * 使用 singletonHolder 实现单例。优雅的方案。只要避免了在访问 Singleton 静态成员时初始化的问题，将这个过程进一步延迟。
 * Created by zhengxiao on 05/04/2017.
 */
public class SingletonViaStatic {

    static class Singleton{

        private Singleton(){
            System.out.println("Singleton()");
        }

        public static String NAME = "Singleton's static name";

        static{
            System.out.println("Singleton.static{}");
        }

        static class SingletonHolder {
            static Singleton instance = new Singleton();

            static{
                System.out.println("SingletonHolder.static{}");
            }
        }

        public static Singleton getInstance(){
            return SingletonHolder.instance;
        }
    }

    public static void main(String[] args) {
        System.out.println("main()");
        System.out.println(Singleton.NAME);
        try { Thread.sleep(1000L); } catch (InterruptedException ignored) {}

        System.out.println("main() will iterate");
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(() -> System.out.println(Singleton.getInstance()));
            t.start();
        }
    }
}
