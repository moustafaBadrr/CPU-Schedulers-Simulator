import java.util.Comparator;

// all works well and tested.
public class ProcessCompare implements Comparator<FinishedProcess>{

	// override
	public int compare(FinishedProcess p1, FinishedProcess p2) {
	
		if(p1.priority < p2.priority) {
			return -1;// means p1 > p2.
		}else if(p1.priority > p2.priority) {
			return 1;
		}else {
			return 0;
		}
	}
}
	