package stage1;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MyFinishButton extends JButton{
	
	private PlayerGUI playerGUI;
	
	public MyFinishButton(PlayerGUI playerGUI) {
		super("End turn");
		this.playerGUI = playerGUI;
		this.setSize(new Dimension(MainStage.WIDTH-MainStage.WBOARD-20, 30));
		this.setMaximumSize(this.getSize());
		this.setPreferredSize(this.getSize());
		this.addActionListener(new ClickAction());
	}
	
	public PlayerGUI getPlayerGUI() {
		return this.playerGUI;
	}
	
	private class ClickAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MyFinishButton button = (MyFinishButton) e.getSource();
			MainStage.endTurn(button.getPlayerGUI());
		}
	}
}
