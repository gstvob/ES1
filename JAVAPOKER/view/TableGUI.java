package view;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TableGUI {

	static final JLabel table = new JLabel(new ImageIcon("/home/gustavob/workspace/Poker/src/view/table_burned.png"));
	static final JLabel playerIcon = new JLabel(new ImageIcon("/home/gustavob/workspace/Poker/src/view/jogador"));
	
	private JPanel panel;

	private JLabel[] botsVisual;

	private int lastPlacedPlayerx = 160;
	private int lastPlacedPlayery = 405;
	private int width = 400;
	private int heigth = 400;

	private int numeroDeBots = 0;
	private int fichas = 0;

	public TableGUI(JFrame _frame, JPanel _panel) {

		numeroDeBots = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero de bots na mesa"));
		fichas = Integer
				.parseInt(JOptionPane.showInputDialog("Digite o numero de fichas que os jogadores vão começar"));
		
		
		//setar as fichas do player aqui caralio.
		panel = _panel;

		panel.setBackground(Color.BLACK);
		System.out.println(numeroDeBots);
		System.out.println(fichas);

		botsVisual = new JLabel[numeroDeBots];

		table.setBounds(100, 0, 600, 600);
		playerIcon.setBounds(160, 405, 400, 400);

		SetPlayersGUI(numeroDeBots, botsVisual, panel);
		panel.add(table);
		panel.add(playerIcon);
	
	}

	public void SetPlayersGUI(int numeroDeBots, JLabel[] bots, JPanel tablePanel) {
		for (int i = 0; i < numeroDeBots; i++) {

			JLabel aux = new JLabel(new ImageIcon("/home/gustavob/workspace/Poker/src/view/jogador"));
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
