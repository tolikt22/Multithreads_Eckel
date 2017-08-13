package PriorityBlockingQueue;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

class PrioritizedTaskProducer implements Runnable {
    private Random rand = new Random(47);
    private Queue<Runnable> queue;
    private ExecutorService exec;
    public PrioritizedTaskProducer(
            Queue<Runnable> q, ExecutorService e) {
        queue = q;
        exec = e; // Используется для EndSentinel
    }
    public void run() {
        // Неограниченная очередь без блокировки.
        // Быстрое заполнение случайными приоритетами:
        for(int i = 0; i < 20; i++) {
            queue.add(new PrioritizedTask(rand.nextInt(10)));
            Thread.yield();
        }
        // Добавление высокоприоритетных задач:
        try {
            for(int i = 0; i < 10; i++) {
                TimeUnit.MILLISECONDS.sleep(250);
                queue.add(new PrioritizedTask(10));
            }
            // Добавление заданий, начиная с наименьших приоритетов:
            for(int i = 0; i < 10; i++)
                queue.add(new PrioritizedTask(i));
            // Предохранитель для остановки всех задач::
            queue.add(new PrioritizedTask.EndSentinel(exec));
        } catch(InterruptedException e) {
            // Приемлемый вариант выхода
        }
        System.out.println("Finished PrioritizedTaskProducer");
    }
}