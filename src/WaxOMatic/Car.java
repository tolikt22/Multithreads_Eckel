package WaxOMatic;
class Car {
    private boolean waxOn = false;
    public synchronized void waxed() {
        waxOn = true; // Готово к обработке
        notifyAll();
    }
    public synchronized void buffed() {
        waxOn = false; // Готово к нанесению очередного слоя
        notifyAll();
    }
    public synchronized void waitForWaxing()
            throws InterruptedException {
        while(waxOn == false)
            wait();
    }
    public synchronized void waitForBuffing()
            throws InterruptedException {
        while(waxOn == true)
            wait();
    }
}