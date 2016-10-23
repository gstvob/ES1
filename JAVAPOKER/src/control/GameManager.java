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
	private PlayerOptions player0;
	private int playerNumber;
	private int turn;
	private int play;
	private int bets;
	private boolean entrada = true;
	private boolean playerFold = false;
	private boolean giveCardTime = true;
	private boolean roundFinished = false;
	private Table mesa;
	private Player player;
	private TableGUI tableG;
	
	private int bigBlind;
	private int blind;
	private JPanel panel;
	private JFrame frame;
	//private boolean logged = false;
	private boolean isRunning;

	public GameManager(JPanel jpanel, JFrame _frame, Player jogador) {
		panel = jpanel;
		frame = _frame;
		tableG = new TableGUI(frame, panel);
		
		
		int numeroDeBots = tableG.getBotsNumber();
		int fichas = tableG.getFichas();

		
		bots = new Bot[numeroDeBots];
		
		
		for (int i = 0; i < numeroDeBots; i++) {
			bots[i] = new Bot(fichas);
		}

		player = jogador;
		jogador.addFichas(fichas);
		
		mesa = new Table(numeroDeBots, fichas);
		mesa.getDeck().shuffle();
		
		setBlind(fichas/100);
		bigBlind = 0;
		mesa.setBots(bots);
		mesa.setPlayer(player);
		
		tableG.setMesa(mesa);
		
		player0 = new PlayerOptions(panel, this);
		playerNumber = numeroDeBots + 1;
		System.out.println("player fichas: " +player.getFichas());
		isRunning = true;
		turn = 0;
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
		tableG.cardsGui(panel,bots.length,player.getHand().getFirstCard(), player.getHand().getSecondCard());
	}
	public void run() {
		while (isRunning) {
			if (giveCardTime == true) {
				GiveCards();
			}
			while (!roundFinished) {
				giveCardTime = false;
				if (isRunning == false) {
					break;
				}
				// esse if serve para cuidar do big e small blind.
				// se for o inicio de uma rodada, são retiradas fichas do small e do big blind direto e passa a vez para o proximo.
				
				if (entrada == true) {
					int sb;
					if (turn == 0) {
						sb = bots.length-1;
						player.addFichas(-1*blind);
						bots[sb].addFichas(-1*(blind/2));
						bets = blind + blind/2;
					}else {
						sb = turn-1;
						if (sb == 0) {
							player.addFichas(-1*(blind/2));
							bets = blind*2;
						} else {
							bots[sb].addFichas(-1*(blind/2));
						}
						bots[bigBlind].addFichas(-1*blind);
					}
					turn++;
					entrada = false;
				}
				//Jogada do usuario
				if (turn == 0) {
					player0.initComponent(true);
					panel.updateUI();
					try {
						sleep(5000);
					} catch (InterruptedException e) {
		
					}
					player0.clearOptions();
					panel.updateUI();
				//Jogada dos bots.
				}else {
					setPlay(bots[turn-1].escolherJogada(),0);
					try {
						sleep(5000);
					} catch (InterruptedException e) {}
					System.out.println("bot "+turn);
				}
				//Aqui se for a vez do participante depois do big blind e todas as apostas já estiverem ok.
				//Aqui eu vo joga o método pra fazer o flop assim que terminarem as apostas.
				if (turn == bigBlind+1 && bets%(bots.length+1) == 0) {
					mesa.setPote(bets);
					turn = bigBlind;	
				} else {
					turn++;
				}
				if (turn > bots.length) {
					turn = 0;
				}
			}
			// aqui tem que fazer as operações depois de trocar o blind certinho e fazer valer o valor de entrada denovo.
			isRunning = false;
		}
	}
	
	synchronized private void Play(int bet) {
		if (play == 0) {
			if (mesa.getBiggestBet() >= player.getBet()) {
				this.interrupt();
				this.notify();
				setBets(getBets() + bet);
				player.addFichas(-1*((mesa.getBiggestBet()-player.getBet())+bet));
			}
		} else if (play == 1) {
			if (mesa.getBiggestBet() > player.getBet()){
				this.interrupt();
				this.notify();
				setBets(getBets() + bet);
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
	public int getBets() {
		return bets;
	}
	public void setBets(int bets) {
		this.bets = bets;
	}
	public int getBlind() {
		return blind;
	}
	public void setBlind(int blind) {
		this.blind = blind;
	}
}
