/**
 * Created by tolik on 09.08.2017.
 */
public class Test0 {
    public static void main(String[] args) {
        for (int i = 2; i < 10; i = (i++) + i--, i++) {
            System.out.print(--i);
        }
    }
}
