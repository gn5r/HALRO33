package tokyo.hal.ro33;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSend {
	private DatagramSocket socket = new DatagramSocket();
	InetAddress inetAddress;
	private int port;

	public UDPSend(int comPort) throws Exception {
		this.port = comPort;
	}

	public void send(String message) throws Exception {
		byte[] buf = message.getBytes("UTF-8");
		DatagramPacket packet = new DatagramPacket(buf, buf.length, inetAddress, port);
		socket.send(packet);

	}

	public void close() {
		this.socket.close();
	}

}