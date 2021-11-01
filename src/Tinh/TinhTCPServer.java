package Tinh;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TinhTCPServer {
	private int port;

	public TinhTCPServer(int port) {
		this.port = port;
	}

	private void execute() throws IOException {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = new ServerSocket(this.port);
		System.out.println("Server is waiting for Client");

		while (true) {
			Socket socket = serverSocket.accept();
			DataInputStream dInputStream = new DataInputStream(socket.getInputStream());
			DataOutputStream dOutputStream = new DataOutputStream(socket.getOutputStream());
			String sendedString = dInputStream.readUTF();
			System.out.println(sendedString);
			System.out.println("Processing");

			dOutputStream.writeUTF(String.valueOf(TinhToan.calculate(sendedString)));
			dOutputStream.flush();
			socket.shutdownOutput();
		}
	}

	public static void main(String[] args) throws IOException  {
		// TODO Auto-generated method stub
		TinhTCPServer server = new TinhTCPServer(6997);
		server.execute();
	}

}
