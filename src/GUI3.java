import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.util.List;
import java.util.Scanner;

public class GUI3 extends JPanel {
	
	public void Erase(Graphics g) { 
		
		super.paintComponent(g);
		
		setBackground(Color.white);
		g.drawString("Process Color", 30, 15);
		g.drawString("Name", 120, 15);
		g.drawString("ID", 180, 15);
		g.drawString("Priority", 220, 15);
		g.drawString("____________________________________________________________________________________________", 0,
				297);
		g.drawString("____________________________________________________________________________________________", 0,
				298);
		g.drawString("____________________________________________________________________________________________", 0,
				299);
		g.drawString("____________________________________________________________________________________________", 0,
				300);
		
		g.drawString("Average Waiting Time   = ", 10, 320);
		g.drawString("Average Turn around Time   = ", 10, 340);
	}

	protected void paintComponent(Graphics g) {

		Scheduler sjf = new Scheduler();
		
		super.paintComponent(g);
		setBackground(Color.white);
		
		g.drawString("Process Color", 30, 15);
		g.drawString("Name", 120, 15);
		g.drawString("ID", 180, 15);
		g.drawString("Priority", 220, 15);
		
		List<FinishedProcess> p2 = new ArrayList<FinishedProcess>();
		
		if (GUI2.message.equals("1"))
			p2 = sjf.SJF(Main.guidata);
		else if (GUI2.message.equals("2"))
			p2 = sjf.SRTF(Main.guidata);
		else if (GUI2.message.equals("3"))
			p2 = sjf.priorityScheduler(Main.guidata);
		else if (GUI2.message.equals("4")) 
			p2 = sjf.AGScheduler(Main.guidata, GUI2.qauntum);
		else
			Erase(g);
			
		g.drawString("____________________________________________________________________________________________", 0,
				297);
		g.drawString("____________________________________________________________________________________________", 0,
				298);
		g.drawString("____________________________________________________________________________________________", 0,
				299);
		g.drawString("____________________________________________________________________________________________", 0,
				300);
		g.drawString("Average Waiting Time   = ", 10, 320);
		g.drawString("Average Turn around Time   = ", 10, 340);

		double total_waiting_time = 0.0, total_turnaround_time = 0.0;
		double atemp = p2.size();
		for (int i = 0; i < p2.size(); i++) {
			total_waiting_time += p2.get(i).waitingTime;
			total_turnaround_time += p2.get(i).turnaroundTime;
		}
		g.drawString("" + (total_waiting_time / atemp), 150, 320);
		g.drawString("" + (total_turnaround_time / atemp), 170, 340);
		int v = 0;
		for (int i = 0; i < p2.size(); i++) {
			GUI2.funcolor(i, g);
			g.drawString(p2.get(i).name, 130, 40 + v);
			g.drawString(p2.get(i).ID + "", 180, 40 + v);
			g.drawString(p2.get(i).priority + "", 220, 40 + v);
			g.fill3DRect(40, 20 + v, 70, 30, true);
			v += 40;
		}

	}

}