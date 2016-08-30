package pkg1;

import java.awt.Color;
import java.awt.Component;

import javax.swing.*;

@SuppressWarnings("serial")
public class Table {
	
	private int playerNumber;
	private int chipsOnTable;
	
	private String[] cardsInTable = new String[5];
	private String[] players;
	
	private Player mainPlayer;
	
	private Bot[] bots;
	
	public Table(int _botsNumber, int _chipAmmount) {
		playerNumber = _botsNumber + 1;
		players = new String[playerNumber];
		bots = new Bot[_botsNumber];
		
		sitPlayersOnTable(_chipAmmount);
	}
	public void sitPlayersOnTable(int chipAmmount) {
		
		mainPlayer = new Player(chipAmmount);
		
		for (int i = 0; i < bots.length; i++) {
			bots[i] = new Bot(chipAmmount);
		}
	}
	
	public Player getPlayer() {
		return mainPlayer;
	}
	
	public Bot getBot(int index) {
		return bots[index];
	}
}
