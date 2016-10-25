package control;

import java.util.ArrayList;

//VAI SER A CLASSE QUE VAI DAR UM "VALOR PARA AS MÃOS" E VAI TER UM MÉTODO PARA COMPARA-LAS E RETORNAR A MAIOR.

// FAZER AQUELE ENUMERADO DO LINK QUE EU MANDEI PRO FAUSTO.
// TERMINANDO ISSO AQUI VAI FALTA SÓ AS PARADAS TEÓRICAS QUE SE EU TERMINAR ISSO AQUI EU VOU TER TERMINADO O PADRÃO DE PROJETO
// E O SEGUNDO CASO DE USO.

public class HandRanker {
	
	public HandRanker() {
	}
	
	public enum HandRank {
		ROYAL_FLUSH, STRAIGHT_FLUSH, FOUR_OF_A_KIND, FULL_HOUSE, FLUSH, STRAIGHT, THREE_OF_A_KIND, TWO_PAIR, PAIR, HIGH_CARD;
	}
	
	// Método que vai testar tipo por tipo pra ver se a mão mais 3 cartas se encaixa em algum tipo de rank de mãos;
	public HandRank returnHandRank(ArrayList<String> cartas) {
		if (isARoyaFlush(cartas)) {
			return HandRank.ROYAL_FLUSH;
		} else if (isAStraightFlush(cartas)) {
			return HandRank.STRAIGHT_FLUSH;
		} else if (isAFourOfAKind(cartas)) {
			return HandRank.FOUR_OF_A_KIND;
		} else if (isAFullHouse(cartas)) {
			return HandRank.FULL_HOUSE;
		} else if (isAFlush(cartas)) {
			return HandRank.FLUSH;
		} else if (isAStraight(cartas)) {
			return HandRank.STRAIGHT;
		} else if (isAThreeOfAKind(cartas)) {
			return HandRank.THREE_OF_A_KIND;
		} else if (isATwoPair(cartas)) {
			return HandRank.TWO_PAIR;
		} else if (isAPair(cartas)) {
			return HandRank.PAIR;
		} else {
			return HandRank.HIGH_CARD;
		}
	}
	private boolean isAPair(ArrayList<String> cartas) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isATwoPair(ArrayList<String> cartas) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isAThreeOfAKind(ArrayList<String> cartas) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isAStraight(ArrayList<String> cartas) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isAFlush(ArrayList<String> cartas) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isAFourOfAKind(ArrayList<String> cartas) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isAFullHouse(ArrayList<String> cartas) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isAStraightFlush(ArrayList<String> cartas) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isARoyaFlush(ArrayList<String> cartas) {
		// TODO Auto-generated method stub
		return false;
	}
}