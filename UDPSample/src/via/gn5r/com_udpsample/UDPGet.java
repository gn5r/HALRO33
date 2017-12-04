package via.gn5r.com_udpsample;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPGet {
	DatagramSocket socket;
	String port;

	public UDPGet() throws Exception {
	}

	public boolean receive() throws Exception {
		byte[] buf = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		System.out.println("受信中...");
		socket.receive(packet);
		System.out.println("受信データ:" + new String(packet.getData()));
		return true;
	}

	public void setSocketPort(int port) throws Exception, SocketException {
		socket = new DatagramSocket(port);
	}
}
