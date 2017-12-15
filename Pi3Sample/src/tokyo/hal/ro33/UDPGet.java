package tokyo.hal.ro33;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPGet extends Thread {
	private DatagramSocket socket;
	private DatagramPacket packet;

	public UDPGet(int comPort) throws Exception, SocketException {
		this.socket = new DatagramSocket(comPort);
	}

	@Override
	public void run() {
		try {
			byte[] buf = new byte[1024];
			this.packet = new DatagramPacket(buf, buf.length);
			socket.receive(this.packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String recieve() {
		return new String(packet.getData());
	}
}
