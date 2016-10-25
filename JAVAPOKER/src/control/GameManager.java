package control;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Interfaces.Observer;
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

public class GameManager extends Thread implements Observer{

	private Bot[] bots;
	private PlayerOptions player0;
	private int playerNumber;
	private int turn;
	private int play;
	private int bets;
	private int apostaram = 0;

	private boolean flop = true;
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

		player.registerInterest(this);
		bots = new Bot[numeroDeBots];
		
		for (int i = 0; i < numeroDeBots; i++) {
			bots[i] = new Bot(fichas);
			bots[i].registerInterest(this);
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
	synchronized private void Play(int bet) {
		if (play == 0) {
			if (mesa.getBiggestBet() >= player.getBet()) {
				this.interrupt();
				this.notify();
				apostaram = 0;
				System.out.println("o jogador aumentou a aposta em "+bet+" fichas");
				bet = bet + (mesa.getBiggestBet()-player.getBet());
				player.setBet(bet);
				setBets(getBets() + bet);
				mesa.setBiggestBet(player.getBet());
				player.addFichas(-1*(bet));
				System.out.println("Lhe restam: " +player.getFichas());
			}
		} else if (play == 1) {
			if (mesa.getBiggestBet() > player.getBet()){
				this.interrupt();
				this.notify();
				setBets(getBets() + (mesa.getBiggestBet() - player.getBet()));
				player.addFichas(-1*(mesa.getBiggestBet()-player.getBet()));
				System.out.println("o jogador pagou agora "+(mesa.getBiggestBet() - player.getBet())+" fichas, lhe restam: " +player.getFichas());
			}
		}
	}
	
	public void BotHandler(Bot bot) {
		if (mesa.getBiggestBet() > bot.getBet()){
			int bet = mesa.getBiggestBet() - bot.getBet();
			setBets(getBets() + bet);
			bot.addFichas(-1*(bet)); 
			bot.setBet(bet);
			System.out.println("o participante pagou agora "+bet+"fichas, lhe restam: " +player.getFichas());
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
	public void virarCartas() {
		if (flop == true) {
			//flop
			flop = false;
			mesa.flop();
			System.out.println("virou o flop");
			System.out.println(mesa.getCardsInTable(0) +", "+mesa.getCardsInTable(1)+", "+mesa.getCardsInTable(2));
		} else {
			mesa.turnRiver();
			System.out.println("virou mais uma carta na mesa");
			if (mesa.getCardsInTable(4) == null) {
				//turn
				System.out.println(mesa.getCardsInTable(0) +", "+mesa.getCardsInTable(1)+", "+mesa.getCardsInTable(2)+", " +mesa.getCardsInTable(3));
			} else {
				//river
				System.out.println(mesa.getCardsInTable(0) +", "+mesa.getCardsInTable(1)+", "+mesa.getCardsInTable(2)+", " +mesa.getCardsInTable(3) +", "+mesa.getCardsInTable(4));
			}
		}
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
					if (bigBlind == 0) {
						sb = bots.length-1;
						player.addFichas(-1*blind);
						player.setBet(blind);
						bots[sb].addFichas(-1*(blind/2));
						bots[sb].setBet(blind/2);
						System.out.println("o big blind foi setado para o player e o bot "+sb+" é o small blind.");
						bets = blind + blind/2;
					}else {
						sb = bigBlind-1;
						if (sb == 0) {
							player.addFichas(-1*(blind/2));
							player.setBet(blind/2);
						} else {
							bots[sb-1].addFichas(-1*(blind/2));
							bots[sb-1].setBet(blind/2);
						}
						bots[bigBlind-1].setBet(blind);
						bots[bigBlind-1].addFichas(-1*blind);
						bets = blind+(blind/2);
					}
					turn++;
					System.out.println("total de apostas = "+bets);
					mesa.setBiggestBet(blind);
					entrada = false;
				}
				//Jogada do usuario
				if (turn == 0) {
					player0.initComponent(true);
					panel.updateUI();
					try {
						sleep(5000);
					} catch (InterruptedException e){}
					player0.clearOptions();
					panel.updateUI();
				//Jogada dos bots.
				}else {
					BotHandler(bots[turn-1]);
					try {
						sleep(5000);
					} catch (InterruptedException e) {}
					System.out.println("bot "+turn);
				}
				//Aqui se for a vez do participante depois do big blind e todas as apostas já estiverem ok.
				//Aqui eu vo joga o método pra fazer o flop assim que terminarem as apostas.
				apostaram++;
				if (turn == bigBlind && bets%(bots.length+1) == 0 && apostaram >= bots.length) {
					mesa.setPote(bets);
					virarCartas();
					turn = bigBlind;
					apostaram = 0;
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
	public void sendNotify(boolean saiu) {
		if (saiu == true) {
			// significa que o player deu fold então tira as cartas da mão dele.
			//fazer um método para tirar as cartas dependendo de quem deu o fold é claro.
			// Esse método vai tar na classe do TableGUI.
			
		} else {
			// significa que o player ta de volta ao jogo então coloca as cartas dele na mão.
			// quando começar uma nova rodada, os que deram fold vão receber cartas novamente então eles irão chamar aqui denovo.
		}
	}
}



