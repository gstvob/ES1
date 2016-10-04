package control;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import models.Bot;
import models.Player;
import models.Table;
import view.MainScreen;
import view.PlayerOptions;
import view.TableGUI;

//Classe game manager vai ser a classe que vai controlar o sistema.
/*!
 * Essa classe ela vai fazer as operações do sistema e vai "gerenciar" o jogo
 * no metodo GameRun ele vai saber de quem é a vez, se for a vez do player vai fazer aparecer as opções
 * se for a vez de um bot ele vai fazer as operações do bot para ele decidir a jogada.
 */

public class GameManager extends Thread{

	private Bot[] bots;

	private int playerNumber;
	private int turn;
	private int play;
	
	private boolean playerFold = false;
	private Table mesa;
	private Player player;
	//private int bigBlind;
	//private int blind;
	private JPanel panel;
	private JFrame frame;
	//private boolean logged = false;
	private boolean isRunning;

	public GameManager(JPanel jpanel, JFrame _frame, Player jogador) {
		
		panel = jpanel;
		frame = _frame;
		TableGUI tableG = new TableGUI(frame, panel);
		
		
		int numeroDeBots = tableG.getBotsNumber();
		int fichas = tableG.getFichas();
		
		mesa = new Table(numeroDeBots, fichas);
		mesa.getDeck().shuffle();

		bots = new Bot[numeroDeBots];

		for (int i = 0; i < numeroDeBots; i++) {
			bots[i] = new Bot(fichas);
		}

		player = jogador;
		jogador.addFichas(fichas);

		//blind = fichas/100;
		
		mesa.setBots(bots);
		mesa.setPlayer(player);

		playerNumber = numeroDeBots + 1;
		System.out.println("player fichas: " +player.getFichas());
	}
	public void GiveCards() {
		player.setCartas(mesa.getDeck().giveCards(), mesa.getDeck().giveCards());
		System.out.println(
				"Cartas player : " + player.getHand().getFirstCard() + ", " + player.getHand().getSecondCard());
		for (int i = 0; i < playerNumber - 1; i++) {
			bots[i].setCartas(mesa.getDeck().giveCards(), mesa.getDeck().giveCards());
			System.out.println("Cartas bot " + (i + 1) + " : " + bots[i].getHand().getFirstCard() + ", "
					+ bots[i].getHand().getSecondCard());
		}
	}
	
	public void run() {
		isRunning = true;
		while (isRunning) {
			GiveCards();
			PlayerOptions playerO = new PlayerOptions(panel, this);
			turn = 0;
			boolean roundFinished = false;
			while (!roundFinished) {
				if (turn == 0) {
					playerO.initComponent(true);
					panel.updateUI();
					turn++;
					try {
						sleep(5000);
						System.out.println(player.getFichas());
					} catch (InterruptedException e){
					}
				}else {
					setPlay(bots[turn-1].escolherJogada(),0);
					try {
						sleep(2000);
					} catch (InterruptedException e) {}
					System.out.println("bot "+turn);
					turn++;
				}
				if (turn > 5) {
					turn = 0;
				}
			}
			isRunning = false;
		}
	}
	public void Play(int bet) {
		if (play == 0) {
			if (mesa.getBiggestBet() >= player.getBet()) {
				player.addFichas(-1*((mesa.getBiggestBet()-player.getBet())+bet));
			}
		} else if (play == 1) {
			if (mesa.getBiggestBet() > player.getBet()){
				player.addFichas(-1*(mesa.getBiggestBet()-player.getBet()));
			}
		}
	}
	
	public void PlayerDesistiu() {
		panel.removeAll();
		frame.setBackground(Color.WHITE);
		MainScreen ms = new MainScreen(frame, panel, 800, 600, player);
		ms.initComponent();
		panel.updateUI();
	}
	public void setTurn(int value) {
		turn = value;
	}
	public void Interrupt() {
		this.interrupt();
		this.notifyAll();
	}
	public int getTurn() {
		return turn;
	}
	public void setPlay(int value, int bet) {
		play = value;
		Play(bet);
	}
	public int getPlay() {
		return play;
	}
	public boolean isPlayerFold() {
		return playerFold;
	}
	public void setPlayerFold(boolean playerFold) {
		this.playerFold = playerFold;
	}
	public void setRunning(boolean b) {	
		isRunning = b;
	}
}
