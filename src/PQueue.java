import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// main purpose is to sovle the problem of java priority queue removing elements with same priority in arbitrary order,
// this class removes them in the order of entrance ( fifo) .

// all works well.
public class PQueue {
	
	// list as collections.sort requires list.
	public List<FinishedProcess> pq;
	
	public PQueue() {
		pq = new ArrayList<FinishedProcess>();
	}
	
	
	public void add( FinishedProcess p) {
		pq.add(p);// append at end.
		// sorts ascending (e.g 0,1,2... ) using merge sort( cuz array of objects).
		// note that merge sort is a stable sort,so the entrance order of process with same priority wont change.
		Collections.sort (pq,new ProcessCompare());// works well.
		
		
	}
	
	public FinishedProcess remove() {
		return pq.remove(0);
		
	}
	
	public void changePriorityToHigh(FinishedProcess p) {// pass by reference.
		p.priority = 0;
		Collections.sort (pq,new ProcessCompare());// reoder the queue.
	}
	
	
	
	public void print() {
		for (FinishedProcess f : pq) {
			System.out.println(f.priority + " " + f.ID);
		}
	}
	
}
