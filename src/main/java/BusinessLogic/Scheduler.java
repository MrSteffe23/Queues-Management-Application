package BusinessLogic;

import Model.ClientTask;
import Model.Server;

import java.util.LinkedList;

public class Scheduler {
    private final LinkedList<Server> servers;
    private static int maxNoServers;
    private static int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer) {
        Scheduler.maxNoServers = maxNoServers;
        Scheduler.maxTasksPerServer = maxTasksPerServer;
        servers = new LinkedList<>();
        for (int i = 0; i < maxNoServers; i++) {
            servers.add(new Server(i + 1)); //creating server object/thread
            servers.get(i).start(); //starting the thread
        }
    }

    public void changeStrategy(SelectionPolicy policy) {
        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ConcreteStrategyQueue();
        }
        if (policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new ConcreteStrategyTime();
        }
    }

    public boolean dispatchTask(ClientTask t) {
        return strategy.addTask(servers, t);
    }

    public static int getMaxTasksPerServer() {
        return maxTasksPerServer;
    }

    public LinkedList<Server> getServers() {
        return servers;
    }

    public static int getMaxNoServers() {
        return maxNoServers;
    }

}
