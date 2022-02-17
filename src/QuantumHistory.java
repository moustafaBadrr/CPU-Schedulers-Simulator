import java.util.List;
import java.util.ArrayList;

public class QuantumHistory {
	public String name;
	public int ID;
	public List<Integer> quantums;
	
	public QuantumHistory() {
		this.name = "";
		this.ID = 0;
		this.quantums = new ArrayList<Integer>();
	}
	
	public QuantumHistory(String name, int ID, int quantum) {
		this.name = name;
		this.ID = ID;
		this.quantums = new ArrayList<Integer>();
		quantums.add(quantum);
	}
	
	public QuantumHistory(Process process, int quantum) {
		this.name = process.name;
		this.ID = process.ID;
		this.quantums = new ArrayList<Integer>();
		quantums.add(quantum);
	}
	
	public String toString() {
		String tempString = "";
		for(Integer integer : quantums) {
			tempString += integer + " ";
		}
		return ID + " " + name + " " + tempString;
	}
}
