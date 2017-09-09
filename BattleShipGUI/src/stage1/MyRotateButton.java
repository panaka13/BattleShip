package stage1;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MyRotateButton extends JButton{
	
	private PlayerGUI playerGUI;
	
	public MyRotateButton(PlayerGUI playerGUI) {
		super("Rotate");
		this.playerGUI = playerGUI;
		this.setSize(new Dimension(MainStage.WIDTH-MainStage.WBOARD-20, 30));
		this.setMaximumSize(this.getSize());
		this.setPreferredSize(this.getSize());
		this.addActionListener(new MyRotateButtonAction());
	}                           
	
	public PlayerGUI getPlayerGUI() {
		return this.playerGUI;
	}
	
	private class MyRotateButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MyRotateButton button = (MyRotateButton) e.getSource();
			button.getPlayerGUI().changeDirection();
		}
	}
}