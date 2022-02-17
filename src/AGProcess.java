
public class AGProcess extends Process {
	public double AGFactor;
	public int quantum;

	public AGProcess() {
		this.name = "";
		this.ID = 0;
		this.arrivalTime = 0;
		this.burstTime = 0;
		this.priority = Integer.MAX_VALUE;
		this.waiting_time = 0;
		this.turnaround_time = 0;
		this.AGFactor = 0;
		this.quantum = 0;
	}

	public AGProcess(String name, int ID, int arrivalTime, int burstTime, int priority, int waiting, int turn,
			int quantum) {
		this.name = name;
		this.ID = ID;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.priority = priority;
		this.waiting_time = waiting;
		this.turnaround_time = turn;
		this.AGFactor = priority + arrivalTime + burstTime;
		this.quantum = quantum;
	}

	public AGProcess(Process process, int quantum) {
		this.name = process.name;
		this.ID = process.ID;
		this.arrivalTime = process.arrivalTime;
		this.burstTime = process.burstTime;
		this.priority = process.priority;
		this.waiting_time = process.waiting_time;
		this.turnaround_time = process.turnaround_time;
		this.AGFactor = priority + arrivalTime + burstTime;
		this.quantum = quantum;
	}

	public String toString() {
		return ID + " " + name + " " + arrivalTime + " " + burstTime + " " + priority + " " + waiting_time + " "
				+ turnaround_time + " " + AGFactor + " " + quantum;
	}
}
