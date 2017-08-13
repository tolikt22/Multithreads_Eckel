package Customer;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

class TellerManager implements Runnable {
    private ExecutorService exec;
    private CustomerLine customers;
    private PriorityQueue<Teller> workingTellers =
            new PriorityQueue<Teller>();
    private Queue<Teller> tellersDoingOtherThings =
            new LinkedList<Teller>();
    private int adjustmentPeriod;
    private static Random rand = new Random(47);
    public TellerManager(ExecutorService e,
                         CustomerLine customers, int adjustmentPeriod) {
        exec = e;
        this.customers = customers;
        this.adjustmentPeriod = adjustmentPeriod;
        // Начинаем с одного кассира:
        Teller teller = new Teller(customers);
        exec.execute(teller);
        workingTellers.add(teller);
    }
    public void adjustTellerNumber() {
        // Фактически это система управления. Регулировка числовых
        // параметров позволяет выявить проблемы стабильности
        // в механизме управления.
        // Если очередь слишком длинна, добавить другого кассира:
        if(customers.size() / workingTellers.size() > 2) {
            // Если кассиры отдыхают или заняты
            // другими делами, вернуть одного из них:
            if(tellersDoingOtherThings.size() > 0) {
                Teller teller = tellersDoingOtherThings.remove();
                teller.serveCustomerLine();
                workingTellers.offer(teller);
                return;
            }
            // Иначе создаем (нанимаем) нового кассира
            Teller teller = new Teller(customers);
            exec.execute(teller);
            workingTellers.add(teller);
            return;
        }
        // Если очередь достаточно коротка, освободить кассира:
        if(workingTellers.size() > 1 &&
                customers.size() / workingTellers.size() < 2)
            reassignOneTeller();
        // Если очереди нет. достаточно одного кассира:
        if(customers.size() == 0)
            while(workingTellers.size() > 1)
                reassignOneTeller();
    }
    // Поручаем кассиру другую работу или отправляем его отдыхать:
    private void reassignOneTeller() {
        Teller teller = workingTellers.poll();
        teller.doSomethingElse();
        tellersDoingOtherThings.offer(teller);
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                adjustTellerNumber();
                System.out.print(customers + " { ");
                for(Teller teller : workingTellers)
                    System.out.print(teller.shortString() + " ");
                System.out.println("}");
            }
        } catch(InterruptedException e) {
            System.out.println(this + "interrupted");
        }
        System.out.println(this + "terminating");
    }
    public String toString() { return "TellerManager "; }
}
