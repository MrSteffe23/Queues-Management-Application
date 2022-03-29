package BusinessLogic;

import Model.ClientTask;
import Model.Server;

import java.util.LinkedList;

public class ConcreteStrategyQueue implements Strategy {

    @Override
    public boolean addTask(LinkedList<Server> servers, ClientTask t) {
        int numberOfServers = Scheduler.getMaxNoServers();
        if (numberOfServers > 0) {
            long min = servers.get(0).getTaskSize();
            Server chosen = servers.get(0);
            int index = 1;
            while (index != numberOfServers) {
                long size = servers.get(index).getTaskSize();
                if (size < min) {
                    min = size;
                    chosen = servers.get(index);
                }
                index++;
            }
            if (min < Scheduler.getMaxTasksPerServer()) {
                SimulationManager.addAverageWaitingTime(chosen.getWaitingPeriod().intValue());
                chosen.addTask(t);
                return true;
            }
        }
        return false;
    }
}