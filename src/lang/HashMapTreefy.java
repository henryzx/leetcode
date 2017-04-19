package lang;

import java.util.HashMap;

/**
 * HashMap 在 1.8 jdk 里进行了优化。链表元素数量 > 8 时会尝试转换为红黑树
 * Created by zx on 2017/4/4.
 */
public class HashMapTreefy {

    interface Key{}

    public static class Key1 implements Key {
        @Override
        public boolean equals(Object obj) {
            return false;
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }
    public static class Key2 implements Key{
        @Override
        public boolean equals(Object obj) {
            return false;
        }

        @Override
        public int hashCode() {
            return 2;
        }
    }

    public static void main(String[] args) {
        HashMap<Key, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            hashMap.put(new Key1(), i);
            hashMap.put(new Key2(), i);
            System.out.println("" + i);
        }

    }

}
