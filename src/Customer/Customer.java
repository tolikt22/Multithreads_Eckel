package Customer;

/**
 * Created by tolik on 13.08.2017.
 */ // Объекты, доступные только для чтения, не требуют синхронизации:
class Customer {
    private final int serviceTime;
    public Customer(int tm) { serviceTime = tm; }
    public int getServiceTime() { return serviceTime; }
    public String toString() {
        return "[" + serviceTime + "]";
    }
}
