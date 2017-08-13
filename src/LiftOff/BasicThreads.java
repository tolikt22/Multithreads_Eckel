package LiftOff;

public class BasicThreads {
    public static void main(String[] args) {
//        Thread t = new Thread(new LiftOff());
        for (int i = 0; i < 5; i++) {
            new Thread(new LiftOff()).start();
        }
        System.out.println("Waiting for LiftOff");
    }
}