package BusinessLogic;

import Model.ClientTask;
import Model.Server;

import java.util.LinkedList;

public interface Strategy {
    boolean addTask(LinkedList<Server> servers, ClientTask t);
}
