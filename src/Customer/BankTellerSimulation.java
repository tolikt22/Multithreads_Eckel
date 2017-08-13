package Customer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BankTellerSimulation {
    static final int MAX_LINE_SIZE = 50;
    static final int ADJUSTMENT_PERIOD = 1000;
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        // Если очередь слишком длинна, клиенты уходят:
        CustomerLine customers =
                new CustomerLine(MAX_LINE_SIZE);
        exec.execute(new CustomerGenerator(customers));
        // TellerManager добавляет и убирает кассиров
        // по мере необходимости:
        exec.execute(new TellerManager(
                exec, customers, ADJUSTMENT_PERIOD));
        if(args.length > 0) // Необязательный аргумент
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
        else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}
/*      Объекты Customer очень просты; они содержат только поле данных final int.
        Так как эти объекты никогда не изменяют своего состояния, они являются объектами,
        доступными только для чтения, и поэтому требуют синхронизации или использования volatile.
        Вдобавок каждая задача Teller удаляет из очереди ввода только один объект
        Customer и работает с ним до завершения, поэтому задачи все равно будут работать с Customer последовательно.

        Класс CustomerLine представляет собой общую очередь, в которой клиенты ожидают обслуживания.
        Он реализован в виде очереди ArrayBlockingQueue с методом toString(),
        который выводит результаты в желаемом формате.
        Генератор CustomerGenerator присоединяется к CustomerLine
        и ставит объекты Customer в очередь со случайными интервалами.

        Teller извлекает клиентов Customer из CustomerLine и обрабатывает их последовательно,
        подсчитывая количество клиентов, обслуженных за текущую смену. Если клиентов не хватает,
        его можно перевести на другую работу (doSomethingElse()), а при появлении большого количества клиентов —
        снова вернуть на обслуживание очереди методом serveCustomerLine().
        Чтобы приказать следующему кассиру вернуться к очереди,
        метод compareTo() проверяет количество обслуженных клиентов,
        чтобы приоритетная очередь автоматически ставила в начало кассира, работавшего меньше других.

        Вся основная деятельность выполняется в TellerManager. Этот класс следит за всеми кассирами и за тем,
        что происходит с клиентами. Одна из интересных особенностей данной имитации заключается в том,
        что она пытается подобрать оптимальное количество кассиров для заданного потока покупателей.
        Пример встречается в методе adjustTellerNumber() — управляющей системе для надежной,
        стабильной регулировки количества кассиров.
        У всех управляющих систем в той или иной мере присутствуют проблемы со стабильностью;
        слишком быстрая реакция на изменения снижает стабильность,
        а слишком медленная переводит систему в одно из крайних состояний.*/
