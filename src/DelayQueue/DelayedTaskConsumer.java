package DelayQueue;

import java.util.concurrent.DelayQueue;

class DelayedTaskConsumer implements Runnable {
    private DelayQueue<DelayedTask> q;
    public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
        this.q = q;
    }
    public void run() {
        try {
            while(!Thread.interrupted())
                q.take().run(); // Выполнение задачи в текущем потоке
        } catch(InterruptedException e) {
            // Приемлемый вариант выхода
        }
        System.out.println("Finished DelayedTaskConsumer");
    }
}