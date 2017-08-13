package Customer;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by tolik on 13.08.2017.
 */ // Очередь клиентов умеет выводить информацию о своем состоянии:
class CustomerLine extends ArrayBlockingQueue<Customer> {
    public CustomerLine(int maxLineSize) {
        super(maxLineSize);
    }
    public String toString() {
        if(this.size() == 0)
            return "[Empty]";
        StringBuilder result = new StringBuilder();
        for(Customer customer : this)
            result.append(customer);
        return result.toString();
    }
}
