package control;

import Interfaces.Observer;
import models.Bot;
import models.Player;
import view.PlayerOptions;

public class OptionsHandler implements Observer {

	
	private Player player;
	private Bot[] bots;
	private PlayerOptions po;
	public OptionsHandler(PlayerOptions po1, Player _player, Bot[] _bots) {
		
		po = po1;
		player = _player;
		bots = _bots;
		
		player.registerInterest(this);
		for(Bot bot : bots) {
			bot.registerInterest(this);
		}
	}
	
	public void sendNotify(boolean canPass) {
			po.initComponent(canPass);
	}
}
