package Model;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Server extends Thread {
    private final LinkedBlockingQueue<ClientTask> tasks;
    private final AtomicInteger waitingPeriod;
    private final int id;

    public Server(int n) {
        tasks = new LinkedBlockingQueue<>();
        waitingPeriod = new AtomicInteger();
        id = n;
    }

    public synchronized void addTask(ClientTask newTask) {
        try {
            tasks.add(newTask);
        } catch (IllegalStateException e) {
            System.out.println("Full linked queue for server " + id);
        }
        waitingPeriod.addAndGet(newTask.getServiceTime());
    }

    @Override
    public void run() {
        while (true) {
            ClientTask extracted = tasks.peek();
            if (extracted != null) {
                try {
                    sleep(500);
                    extracted.decrementServiceTime();
                    if (extracted.getServiceTime() == 0)
                        tasks.poll();
                    waitingPeriod.decrementAndGet();
                } catch (InterruptedException e) {
                    System.out.println("Problem with thread sleeping");
                }
            }
        }
    }

    public ClientTask[] getTasks() {
        ClientTask[] taskList = new ClientTask[tasks.size()];
        int k = 0;
        for (ClientTask client : tasks)
            taskList[k++] = client;
        return taskList;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public synchronized long getTaskSize() {
        return tasks.size();
    }

    public int getTheId() {
        return id;
    }
}

