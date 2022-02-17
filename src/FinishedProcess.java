import java.util.List;
import java.util.ArrayList;

public class FinishedProcess {
	public String name;
	public int ID;
	public int arrivalTime;
	public int burstTime;
	public int priority; //Smallest Integer = Highest priority.
	public int waitingTime;
	public int turnaroundTime;
	public List<Integer> startedTimes = null;// excution order.
	public List<Integer> leftTimes = null;
	int starvationCounter;
	public FinishedProcess() {
		this.name = "";
		this.ID = 0;
		this.arrivalTime = 0;
		this.burstTime = 0;
		this.priority = Integer.MAX_VALUE;
		this.waitingTime = 0;
		this.turnaroundTime = 0;
		this.startedTimes = new ArrayList<Integer>();
		this.leftTimes = new ArrayList<Integer>();
	}
	
	public FinishedProcess(String name, int ID, int arrivalTime, int burstTime, int priority, int waiting_time, int turnaround_time) {
		this.name = name;
		this.ID = ID;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.priority = priority;
		this.waitingTime = waiting_time;
		this.turnaroundTime = turnaround_time;
		this.startedTimes = new ArrayList<Integer>();
		this.leftTimes = new ArrayList<Integer>();
	}
	
	public FinishedProcess(Process process) {
		this.name = process.name;
		this.ID = process.ID;
		this.arrivalTime = process.arrivalTime;
		this.burstTime = process.burstTime;
		this.priority = process.priority;
		this.waitingTime = process.waiting_time;
		this.turnaroundTime = process.turnaround_time;
		this.startedTimes = new ArrayList<Integer>();
		this.leftTimes = new ArrayList<Integer>();
	}
	
	public String toString() {
		return ID + " " + name + " " + arrivalTime + " " + burstTime + " " + priority + " " + waitingTime + " " + turnaroundTime;
	}
	
	public void display() {
		System.out.println("Process ID :" + ID);
		System.out.println("Process Name :" + name);
		System.out.println("Arrival Time :" + arrivalTime);
		System.out.println("Burst Time :" + burstTime);
		System.out.println("Priority :" + priority);
		System.out.println("Waiting Time :" + waitingTime);
		System.out.println("Turnaround Time :" + turnaroundTime);
		
		String tempString1 = "";
		String tempString2 = "";
		
		for(int i = 0; i < startedTimes.size(); i++) {
			tempString1 += startedTimes.get(i) + " ";
			tempString2 += leftTimes.get(i) + " ";
		}
		
		System.out.println("Entered Times : " + tempString1);
		System.out.println("Left Times    : " + tempString2);
	}
}
