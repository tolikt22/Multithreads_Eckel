package PriorityBlockingQueue;

import java.util.concurrent.PriorityBlockingQueue;

class PrioritizedTaskConsumer implements Runnable {
    private PriorityBlockingQueue<Runnable> q;
    public PrioritizedTaskConsumer(
            PriorityBlockingQueue<Runnable> q) {
        this.q = q;
    }
    public void run() {
        try {
            while(!Thread.interrupted())
                // Использование текущего потока для запуска задачи:
                q.take().run();
        } catch(InterruptedException e) {
            // Приемлемый вариант выхода
        }
        System.out.println("Finished PrioritizedTaskConsumer");
    }
}
