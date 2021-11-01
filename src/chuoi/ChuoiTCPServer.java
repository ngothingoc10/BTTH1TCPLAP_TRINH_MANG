package chuoi;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChuoiTCPServer extends Thread{
private int port;
	
	public ChuoiTCPServer(int port) {
		this.port = port;
	}
	
	private void execute() throws IOException {
		// TODO Auto-generated method stub
		ServerSocket serverSocket=new ServerSocket(this.port);
		System.out.println("Server is waiting for Client");

		while(true) {
			Socket socket=serverSocket.accept();
			DataInputStream dInputStream = new DataInputStream(socket.getInputStream());
			DataOutputStream dOutputStream = new DataOutputStream(socket.getOutputStream());
			String sendedString = dInputStream.readUTF();
			System.out.println(sendedString);
			System.out.println("Processing");
			String chuoihoa = ChuHoa(sendedString);
			String chuoithuong = ChuThuong(sendedString);
			String hoathuong = VuaHoaVuaThuong(sendedString);
			int demtu = DemSoTu(sendedString);
			int demnguyenam = DemNguyenAm(sendedString);
			dOutputStream.writeUTF(chuoihoa+"\n"+chuoithuong+"\n"+hoathuong+"\n"+
			"So tu trong chuoi : "+demtu+"\n"+"So nguyen am trong chuoi : "+demnguyenam);
			dOutputStream.flush();
			socket.shutdownOutput();
		}
	}
	private static String ChuHoa(String str) {
		String temp = "";
		int len = str.length();
		for (int i = 0; i < len; i++) {
			char ch = str.charAt(i);
			if (ch >= 'a' && ch <= 'z') {
				ch = (char) (ch - 32);
			}
			temp += ch;
		}
		return temp;

	}

	private static String ChuThuong(String str) {
		String temp = "";
		int len = str.length();
		for (int i = 0; i < len; i++) {
			char ch = str.charAt(i);
			if (ch >= 'A' && ch <= 'Z') {
				ch = (char) (ch + 32);
			}
			temp += ch;
		}
		return temp;

	}

	private static String VuaHoaVuaThuong(String str) {
		String temp = "";
		int len = str.length();
		for (int i = 0; i < len; i++) {
			char ch = str.charAt(i);
			if (ch >= 'A' && ch <= 'Z') {
				ch = (char) (ch + 32);
			} else if (ch >= 'a' && ch <= 'z') {
				ch = (char) (ch - 32);
			}
			temp += ch;
		}
		return temp;

	}

	private static String ChuoiDaoNguoc(String str) {
		String temp = "";
		int len = str.length();
		for (int i = 0; i < len; i++) {
			char ch = str.charAt(len - i - 1);
			temp += ch;
		}
		return temp;

	}

	private static int DemSoTu(String str) {
		int count = 0;
		boolean notCounted = true;
		if (str == null) {
			return -1;
		}
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != ' ' && str.charAt(i) != '\t' && str.charAt(i) != '\n') {
				if (notCounted) {
					count++;
					notCounted = false;

				}
			} else {
				notCounted = true;

			}

		}
		return count;

	}

	private static int DemNguyenAm(String str) {
		int count = 0;
		String temp = str.toLowerCase();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
				count++;
			}
		}
		return count;

	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ChuoiTCPServer server=new ChuoiTCPServer(6997);
		server.execute();
	}

}
