package view;

import control.DatabaseAcc;
import models.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AccForm implements ActionListener {

	private JFrame frame;
	private JPanel panel;
	private JLabel lblTitulo, user, pass, email, recoverWord;
	private JButton create, cancel;
	private JTextField fieldUser, fieldPass, fieldEmail, fieldRecoverW;
	private int width, height;

	public AccForm(JFrame _frame, JPanel _panel, int _width, int _height) {

		width = _width;
		height = _height;

		frame = _frame;
		frame.setTitle("Create Account");
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		panel = _panel;
		panel.setSize(width, height);
		panel.setLayout(null);

		frame.setContentPane(panel);
	}

	public void initComponent() {

		this.lblTitulo = new JLabel("Poker");
		lblTitulo.setSize(width / 5, height / 10);
		lblTitulo.setFont(lblTitulo.getFont().deriveFont(31f));
		lblTitulo.setLocation(((width - lblTitulo.getWidth()) / 2), height / 20);
		panel.add(lblTitulo);
		// jpanel.setComponentZOrder(lblTitulo, 3);

		// make login lbl
		this.user = new JLabel("Usuário:");
		user.setSize(width / 5, height / 20);
		user.setLocation(width / 5, height / 4);
		panel.add(user);
		// jpanel.setComponentZOrder(lblLogin, 3);

		// make login field
		this.fieldUser = new JTextField();
		fieldUser.setSize(width / 3, height / 25);
		fieldUser.setLocation((width / 3 + width / 20), height / 4);
		panel.add(fieldUser);

		this.pass = new JLabel("Senha:");
		pass.setSize(width / 5, height / 20);
		pass.setLocation(width / 5, (height / 4 + height / 20));
		panel.add(pass);

		// make password field
		this.fieldPass = new JPasswordField(10);
		fieldPass.setSize(width / 3, height / 25);
		fieldPass.setLocation((width / 3 + width / 20), (height / 4 + height / 20));
		panel.add(fieldPass);

		this.email = new JLabel("Email:");
		email.setSize(width / 5, height / 20);
		email.setLocation(width / 5, (height / 4 + height / 10));
		panel.add(email);

		this.fieldEmail = new JTextField();
		fieldEmail.setSize(width / 3, height / 25);
		fieldEmail.setLocation((width / 3 + width / 20), (height / 4 + height / 10));
		panel.add(fieldEmail);

		this.recoverWord = new JLabel("Palavra de Recuperação:");
		recoverWord.setSize(width / 5 + 20, height / 20);
		recoverWord.setLocation(width / 5 - 40, (height / 4 + height / 5));
		panel.add(recoverWord);

		this.fieldRecoverW = new JTextField();
		fieldRecoverW.setSize(width / 3, height / 25);
		fieldRecoverW.setLocation((width / 3 + width / 20), (height / 4 + height / 5));
		panel.add(fieldRecoverW);

		// make password lbl

		// make login btn
		this.create = new JButton("Criar");
		create.setSize(width / 4, height / 20);
		create.setLocation(width / 2 - create.getWidth(), 4 * height / 5);
		create.addActionListener(this);
		panel.add(create);

		// make cancel btn
		this.cancel = new JButton("Cancelar");
		cancel.setSize(width / 4, height / 20);
		cancel.setLocation(width / 2, 4 * height / 5);
		cancel.addActionListener(this);
		panel.add(cancel);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == cancel) {
			System.exit(0);
		} else if (arg0.getSource() == create) {
			if (fieldUser.getText().length() < 5) {
				JOptionPane.showMessageDialog(null, "Nome de usuário deve ter pelo menos 5 letras");
			} else if (fieldPass.getText().length() < 10) {
				JOptionPane.showMessageDialog(null, "Senha deve ter no minimo 10 caracteres");
			} else if (fieldRecoverW.getText().length() < 5) {
				JOptionPane.showMessageDialog(null, "Sua palavra de recuperação deve ter pelo menos 5 caracteres");
			} else {
				// do somethin somethin (with somethin meaning that proceeds to
				// login screen and call the database functions).
				DatabaseAcc accountCreated = new DatabaseAcc();
				Player player = new Player();
				player.setConta(fieldUser.getText(), fieldPass.getText(), fieldEmail.getText(), fieldRecoverW.getText(), 0);
				try {
					accountCreated.connect("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/PokerSim", "postgres", "admin");
					accountCreated.put(player);
					accountCreated.close();
				} catch (Exception e) {}
				panel.removeAll();
				panel.updateUI();
				JOptionPane.showMessageDialog(null, "USUARIO CRIADO COM SUCESSO");
				WindowLogin login = new WindowLogin(frame, panel);
			    login.initComponent();
			}
		}
	}
}
