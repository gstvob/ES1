package models;

public class Bot extends Player{
	
	//private char jogada;
	
	private int fichas;
	
	public Bot(int _fichas) {
		super();
		setFichas(_fichas);
	}
	
	public int escolherJogada() {
		return 1;
	}

	public int getFichas() {
		return fichas;
	}

	public void setFichas(int fichas) {
		this.fichas = fichas;
	}
}
