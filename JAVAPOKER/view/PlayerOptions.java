package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.GameManager;

public class PlayerOptions implements ActionListener{

	private JButton passar, apostar, pagar, desistir;
	private JTextField aposta;
	private JPanel panel;
	private GameManager gmgr;
	
	public PlayerOptions(JPanel _pane, GameManager gmgr) {
		panel = _pane;
		this.gmgr = gmgr;
	}
	public void initComponent(boolean canPass) {
		passar = new JButton("Passar");
		apostar = new JButton("Apostar");
		pagar =  new JButton("Pagar");
		desistir = new JButton("Desistir");
		aposta = new JTextField("10");
		
		passar.setSize(120,50);
		apostar.setSize(120,50);
		pagar.setSize(120,50);
		desistir.setSize(120,50);
		aposta.setSize(120,20);
		
		pagar.setLocation(0, 550);
		apostar.setLocation(130, 550);
		aposta.setLocation(130,530);
		desistir.setLocation(260, 550);
		passar.setLocation(390, 550);
		
		passar.addActionListener(this);
		pagar.addActionListener(this);
		desistir.addActionListener(this);
		apostar.addActionListener(this);
		
		if (canPass == true) {
			panel.add(passar);
		}
		panel.add(desistir);
		panel.add(apostar);
		panel.add(pagar);
		panel.add(aposta);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == passar) {
			clearOptions();
			gmgr.Interrupt();
		} else if (arg0.getSource() == apostar) {
			clearOptions();
			int bet = Integer.parseInt(aposta.getText());
			gmgr.setPlay(0,bet);
			gmgr.Interrupt();
		} else if (arg0.getSource() == desistir) {
			clearOptions();
			panel.setBackground(Color.WHITE);
			gmgr.setPlayerFold(true);
			gmgr.setRunning(false);
			gmgr.PlayerDesistiu();
			gmgr.Interrupt();
		} else if (arg0.getSource() == pagar) {
			clearOptions();
			gmgr.setPlay(1,0);
			gmgr.Interrupt();
		}
	}
	
	public void clearOptions() {
		panel.remove(passar);
		panel.remove(apostar);
		panel.remove(aposta);
		panel.remove(pagar);
		panel.remove(desistir);
		panel.updateUI();
	}
}
