/**
 * Created by tolik on 08.08.2017.
 */
public class Test3 {
    public static void main(String args[]) {
        String  s = new String("ssssss");
        StringBuffer sb = new StringBuffer("bbbbbb");
        s.concat("-аaa");
        sb.append("-aаa");
        System.out.println(s);
        System.out.println(sb);
    }
}
