import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;


public class GUI extends JFrame {
	private JPanel contentPane;
	JComboBox cb, cb1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUI() {
		
		Main.data();  // Data
		GUI2.msg();   // Select the Algorithm
		
		String[] graphs = { "SJF", "SRTF", "priorityScheduler", "AGScheduler" };
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("HELLO");

		setBounds(100, 100, 101, 100);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setSize(1280, 847);

		cb = new JComboBox(graphs);
		cb.setBackground(Color.white);
		cb.setBounds(1101, 82, 120, 25); // x y height width
		getContentPane().add(cb);
		cb.setEditable(true);
		cb.setSelectedIndex(0);
		add(cb);

		getContentPane().setLayout(null); // add it its very important otherwise Combo Box will occupy the whole
											// screen.

		JLabel lblNewLabel = new JLabel("Select The Alogrithm");
		lblNewLabel.setBounds(1101, 61, 161, 16);
		contentPane.add(lblNewLabel);

		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GUI2 i2 = new GUI2();
		i2.setForeground(Color.WHITE);
		i2.setBounds(10, 50, 853, 726);
		contentPane.add(i2);
		i2.setBackground(Color.WHITE);

		GUI3 i3 = new GUI3();
		i3.setBounds(921, 194, 300, 350);
		contentPane.add(i3);
		i3.setBackground(Color.WHITE);

		String[] ggg = { "Draw Graph", "Erase Graph" };
		cb1 = new JComboBox(ggg);
		cb1.setBounds(1101, 141, 120, 22);
		contentPane.add(cb1);
		cb1.setBackground(Color.white);
		cb1.setSelectedIndex(0);
		add(cb1);

		JLabel lblDrawOrErase = new JLabel("Draw or Erase");
		lblDrawOrErase.setBounds(1101, 120, 120, 16);
		contentPane.add(lblDrawOrErase);

	}

}
