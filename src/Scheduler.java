import java.util.List;
import java.util.ArrayList;

public class Scheduler {

	public Scheduler() {
	}

	public Process Get_FirstProcess(List<Process> p) { // badr
		// System.out.println(p.size());
		Process temp = p.get(0);
		for (int i = 0; i < p.size(); i++) {
			if (p.get(i).arrivalTime < temp.arrivalTime)
				temp = p.get(i);
			if (temp.arrivalTime == p.get(i).arrivalTime && temp.burstTime > p.get(i).burstTime)
				temp = p.get(i);
		}

		for (int i = 0; i < p.size(); i++) {
			if (temp.ID == p.get(i).ID) {
				p.remove(i);
				break;
			}
		}
		return temp;
	}

	public Process Get_Process_FromQueue(List<Process> p, double finish) { // badr
		Process temp = p.get(0);
		int t = p.get(0).arrivalTime;
		for (int i = 0; i < p.size(); i++) {
			if (p.get(i).arrivalTime < t ) {
				temp = p.get(i);
			}
		}
		
		for (int i = 0; i < p.size(); i++) {
			if (p.get(i).arrivalTime <= finish && temp.burstTime > p.get(i).burstTime) {
				temp = p.get(i);
			}
		}
		for (int i = 0; i < p.size(); i++) {
			if (temp.ID == p.get(i).ID) {
				p.remove(i);
				break;
			}
		}

		return temp;
	}
	
	public Process Get_aProcess(List<Process> p, int start, Process p2)
	{
		p.add(p2);
		Process temp = p2;
		for (int i = 0; i < p.size(); i++)
		{
			if (p.get(i).burstTime < temp.burstTime && p.get(i).arrivalTime <= start) {
				temp = p.get(i);
			}
		}
		
		for (int i = 0; i < p.size(); i++) {
			if (temp.ID == p.get(i).ID) {
				p.remove(i);
				break;
			}
		}
		
		return temp;
	}
	
	public List<FinishedProcess> SJF(List<Process> p) { // badr

		double avg_waiting = 0.0, avg_turn = 0.0;
		Double total_waiting_time = 0.0, total_turnaround_time = 0.0;
		int wtemp = 0;

		List<FinishedProcess> output = new ArrayList<FinishedProcess>();

		Process res = new Process();

		List<Process> temp = new ArrayList<Process>();

		for (int i = 0; i < p.size(); i++) {
			temp.add(new Process(p.get(i).name, p.get(i).ID, p.get(i).arrivalTime, p.get(i).burstTime,
					p.get(i).priority, p.get(i).waiting_time, p.get(i).turnaround_time));
		}

		int x = temp.size();

		res = Get_FirstProcess(temp);

		res.turnaround_time = res.burstTime;
		res.waiting_time = 0;
		total_waiting_time += res.waiting_time;
		total_turnaround_time += res.turnaround_time;

		FinishedProcess f;
		f = new FinishedProcess(new Process(res.name, res.ID, res.arrivalTime, res.burstTime, res.priority,
				res.waiting_time, res.turnaround_time));
		f.startedTimes.add(res.arrivalTime);
		f.leftTimes.add(res.burstTime + res.arrivalTime);

		output.add(f);

		wtemp = f.leftTimes.get(0);
		int arrv = res.burstTime + (int) res.arrivalTime;
		for (int i = 0; i < x - 1; i++) {
			res = Get_Process_FromQueue(temp, wtemp);
			if(res.arrivalTime > wtemp)
			{
				res = Get_aProcess(temp, res.arrivalTime, res);
			}
			if (res.arrivalTime >= wtemp) {
				res.waiting_time = 0;
				res.turnaround_time = res.burstTime;

			} else {
				res.waiting_time = wtemp - res.arrivalTime;
				res.turnaround_time = res.waiting_time + res.burstTime;
			}

			total_waiting_time += res.waiting_time;
			total_turnaround_time += res.turnaround_time;

			f = new FinishedProcess(new Process(res.name, res.ID, res.arrivalTime, res.burstTime, res.priority,
					res.waiting_time, res.turnaround_time));
			if (arrv < res.arrivalTime) {
				arrv = (int) res.arrivalTime;
			}
			f.startedTimes.add(arrv);
			f.leftTimes.add(arrv + res.burstTime);
			output.add(f);

			wtemp = f.leftTimes.get(0);
			arrv += res.burstTime;
		}
		avg_waiting = total_waiting_time / x;
		avg_turn = total_turnaround_time / x;

		System.out.println("\nAverage Waiting Time  For SJF : " + avg_waiting);
		System.out.println("Average Turnarround Time  For SJF: " + avg_turn);

		return output;
	}

