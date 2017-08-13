package WaxOMatic;

import java.util.concurrent.TimeUnit;

class WaxOff implements Runnable {
    private Car car;
    public WaxOff(Car c) { car = c; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                car.waitForWaxing();
                System.out.print("Wax Off! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch(InterruptedException e) {
            System.out.print("Exiting via interrupt");
        }
        System.out.print("Ending Wax Off task");
    }
}