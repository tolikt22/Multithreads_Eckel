
public class Test {
    public static void main(String[] args) {
Object obj = null;
        String str = new String("str");
        str = (String) obj;
        obj = new String("obj");
        System.out.print(obj + ", " + str);
    }
}
