package WaxOMatic;

import java.util.concurrent.TimeUnit;

class WaxOn implements Runnable {
    private Car car;
    public WaxOn(Car c) { car = c; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                System.out.print("Wax On! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch(InterruptedException e) {
            System.out.print("Exiting via interrupt");
        }
        System.out.print("Ending Wax On task");
    }
}