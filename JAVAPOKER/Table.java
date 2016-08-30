package pkg1;

public class Table {
	
	private int playerNumber;
	private int chipsOnTable;
	private Deck deck;
	private String[] cardsInTable = new String[5];
	private String[] players;
	
	private Player mainPlayer;
	
	private Bot[] bots;
	
	public Table(int _botsNumber, int _chipAmmount) {
		playerNumber = _botsNumber + 1;
		deck = new Deck();
		players = new String[playerNumber];
		bots = new Bot[_botsNumber];
	}
	public Deck getDeck() {
		return deck;
	}
	public void setPlayer(Player player) {
		mainPlayer = player;
	}
	public void setBots(Bot[] bots) {
		this.bots = bots;
	}
	public Player getPlayer() {
		return mainPlayer;
	}
	public Bot getBot(int index) {
		return bots[index];
	}
}
