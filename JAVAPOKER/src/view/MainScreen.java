package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.*;

import control.GameManager;
import models.Player;
public class MainScreen implements ActionListener {
	
	private JFrame jframe;
	private JPanel jpanel;
	private JLabel title;
	private Player jogador;
	private JButton newGame, logout;
	private int width, height;

	public MainScreen(JFrame _frame, JPanel _jpanel, int _width, int _height, Player _jogador) {
		width = _width;
		height = _height;
		
		jogador = _jogador;
		jframe = _frame;
		jframe.setTitle("Poker Simulator");
		jframe.setSize(width, height);
		jframe.setResizable(false);
		jframe.setLocationRelativeTo(null);
		
		jpanel = _jpanel;
		jpanel.setSize(width,height);
        jpanel.setLayout(null);

        jframe.setContentPane(jpanel);
	}
	
	public void initComponent() {
		this.title = new JLabel("Main Menu");
		title.setSize(width/3, height/10);
        title.setFont(title.getFont().deriveFont(31f));
        title.setLocation(((width - title.getWidth())/2), height/20);
        jpanel.add(title);
        
        JLabel user = new JLabel("Logado como: "+jogador.getUsuario());
        user.setSize(user.getText().length()*10,100);
        user.setLocation(600, 0);
        jpanel.add(user);
		this.newGame = new JButton("NOVO JOGO");
        newGame.setSize(width/3, height/10);
        newGame.setLocation(width/3,height/3);
        newGame.addActionListener(this);
        jpanel.add(newGame);
        
		this.logout = new JButton("LOG OUT");
        logout.setSize(width/3, height/10);
        logout.setLocation(width/3,height/4 + height/3);
        logout.addActionListener(this);
        jpanel.add(logout);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == newGame) {
			jpanel.removeAll();
			jpanel.updateUI();
			GameManager gmgr = new GameManager(jpanel, jframe, jogador);
			ExecutorService exec = Executors.newSingleThreadExecutor();
			exec.execute(gmgr);
		}else if (arg0.getSource() == logout) {
			jpanel.removeAll();
			WindowLogin log = new WindowLogin(jframe,jpanel);
			log.initComponent();
			jpanel.updateUI();
		}
	}
}