	public int Get_First(List<Process> p) { // omar
		int index = 0;
		Process temp = p.get(0);
		for (int i = 1; i < p.size(); i++) {
			if (p.get(i).arrivalTime < temp.arrivalTime) {
				temp = p.get(i);
				index = i;
			}
			if (temp.arrivalTime == p.get(i).arrivalTime && temp.burstTime > p.get(i).burstTime) {
				temp = p.get(i);
				index = i;
			}
		}

		return index;
	}

	public int Get_Perfect(List<Process> p) { // omar
		int index = -1;
		if (p.size() == 0) {
			index = -1;
		} else {
			Process temp = p.get(0);
			for (int i = 0; i < p.size(); i++) {
				if (p.get(i).burstTime <= temp.burstTime) {
					temp = p.get(i);
					index = i;
				}
			}
		}
		return index;
	}

	public int getindex(List<Process> p, Process p2) { // omar
		int d = 0;
		for (int i = 0; i < p.size(); i++) {
			if (p2.ID == p.get(i).ID) {
				d = i;
			}
		}

		return d;
	}
	
	public int getindex2(List<FinishedProcess> p, Process p2) { // omar
		int d = 0;
		for (int i = 0; i < p.size(); i++) {
			if (p2.ID == p.get(i).ID) {
				d = i;
			}
		}

		return d;
	}

