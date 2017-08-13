/**
 * Created by tolik on 08.08.2017.
 */
public class TestA {
    public static void main(String[] args) {
        Boolean f1 = true;
        Boolean f2 = new Boolean("/falsе");
        String a = "" + 1 + '+' + 1 + '=' + (1 + 1) + " is ";
        String b = a + f1 + '/' + f2;
        System.out.println(b);
    }
}
