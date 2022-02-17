
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
	public static List<Process> guidata = new ArrayList<Process>();

	public static void data() {
		Scanner scan = new Scanner(System.in);
		int numberOfProcesses = 0;
		while (true) {
			System.out.print("Enter the number of processes :");
			numberOfProcesses = scan.nextInt();
			scan.nextLine();
			if (numberOfProcesses == 0 || numberOfProcesses < 0) {
				System.out.println("Number of Processes Must be greater than zero....");
				System.out.println("Please Insert Number Of Processes correctly.....");
			}
			else
				break;
		}

		List<Process> processes = new ArrayList<Process>();

		int ID = 0;
		for (int i = 0; i < numberOfProcesses; i++) {
			Process tempProcess = new Process();

			System.out.print("Enter the name of prcoess number " + (i + 1) + " :");
			tempProcess.name = scan.nextLine();

			System.out.print("Enter the arrival time of prcoess number " + (i + 1) + " :");
			tempProcess.arrivalTime = scan.nextInt();

			System.out.print("Enter the burst time of prcoess number " + (i + 1) + " :");
			tempProcess.burstTime = scan.nextInt();

			System.out.print(
					"Enter the priority of prcoess number " + (i + 1) + " (Smallest Integer = Highest Priority):");
			tempProcess.priority = scan.nextInt();
			scan.nextLine();

			tempProcess.ID = ID;
			ID++;

			guidata.add(new Process(tempProcess.name, tempProcess.ID, tempProcess.arrivalTime, tempProcess.burstTime,
					tempProcess.priority, tempProcess.waiting_time, tempProcess.turnaround_time));
		}
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int numberOfProcesses = 0;
		int quantumTime = 0;
		
	   while (true) {
			System.out.print("Enter the number of processes :");
			numberOfProcesses = scan.nextInt();
			scan.nextLine();
			if (numberOfProcesses == 0 || numberOfProcesses < 0) {
				System.out.println("Number of Processes Must be greater than zero....");
				System.out.println("Please Insert Number Of Processes correctly.....");
			}
			else
				break;
		}

		List<Process> processes = new ArrayList<Process>();

		int ID = 0;
		for (int i = 0; i < numberOfProcesses; i++) {

			Process tempProcess = new Process();

			System.out.print("Enter the name of prcoess number " + (i + 1) + " :");
			tempProcess.name = scan.nextLine();

			System.out.print("Enter the arrival time of prcoess number " + (i + 1) + " :");
			tempProcess.arrivalTime = scan.nextInt();

			System.out.print("Enter the burst time of prcoess number " + (i + 1) + " :");
			tempProcess.burstTime = scan.nextInt();

			System.out.print(
					"Enter the priority of prcoess number " + (i + 1) + " (Smallest Integer = Highest Priority):");
			tempProcess.priority = scan.nextInt();
			scan.nextLine();

			tempProcess.ID = ID;
			ID++;

			processes.add(new Process(tempProcess.name, tempProcess.ID, tempProcess.arrivalTime, tempProcess.burstTime,
					tempProcess.priority, tempProcess.waiting_time, tempProcess.turnaround_time));

		}

		List<FinishedProcess> log = new ArrayList<FinishedProcess>();
		Scheduler scheduler = new Scheduler();

		while (true) {
			Scanner algorithm = new Scanner(System.in);
			int select;
			System.out.println("\n1-SJF\n2-SRTF\n3-Priority Scheduler\n4-AGScheduler\n5-Exit");
			System.out.print("Enter The Algorithm :");
			select = algorithm.nextInt();
			if (select == 1) {
				log = scheduler.SJF(processes);
				for (FinishedProcess process : log) {
					process.display();
				}
			} else if (select == 2) {
				log = scheduler.SRTF(processes);
				for (FinishedProcess process : log) {
					process.display();
				}
			} else if (select == 3) {
				log = scheduler.priorityScheduler(processes);
				for (FinishedProcess process : log) {
					process.display();
				}
			} else if (select == 4) {
				System.out.print("Enter The Quantem :");
				int quantum = algorithm.nextInt();
				log = scheduler.AGScheduler(processes, quantum);
				for (FinishedProcess process : log) {
					process.display();
				}
			} else
				System.exit(0);
		}
		// Mostafa will take each log list and draw it.
	}
}