	public List<FinishedProcess> SRTF(List<Process> p) {// omar
														
		List<FinishedProcess> output = new ArrayList<FinishedProcess>();
		List<Process> ReadyQueue = new ArrayList<Process>();

		List<Process> temp = new ArrayList<Process>();

		for (int i = 0; i < p.size(); i++) {
			temp.add(new Process(p.get(i).name, p.get(i).ID, p.get(i).arrivalTime, p.get(i).burstTime,
					p.get(i).priority, p.get(i).waiting_time, p.get(i).turnaround_time));
		}
		for (Process process : temp) {
			output.add(new FinishedProcess(process));
		}
		
		double total_waiting_time = 0, total_turnaround_time = 0;
		int time = 0;

		Process currentProcess = null;
		int index = Get_First(temp);

		currentProcess = temp.get(index);
		time = currentProcess.arrivalTime;
		ReadyQueue.add(currentProcess);

		int index3 = getindex2(output, currentProcess);
		output.get(index3).startedTimes.add(time);

		while (temp.size() != 0) { 
			
			time++;
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).arrivalTime <= time && !(temp.get(i).equals(currentProcess))
						&& !(ReadyQueue.contains(temp.get(i)))) {
					ReadyQueue.add(temp.get(i));
				}
			}
			if (currentProcess == null)
			{
				if (ReadyQueue.size() == 0 && temp.size() > 0) { // 
					continue;
				}
				else{
					int get = Get_Perfect(ReadyQueue);
					currentProcess = ReadyQueue.get(get);
					int index1 = getindex2(output, currentProcess);
					output.get(index1).startedTimes.add(time);
					continue;
					
				}
			}
			if (currentProcess.burstTime == 0) {
				if (ReadyQueue.size() == 1 && temp.size() == 1 && currentProcess.equals(ReadyQueue.get(0))
						&& currentProcess.equals(temp.get(0))) {
					time--;
					int index1 = getindex2(output, currentProcess);
					output.get(index1).leftTimes.add(time);
					break;

				}
				if (ReadyQueue.size() == 0 && temp.size() > 0) { // 
					currentProcess = null;
					continue;
				} else {
					
					time--;
					int index1 = getindex2(output, currentProcess);
					output.get(index1).leftTimes.add(time);

					int d = getindex(temp, currentProcess);
					temp.remove(d);
					d = getindex(ReadyQueue, currentProcess);
					ReadyQueue.remove(d);
					
					int x = 0;
					x = Get_Perfect(ReadyQueue);
					if (x == -1) {
						continue;
					} else {
						time++;
						currentProcess = ReadyQueue.get(x);
						int index2 = getindex2(output, currentProcess);
						output.get(index2).startedTimes.add(time);
					}
				}
			} else {
				int x = 0;
				x = Get_Perfect(ReadyQueue);
				if (x == -1)
					continue;
				else {
					if (ReadyQueue.get(x).burstTime < currentProcess.burstTime) {
						// NEW
						int d = getindex(temp, currentProcess);
						temp.get(d).burstTime--;
						currentProcess.burstTime = temp.get(d).burstTime;
						int d1 = getindex(ReadyQueue, currentProcess);
						ReadyQueue.get(d1).burstTime = temp.get(d).burstTime;
						// END NEW
						int index2 = getindex2(output, currentProcess);
						output.get(index2).leftTimes.add(time);
						time++;
						currentProcess = ReadyQueue.get(x);
						index2 = getindex2(output, currentProcess);
						output.get(index2).startedTimes.add(time);

					} else {
						int d = getindex(temp, currentProcess);
						temp.get(d).burstTime--;
						currentProcess.burstTime = temp.get(d).burstTime;
						int d1 = getindex(ReadyQueue, currentProcess);
						ReadyQueue.get(d1).burstTime = temp.get(d).burstTime;
					}
				}
			}
		}
		for (FinishedProcess process : output) {
			process.waitingTime = process.startedTimes.get(0) - process.arrivalTime;
			for (int i = 0; i < process.startedTimes.size() - 1; i++) {
				process.waitingTime += process.startedTimes.get(i + 1) - process.leftTimes.get(i);
			}
			process.turnaroundTime = process.waitingTime + process.burstTime;
			total_waiting_time += process.waitingTime;
			total_turnaround_time += process.turnaroundTime;
		}
		
		System.out.println("Average Waiting Time: " + total_waiting_time / p.size());
		System.out.println("Average Turn around Time: " + total_turnaround_time / p.size());
		
		return output;
	}

	// all tested and works good.
	// starvation limit = 400.
	public List<FinishedProcess> priorityScheduler(List<Process> processes) { // fares
		
		// turning all processes to finished processes ( to have the 2 lists) , don't know why they didn't add them to process from start:/.
		// and to avoid messing with the process list.
		List<FinishedProcess> input = new ArrayList<FinishedProcess>();
		for (Process p : processes) {
			input.add( new FinishedProcess(p) );
			
		}
		// output turn around time, waiting time , excution order(the 2 lists in process).
		// assuming that the processes doing do any i/o operations .
		
		PQueue readyQueue = new PQueue();
		
		int busy = 0;// 0 means CPU not busy.
		int notStartedProcesses = input.size();
		for (int i = 0; ; i++) {// forever. i is the current time unit.
			
			// increment starvationCounter in all queued processes.
			for (int j = 0; j < readyQueue.pq.size(); j++) {
				readyQueue.pq.get(j).starvationCounter+=1;
				// note that though starvation counter not initialized in constructor in finished process,
				// in java int variables have default value of 0 if not initialized.
				
				
			}
			
			// check if a queued process waited too long if so make its priority 0 .
			for (int j = 0; j < readyQueue.pq.size(); j++) {
				if(readyQueue.pq.get(j).starvationCounter == 400 ) {// 400 is the limit here.
					readyQueue.changePriorityToHigh( readyQueue.pq.get(j));
					readyQueue.pq.get(j).starvationCounter = 0;// reset starvation counter.
				}
			}
			
			
			if(notStartedProcesses == 0) {// all processes started.
				break;// the loop job is to calculate wait time only (= start - arrival), so when last start happens signals loop break.
			}
			
			if(busy != 0) {
				busy--;// that is make time pass as the cpu deal with the process.
				
			}
			
			// check if a process ( or multiple ones) arrived at time i. if so add to readyQueue.
			for(int j  = 0; j < input.size(); j++) {
				if( input.get(j).arrivalTime == i ) {// process arrived.
					readyQueue.add(  ( input.get(j) )  );
				
				}
			}
			if(busy == 0) {//cpu is not busy with a process.
				if(readyQueue.pq.isEmpty() == false) {// if the readyQueue has a process waiting.
					// make him start.
					FinishedProcess removed = readyQueue.remove(); 
					removed.waitingTime = i - removed.arrivalTime;// i is the starting time of this process.
					removed.startedTimes.add(i);
					notStartedProcesses--;// process started.
					busy += removed.burstTime;// cpu will be busy till the end of burst time.
				}
			}
			
		}
		
		// all turnarround = burstime(known for each process) + waittime.
		
		for (int i = 0; i < input.size(); i++) {
			input.get(i).turnaroundTime = input.get(i).burstTime + input.get(i).waitingTime;
			input.get(i).leftTimes.add( input.get(i).burstTime + input.get(i).startedTimes.get(0));// starttime + turnarround time.
		}
		
		
		Double total_waiting_time = 0.0, total_turnaround_time = 0.0;
		// made by group.
		for(FinishedProcess process : input) {
			total_waiting_time += process.waitingTime;
			total_turnaround_time += process.turnaroundTime;
		}
		
		System.out.println("Average Waiting time : "  + total_waiting_time / processes.size());
		System.out.println("Average Turn around Time : " +total_turnaround_time / processes.size());
		
		return input;
	}
	
	public List<FinishedProcess> AGScheduler(List<Process> sentProcesses, int quantum) { // mina & mounir

		List<FinishedProcess> output = new ArrayList<FinishedProcess>();
		List<QuantumHistory> history = new ArrayList<QuantumHistory>();
		List<AGProcess> processes = new ArrayList<AGProcess>();
		List<AGProcess> dieQueue = new ArrayList<AGProcess>();

		for (Process process : sentProcesses) {
			processes.add(new AGProcess(process, quantum));
			output.add(new FinishedProcess(process));
			history.add(new QuantumHistory(process, quantum));
		}

		int currentTime = 0;
		int numberOfProcessesLeft = sentProcesses.size();

		List<AGProcess> readyQueue = new ArrayList<AGProcess>();

		int enteredTime = 0;
		int leftTime = 0;
		boolean isFirstEntered = false;
		AGProcess currentProcess = null;

		while (numberOfProcessesLeft > 0) {
			updateQueue(readyQueue, processes, dieQueue, currentProcess, currentTime);

			if (!isFirstEntered && readyQueue.size() != 0) {
				currentProcess = readyQueue.remove(0);
				isFirstEntered = true;
				enteredTime = currentTime;
			}
			else if(currentProcess != null) {
				 if (numberOfProcessesLeft == 1 && (currentTime - enteredTime) == currentProcess.burstTime) {
					leftTime = currentTime;
					output.get(currentProcess.ID).startedTimes.add(Integer.valueOf(enteredTime));
					output.get(currentProcess.ID).leftTimes.add(Integer.valueOf(leftTime));
	
					history.get(currentProcess.ID).quantums.add(0);
					dieQueue.add(currentProcess);
	
					break;
				} else if (enteredTime + currentProcess.burstTime <= currentTime) { // Completely Finished.
					leftTime = currentTime;
					output.get(currentProcess.ID).startedTimes.add(Integer.valueOf(enteredTime));
					output.get(currentProcess.ID).leftTimes.add(Integer.valueOf(leftTime));
	
					history.get(currentProcess.ID).quantums.add(0);
					dieQueue.add(currentProcess);
					if(readyQueue.size() != 0) {
						currentProcess = readyQueue.remove(0);
					} else {
						currentProcess = null;
					}
					enteredTime = currentTime;
					numberOfProcessesLeft--;
				} else if (enteredTime + currentProcess.quantum <= currentTime) { // Finished Quantum Only.
					leftTime = currentTime;
					output.get(currentProcess.ID).startedTimes.add(Integer.valueOf(enteredTime));
					output.get(currentProcess.ID).leftTimes.add(Integer.valueOf(leftTime));
	
					currentProcess.burstTime -= currentProcess.quantum;
	
					currentProcess.quantum += (int) Math.ceil(0.10 * meanQuantum(readyQueue));
					history.get(currentProcess.ID).quantums.add(currentProcess.quantum);
	
					readyQueue.add(currentProcess);
					currentProcess = readyQueue.remove(0);
					enteredTime = currentTime;
				} else if (Math.ceil(enteredTime + currentProcess.quantum / 2.0) <= currentTime) {
					int swapProcessIndex = isWorthSwapping(readyQueue, currentProcess);
	
					if (swapProcessIndex != -1) {
						AGProcess swapProcess = readyQueue.remove(swapProcessIndex);
	
						leftTime = currentTime;
						output.get(currentProcess.ID).startedTimes.add(Integer.valueOf(enteredTime));
						output.get(currentProcess.ID).leftTimes.add(Integer.valueOf(leftTime));
	
						currentProcess.burstTime -= leftTime - enteredTime;
						currentProcess.quantum += (enteredTime + currentProcess.quantum) - leftTime;
						history.get(currentProcess.ID).quantums.add(currentProcess.quantum);
	
						readyQueue.add(currentProcess);
						currentProcess = swapProcess;
						enteredTime = currentTime;
					}
				} 
			}
			else {
				if(readyQueue.size() != 0 && currentProcess == null) {
					currentProcess = readyQueue.remove(0);
					enteredTime = currentTime;
				}
			}
			currentTime++;
		}

		Double total1 = 0.0, total2 = 0.0;
		for (FinishedProcess process : output) {
			process.waitingTime = process.startedTimes.get(0) - process.arrivalTime;
			for (int i = 0; i < process.startedTimes.size() - 1; i++) {
				process.waitingTime += process.startedTimes.get(i + 1) - process.leftTimes.get(i);
				total1 += process.waitingTime;
			}
			process.turnaroundTime = process.waitingTime + process.burstTime;
			total2 += process.turnaroundTime;
		}
		System.out.println("Average Waiting Time : " +  total1/ sentProcesses.size());
		System.out.println("Average Turn around Time : " +  total2/ sentProcesses.size());
		for(QuantumHistory historyObject : history) {
			System.out.println(historyObject);
		}
		
		return output;
	}

	public void updateQueue(List<AGProcess> readyQueue, List<AGProcess> processes, List<AGProcess> dieQueue,
			AGProcess currentProcess, int currentTime) {
		for (AGProcess process : processes) {
			if (process.arrivalTime <= currentTime && !readyQueue.contains(process) && !dieQueue.contains(process)
					&& process != currentProcess) {
				readyQueue.add(process);
			}
		}
	}

	public int isWorthSwapping(List<AGProcess> readyQueue, AGProcess currentProcess) {
		int swapProcessIndex = -1;
		for (int i = 0; i < readyQueue.size(); i++) {
			if (readyQueue.get(i).AGFactor < currentProcess.AGFactor) {
				swapProcessIndex = i;
			}
		}
		return swapProcessIndex;
	}

	public int getMostSuitableProcess(List<AGProcess> readyQueue, int currentTime) {
		int suitableProcessIndex = 0;
		for (int i = 0; i < readyQueue.size(); i++) {
			if (readyQueue.get(i).AGFactor < readyQueue.get(suitableProcessIndex).AGFactor) {
				suitableProcessIndex = i;
			}
		}
		return suitableProcessIndex;
	}

	public double meanQuantum(List<AGProcess> readyQueue) {
		int totalQuantum = 0;
		for (AGProcess process : readyQueue) {
			totalQuantum += process.quantum;
		}
		return totalQuantum / (double) readyQueue.size();
	}

}

