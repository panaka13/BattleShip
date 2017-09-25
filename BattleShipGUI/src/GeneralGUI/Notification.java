package GeneralGUI;

import java.awt.event.WindowEvent;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Notification extends JFrame {
	
	private JPanel panel = new JPanel();
	
	public Notification(String[] noti) {
		super();
		panel.setSize(480, 50);
		panel.setLayout(new GridLayout(noti.length, 1));
		panel.setAlignmentX(0.5f);
		for(String s: noti)
			panel.add(new JLabel(s));
		ControlResponse.turnOff();
		this.add(panel);
		this.setSize(480, 50);
		this.setVisible(true);		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				ControlResponse.turnOn();
				Main.Test.stage2.changeTurn();
			}
		});
	}
}
