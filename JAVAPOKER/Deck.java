package pkg1;

import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

public class Deck {
	private String[] cards = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	private String[] sets = {"Spades", "Hearts", "Clubs", "Diamonds"};
	private Vector<String> deck = new Vector<String>();
	int contador = 52;
	
	public Deck() {
		
	}
	
	public void shuffle() {
		for(int i = 0; i < cards.length; i++) {
			for (int j = 0; j < sets.length; j++) {
				String carta = cards[i]+" of "+sets[j];
				deck.add(carta);
			}
		}
		Collections.shuffle(deck);
		//for debugging purposes
		//for (int i = 0; i < 52; i++) {
			//System.out.println(deck.get(i));
		//}
	}
	public String giveCards() {
		contador--;
		return deck.get(contador);
	}
}
