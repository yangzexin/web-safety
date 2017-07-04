package tests.safety.util;

/**
 * Created by yangzexin189 on 2017-07-04.
 */
public class MathUtils {
    private static int mod(int i, int n) {
        return i - i / n * n;
    }

    public static void main(String[] args) {
        int r = mod(19, 4);
        System.out.println(r);
    }
}
