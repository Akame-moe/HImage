package sometest;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by gentle-hu on 2018/7/29 9:43.
 * Email:me@gentlehu.com
 */
public class Test {

    public static void main(String[] args) {
        new Test().test();
    }
    public void test(){
        try{

            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            Unsafe unsafe = (Unsafe) f.get(null);
            System.out.println(unsafe);
        }catch (Exception e){

        }
    }
}
