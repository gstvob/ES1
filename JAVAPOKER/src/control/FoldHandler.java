package control;

import Interfaces.Observer;
import models.Bot;
import models.Player;

public class FoldHandler implements Observer {

	
	private Player player;
	private Bot[] bots;
	
	public FoldHandler(Player _player, Bot[] _bots) {
		
		player = _player;
		bots = _bots;
		
		player.registerInterest(this);
		for(Bot bot : bots) {
			bot.registerInterest(this);
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
