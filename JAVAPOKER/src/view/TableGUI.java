package view;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import models.Table;

public class TableGUI {

	static final JLabel table = new JLabel(new ImageIcon("/home/gustavob/workspace/Poker/src/images/table_burned.png"));
	static final JLabel playerIcon = new JLabel(new ImageIcon("/home/gustavob/workspace/Poker/src/images/jogador"));
	
	private JPanel panel;

	private JLabel[] botsVisual;
	private Table mesa;
	private JLabel[] cards;
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

			JLabel aux = new JLabel(new ImageIcon("/home/gustavob/workspace/Poker/src/images/jogador"));
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
	
	public void cardsGui(JPanel panel,int length ,String card1, String card2) {
		int posx = 140, posy = 360;
		System.out.println("PRINT DAS CARTAS PARA GUI: "+card1+ ", "+card2);
		cards = new JLabel[length*2 + 2];
		
		JLabel carta1 = new JLabel(new ImageIcon("/home/gustavob/workspace/Poker/src/images/"+card1));
		JLabel carta2 = new JLabel(new ImageIcon("/home/gustavob/workspace/Poker/src/images/"+card2));
		carta1.setBounds(posx,posy,65,85);
		carta2.setBounds(posx+40,posy,65,85);
		cards[0] = carta1;
		cards[1] = carta2;
		table.add(carta1);
		table.add(carta2);
		posx += 240;	
		int i = 2;
		/*adicionar aqui as back cards para os bots já que eles não vao mostrar suas cartas.*/
		while (i < cards.length-1){
			JLabel aux = new JLabel(new ImageIcon("/home/gustavob/workspace/Poker/src/images/cardBack1"));
			JLabel aux2 = new JLabel(new ImageIcon("/home/gustavob/workspace/Poker/src/images/cardBack2"));
			if (i == 2) {
				cards[i] = aux;
				cards[i+1]=aux2;
				cards[i].setBounds(posx,posy,55,75);
				cards[i+1].setBounds(posx+40,posy,55,75);
				table.add(cards[i]);
				table.add(cards[i+1]);
				posy -= 125;
				posx += 140;
			} else if(i == 4 || i == 10) {
				cards[i] = aux;
				cards[i+1]=aux2;
				cards[i].setBounds(posx,posy,55,75);
				cards[i+1].setBounds(posx+40,posy,55,75);
				table.add(cards[i]);
				table.add(cards[i+1]);
				posy -= 100;
				posx -= 60;
				if (i == 4) {
					posx -= 80;
				}
			} else {
				cards[i] = aux;
				cards[i+1]=aux2;
				cards[i].setBounds(posx,posy,55,75);
				cards[i+1].setBounds(posx+40,posy,55,75);
				table.add(cards[i]);
				table.add(cards[i+1]);
				posx -= 220;
				if (i == 8) {
					posx += 50;
					posy += 100;
				}
			}
			i+=2;
		}
		panel.updateUI();
	}
	public int getBotsNumber() {
		return numeroDeBots;
	}

	public int getFichas() {
		return fichas;
	}

	public Table getMesa() {
		return mesa;
	}

	public void setMesa(Table mesa) {
		this.mesa = mesa;
	}
}
