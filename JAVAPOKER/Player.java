package pkg1;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player {
	
	private Hand mao;
	
	private boolean eJogada;
	private boolean estaJogando;
	
	private char jogada;
	
	private int fichas;
	private int pontuacao;
	
	private String cartasNaMesa;
	private String melhorSeq;
	
	Player(int _fichas) {
		eJogada = false;
		estaJogando = true;
		fichas = _fichas;
	}
	public void setCartas(String _card1, String _card2) {
		mao = new Hand(_card1, _card2);
	}
	public void tiraFichas(int value) {
		fichas-= value;
	}
	public void addFichas(int value) {
		fichas+= value;
	}
	public char getJogada() {
		return jogada;
	}
	public int getFichas() {
		return fichas;
	}
	public int getPontuacao() {
		return pontuacao;
	}
}
