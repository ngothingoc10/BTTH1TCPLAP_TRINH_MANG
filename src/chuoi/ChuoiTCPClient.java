package chuoi;

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

import javax.swing.JFrame;

public class ChuoiTCPClient extends JFrame implements ActionListener{
	JTextArea txtgui,txtnhan;
	 JButton send;
	 JPanel pn, pn1, pn2, pn3;
	 PrintWriter pw = null;
	 BufferedReader br = null;
	 Socket socket;

	public void GUI() {
	
		txtgui=new JTextArea(2,20);
		txtnhan=new JTextArea(5,20);
		txtnhan.setEditable(false);

		send = new JButton("Send");
		send.addActionListener(this);
		pn = new JPanel(new GridLayout(3, 1));
		pn1 = new JPanel(new FlowLayout());
		pn2 = new JPanel(new FlowLayout());
		pn3 = new JPanel(new FlowLayout());
		pn1.add(txtgui);
		pn2.add(send);
		pn3.add(txtnhan);
		pn.add(pn1);
		pn.add(pn2);
		pn.add(pn3);// add vao frame
		add(pn);
		setSize(400, 400);// thiet lap kich thuwoc
		setVisible(true);// hien thi

	}

	public ChuoiTCPClient(String st) {
		super(st);
		GUI();

	}

	//------------
	public void actionPerformed(ActionEvent e) {
			if (e.getSource()== send) {
				try {
					socket = new Socket("localhost",6997);
					DataInputStream dInputStream=new DataInputStream(socket.getInputStream());
					DataOutputStream dOutputStream=new DataOutputStream(socket.getOutputStream());
					while(true) {
						dOutputStream.writeUTF(txtgui.getText());
						dOutputStream.flush();
						// nhan ket qua tra ve tu server sau khi xu ly
						String result=dInputStream.readUTF();
						txtnhan.setText(result);
					}
				} catch (Exception e2) {
				}
			}

	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		new ChuoiTCPClient("Chuoi Ky Tu");

	}

}
