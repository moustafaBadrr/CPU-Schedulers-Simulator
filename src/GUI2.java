import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javafx.event.ActionEvent;

import java.util.List;
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class GUI2 extends JPanel {
	public static String message;
	public static int qauntum;
	GUI2() {
	};

	public static void msg()
	{
		message = JOptionPane.showInputDialog("1 - SJF \n2 - SRTF \n3 - Priority Scheduler\n4 - AGScheduler");
		if (message.equals("4")) {
			String q;
			q = JOptionPane.showInputDialog("Enter the Quantum");
			qauntum = Integer.parseInt(q.trim());
		}
	}

	public static void funcolor(int x, Graphics g) {
		if (x == 0)
			g.setColor(Color.RED);
		else if (x == 1)
			g.setColor(Color.orange);
		else if (x == 2)
			g.setColor(Color.pink);
		else if (x == 3)
			g.setColor(Color.LIGHT_GRAY);
		else if (x == 4)
			g.setColor(Color.MAGENTA);
		else if (x == 5)
			g.setColor(Color.black);
		else if (x == 6)
			g.setColor(new Color(255, 100, 100));
		else if (x == 7)
			g.setColor(Color.blue);
		else if (x == 8)
			g.setColor(Color.cyan);
		else
			g.setColor(Color.green);
	}

	public void Erase(Graphics g) { // there is an another way which is you can draw the same processes
									// But the color will be white.
		super.paintComponent(g);
		g.setColor(Color.white);
		g.fill3DRect(0, 0, 10000, 10000, true);
		g.setColor(Color.BLACK);
		g.drawLine(40, 70, 40, 700); // x1 y1 x2 y2
		g.drawLine(810, 700, 40, 700); // x1 y1 x2 y2
		g.drawString("Processes", 40, 65);
		g.drawString("Time", 815, 700);
		g.setColor(Color.BLACK);
		int v = 0;
		for (int i = 0; i <= 25; i++) {
			g.drawString("" + i + " ", 40 + v, 715);
			v += 30;
		}
		v = 0;
		for (int i = 10; i >= 0; i--) {
			g.drawString(i + " ", 20, 100 + v);
			v += 60;
		}
	}

	public void SjF(Graphics g, List<Process> p) {

		Scheduler sjf = new Scheduler();
		List<FinishedProcess> log = new ArrayList<FinishedProcess>();

		log = sjf.SJF(p);
		int v = 0;
		for (int i = 0; i < log.size(); i++) {
			if ((log.get(i).startedTimes.get(0).intValue()) == 0) {
				funcolor(i, g);
				g.fill3DRect(41, 600, 30 * log.get(i).burstTime, 40, true);
			} else {
				funcolor(i, g);
				g.fill3DRect(41 + 30 * (log.get(i).startedTimes.get(0).intValue()), 600 - v, 30 * log.get(i).burstTime,
						40, true);
			}
			v += 60;
		}
	}

	public void SRTF(Graphics g, List<Process> p) {
		List<FinishedProcess> p2 = new ArrayList<FinishedProcess>();
		Scheduler sjf = new Scheduler();
		int v = 0;
		p2 = sjf.SRTF(p);
		for (int i = 0; i < p2.size(); i++) {
			for (int j = 0; j < p2.get(i).startedTimes.size(); j++) {
				if ((p2.get(i).startedTimes.get(j).intValue()) == 0) {
					funcolor(i, g);
					g.fill3DRect(41, 600,
							30 * (p2.get(i).leftTimes.get(j).intValue() - p2.get(i).startedTimes.get(j).intValue()), 40,
							true);
				} else {
					funcolor(i, g);
					g.fill3DRect(41 + 30 * p2.get(i).startedTimes.get(j).intValue(), 600 - v,
							30 * (p2.get(i).leftTimes.get(j).intValue() - p2.get(i).startedTimes.get(j).intValue()), 40,
							true);
				}

			}
			v += 60;
		}
	}

	public void priorityScheduler(Graphics g, List<Process> p) {
		List<FinishedProcess> p2 = new ArrayList<FinishedProcess>();
		Scheduler sjf = new Scheduler();
		int v = 0;
		p2 = sjf.priorityScheduler(p);
		for (int i = 0; i < p2.size(); i++) {
			for (int j = 0; j < p2.get(i).startedTimes.size(); j++) {
				if ((p2.get(i).startedTimes.get(j).intValue()) == 0) {
					funcolor(i, g);
					g.fill3DRect(41, 600,
							30 * (p2.get(i).leftTimes.get(j).intValue() - p2.get(i).startedTimes.get(j).intValue()), 40,
							true);
				} else {
					funcolor(i, g);
					g.fill3DRect(41 + 30 * p2.get(i).startedTimes.get(j).intValue(), 600 - v,
							30 * (p2.get(i).leftTimes.get(j).intValue() - p2.get(i).startedTimes.get(j).intValue()), 40,
							true);
				}

			}
			v += 60;
		}
	}

	public void AGProcess(Graphics g, List<Process> p) {

		List<FinishedProcess> p2 = new ArrayList<FinishedProcess>();
		Scheduler sjf = new Scheduler();
		int v = 0;
		p2 = sjf.AGScheduler(p, qauntum);
		for (int i = 0; i < p2.size(); i++) {
			for (int j = 0; j < p2.get(i).startedTimes.size(); j++) {
				if ((p2.get(i).startedTimes.get(j).intValue()) == 0) {
					funcolor(i, g);
					g.fill3DRect(41, 600,
							30 * (p2.get(i).leftTimes.get(j).intValue() - p2.get(i).startedTimes.get(j).intValue()), 40,
							true);
				} else {
					funcolor(i, g);
					g.fill3DRect(41 + 30 * p2.get(i).startedTimes.get(j).intValue(), 600 - v,
							30 * (p2.get(i).leftTimes.get(j).intValue() - p2.get(i).startedTimes.get(j).intValue()), 40,
							true);
				}

			}
			v += 60;
		}

	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawLine(40, 70, 40, 700); // x1 y1 x2 y2
		g.drawLine(810, 700, 40, 700); // x1 y1 x2 y2
		g.drawString("Processes", 40, 65);
		g.drawString("Time", 815, 700);
		g.setColor(Color.BLACK);
		int v = 0;
		for (int i = 0; i <= 25; i++) {
			g.drawString("" + i + " ", 40 + v, 715);
			v += 30;
		}
		v = 0;
		for (int i = 10; i >= 0; i--) {
			g.drawString(i + " ", 20, 100 + v);
			v += 60;
		}
		
		if (message.equals("1"))
			SjF(g, Main.guidata);
		else if (message.equals("2"))
			SRTF(g, Main.guidata);
		else if (message.equals("3"))
			priorityScheduler(g, Main.guidata );
		else if (message.equals("4"))
			AGProcess(g, Main.guidata);
		else
			Erase(g);
	//SjF(g,Main.guidata);
	}
	
}
