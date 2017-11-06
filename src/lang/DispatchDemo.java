package lang;

public class DispatchDemo {

    static class A{
        public void yo(){
            System.out.println("yo A");
        }
    }

    static class B extends A{
        @Override
        public void yo() {
            System.out.println("yo B");
        }
    }

    public void hi(A a){
        System.out.println("hi A");
    }

    public void hi(B b){
        System.out.println("hi B");
    }

    public static void main(String[] args) {
        A b = new B();
        new DispatchDemo().hi(b); // outputs 'hi A', static override resolution happens (by compiler)
        b.yo(); // outputs 'yo B', dynamic dispatch happens (by vm)
    }
}
