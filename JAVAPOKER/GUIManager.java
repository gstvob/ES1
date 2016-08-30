package pkg1;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUIManager {

	static final JLabel table = new JLabel(new ImageIcon("/home/gustavob/workspace/Poker/src/pkg1/table_burned.png"));
	static final JLabel playerIcon = new JLabel(new ImageIcon("/home/gustavob/workspace/Poker/src/pkg1/jogador"));
	
	private JLabel[] botsVisual;
	
	private int lastPlacedPlayerx = 160;
	private int lastPlacedPlayery = 405;
	private int width = 400;
	private int heigth = 400;
	
	private int numeroDeBots = 0;
	private int fichas = 0;
	
	public GUIManager() {
		
		JFrame mainFrame = new JFrame("Poker Simulator");
		JPanel tablePanel = new JPanel();

		numeroDeBots = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero de bots na mesa"));
		fichas = Integer
				.parseInt(JOptionPane.showInputDialog("Digite o numero de fichas que os jogadores vão começar"));
		System.out.println(numeroDeBots);
		System.out.println(fichas);
		
		botsVisual = new JLabel[numeroDeBots];
		tablePanel.setLayout(null);
		tablePanel.setBackground(Color.BLACK);

		table.setBounds(100, 0, 600, 600);
		playerIcon.setBounds(160, 405, 400, 400);

		SetPlayersGUI(numeroDeBots, botsVisual, tablePanel);
		
		tablePanel.add(table);
		tablePanel.add(playerIcon);

		mainFrame.setBounds(0, 0, 800, 600);
		mainFrame.setResizable(false);
		mainFrame.add(tablePanel);
		mainFrame.setVisible(true);
	}
	public void SetPlayersGUI(int numeroDeBots, JLabel[] bots, JPanel tablePanel) {
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
				bots[i].setBounds(lastPlacedPlayerx, lastPlacedPlayery - 190, width, heigth);
				lastPlacedPlayerx = lastPlacedPlayerx - 240;
			}
			tablePanel.add(bots[i]);
		}
	}
	public int getBotsNumber() {
		return numeroDeBots;
	}
	public int getFichas() {
		return fichas;
	}
}
