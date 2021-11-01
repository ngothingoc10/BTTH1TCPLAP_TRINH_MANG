package ChatRoom;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.awt.*;

public class Login extends JFrame implements ActionListener {
	JTextField txtnameUser;
	JLabel lbTitle,lbUser;
	JButton login, reset, exit;
	JPanel pn, pn1, pn2, pn3;
	
	public void GUI() {
		lbTitle = new JLabel(" CHAT ROOM TCP");
		lbUser = new JLabel("Name User: ");
		
		txtnameUser =new JTextField(25);
		
		login = new JButton("Login");
		reset = new JButton("Reset");
		exit = new JButton("Exit");
		login.addActionListener(this);
		reset.addActionListener(this);
		exit.addActionListener(this);
		
		pn = new JPanel(new GridLayout(3, 1));
		pn1 = new JPanel(new FlowLayout());
		pn2 = new JPanel(new FlowLayout());
		pn3 = new JPanel(new FlowLayout());
		pn1.add(lbTitle);
		pn2.add(lbUser);
		pn2.add(txtnameUser);
		pn3.add(login);
		pn3.add(reset);
		pn3.add(exit);
		pn.add(pn1);
		pn.add(pn2);
		pn.add(pn3);// add vao frame
		add(pn);
		setSize(400, 400);// thiet lap kich thuwoc
		setVisible(true);// hien thi

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()== login) {
			String user = txtnameUser.getText();
			new ChatRoomTCPClient(user, "localhost", 6997);
		}

		
	}
	
	public Login(String st) {
		super(st);
		GUI();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login("Login Chat Room TCP");

	}

	

}
