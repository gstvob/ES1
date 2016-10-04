package control;
import view.WindowLogin;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
	
	public static void main(String[] args) {

        JFrame jframe = new JFrame();
        JPanel jpanel = new JPanel();
        jframe.setSize(800, 600);
        jpanel.setSize(800,600);
        WindowLogin login = new WindowLogin(jframe, jpanel);
        login.initComponent();

        //GameManager gameMgr = new GameManager(jpanel, jframe, jogador);
				
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
