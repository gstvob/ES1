package pkg1;

import java.awt.Color;

import javax.swing.*;

public class Table extends JFrame {
	
	private String[] cardsInTable = new String[5];
	
	public Table() {
		getContentPane().setBackground(Color.BLACK);
		getContentPane().add(new JLabel(new ImageIcon("/home/gustavob/workspace/Poker/src/pkg1/table_burned.png")));
	}
}
