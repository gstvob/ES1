package Interfaces;

// INTERFACE DO OBSERVADO, DA DE FAZER ALGO COM TIPO, QUANDO UM JOGADOR SAI DA MESA SIGNIFICA QUE MUDOU O ESTADO DELE.
// ENTÃO ELE VAI NOTIFICAR O SEU OBSERVADOR E VAI FAZER COM QUE SUAS CARTAS SAIAM DA MESA.

public interface Subject {

	
	public void registerInterest(Observer ob);
}