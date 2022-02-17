
public class Process {
	public String name;
	public int ID;
	public int arrivalTime;
	public int burstTime;
	public int priority; //Smallest Integer = Highest priority.
	public int waiting_time;// output
	public int turnaround_time;// output.
	
	public Process() {
		this.name = "";
		this.ID = 0;
		this.arrivalTime = 0;
		this.burstTime = 0;
		this.priority = Integer.MAX_VALUE;
		this.waiting_time = 0;
		this.turnaround_time = 0;
	}
	
	public Process(String name, int ID, int arrivalTime, int burstTime, int priority, int waiting, int turn) {
		this.name = name;
		this.ID = ID;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.priority = priority;
		this.waiting_time = waiting;
		this.turnaround_time = turn;
	}
	
	public String toString() {
		return ID + " " + name + " " + arrivalTime + " " + burstTime + " " + priority + " " + waiting_time + " " + turnaround_time;
	}
	
	public void Display() 
	{
		System.out.println("The Name of the process: " + this.name + "\nThe Arrival Time: "+ this.arrivalTime);
		System.out.println("The Burst time: " + this.burstTime + "\nthe  priority: " + this.priority);
		System.out.println("The Waiting time: " + this.waiting_time + "\nThe Turnarround time: " + this.turnaround_time);
	}
}
