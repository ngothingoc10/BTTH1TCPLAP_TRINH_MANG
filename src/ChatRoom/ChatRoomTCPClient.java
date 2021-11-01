package ChatRoom;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.*;
import java.awt.*;

public class ChatRoomTCPClient extends JFrame implements ActionListener {
	static JTextArea txtnhan;
	JTextArea txtgui;
	JButton send, disconnected;
	JPanel pn, pn1, pn2;
	PrintWriter pw = null;
	BufferedReader br = null;
	String user, ip;
	int port;
	Socket socket;
	static DataInputStream dInputStream;
	static DataOutputStream dOutputStream;

	public void GUI() {

		txtnhan = new JTextArea(50, 40);
		txtgui = new JTextArea(3, 25);
		txtnhan.setEditable(false);

		send = new JButton("Send");
		disconnected = new JButton("Disconnected");
		send.addActionListener(this);
		disconnected.addActionListener(this);

		pn = new JPanel(new GridLayout(2, 1));
		pn1 = new JPanel(new FlowLayout());
		pn2 = new JPanel(new FlowLayout());
		pn1.add(txtnhan);
		pn2.add(disconnected);
		pn2.add(txtgui);
		pn2.add(send);

		pn.add(pn1);
		pn.add(pn2);
		add(pn);
		setSize(500, 300);// thiet lap kich thuwoc
		setVisible(true);// hien thi

	}

	public ChatRoomTCPClient(String user, String ip, int port) {
		super("Chat Room TCP");
		this.user = user;
		this.ip = ip;
		this.port = port;
		GUI();
		try {
			socket = new Socket(ip, port);
			dInputStream = new DataInputStream(socket.getInputStream());
			dOutputStream = new DataOutputStream(socket.getOutputStream());
			dOutputStream.writeUTF(user);
			dOutputStream.flush();

		} catch (Exception e2) {
		}

	}

	public void receiverThread() {
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						String smsR = dInputStream.readUTF();
						txtnhan.setText(smsR);
						System.out.println(smsR);
						System.out.println("You : ");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}).start();

	}

	public void sendThread() {
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						dOutputStream.writeUTF(txtgui.getText());
						dOutputStream.flush();
						txtgui.setText(" ");

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}).start();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == send) {
			sendThread();
		}

	}
}
