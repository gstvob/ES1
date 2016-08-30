package pkg1;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//Classe game manager vai ser a classe que vai controlar o sistema.
/*!
 * Essa classe ela vai fazer as operações do sistema e vai "gerenciar" o jogo
 * no metodo GameRun ele vai saber de quem é a vez, se for a vez do player vai fazer aparecer as opções
 * se for a vez de um bot ele vai fazer as operações do bot para ele decidir a jogada.
 */

public class GameManager {

	static final JLabel table = new JLabel(new ImageIcon("/home/gustavob/workspace/Poker/src/pkg1/table_burned.png"));
	static final JLabel playerIcon = new JLabel(new ImageIcon("/home/gustavob/workspace/Poker/src/pkg1/jogador"));

	private JLabel[] botsVisual;
	private String[] players;
	private Bot[] bots;

	private Player player;
	private Deck deck;

	private int playerNumber;
	private int lastPlacedPlayerx = 160;
	private int lastPlacedPlayery = 405;
	private int width = 400;
	private int heigth = 400;

	private boolean isRunning;

	public GameManager() {

		JFrame mainFrame = new JFrame("Poker Simulator");
		JPanel tablePanel = new JPanel();

		int numeroDeBots = 0;
		int fichas = 0;

		deck = new Deck();

		numeroDeBots = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero de bots na mesa"));
		fichas = Integer
				.parseInt(JOptionPane.showInputDialog("Digite o numero de fichas que os jogadores vão começar"));
		System.out.println(numeroDeBots);
		System.out.println(fichas);

		Table mesa = new Table(numeroDeBots, fichas);
		deck.shuffle();

		tablePanel.setLayout(null);
		tablePanel.setBackground(Color.BLACK);

		table.setBounds(100, 0, 600, 600);
		playerIcon.setBounds(160, 405, 400, 400);

		tablePanel.add(table);
		tablePanel.add(playerIcon);

		botsVisual = new JLabel[numeroDeBots];
		players = new String[numeroDeBots + 1];
		players[0] = "Player 1";

		SetPlayersGUI(numeroDeBots, botsVisual, tablePanel);
		bots = new Bot[numeroDeBots];
		for (int i = 0; i < numeroDeBots; i++) {
			bots[i] = new Bot(fichas);
		}
		player = new Player(fichas);

		mesa.setBots(bots);
		mesa.setPlayer(player);

		mainFrame.setBounds(0, 0, 800, 600);
		mainFrame.setResizable(false);
		mainFrame.add(tablePanel);
		mainFrame.setVisible(true);

		playerNumber = numeroDeBots + 1;
		GameRun();
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
			players[i + 1] = "Player " + i + 2;
			tablePanel.add(bots[i]);
		}
	}

	public void GiveCards() {
		player.setCartas(deck.giveCards(), deck.giveCards());
		System.out.println(
				"Cartas player : " + player.getHand().getFirstCard() + ", " + player.getHand().getSecondCard());
		for (int i = 0; i < playerNumber - 1; i++) {
			bots[i].setCartas(deck.giveCards(), deck.giveCards());
			System.out.println("Cartas bot " + (i + 1) + " : " + bots[i].getHand().getFirstCard() + ", "
					+ bots[i].getHand().getSecondCard());
		}
	}

	public void GameRun() {
		isRunning = true;
		while (isRunning) {
			GiveCards();
			boolean fimRodada;
			isRunning = false;
		}
	}
}
