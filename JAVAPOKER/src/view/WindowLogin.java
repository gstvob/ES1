package view;
import models.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import control.DatabaseAcc;

public class WindowLogin implements ActionListener {

    private JFrame jframe;
    private JPanel jpanel;
    private JLabel lblTitulo, lblLogin, lblSenha;
    private JButton btnCancel, btnLogin, btnCreateAcc;
    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private DatabaseAcc db;
    private boolean logou;
    
    WindowLogin(){}

    public WindowLogin(JFrame jframe, JPanel jpanel) {
        this.jframe = jframe;
        jframe.setTitle("LoginPoker");
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);

        this.jpanel = jpanel;
        jpanel.setLayout(null);
        logou = false;
        jframe.setContentPane(jpanel);
    }
    public void initComponent(){
       
    	//make title lbl
        this.lblTitulo = new JLabel("Poker");
        lblTitulo.setSize(800/5, 600/10);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(31f));
        lblTitulo.setLocation(((800 - lblTitulo.getWidth())/2), 600/20);
        jpanel.add(lblTitulo);
        //jpanel.setComponentZOrder(lblTitulo, 3);
        
        //make login lbl
        this.lblLogin = new JLabel("Login:");
        lblLogin.setSize(800/5, 600/20);
        lblLogin.setLocation(800/5,600/4);
        jpanel.add(lblLogin);
        //jpanel.setComponentZOrder(lblLogin, 3);

        //make login field
        this.txtLogin = new JTextField();
        txtLogin.setSize(800/3,600/25);
        txtLogin.setLocation((800/3 + 800/20),600/4);
        jpanel.add(txtLogin);
        //jpanel.setComponentZOrder(txtLogin, 3);
        
        //make password lbl
        this.lblSenha = new JLabel("Senha:");
        lblSenha.setSize(800/5, 600/20);
        lblSenha.setLocation(800/5,(600/4 + 600/20));
        jpanel.add(lblSenha);

        //make password field
        this.txtSenha = new JPasswordField(10);
        txtSenha.setSize(800/3,600/25);
        txtSenha.setLocation((800/3 + 800/20),(600/4 + 600/20));
        jpanel.add(txtSenha);

        //make login btn
        this.btnLogin = new JButton("Logar");
        btnLogin.setSize(800/4, 600/20);
        btnLogin.setLocation(800 /2 - btnLogin.getWidth(), 4 * 600/5);
        btnLogin.addActionListener(this);
        jpanel.add(btnLogin);

        //make cancel btn
        this.btnCancel = new JButton("Cancelar");
        btnCancel.setSize(800/4, 600/20);
        btnCancel.setLocation(800 /2 , 4 * 600/5);
        btnCancel.addActionListener(this);
        jpanel.add(btnCancel);
        
        this.btnCreateAcc = new JButton("Criar Conta");
        btnCreateAcc.setSize(800/7, 600/20);
        btnCreateAcc.setLocation(800 /2 - btnLogin.getWidth() , 600/2 + 600/4 + 65);
        btnCreateAcc.addActionListener(this);
        jpanel.add(btnCreateAcc);
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == btnCancel)
            System.exit(1);
        else if (event.getSource() == btnLogin){
            String login = txtLogin.getText();
            char[] senhaMask = txtSenha.getPassword();
            String senha = new String(senhaMask);
            db = new DatabaseAcc();
            try {
            	db.connect("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/PokerSim", "postgres", "admin");
				logou = db.check(login, senha);
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
            
            if (logou) {
            	jpanel.removeAll();
            	jpanel.updateUI();
            	//pegar todos os dados do player pa setar ele aqui.
            	//player.setConta(login, senha, _email, _recover, value);
            	Player player = new Player();
            	player.setUsuario(login);
            	MainScreen telaP = new MainScreen(jframe, jpanel, 800, 600, player);
            	telaP.initComponent();
            } else {
            	JOptionPane.showMessageDialog(null, "Usuário e/ou senha incorreto(s)");
            }
        } else if (event.getSource() == btnCreateAcc) {
        	jpanel.removeAll();
        	jpanel.updateUI();
        	AccForm createAccount = new AccForm(jframe, jpanel, 800,600);
    		createAccount.initComponent();
        }
    }
}
