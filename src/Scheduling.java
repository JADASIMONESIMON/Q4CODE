
import java.util.Arrays;
import java.util.Comparator;

class Process {
    int processID;
    int burstTime;
    int waitingTime;
    int turnaroundTime;

    Process(int processID, int burstTime) {
        this.processID = processID;
        this.burstTime = burstTime;
    }
}

public class Scheduling {

    public static void main(String[] args) {
        Process[] processes = {
                new Process(1, 2),
                new Process(2, 1),
                new Process(3, 8),
                new Process(4, 4),
                new Process(5, 5)
        };

        System.out.println("\n----------------- FCFS -----------------");
        calculateFCFS(processes);
        printProcesses(processes);

        System.out.println("\n----------------- SJF -----------------");
        calculateSJF(processes);
        printProcesses(processes);
    }

    public static void calculateFCFS(Process[] processes) {
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        // Calculate waiting time and turnaround time
        processes[0].waitingTime = 0;
        processes[0].turnaroundTime = processes[0].burstTime;

        for (int i = 1; i < processes.length; i++) {
            processes[i].waitingTime = processes[i - 1].waitingTime + processes[i - 1].burstTime;
            processes[i].turnaroundTime = processes[i].waitingTime + processes[i].burstTime;
        }
    }

    public static void calculateSJF(Process[] processes) {
        Arrays.sort(processes, Comparator.comparingInt(p -> p.burstTime));

        processes[0].waitingTime = 0;
        processes[0].turnaroundTime = processes[0].burstTime;

        for (int i = 1; i < processes.length; i++) {
            processes[i].waitingTime = processes[i - 1].waitingTime + processes[i - 1].burstTime;
            processes[i].turnaroundTime = processes[i].waitingTime + processes[i].burstTime;
        }
    }

    public static void printProcesses(Process[] processes) {
        System.out.printf("%-10s | %-12s | %-15s\n", "Process ID", "Waiting Time", "Turnaround Time");
        for (Process p : processes) {
            System.out.printf("%-10d | %-12d | %-15d\n", p.processID, p.waitingTime, p.turnaroundTime);
        }
    }
}