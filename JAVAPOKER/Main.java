package pkg1;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		Deck deck = new Deck();
		
		Table mesa = new Table();
		mesa.setVisible(true);
		mesa.setSize(800, 600);
		mesa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mesa.setLocationRelativeTo(null);
		
		deck.shuffle();
		
	}
}
