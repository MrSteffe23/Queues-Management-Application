package BusinessLogic;

import Model.ClientTask;
import Model.Server;

import java.util.LinkedList;

public class ConcreteStrategyTime implements Strategy {

    public Server checkMinOrBigger(LinkedList<Server> servers, int min) {
        Server second = null;
        int ok = 0;
        int minSecond = 0;
        for (Server client : servers) {//looking for a server with the same minimum waiting time but not full or the first server with a bigger waiting time but not full
            if (client.getWaitingPeriod().intValue() == min && client.getTaskSize() < Scheduler.getMaxTasksPerServer()) {
                second = client;
                break;
            }
            if (client.getTaskSize() < Scheduler.getMaxTasksPerServer() && ok == 0) {
                minSecond = client.getWaitingPeriod().intValue();
                ok = 1;
                second = client;
            } else if (client.getTaskSize() < Scheduler.getMaxTasksPerServer() && ok == 1 && client.getWaitingPeriod().intValue() < minSecond) {
                minSecond = client.getWaitingPeriod().intValue();
                second = client;
            }
        }
        return second;
    }

    @Override
    public boolean addTask(LinkedList<Server> servers, ClientTask t) {
        int numberOfServers = Scheduler.getMaxNoServers();
        if (numberOfServers == 0)
            return false;
        int min = servers.get(0).getWaitingPeriod().intValue();
        Server chosen = servers.get(0);
        int index = 1;
        while (index != numberOfServers) {
            int waitingPeriod = servers.get(index).getWaitingPeriod().intValue();
            if (waitingPeriod < min) {
                min = waitingPeriod;
                chosen = servers.get(index);
            }
            index++;
        }
        if (chosen.getTaskSize() < Scheduler.getMaxTasksPerServer()) {
            chosen.addTask(t);
            SimulationManager.addAverageWaitingTime(chosen.getWaitingPeriod().intValue());
        } else {
            Server second = checkMinOrBigger(servers, min);
            if (second == null)//there are no servers available yet
                return false;
            second.addTask(t);
            SimulationManager.addAverageWaitingTime(second.getWaitingPeriod().intValue());
        }
        return true;
    }
}