package models;

import java.util.Vector;

import Interfaces.Observer;
import Interfaces.Subject;

public class Player implements Subject {
	
	private Hand mao;
	
	private boolean isPlaying;
	private boolean folded;
	private int fichas;
	
	private int bet;
	
	private String usuario;
	private String senha;
	private String email;
	private String recoverW;
	
	private int totalWins;
	
	private String cartasNaMesa;

	private Vector<Observer> observers;
	
	public Player() {
		setPlaying(true);
		folded = false;
		observers = new Vector<Observer>();
	}
	public Player(int _fichas, int wins) {
		fichas = _fichas;
		totalWins = wins;
		bet = 0;
		folded = false;
		observers = new Vector<Observer>();
	}
	public boolean canPass(int biggestBet) {
		if (biggestBet <= bet) {
			return true;
		}
		return false;
	}
	public Hand getHand() {
		return mao;
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
	public int getFichas() {
		return fichas;
	}
	public void setConta(String _user, String _pass, String _email, String _recover, int value) {
		totalWins = value;
		setUsuario(_user);
		setSenha(_pass);
		setEmail(_email);
		setRecoverW(_recover);
	}
	public int getBet() {
		return bet;
	}
	public void setFold(boolean value) {
		folded = value;
		((Observer) observers.elementAt(0)).sendNotify(folded);
	}
	public boolean getFold() {
		return folded;
	}
	public int getWins() {
		return totalWins;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRecoverW() {
		return recoverW;
	}
	public void setRecoverW(String recoverW) {
		this.recoverW = recoverW;
	}
	public String getCartasNaMesa() {
		return cartasNaMesa;
	}
	public void setCartasNaMesa(String cartasNaMesa) {
		this.cartasNaMesa = cartasNaMesa;
	}
	public boolean isPlaying() {
		return isPlaying;
	}
	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}
	public void setBet(int value) {
		bet += value;
	}
	public void registerInterest(Observer ob) {
		observers.addElement(ob);
	}
}
