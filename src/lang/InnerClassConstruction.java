package lang;

/**
 * Created by zhengxiao on 15/03/2017.
 */
public class InnerClassConstruction {

    static class OuterClass {

        class InnerClass {

            class InnerInnerClass {

            }
        }
    }

    public static void main(String[] args) {
        // 可以这么写
        new OuterClass().new InnerClass().new InnerInnerClass();

        C c = new C();
        A a = c;
        B b = c;
        a.a();
        b.a();
    }

    interface A {

        void a();

    }

    interface B extends A {

        @Override
        void a();

    }

    static class C implements B {

        @Override
        public void a() {
            System.out.println("C");
        }

    }

}
