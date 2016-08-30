package pkg1;

//Classe game manager vai ser a classe que vai controlar o sistema.
/*!
 * Essa classe ela vai fazer as operações do sistema e vai "gerenciar" o jogo
 * no metodo GameRun ele vai saber de quem é a vez, se for a vez do player vai fazer aparecer as opções
 * se for a vez de um bot ele vai fazer as operações do bot para ele decidir a jogada.
 */

public class GameManager {

	private Bot[] bots;

	private int playerNumber;
	
	private Table mesa;
	private Player player;

	private boolean isRunning;

	public GameManager() {

		GUIManager guiManager= new GUIManager();
		
		int numeroDeBots = guiManager.getBotsNumber();
		int fichas = guiManager.getFichas();
		mesa = new Table(numeroDeBots, fichas);
		mesa.getDeck().shuffle();

		bots = new Bot[numeroDeBots];

		for (int i = 0; i < numeroDeBots; i++) {
			bots[i] = new Bot(fichas);
		}

		player = new Player(fichas);

		mesa.setBots(bots);
		mesa.setPlayer(player);

		playerNumber = numeroDeBots + 1;
		GameRun();
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

	public void GameRun() {
		isRunning = true;
		while (isRunning) {
			GiveCards();
			boolean fimRodada;
			isRunning = false;
		}
	}
}
