package via.gn5r.com_udpsample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSend {
	private DatagramSocket socket = new DatagramSocket();
	InetAddress inetAddress;
	private int port;
	
	public UDPSend() throws Exception {

	}
	
	public boolean send() throws Exception {
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		System.out.println("Type send message:");
		String message = br.readLine();
		byte[] buf = message.getBytes("UTF-8");

		DatagramPacket packet = new DatagramPacket(buf,buf.length,inetAddress,port);
		socket.send(packet);
		if(message.equals("")) {
			return false;
		}
		return true;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public void close() {
		this.socket.close();
	}
	
}
