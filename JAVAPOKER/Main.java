package pkg1;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main {
	
	static final JLabel table = new JLabel(new ImageIcon("/home/gustavob/workspace/Poker/src/pkg1/table_burned.png"));
	static final JLabel playerIcon = new JLabel(new ImageIcon("/home/gustavob/workspace/Poker/src/pkg1/jogador"));
	
	public static void main(String[] args) {

		// Main temporario apenas pra instanciar as coisas e testar pra ver se ta funcionando tudo certinho
		// Muda-lo futuramente.

		JFrame mainFrame = new JFrame("Poker Simulator");
		JPanel tablePanel = new JPanel();
	
		int numeroDeBots = 0;
		int fichas = 0;

		numeroDeBots = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero de bots na mesa"));
		fichas = Integer
				.parseInt(JOptionPane.showInputDialog("Digite o numero de fichas que os jogadores vão começar"));
		System.out.println(numeroDeBots);
		System.out.println(fichas);

		Deck deck = new Deck();
		Table mesa = new Table(numeroDeBots, fichas);
		deck.shuffle();


		int lastPlacedPlayerx = 160;
		int lastPlacedPlayery = 405;
		int width = 400;
		int heigth = 400;
		
		tablePanel.setLayout(null);
		tablePanel.setBackground(Color.BLACK);
		
		table.setBounds(100, 0, 600, 600);
		playerIcon.setBounds(160, 405, 400,400);
		
		tablePanel.add(table);
		tablePanel.add(playerIcon);
		
		JLabel[] bots = new JLabel[numeroDeBots];
		
		for (int i = 0; i < numeroDeBots; i++) {
			JLabel aux = new JLabel(new ImageIcon("/home/gustavob/workspace/Poker/src/pkg1/jogador"));
			bots[i] = aux;
			if (i == 0) {
				lastPlacedPlayerx = lastPlacedPlayerx + 240;
				bots[i].setBounds(lastPlacedPlayerx, lastPlacedPlayery, width, heigth);
			} else if (i == 1 || i == 4) {
					if (i == 1) {
						lastPlacedPlayery = lastPlacedPlayery - 190;
						bots[i].setBounds(lastPlacedPlayerx + 230, lastPlacedPlayery, width, heigth);
					} else {
						lastPlacedPlayerx = lastPlacedPlayerx + 190;
						bots[i].setBounds(lastPlacedPlayerx - 170, lastPlacedPlayery, width, heigth);
					}
			} else { 
				bots[i].setBounds(lastPlacedPlayerx, lastPlacedPlayery-190, width, heigth);
				lastPlacedPlayerx = lastPlacedPlayerx - 240;
			}
			tablePanel.add(bots[i]);
		}
		
		mainFrame.setBounds(0, 0, 800, 600);
		mainFrame.setResizable(false);
		mainFrame.add(tablePanel);
		mainFrame.setVisible(true);
	}
	
}
