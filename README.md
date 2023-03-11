# Queues-Management-Application
Queues management application using threads and synchronization mechanisms

I built this project in **Java** using IntelliJ IDEA.   
This application assigns clients to queues such that the waiting time is minimized. Basically, this program should simulate a series of N clients arriving for service, entering Q queues, waiting, being served and finally leaving the queues, all that in the shortest time possible.   
*The main objective of a queue is to provide a place for a "client" to wait before receiving a "service".*   
All clients are generated random when the simulation is started, and are characterized by three parameters:
- **ID** (a number between 1 and N)
- 𝑡𝑎𝑟𝑟𝑖𝑣𝑎𝑙 (simulation time when they are ready to enter the queue)
- 𝑡𝑠𝑒𝑟𝑣𝑖𝑐𝑒 (time interval or duration needed to serve the client; i.e. waiting time when the client is in front of the queue).   

The following data should be considered as **input data for the application** that should be inserted by the user in the application’s user interface:
- Number of clients (N);
- Number of queues (Q);
- Simulation interval (𝑡𝑠𝑖𝑚𝑢𝑙𝑎𝑡𝑖𝑜𝑛 𝑀𝐴𝑋 );
- Minimum and maximum arrival time (𝑡𝑎𝑟𝑟𝑖𝑣𝑎𝑙 𝑀𝐼𝑁 ≤ 𝑡𝑎𝑟𝑟𝑖𝑣𝑎𝑙 ≤ 𝑡𝑎𝑟𝑟𝑖𝑣𝑎𝑙 𝑀𝐴𝑋 ); 
- Minimum and maximum service time (𝑡𝑠𝑒𝑟𝑣𝑖𝑐𝑒 𝑀𝐼𝑁 ≤ 𝑡𝑠𝑒𝑟𝑣𝑖𝑐𝑒 ≤ 𝑡𝑠𝑒𝑟𝑣𝑖𝑐𝑒 𝑀𝐴𝑋 );

A number of Q threads will be launched to **process in parallel the clients**. Another thread will be launched to hold the simulation time 𝑡𝑠𝑖𝑚𝑢𝑙𝑎𝑡𝑖𝑜𝑛 and distribute each client *i* to the queue with the smallest waiting time when 𝑡𝑎𝑟𝑟𝑖𝑣𝑎𝑙 ≥ 𝑡𝑠𝑖𝑚𝑢𝑙𝑎𝑡𝑖𝑜𝑛.     
The application tracks the total time spent by every client in the queues and computes the **average waiting time**.      
I built a friendly user interface where the user can insert the specified values, validate the data and then start the simulation. After the user starts the simulation, he can see the queues evolution in real time in the same interface, together with the time the simulation have and the clients waiting queue.     
Some stats will be shown when all threads are closed.   







In the same time, a log of events will be displayed in a *.txt* file. It contains the status of the pool of waiting clients and the queues as the simulation 
time 𝑡𝑠𝑖𝑚𝑢𝑙𝑎𝑡𝑖𝑜𝑛 goes from 0 to 𝑡𝑠𝑖𝑚𝑢𝑙𝑎𝑡𝑖𝑜n(MAX).


   



The challenge with this project was working with **threads** and **synchronization mechanisms**.
